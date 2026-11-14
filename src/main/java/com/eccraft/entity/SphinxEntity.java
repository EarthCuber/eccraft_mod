package com.eccraft.entity;

import com.eccraft.Eccraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SphinxEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final Identifier SPAWNED_FUNCTION = Eccraft.id("sphinx/spawned");
    public static final Identifier DEFEATED_FUNCTION = Eccraft.id("sphinx/defeated");
    public static final Identifier CLEARED_FUNCTION = Eccraft.id("sphinx/cleared");
    public static final double MAX_HEALTH = 2500.0;
    public static final double ARMOR = 2.0;
    public static final double MOVEMENT_SPEED = 0.28;
    public static final double KNOCKBACK_RESISTANCE = 1.0;
    public static final double SCALE = 1.0;
    public static final int EXPERIENCE_REWARD = 100;
    public static final double EXISTENCE_RANGE = 16.0;
    public static final double PATROL_RANGE = 20.0;
    public static final double MELEE_TRIGGER_RANGE = 3.5;
    public static final double MELEE_HIT_RANGE = 5.0;
    public static final int COMBAT_TIMEOUT_TICKS = 400;
    public static final float BASIC_ATTACK_DAMAGE = 14.0F;
    // Action event times in server ticks; durations are defined in SphinxActionState.
    public static final int BASIC_ATTACK_HIT_TICK = 14;
    public static final int SKILL_1_COOLDOWN_TICKS = 160;
    public static final int SKILL_1_EFFECT_TICK = 13;
    public static final int SKILL_2_COOLDOWN_TICKS = 380;
    public static final int SKILL_2_EFFECT_TICK = 20;

    private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(SphinxEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> ACTION_START = SynchedEntityData.defineId(SphinxEntity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(SphinxEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            UUID.randomUUID(), Component.translatable("entity.eccraft.sphinx"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_10);
    private final Set<UUID> summons = new HashSet<>();
    private SphinxActionState action = SphinxActionState.IDLE;
    private UUID actionTarget;
    private boolean actionEffectApplied;
    private int withoutMeleeTargetTicks;
    private long nextSkill1Tick;
    private long nextSkill2Tick;
    private boolean silentRemoval;
    private boolean spawnFunctionExecuted;
    private boolean spawnPositionRecorded;
    private double spawnX;
    private double spawnY;
    private double spawnZ;

    public SphinxEntity(EntityType<? extends SphinxEntity> type, Level level) {
        super(type, level);
        this.xpReward = EXPERIENCE_REWARD;
        setPersistenceRequired();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.ARMOR, ARMOR)
                .add(Attributes.ATTACK_DAMAGE, BASIC_ATTACK_DAMAGE)
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.FOLLOW_RANGE, EXISTENCE_RANGE)
                .add(Attributes.KNOCKBACK_RESISTANCE, KNOCKBACK_RESISTANCE)
                .add(Attributes.SCALE, SCALE);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ACTION, SphinxActionState.IDLE.animation());
        builder.define(ACTION_START, 0L);
        builder.define(PHASE, 1);
    }

    @Override
    protected void registerGoals() {
        // One state machine owns all movement and attacks, preventing overlapping actions.
    }

    public static boolean isCombatPlayer(ServerPlayer player) {
        return player.isAlive() && !player.isCreative() && !player.isSpectator();
    }

    @Override
    protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        if (!spawnPositionRecorded) recordSpawnPosition();
        if (!spawnFunctionExecuted) {
            spawnFunctionExecuted = true;
            runDataFunction(level, SPAWNED_FUNCTION);
        }
        if (isRemoved()) return;
        bossEvent.setProgress(isAlive() ? getHealth() / getMaxHealth() : 0.0F);
        if (!isAlive()) return;

        if (distanceToSqr(spawnX, spawnY, spawnZ) > PATROL_RANGE * PATROL_RANGE) {
            removeWithoutLoot(level);
            return;
        }

        List<ServerPlayer> nearby = validPlayers(level, EXISTENCE_RANGE);
        syncBossBarPlayers(nearby);
        if (nearby.isEmpty()) {
            removeWithoutLoot(level);
            return;
        }

        double meleeTriggerRangeSqr = MELEE_TRIGGER_RANGE * MELEE_TRIGGER_RANGE;
        // Attack budget is updated only after higher-priority skills are considered.

        updatePhase();
        long now = level.getGameTime();
        if (action != SphinxActionState.IDLE && action != SphinxActionState.CHASE) {
            getNavigation().stop();
            tickAction(level, now);
            return;
        }

        ServerPlayer nearest = nearby.stream().min(Comparator.comparingDouble(p -> com.eccraft.combat.TargetPreference.score(p, distanceToSqr(p)))).orElseThrow();
        getLookControl().setLookAt(nearest, 30.0F, 30.0F);
        int phase = getPhase();
        if (phase >= 3 && now >= nextSkill2Tick) beginAction(SphinxActionState.SKILL_2, nearest.getUUID(), now);
        else if (phase >= 2 && now >= nextSkill1Tick) beginAction(SphinxActionState.SKILL_1, nearest.getUUID(), now);
        else if (distanceToSqr(nearest) <= meleeTriggerRangeSqr) beginAction(SphinxActionState.ATTACK, nearest.getUUID(), now);
        else {
            if(failedMeleeAttempt()){removeWithoutLoot(level);return;}
            setPassiveAction(SphinxActionState.CHASE, now);
            getNavigation().moveTo(nearest, 1.0);
        }
    }

    private List<ServerPlayer> validPlayers(ServerLevel level, double range) {
        double rangeSqr = range * range;
        return level.getPlayers(p -> isCombatPlayer(p) && distanceToSqr(p) <= rangeSqr);
    }

    private void recordSpawnPosition() {
        spawnX = getX();
        spawnY = getY();
        spawnZ = getZ();
        spawnPositionRecorded = true;
    }

    private void updatePhase() {
        int phase = getPhase();
        if (getHealth() <= getMaxHealth() * 0.5F) phase = Math.max(phase, 3);
        else if (getHealth() <= getMaxHealth() * 0.8F) phase = Math.max(phase, 2);
        entityData.set(PHASE, phase);
    }

    private void beginAction(SphinxActionState next, UUID target, long now) {
        if(next==SphinxActionState.ATTACK)meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);
        action = next;
        actionTarget = target;
        actionEffectApplied = false;
        entityData.set(ACTION, next.animation());
        entityData.set(ACTION_START, now);
        getNavigation().stop();
        if (next == SphinxActionState.ATTACK || next == SphinxActionState.SKILL_1 || next == SphinxActionState.SKILL_2) faceAttackTargetOnce();
        if (next == SphinxActionState.SKILL_1) nextSkill1Tick = now + SKILL_1_COOLDOWN_TICKS;
        if (next == SphinxActionState.SKILL_2) nextSkill2Tick = now + SKILL_2_COOLDOWN_TICKS;
    }

    private void setPassiveAction(SphinxActionState next, long now) {
        if (action != next) {
            action = next;
            entityData.set(ACTION, next.animation());
            entityData.set(ACTION_START, now);
        }
    }

    private void tickAction(ServerLevel level, long now) {
        int elapsed = (int) (now - entityData.get(ACTION_START));
        if (!actionEffectApplied) {
            if (action == SphinxActionState.ATTACK && elapsed >= BASIC_ATTACK_HIT_TICK) applyBasicAttack(level);
            else if (action == SphinxActionState.SKILL_1 && elapsed >= SKILL_1_EFFECT_TICK) castSkill1(level);
            else if (action == SphinxActionState.SKILL_2 && elapsed >= SKILL_2_EFFECT_TICK) castSkill2(level);
        }
        if (elapsed >= action.duration()) {
            actionTarget = null;
            actionEffectApplied = false;
            setPassiveAction(SphinxActionState.IDLE, now);
        }
    }

    private void faceAttackTargetOnce() {
        if (actionTarget == null) return;
        if (!(level() instanceof ServerLevel serverLevel)) return;
        Entity entity = serverLevel.getEntity(actionTarget);
        if (!(entity instanceof ServerPlayer player) || !isCombatPlayer(player)) return;

        double dx = player.getX() - getX();
        double dz = player.getZ() - getZ();
        if (dx * dx + dz * dz < 1.0E-6) return;

        float targetYaw = (float) (Mth.atan2(dz, dx) * Mth.RAD_TO_DEG) - 90.0F;
        setYRot(targetYaw);
        setYBodyRot(targetYaw);
        setYHeadRot(targetYaw);
    }

    private void applyBasicAttack(ServerLevel level) {
        actionEffectApplied = true;
        if (actionTarget == null) return;
        Entity entity = level.getEntity(actionTarget);
        if (entity instanceof ServerPlayer player && isCombatPlayer(player)
                && distanceToSqr(player) <= MELEE_HIT_RANGE * MELEE_HIT_RANGE) {
            if (player.hurtServer(level, com.eccraft.damage.BossDamageSources.melee(level, this), BASIC_ATTACK_DAMAGE)) {
                com.eccraft.sound.SphinxSounds.onAttackHit(this);
            }
        }
    }

@Override
    protected boolean canRide(Entity vehicle) {
        return false;
    }

    public static final double SKILL_1_WIDTH = 3.0;
    public static final double SKILL_1_LENGTH = 8.0;
    public static final double SKILL_1_HEIGHT = 4.0;
    public static final float SKILL_1_DAMAGE = 16.0F;
    public static final float SKILL_2_DAMAGE = 4.0F;
    public static final double SUMMON_RADIUS = 6.0;
    public static final double SUMMON_HEIGHT_OFFSET = 0.5;
    private boolean movementLocked() {
        return action != SphinxActionState.IDLE && action != SphinxActionState.CHASE;
    }

    @Override public void travel(net.minecraft.world.phys.Vec3 input) {
        if (movementLocked()) {
            getNavigation().stop();
            setDeltaMovement(0, getDeltaMovement().y, 0);
            super.travel(net.minecraft.world.phys.Vec3.ZERO);
        } else super.travel(input);
    }

    @Override public void move(net.minecraft.world.entity.MoverType type, net.minecraft.world.phys.Vec3 delta) {
        super.move(type, movementLocked() ? new net.minecraft.world.phys.Vec3(0, delta.y, 0) : delta);
    }

    private void castSkill1(ServerLevel level) {
        actionEffectApplied = true;
        com.eccraft.sound.SphinxSounds.onSkill1Damage(this);
        com.eccraft.sphinx.SphinxParticles.burst(level, random, position(), getYRot(), false);
        for (ServerPlayer player : level.getPlayers(SphinxEntity::isCombatPlayer)) {
            var box = player.getBoundingBox();
            if (com.eccraft.sphinx.ForwardStrike.intersects(getX(), getY(), getZ(), getYRot(),
                    SKILL_1_WIDTH, SKILL_1_LENGTH, SKILL_1_HEIGHT,
                    box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ)) {
                player.hurtServer(level, magic(level), SKILL_1_DAMAGE);
            }
        }
    }

    private void castSkill2(ServerLevel level) {
        actionEffectApplied = true;
        com.eccraft.sound.SphinxSounds.onSkill2Volley(this);
        com.eccraft.sphinx.SphinxParticles.burst(level, random, position(), getYRot(), true);
        var magic = level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                .getOrThrow(net.minecraft.resources.ResourceKey.create(
                        net.minecraft.core.registries.Registries.DAMAGE_TYPE, com.eccraft.Eccraft.id("magic")));
        for (ServerPlayer player : validPlayers(level, EXISTENCE_RANGE)) {
            player.hurtServer(level, new DamageSource(magic, this), SKILL_2_DAMAGE);
        }
        summon(level, net.minecraft.world.entity.EntityTypes.HUSK);
        summon(level, net.minecraft.world.entity.EntityTypes.BOGGED);
    }

    private DamageSource magic(ServerLevel level) {
        return new DamageSource(level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                .getOrThrow(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.DAMAGE_TYPE,Eccraft.id("magic"))),this);
    }

    private void summon(ServerLevel level, EntityType<? extends net.minecraft.world.entity.Mob> type) {
        var minion=type.create(level,EntitySpawnReason.MOB_SUMMONED);
        if(minion==null)return;
        boolean found=false;
        for(int attempt=0;attempt<96;attempt++){
            double angle=random.nextDouble()*Math.PI*2, radius=Math.sqrt(random.nextDouble())*SUMMON_RADIUS;
            minion.setPos(getX()+Math.cos(angle)*radius,getY()+SUMMON_HEIGHT_OFFSET,getZ()+Math.sin(angle)*radius);
            if(level.noCollision(minion)&&!minion.getBoundingBox().intersects(getBoundingBox())){found=true;break;}
        }
        if(!found)return;
        minion.finalizeSpawn(level,level.getCurrentDifficultyAt(minion.blockPosition()),EntitySpawnReason.MOB_SUMMONED,null);
        minion.setCanPickUpLoot(false);minion.setPersistenceRequired();
        minion.addTag(com.eccraft.sphinx.SphinxSummons.MARKER);
        minion.addTag(com.eccraft.sphinx.SphinxSummons.OWNER_PREFIX+getUUID());
        for(var slot:net.minecraft.world.entity.EquipmentSlot.values())minion.setDropChance(slot,0);
        var targets=validPlayers(level,EXISTENCE_RANGE);
        if(!targets.isEmpty())minion.setTarget(targets.stream().min(Comparator.comparingDouble(p -> com.eccraft.combat.TargetPreference.score(p, minion.distanceToSqr(p)))).orElseThrow());
        if(level.addFreshEntity(minion))summons.add(minion.getUUID());
    }
    private void cleanupSummons(ServerLevel level) {
        for (UUID uuid : new ArrayList<>(summons)) {
            Entity entity = level.getEntityInAnyDimension(uuid);
            if (entity != null) entity.discard();
        }
        summons.clear();
    }

    private void removeWithoutLoot(ServerLevel level) {
        silentRemoval = true;
        runDataFunction(level, CLEARED_FUNCTION);
        cleanupSummons(level);
        bossEvent.removeAllPlayers();
        discard();
    }

    @Override
    public void die(DamageSource source) {
        if (action == SphinxActionState.DEATH) return;
        if (level() instanceof ServerLevel level) cleanupSummons(level);
        bossEvent.setProgress(0.0F);
        super.die(source);
        action = SphinxActionState.DEATH;
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, level().getGameTime());
        getNavigation().stop();
    }

@Override
    protected void tickDeath() {
        ++deathTime;
        if (deathTime >= SphinxActionState.DEATH.duration() && level() instanceof ServerLevel serverLevel) {
            bossEvent.removeAllPlayers();
            runDataFunction(serverLevel, DEFEATED_FUNCTION);
            serverLevel.broadcastEntityEvent(this, (byte) 60);
            remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return com.eccraft.sound.SphinxSounds.DEATH;
    }

    private void runDataFunction(ServerLevel level, Identifier functionId) {
        var functionManager = level.getServer().getFunctions();
        functionManager.get(functionId).ifPresent(function -> functionManager.execute(function,
                functionManager.getGameLoopSender()
                        .withLevel(level)
                        .withPosition(position())
                        .withEntity(this)
                        .withSuppressedOutput()));
    }

    @Override
    protected boolean shouldDropLoot(ServerLevel level) {
        return !silentRemoval && super.shouldDropLoot(level);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }

    private void syncBossBarPlayers(List<ServerPlayer> nearbyPlayers) {
        Set<ServerPlayer> expected = new HashSet<>(nearbyPlayers);
        for (ServerPlayer current : new ArrayList<>(bossEvent.getPlayers())) {
            if (!expected.contains(current)) bossEvent.removePlayer(current);
        }
        for (ServerPlayer player : expected) bossEvent.addPlayer(player);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);output.putInt("EccraftMeleeBudgetSpent",meleeBudgetSpent);
        output.putInt("SphinxDeathTicks", deathTime);
        output.putString("SphinxAction", action.name());
        output.putLong("SphinxActionStart", entityData.get(ACTION_START));
        output.putInt("SphinxPhase", getPhase());
        output.putBoolean("SphinxActionEffect", actionEffectApplied);
        output.putInt("SphinxNoMeleeTicks", withoutMeleeTargetTicks);
        output.putLong("SphinxSkill1Ready", nextSkill1Tick);
        output.putLong("SphinxSkill2Ready", nextSkill2Tick);
        output.putBoolean("SphinxSpawnFunctionExecuted", spawnFunctionExecuted);
        output.putBoolean("SphinxSpawnPositionRecorded", spawnPositionRecorded);
        if (spawnPositionRecorded) {
            output.putDouble("SphinxSpawnX", spawnX);
            output.putDouble("SphinxSpawnY", spawnY);
            output.putDouble("SphinxSpawnZ", spawnZ);
        }
        if (actionTarget != null) output.putString("SphinxActionTarget", actionTarget.toString());
        output.putString("SphinxSummons", summons.stream().map(UUID::toString).reduce((a, b) -> a + "," + b).orElse(""));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(input.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);
        deathTime = input.getIntOr("SphinxDeathTicks", 0);
        action = SphinxActionState.byName(input.getStringOr("SphinxAction", SphinxActionState.IDLE.name()));
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, input.getLongOr("SphinxActionStart", level().getGameTime()));
        entityData.set(PHASE, Mth.clamp(input.getIntOr("SphinxPhase", 1), 1, 3));
        actionEffectApplied = input.getBooleanOr("SphinxActionEffect", false);
        withoutMeleeTargetTicks = input.getIntOr("SphinxNoMeleeTicks", 0);
        nextSkill1Tick = input.getLongOr("SphinxSkill1Ready", 0L);
        nextSkill2Tick = input.getLongOr("SphinxSkill2Ready", 0L);
        spawnFunctionExecuted = input.getBooleanOr("SphinxSpawnFunctionExecuted", true);
        spawnPositionRecorded = input.getBooleanOr("SphinxSpawnPositionRecorded", false);
        if (spawnPositionRecorded) {
            spawnX = input.getDoubleOr("SphinxSpawnX", getX());
            spawnY = input.getDoubleOr("SphinxSpawnY", getY());
            spawnZ = input.getDoubleOr("SphinxSpawnZ", getZ());
        }
        String target = input.getStringOr("SphinxActionTarget", "");
        if (!target.isBlank()) actionTarget = UUID.fromString(target);
        String savedSummons = input.getStringOr("SphinxSummons", "");
        if (!savedSummons.isBlank()) for (String uuid : savedSummons.split(",")) summons.add(UUID.fromString(uuid));
    }

    public int getPhase() { return entityData.get(PHASE); }
    public String sphinxAction() { return entityData.get(ACTION); }
    public long sphinxActionStartTick() { return entityData.get(ACTION_START); }
}
