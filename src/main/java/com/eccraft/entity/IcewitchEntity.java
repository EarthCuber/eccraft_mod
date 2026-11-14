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

public class IcewitchEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final Identifier SPAWNED_FUNCTION = Eccraft.id("icewitch/spawned");
    public static final Identifier DEFEATED_FUNCTION = Eccraft.id("icewitch/defeated");
    public static final Identifier CLEARED_FUNCTION = Eccraft.id("icewitch/cleared");
    public static final double MAX_HEALTH = 2000.0;
    public static final double ARMOR = 2.0;
    public static final double MOVEMENT_SPEED = 0.0;
    public static final double KNOCKBACK_RESISTANCE = 1.0;
    public static final double SCALE = 1.0;
    public static final int EXPERIENCE_REWARD = 100;
    public static final double EXISTENCE_RANGE = 16.0;
    public static final double PATROL_RANGE = 1.0;
    public static final double MELEE_TRIGGER_RANGE = 8.0;
    public static final double MELEE_HIT_RANGE = 12.0;
    public static final int COMBAT_TIMEOUT_TICKS = 240;
    public static final float BASIC_ATTACK_DAMAGE = 14.0F;
    // Provisional animation event times; finalize together with the GLB clips.
    public static final int BASIC_ATTACK_HIT_TICK = 8;
    public static final int SKILL_1_COOLDOWN_TICKS = 360;
    public static final int SKILL_1_EFFECT_TICK = 40;
    public static final int SKILL_2_COOLDOWN_TICKS = 440;
    public static final int SKILL_2_EFFECT_TICK = 40;
    public static final int SKILL_3_COOLDOWN_TICKS = 440;
    public static final int SKILL_3_EFFECT_TICK = 40;

    public static final int SKILL_4_COOLDOWN_TICKS = SKILL_1_COOLDOWN_TICKS + 40;
    public static final int SKILL_4_EFFECT_TICK = 40, SKILL_4_PROJECTILE_LIFETIME = 100;
    public static final float SKILL_4_PROJECTILE_SPEED = 1.0F, SKILL_4_DAMAGE = 8.0F;
    public static final double SKILL_4_RANGE = EXISTENCE_RANGE, SKILL_4_SPAWN_HEIGHT = 2.0, SKILL_4_TARGET_HEIGHT = 1.0;

    private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(IcewitchEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> ACTION_START = SynchedEntityData.defineId(IcewitchEntity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(IcewitchEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            UUID.randomUUID(), Component.translatable("entity.eccraft.icewitch"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_10);
    private final Set<UUID> summons = new HashSet<>();
    private IcewitchActionState action = IcewitchActionState.IDLE;
    private UUID actionTarget;
    private boolean actionEffectApplied;
    private int withoutMeleeTargetTicks;
    private long nextSkill1Tick;
    private long nextSkill2Tick;
    private long nextSkill3Tick;
    private long nextSkill4Tick;
    private final java.util.Map<UUID, net.minecraft.world.phys.Vec3> previousPositions = new java.util.HashMap<>();
    private final java.util.Map<UUID, net.minecraft.world.phys.Vec3> velocities = new java.util.HashMap<>();
    private boolean silentRemoval;
    private boolean spawnFunctionExecuted;
    private boolean spawnPositionRecorded;
    private double spawnX;
    private double spawnY;
    private double spawnZ;

    public IcewitchEntity(EntityType<? extends IcewitchEntity> type, Level level) {
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
        builder.define(ACTION, IcewitchActionState.IDLE.animation());
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
        samplePlayerVelocities(nearby);
        if (nearby.isEmpty()) {
            removeWithoutLoot(level);
            return;
        }

        double meleeTriggerRangeSqr = MELEE_TRIGGER_RANGE * MELEE_TRIGGER_RANGE;
        // Attack budget is updated only after higher-priority skills are considered.

        updatePhase();
        long now = level.getGameTime();
        if (action != IcewitchActionState.IDLE && action != IcewitchActionState.CHASE) {
            getNavigation().stop();
            tickAction(level, now);
            return;
        }

        ServerPlayer nearest = nearby.stream().min(Comparator.comparingDouble(p -> com.eccraft.combat.TargetPreference.score(p, distanceToSqr(p)))).orElseThrow();
        getLookControl().setLookAt(nearest, 30.0F, 30.0F);
        int phase = getPhase();
        if (phase >= 3 && now >= nextSkill2Tick) beginAction(IcewitchActionState.SKILL_2, null, now);
        else if (phase >= 3 && now >= nextSkill3Tick) beginAction(IcewitchActionState.SKILL_3, null, now);
        else if (phase >= 2 && now >= nextSkill1Tick) beginAction(IcewitchActionState.SKILL_1, null, now);
        else if (phase >= 2 && now >= nextSkill4Tick) beginAction(IcewitchActionState.SKILL_4, null, now);
        else if (distanceToSqr(nearest) <= meleeTriggerRangeSqr) beginAction(IcewitchActionState.ATTACK, nearest.getUUID(), now);
        else {
            if(failedMeleeAttempt()){removeWithoutLoot(level);return;}
            setPassiveAction(IcewitchActionState.CHASE, now);
            getNavigation().stop();
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

    private void beginAction(IcewitchActionState next, UUID target, long now) {
        if(next==IcewitchActionState.ATTACK)meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);
        action = next;
        actionTarget = target;
        actionEffectApplied = false;
        entityData.set(ACTION, next.animation());
        entityData.set(ACTION_START, now);
        getNavigation().stop();
        if (next == IcewitchActionState.ATTACK) faceAttackTargetOnce();
        if (next == IcewitchActionState.SKILL_1) playSound(com.eccraft.sound.ModSoundEvents.ICEWITCH_SKILL_1, 1.0F, 1.0F);
        if (next == IcewitchActionState.SKILL_2) playSound(com.eccraft.sound.ModSoundEvents.ICEWITCH_SKILL_2, 1.0F, 1.0F);
        if (next == IcewitchActionState.SKILL_3) playSound(com.eccraft.sound.ModSoundEvents.ICEWITCH_SKILL_3, 1.0F, 1.0F);
        if (next == IcewitchActionState.SKILL_1) nextSkill1Tick = now + SKILL_1_COOLDOWN_TICKS;
        if (next == IcewitchActionState.SKILL_2) nextSkill2Tick = now + SKILL_2_COOLDOWN_TICKS;
        if (next == IcewitchActionState.SKILL_3) nextSkill3Tick = now + SKILL_3_COOLDOWN_TICKS;
        if (next == IcewitchActionState.SKILL_4) nextSkill4Tick = now + SKILL_4_COOLDOWN_TICKS;
    }

    private void setPassiveAction(IcewitchActionState next, long now) {
        if (action != next) {
            action = next;
            entityData.set(ACTION, next.animation());
            entityData.set(ACTION_START, now);
        }
    }

    private void tickAction(ServerLevel level, long now) {
        int elapsed = (int) (now - entityData.get(ACTION_START));
        if (!actionEffectApplied) {
            if (action == IcewitchActionState.ATTACK && elapsed >= BASIC_ATTACK_HIT_TICK) applyBasicAttack(level);
            else if (action == IcewitchActionState.SKILL_1 && elapsed >= SKILL_1_EFFECT_TICK) castSkill1(level);
            else if (action == IcewitchActionState.SKILL_2 && elapsed >= SKILL_2_EFFECT_TICK) castSkill2(level);
            else if (action == IcewitchActionState.SKILL_3 && elapsed >= SKILL_3_EFFECT_TICK) castSkill3(level);
            else if (action == IcewitchActionState.SKILL_4 && elapsed >= SKILL_4_EFFECT_TICK) castSkill4(level);
        }
        if (elapsed >= action.duration()) {
            actionTarget = null;
            actionEffectApplied = false;
            setPassiveAction(IcewitchActionState.IDLE, now);
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
                playSound(net.minecraft.sounds.SoundEvents.PLAYER_HURT_FREEZE, 1.0F, 1.0F);
            }
        }
    }

@Override
    protected boolean canRide(Entity vehicle) {
        return false;
    }

    public static final int STRAY_COUNT = 2;
    public static final double STRAY_RADIUS = 9.0;
    public static final int ICEFIRE_COUNT = 9;
    public static final double ICEFIRE_RADIUS = 10.0;
    public static final double ICEFIRE_HEIGHT = 2.0;
    public static final double BURST_RADIUS = 16.0;
    public static final float BURST_DAMAGE = 4.0F;

    @Override public boolean isPushable() { return false; }
    @Override public void push(Entity other) { }
    @Override public void push(double x, double y, double z) { }
    @Override public boolean isPushedByFluid() { return false; }
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction() {
        return net.minecraft.world.level.material.PushReaction.IGNORE;
    }
    @Override public void knockback(double strength, double x, double z, DamageSource source,
                                    float damage, boolean force) { }

    private void castSkill1(ServerLevel level) {
        actionEffectApplied = true;
        for (int i = 0; i < STRAY_COUNT; i++) {
            IcewitchStrayEntity stray = new IcewitchStrayEntity(ModEntityTypes.ICEWITCH_STRAY, level);
            boolean found = false;
            for (int attempt = 0; attempt < 64; attempt++) {
                double angle = random.nextDouble() * Math.PI * 2;
                double radius = Math.sqrt(random.nextDouble()) * STRAY_RADIUS;
                stray.setPos(getX() + Math.cos(angle) * radius, getY(), getZ() + Math.sin(angle) * radius);
                if (level.noCollision(stray) && !stray.getBoundingBox().intersects(getBoundingBox())) { found = true; break; }
            }
            if (!found) continue;
            stray.finalizeSpawn(level, level.getCurrentDifficultyAt(stray.blockPosition()), EntitySpawnReason.MOB_SUMMONED, null);
            stray.setOwnerUuid(getUUID());
            stray.setHealth(stray.getMaxHealth());
            if (level.addFreshEntity(stray)) summons.add(stray.getUUID());
        }
    }

    private void castSkill2(ServerLevel level) {
        actionEffectApplied = true;
        for (int i = 0; i < ICEFIRE_COUNT; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = Math.sqrt(random.nextDouble()) * ICEFIRE_RADIUS;
            IcefireEntity fire = new IcefireEntity(ModEntityTypes.ICEFIRE, level);
            fire.setOwnerUuid(getUUID());
            fire.setPos(getX() + Math.cos(angle) * radius, getY() + ICEFIRE_HEIGHT, getZ() + Math.sin(angle) * radius);
            if (level.addFreshEntity(fire)) summons.add(fire.getUUID());
        }
    }

    private void castSkill3(ServerLevel level) {
        actionEffectApplied = true;
        var type = level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                .getOrThrow(com.eccraft.effect.ColdnessEffect.MAGIC);
        for (ServerPlayer player : validPlayers(level, BURST_RADIUS)) {
            player.hurtServer(level, new DamageSource(type, this), BURST_DAMAGE);
            if (player.isAlive()) player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                    com.eccraft.effect.ModEffects.COLDNESS, 640, 6), this);
        }
    }
    private void samplePlayerVelocities(List<ServerPlayer> players) {
        Set<UUID> present = new HashSet<>();
        for (var player : players) {
            UUID id = player.getUUID(); present.add(id);
            var previous = previousPositions.put(id, player.position());
            velocities.put(id, previous == null ? net.minecraft.world.phys.Vec3.ZERO : player.position().subtract(previous));
        }
        previousPositions.keySet().retainAll(present); velocities.keySet().retainAll(present);
    }

    private void castSkill4(ServerLevel level) {
        actionEffectApplied = true;
        var origin = position().add(0, SKILL_4_SPAWN_HEIGHT, 0);
        for (var player : validPlayers(level, SKILL_4_RANGE)) {
            var projectile = new com.eccraft.staff.StaffProjectile(com.eccraft.staff.StaffSystem.PROJECTILE, level);
            projectile.setOwner(this); projectile.setPos(origin);
            projectile.configureBossFrostShot(SKILL_4_DAMAGE, SKILL_4_PROJECTILE_LIFETIME);
            var aim = com.eccraft.banshee.BansheeAim.predict(origin, player.position().add(0, SKILL_4_TARGET_HEIGHT, 0),
                    velocities.getOrDefault(player.getUUID(), net.minecraft.world.phys.Vec3.ZERO), SKILL_4_PROJECTILE_SPEED);
            var direction = aim.subtract(origin);
            projectile.shoot(direction.x, direction.y, direction.z, SKILL_4_PROJECTILE_SPEED, 0);
            if (level.addFreshEntity(projectile)) summons.add(projectile.getUUID());
        }
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
        if (action == IcewitchActionState.DEATH) return;
        if (level() instanceof ServerLevel level) cleanupSummons(level);
        super.die(source);
        action = IcewitchActionState.DEATH;
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, level().getGameTime());
        getNavigation().stop();
    }

@Override
    protected void tickDeath() {
        ++deathTime;
        if (deathTime >= IcewitchActionState.DEATH.duration() && level() instanceof ServerLevel serverLevel) {
            runDataFunction(serverLevel, DEFEATED_FUNCTION);
            serverLevel.broadcastEntityEvent(this, (byte) 60);
            remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return com.eccraft.sound.ModSoundEvents.ICEWITCH_DEATH;
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
        output.putString("IcewitchAction", action.name());
        output.putLong("IcewitchActionStart", entityData.get(ACTION_START));
        output.putInt("IcewitchPhase", getPhase());
        output.putBoolean("IcewitchActionEffect", actionEffectApplied);
        output.putInt("IcewitchNoMeleeTicks", withoutMeleeTargetTicks);
        output.putLong("IcewitchSkill1Ready", nextSkill1Tick);
        output.putLong("IcewitchSkill2Ready", nextSkill2Tick);
        output.putLong("IcewitchSkill3Ready", nextSkill3Tick);
        output.putLong("IcewitchSkill4Ready", nextSkill4Tick);
        output.putBoolean("IcewitchSpawnFunctionExecuted", spawnFunctionExecuted);
        output.putBoolean("IcewitchSpawnPositionRecorded", spawnPositionRecorded);
        if (spawnPositionRecorded) {
            output.putDouble("IcewitchSpawnX", spawnX);
            output.putDouble("IcewitchSpawnY", spawnY);
            output.putDouble("IcewitchSpawnZ", spawnZ);
        }
        if (actionTarget != null) output.putString("IcewitchActionTarget", actionTarget.toString());
        output.putString("IcewitchSummons", summons.stream().map(UUID::toString).reduce((a, b) -> a + "," + b).orElse(""));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(input.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);
        action = IcewitchActionState.byName(input.getStringOr("IcewitchAction", IcewitchActionState.IDLE.name()));
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, input.getLongOr("IcewitchActionStart", level().getGameTime()));
        entityData.set(PHASE, Mth.clamp(input.getIntOr("IcewitchPhase", 1), 1, 3));
        actionEffectApplied = input.getBooleanOr("IcewitchActionEffect", false);
        withoutMeleeTargetTicks = input.getIntOr("IcewitchNoMeleeTicks", 0);
        nextSkill1Tick = input.getLongOr("IcewitchSkill1Ready", 0L);
        nextSkill2Tick = input.getLongOr("IcewitchSkill2Ready", 0L);
        nextSkill3Tick = input.getLongOr("IcewitchSkill3Ready", 0L);
        nextSkill4Tick = input.getLongOr("IcewitchSkill4Ready", 0L);
        spawnFunctionExecuted = input.getBooleanOr("IcewitchSpawnFunctionExecuted", true);
        spawnPositionRecorded = input.getBooleanOr("IcewitchSpawnPositionRecorded", false);
        if (spawnPositionRecorded) {
            spawnX = input.getDoubleOr("IcewitchSpawnX", getX());
            spawnY = input.getDoubleOr("IcewitchSpawnY", getY());
            spawnZ = input.getDoubleOr("IcewitchSpawnZ", getZ());
        }
        String target = input.getStringOr("IcewitchActionTarget", "");
        if (!target.isBlank()) actionTarget = UUID.fromString(target);
        String savedSummons = input.getStringOr("IcewitchSummons", "");
        if (!savedSummons.isBlank()) for (String uuid : savedSummons.split(",")) summons.add(UUID.fromString(uuid));
    }

    public int getPhase() { return entityData.get(PHASE); }
    public String icewitchAction() { return entityData.get(ACTION); }
    public long icewitchActionStartTick() { return entityData.get(ACTION_START); }
}
