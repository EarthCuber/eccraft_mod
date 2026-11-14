package com.eccraft.entity;

import com.eccraft.Eccraft;
import com.eccraft.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
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

public class AnubisEntity extends Monster implements AnubisAnimationSource {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final Identifier SPAWNED_FUNCTION = Eccraft.id("anubis/spawned");
    public static final Identifier DEFEATED_FUNCTION = Eccraft.id("anubis/defeated");
    public static final Identifier CLEARED_FUNCTION = Eccraft.id("anubis/cleared");
    public static final double MAX_HEALTH = 2500.0;
    public static final double ARMOR = 2.0;
    public static final double MOVEMENT_SPEED = 0.27;
    public static final double KNOCKBACK_RESISTANCE = 1.0;
    public static final double SCALE = 1.0;
    public static final int EXPERIENCE_REWARD = 100;
    public static final double EXISTENCE_RANGE = 24.0;
    public static final double PATROL_RANGE = 18.0;
    public static final double MELEE_TRIGGER_RANGE = 3.5;
    public static final double MELEE_HIT_RANGE = 4.5;
    public static final int COMBAT_TIMEOUT_TICKS = 400;
    public static final float BASIC_ATTACK_DAMAGE = 14.0F;
    public static final int BASIC_ATTACK_HIT_TICK = 25;
    public static final float SHIELD_DISABLE_SECONDS = 10.0F;
    public static final float PHASE_2_HEALTH = 2000.0F;
    public static final float PHASE_3_HEALTH = 1250.0F;
    public static final int SKILL_1_COOLDOWN_TICKS = 100;
    public static final int SKILL_1_EFFECT_TICK = 10;
    public static final int SKILL_1_FLAME_COUNT = 8;
    public static final int SKILL_1_HORIZONTAL_RANGE = 10;
    public static final double SKILL_1_SPAWN_HEIGHT = 6.0;
    public static final int SKILL_2_COOLDOWN_TICKS = 480;
    public static final int SKILL_2_EFFECT_TICK = 20;
    public static final float SKILL_2_DAMAGE = 6.0F;
    public static final float SKILL_2_FIRE_SECONDS = 6.0F;
    public static final int SKILL_3_COOLDOWN_TICKS = 480;
    public static final int SKILL_3_EFFECT_TICK = 20;

    private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(AnubisEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> ACTION_START = SynchedEntityData.defineId(AnubisEntity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(AnubisEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            UUID.randomUUID(), Component.translatable("entity.eccraft.anubis"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_10);
    private final Set<UUID> summons = new HashSet<>();
    private BossActionState action = BossActionState.IDLE;
    private UUID actionTarget;
    private boolean actionEffectApplied;
    private int withoutMeleeTargetTicks;
    private long nextSkill1Tick;
    private long nextSkill2Tick;
    private long nextSkill3Tick;
    private boolean silentRemoval;
    private boolean spawnFunctionExecuted;
    private boolean spawnPositionRecorded;
    private double spawnX;
    private double spawnY;
    private double spawnZ;

    public AnubisEntity(EntityType<? extends AnubisEntity> type, Level level) {
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
        builder.define(ACTION, BossActionState.IDLE.animation());
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
        bossEvent.setProgress(getHealth() / getMaxHealth());
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
        if (action != BossActionState.IDLE && action != BossActionState.CHASE) {
            getNavigation().stop();
            tickAction(level, now);
            return;
        }

        ServerPlayer nearest = nearby.stream().min(Comparator.comparingDouble(p -> com.eccraft.combat.TargetPreference.score(p, distanceToSqr(p)))).orElseThrow();
        getLookControl().setLookAt(nearest, 30.0F, 30.0F);
        int phase = getPhase();
        if (phase >= 3 && now >= nextSkill2Tick) beginAction(BossActionState.SKILL_2, null, now);
        else if (phase >= 3 && now >= nextSkill3Tick) beginAction(BossActionState.SKILL_3, null, now);
        else if (phase >= 2 && now >= nextSkill1Tick) beginAction(BossActionState.SKILL_1, null, now);
        else if (distanceToSqr(nearest) <= meleeTriggerRangeSqr) beginAction(BossActionState.ATTACK, nearest.getUUID(), now);
        else {
            if(failedMeleeAttempt()){removeWithoutLoot(level);return;}
            setPassiveAction(BossActionState.CHASE, now);
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
        if (getHealth() <= PHASE_3_HEALTH) phase = Math.max(phase, 3);
        else if (getHealth() <= PHASE_2_HEALTH) phase = Math.max(phase, 2);
        entityData.set(PHASE, phase);
    }

    private void beginAction(BossActionState next, UUID target, long now) {
        if(next==BossActionState.ATTACK)meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);
        action = next;
        actionTarget = target;
        actionEffectApplied = false;
        entityData.set(ACTION, next.animation());
        entityData.set(ACTION_START, now);
        getNavigation().stop();
        if (next == BossActionState.ATTACK) faceAttackTargetOnce();
        if (next == BossActionState.SKILL_1) nextSkill1Tick = now + SKILL_1_COOLDOWN_TICKS;
        if (next == BossActionState.SKILL_2) nextSkill2Tick = now + SKILL_2_COOLDOWN_TICKS;
        if (next == BossActionState.SKILL_3) nextSkill3Tick = now + SKILL_3_COOLDOWN_TICKS;
    }

    private void setPassiveAction(BossActionState next, long now) {
        if (action != next) {
            action = next;
            entityData.set(ACTION, next.animation());
            entityData.set(ACTION_START, now);
        }
    }

    private void tickAction(ServerLevel level, long now) {
        int elapsed = (int) (now - entityData.get(ACTION_START));
        if (!actionEffectApplied) {
            if (action == BossActionState.ATTACK && elapsed >= BASIC_ATTACK_HIT_TICK) applyBasicAttack(level);
            else if (action == BossActionState.SKILL_1 && elapsed >= SKILL_1_EFFECT_TICK) castSkill1(level);
            else if (action == BossActionState.SKILL_2 && elapsed >= SKILL_2_EFFECT_TICK) castSkill2(level);
            else if (action == BossActionState.SKILL_3 && elapsed >= SKILL_3_EFFECT_TICK) castSkill3(level);
        }
        if (elapsed >= action.duration()) {
            actionTarget = null;
            actionEffectApplied = false;
            setPassiveAction(BossActionState.IDLE, now);
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
            player.hurtServer(level, com.eccraft.damage.BossDamageSources.melee(level, this), BASIC_ATTACK_DAMAGE);
        }
    }

    @Override
    public float getSecondsToDisableBlocking() {
        return SHIELD_DISABLE_SECONDS;
    }

    @Override
    protected boolean canRide(Entity vehicle) {
        return false;
    }

    private void castSkill1(ServerLevel level) {
        actionEffectApplied = true;
        playSound(ModSoundEvents.ANUBIS_SUMMON_FLAME, 1.0F, 1.0F);
        Set<Long> usedOffsets = new HashSet<>();
        while (usedOffsets.size() < SKILL_1_FLAME_COUNT) {
            int diameter = SKILL_1_HORIZONTAL_RANGE * 2 + 1;
            int dx = random.nextInt(diameter) - SKILL_1_HORIZONTAL_RANGE;
            int dz = random.nextInt(diameter) - SKILL_1_HORIZONTAL_RANGE;
            if (!usedOffsets.add(BlockPos.asLong(dx, 0, dz))) continue;
            AnubisFlameEntity flame = new AnubisFlameEntity(ModEntityTypes.ANUBIS_FLAME, level);
            flame.setOwnerUuid(getUUID());
            flame.setPos(getX() + dx + 0.5, getY() + SKILL_1_SPAWN_HEIGHT, getZ() + dz + 0.5);
            level.addFreshEntity(flame);
            summons.add(flame.getUUID());
        }
    }

    private void castSkill2(ServerLevel level) {
        actionEffectApplied = true;
        playSound(ModSoundEvents.ANUBIS_EXPLOSION, 1.0F, 1.0F);
        for (ServerPlayer player : validPlayers(level, EXISTENCE_RANGE)) {
            player.hurtServer(level, damageSources().indirectMagic(this, this), SKILL_2_DAMAGE);
            player.igniteForSeconds(SKILL_2_FIRE_SECONDS);
        }
        level.sendParticles(ParticleTypes.FLAME, getX(), getY() + 1.5, getZ(), 80, 8.0, 1.5, 8.0, 0.02);
    }

    private void castSkill3(ServerLevel level) {
        actionEffectApplied = true;
        playSound(ModSoundEvents.ANUBIS_SUMMON_GUARD, 1.0F, 1.0F);
        AnubisHuskEntity husk = new AnubisHuskEntity(ModEntityTypes.ANUBIS_HUSK, level);
        husk.setOwnerUuid(getUUID());
        for (int attempt = 0; attempt < 12; attempt++) {
            double angle = random.nextDouble() * Mth.TWO_PI;
            double radius = 2.0 + random.nextDouble() * 2.0;
            husk.setPos(getX() + Math.cos(angle) * radius, getY(), getZ() + Math.sin(angle) * radius);
            if (level.noCollision(husk)) break;
        }
        husk.finalizeSpawn(level, level.getCurrentDifficultyAt(husk.blockPosition()),
                EntitySpawnReason.MOB_SUMMONED, null);
        husk.setHealth(husk.getMaxHealth());
        level.addFreshEntity(husk);
        summons.add(husk.getUUID());
        level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, husk.getX(), husk.getY() + 1.0, husk.getZ(), 30, 0.5, 1.0, 0.5, 0.02);
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
        discard();
    }

    @Override
    public void die(DamageSource source) {
        if (action == BossActionState.DEATH) return;
        if (level() instanceof ServerLevel level) cleanupSummons(level);
        super.die(source);
        action = BossActionState.DEATH;
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, level().getGameTime());
        getNavigation().stop();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.ANUBIS_DEATH;
    }

    @Override
    protected void tickDeath() {
        ++deathTime;
        if (deathTime >= BossActionState.DEATH.duration() && level() instanceof ServerLevel serverLevel) {
            runDataFunction(serverLevel, DEFEATED_FUNCTION);
            serverLevel.broadcastEntityEvent(this, (byte) 60);
            remove(RemovalReason.KILLED);
        }
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
        output.putString("AnubisAction", action.name());
        output.putLong("AnubisActionStart", entityData.get(ACTION_START));
        output.putInt("AnubisPhase", getPhase());
        output.putBoolean("AnubisActionEffect", actionEffectApplied);
        output.putInt("AnubisNoMeleeTicks", withoutMeleeTargetTicks);
        output.putLong("AnubisSkill1Ready", nextSkill1Tick);
        output.putLong("AnubisSkill2Ready", nextSkill2Tick);
        output.putLong("AnubisSkill3Ready", nextSkill3Tick);
        output.putBoolean("AnubisSpawnFunctionExecuted", spawnFunctionExecuted);
        output.putBoolean("AnubisSpawnPositionRecorded", spawnPositionRecorded);
        if (spawnPositionRecorded) {
            output.putDouble("AnubisSpawnX", spawnX);
            output.putDouble("AnubisSpawnY", spawnY);
            output.putDouble("AnubisSpawnZ", spawnZ);
        }
        if (actionTarget != null) output.putString("AnubisActionTarget", actionTarget.toString());
        output.putString("AnubisSummons", summons.stream().map(UUID::toString).reduce((a, b) -> a + "," + b).orElse(""));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(input.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);
        action = BossActionState.byName(input.getStringOr("AnubisAction", BossActionState.IDLE.name()));
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, input.getLongOr("AnubisActionStart", level().getGameTime()));
        entityData.set(PHASE, Mth.clamp(input.getIntOr("AnubisPhase", 1), 1, 3));
        actionEffectApplied = input.getBooleanOr("AnubisActionEffect", false);
        withoutMeleeTargetTicks = input.getIntOr("AnubisNoMeleeTicks", 0);
        nextSkill1Tick = input.getLongOr("AnubisSkill1Ready", 0L);
        nextSkill2Tick = input.getLongOr("AnubisSkill2Ready", 0L);
        nextSkill3Tick = input.getLongOr("AnubisSkill3Ready", 0L);
        spawnFunctionExecuted = input.getBooleanOr("AnubisSpawnFunctionExecuted", true);
        spawnPositionRecorded = input.getBooleanOr("AnubisSpawnPositionRecorded", false);
        if (spawnPositionRecorded) {
            spawnX = input.getDoubleOr("AnubisSpawnX", getX());
            spawnY = input.getDoubleOr("AnubisSpawnY", getY());
            spawnZ = input.getDoubleOr("AnubisSpawnZ", getZ());
        }
        String target = input.getStringOr("AnubisActionTarget", "");
        if (!target.isBlank()) actionTarget = UUID.fromString(target);
        String savedSummons = input.getStringOr("AnubisSummons", "");
        if (!savedSummons.isBlank()) for (String uuid : savedSummons.split(",")) summons.add(UUID.fromString(uuid));
    }

    public int getPhase() { return entityData.get(PHASE); }
    @Override public String anubisAction() { return entityData.get(ACTION); }
    @Override public long anubisActionStartTick() { return entityData.get(ACTION_START); }
}
