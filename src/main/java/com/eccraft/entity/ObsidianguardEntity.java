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

public class ObsidianguardEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final Identifier SPAWNED_FUNCTION = Eccraft.id("obsidianguard/spawned");
    public static final Identifier DEFEATED_FUNCTION = Eccraft.id("obsidianguard/defeated");
    public static final Identifier CLEARED_FUNCTION = Eccraft.id("obsidianguard/cleared");
    public static final double MAX_HEALTH = 2500.0;
    public static final double ARMOR = 2.0;
    public static final double MOVEMENT_SPEED = 0.28;
    public static final double KNOCKBACK_RESISTANCE = 1.0;
    public static final double SCALE = 1.0;
    public static final int EXPERIENCE_REWARD = 100;
    public static final double EXISTENCE_RANGE = 32.0;
    public static final double PATROL_RANGE = 19.0;
    public static final double MELEE_TRIGGER_RANGE = 3.5;
    public static final double MELEE_HIT_RANGE = 5.0;
    public static final int COMBAT_TIMEOUT_TICKS = 400;
    public static final float BASIC_ATTACK_DAMAGE = 14.0F;
    // Action event times in server ticks; durations are defined in ObsidianguardActionState.
    public static final int BASIC_ATTACK_HIT_TICK = 10;
    public static final int SKILL_1_COOLDOWN_TICKS = 220;
    public static final int SKILL_1_EFFECT_TICK = 10;
    public static final int SKILL_2_COOLDOWN_TICKS = 600;
    public static final int SKILL_2_EFFECT_TICK = 9;

    private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(ObsidianguardEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> ACTION_START = SynchedEntityData.defineId(ObsidianguardEntity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(ObsidianguardEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            UUID.randomUUID(), Component.translatable("entity.eccraft.obsidianguard"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_10);
    private final Set<UUID> summons = new HashSet<>();
    private ObsidianguardActionState action = ObsidianguardActionState.IDLE;
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

    public ObsidianguardEntity(EntityType<? extends ObsidianguardEntity> type, Level level) {
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
        builder.define(ACTION, ObsidianguardActionState.IDLE.animation());
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
        if (action != ObsidianguardActionState.IDLE && action != ObsidianguardActionState.CHASE) {
            getNavigation().stop();
            tickAction(level, now);
            return;
        }

        ServerPlayer nearest = nearby.stream().min(Comparator.comparingDouble(p -> com.eccraft.combat.TargetPreference.score(p, distanceToSqr(p)))).orElseThrow();
        getLookControl().setLookAt(nearest, 30.0F, 30.0F);
        int phase = getPhase();
        if (phase >= 3 && now >= nextSkill2Tick) beginAction(ObsidianguardActionState.SKILL_2, nearest.getUUID(), now);
        else if (phase >= 2 && now >= nextSkill1Tick) beginAction(ObsidianguardActionState.SKILL_1, nearest.getUUID(), now);
        else if (distanceToSqr(nearest) <= meleeTriggerRangeSqr) beginAction(ObsidianguardActionState.ATTACK, nearest.getUUID(), now);
        else {
            if(failedMeleeAttempt()){removeWithoutLoot(level);return;}
            setPassiveAction(ObsidianguardActionState.CHASE, now);
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

    private void beginAction(ObsidianguardActionState next, UUID target, long now) {
        if(next==ObsidianguardActionState.ATTACK)meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);
        action = next;
        actionTarget = target;
        actionEffectApplied = false;
        entityData.set(ACTION, next.animation());
        entityData.set(ACTION_START, now);
        getNavigation().stop();
        if (next == ObsidianguardActionState.ATTACK || next == ObsidianguardActionState.SKILL_1 || next == ObsidianguardActionState.SKILL_2) faceAttackTargetOnce();
        if (next == ObsidianguardActionState.SKILL_1) nextSkill1Tick = now + SKILL_1_COOLDOWN_TICKS;
        if (next == ObsidianguardActionState.SKILL_2) nextSkill2Tick = now + SKILL_2_COOLDOWN_TICKS;
    }

    private void setPassiveAction(ObsidianguardActionState next, long now) {
        if (action != next) {
            action = next;
            entityData.set(ACTION, next.animation());
            entityData.set(ACTION_START, now);
        }
    }

    private void tickAction(ServerLevel level, long now) {
        int elapsed = (int) (now - entityData.get(ACTION_START));
        if (!actionEffectApplied) {
            if (action == ObsidianguardActionState.ATTACK && elapsed >= BASIC_ATTACK_HIT_TICK) applyBasicAttack(level);
            else if (action == ObsidianguardActionState.SKILL_1 && elapsed >= SKILL_1_EFFECT_TICK) castSkill1(level);
            else if (action == ObsidianguardActionState.SKILL_2 && elapsed >= SKILL_2_EFFECT_TICK) castSkill2(level);
        }
        if (elapsed >= action.duration()) {
            actionTarget = null;
            actionEffectApplied = false;
            setPassiveAction(ObsidianguardActionState.IDLE, now);
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
                com.eccraft.sound.ObsidianguardSounds.onAttackHit(this);
            }
        }
    }

@Override
    protected boolean canRide(Entity vehicle) {
        return false;
    }

    public static final double SKILL_1_RADIUS = 16.0;
    public static final float SKILL_1_DAMAGE = 6.0F;
    public static final double METEOR_SPAWN_HEIGHT = 12.0;
    public static final double METEOR_SPEED = 0.2;
    public static final float METEOR_DAMAGE = 20.0F;
    public static final int METEOR_LIFETIME = 240;
    private boolean movementLocked() {
        return action != ObsidianguardActionState.IDLE && action != ObsidianguardActionState.CHASE;
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
        com.eccraft.sound.ObsidianguardSounds.onSkill1Damage(this);
        com.eccraft.obsidianguard.ObsidianguardParticles.burst(level, position().add(0,getBbHeight()/2.0,0), 96, 0.5);
        var source=com.eccraft.obsidianguard.ObsidianguardDamage.magic(level,this,this);
        for(var player:validPlayers(level,SKILL_1_RADIUS))player.hurtServer(level,source,SKILL_1_DAMAGE);
    }

    private void castSkill2(ServerLevel level) {
        actionEffectApplied = true;
        var players=validPlayers(level,EXISTENCE_RANGE);
        if(players.isEmpty())return;
        var target=players.get(random.nextInt(players.size()));
        var meteor=new ObsidianguardMeteor(com.eccraft.obsidianguard.ObsidianguardContent.METEOR,level);
        var origin=position().add(0,METEOR_SPAWN_HEIGHT,0);
        meteor.setOwner(this);meteor.setPos(origin);
        // Aim the center of the 2x2x2 collider at the sampled foot position; never predict or retarget.
        meteor.setDeltaMovement(target.position().subtract(origin.add(0,1,0)).normalize().scale(METEOR_SPEED));
        if(level.addFreshEntity(meteor)){
            track(meteor);
            com.eccraft.sound.ObsidianguardSounds.onSkill2Volley(this);
        }
    }

    public void track(Entity entity){summons.add(entity.getUUID());}
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
        if (action == ObsidianguardActionState.DEATH) return;
        if (level() instanceof ServerLevel level) cleanupSummons(level);
        bossEvent.setProgress(0.0F);
        super.die(source);
        action = ObsidianguardActionState.DEATH;
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, level().getGameTime());
        getNavigation().stop();
    }

@Override
    protected void tickDeath() {
        ++deathTime;
        if (deathTime >= ObsidianguardActionState.DEATH.duration() && level() instanceof ServerLevel serverLevel) {
            bossEvent.removeAllPlayers();
            runDataFunction(serverLevel, DEFEATED_FUNCTION);
            serverLevel.broadcastEntityEvent(this, (byte) 60);
            remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return com.eccraft.sound.ObsidianguardSounds.DEATH;
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
        output.putInt("ObsidianguardDeathTicks", deathTime);
        output.putString("ObsidianguardAction", action.name());
        output.putLong("ObsidianguardActionStart", entityData.get(ACTION_START));
        output.putInt("ObsidianguardPhase", getPhase());
        output.putBoolean("ObsidianguardActionEffect", actionEffectApplied);
        output.putInt("ObsidianguardNoMeleeTicks", withoutMeleeTargetTicks);
        output.putLong("ObsidianguardSkill1Ready", nextSkill1Tick);
        output.putLong("ObsidianguardSkill2Ready", nextSkill2Tick);
        output.putBoolean("ObsidianguardSpawnFunctionExecuted", spawnFunctionExecuted);
        output.putBoolean("ObsidianguardSpawnPositionRecorded", spawnPositionRecorded);
        if (spawnPositionRecorded) {
            output.putDouble("ObsidianguardSpawnX", spawnX);
            output.putDouble("ObsidianguardSpawnY", spawnY);
            output.putDouble("ObsidianguardSpawnZ", spawnZ);
        }
        if (actionTarget != null) output.putString("ObsidianguardActionTarget", actionTarget.toString());
        output.putString("ObsidianguardSummons", summons.stream().map(UUID::toString).reduce((a, b) -> a + "," + b).orElse(""));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(input.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);
        deathTime = input.getIntOr("ObsidianguardDeathTicks", 0);
        action = ObsidianguardActionState.byName(input.getStringOr("ObsidianguardAction", ObsidianguardActionState.IDLE.name()));
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, input.getLongOr("ObsidianguardActionStart", level().getGameTime()));
        entityData.set(PHASE, Mth.clamp(input.getIntOr("ObsidianguardPhase", 1), 1, 3));
        actionEffectApplied = input.getBooleanOr("ObsidianguardActionEffect", false);
        withoutMeleeTargetTicks = input.getIntOr("ObsidianguardNoMeleeTicks", 0);
        nextSkill1Tick = input.getLongOr("ObsidianguardSkill1Ready", 0L);
        nextSkill2Tick = input.getLongOr("ObsidianguardSkill2Ready", 0L);
        spawnFunctionExecuted = input.getBooleanOr("ObsidianguardSpawnFunctionExecuted", true);
        spawnPositionRecorded = input.getBooleanOr("ObsidianguardSpawnPositionRecorded", false);
        if (spawnPositionRecorded) {
            spawnX = input.getDoubleOr("ObsidianguardSpawnX", getX());
            spawnY = input.getDoubleOr("ObsidianguardSpawnY", getY());
            spawnZ = input.getDoubleOr("ObsidianguardSpawnZ", getZ());
        }
        String target = input.getStringOr("ObsidianguardActionTarget", "");
        if (!target.isBlank()) actionTarget = UUID.fromString(target);
        String savedSummons = input.getStringOr("ObsidianguardSummons", "");
        if (!savedSummons.isBlank()) for (String uuid : savedSummons.split(",")) summons.add(UUID.fromString(uuid));
    }

    public int getPhase() { return entityData.get(PHASE); }
    public String obsidianguardAction() { return entityData.get(ACTION); }
    public long obsidianguardActionStartTick() { return entityData.get(ACTION_START); }
}
