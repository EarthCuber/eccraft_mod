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

public class BansheeEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final Identifier SPAWNED_FUNCTION = Eccraft.id("banshee/spawned");
    public static final Identifier DEFEATED_FUNCTION = Eccraft.id("banshee/defeated");
    public static final Identifier CLEARED_FUNCTION = Eccraft.id("banshee/cleared");
    public static final double MAX_HEALTH = 1500.0;
    public static final double ARMOR = 2.0;
    public static final double MOVEMENT_SPEED = 0.0;
    public static final double KNOCKBACK_RESISTANCE = 1.0;
    public static final double SCALE = 1.0;
    public static final int EXPERIENCE_REWARD = 100;
    public static final double EXISTENCE_RANGE = 15.0;
    public static final double PATROL_RANGE = 1.0;
    public static final double TARGET_RANGE = 12.0;
    public static final int COMBAT_TIMEOUT_TICKS = 240;
    public static final float BASIC_ATTACK_DAMAGE = 12.0F;
    // Action event times in server ticks, editable independently from the animation.
    public static final int BASIC_ATTACK_HIT_TICK = 8;
    public static final int SKILL_1_COOLDOWN_TICKS = 140;
    public static final int SKILL_1_EFFECT_TICK = 6;
    public static final int SKILL_2_COOLDOWN_TICKS = 400;
    public static final int SKILL_2_EFFECT_TICK = 8;

    private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(BansheeEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> ACTION_START = SynchedEntityData.defineId(BansheeEntity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(BansheeEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            UUID.randomUUID(), Component.translatable("entity.eccraft.banshee"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_10);
    private final Set<UUID> summons = new HashSet<>();
    private BansheeActionState action = BansheeActionState.IDLE;
    private UUID actionTarget;
    private boolean actionEffectApplied;
    private int withoutMeleeTargetTicks;
    private final java.util.Map<UUID, net.minecraft.world.phys.Vec3> previousPositions = new java.util.HashMap<>();
    private final java.util.Map<UUID, net.minecraft.world.phys.Vec3> velocities = new java.util.HashMap<>();
    private long nextSkill1Tick;
    private long nextSkill2Tick;
    private boolean silentRemoval;
    private boolean spawnFunctionExecuted;
    private boolean spawnPositionRecorded;
    private double spawnX;
    private double spawnY;
    private double spawnZ;

    public BansheeEntity(EntityType<? extends BansheeEntity> type, Level level) {
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
        builder.define(ACTION, BansheeActionState.IDLE.animation());
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
        samplePlayerVelocities(nearby);
        if (nearby.isEmpty()) {
            removeWithoutLoot(level);
            return;
        }

        double meleeTriggerRangeSqr = TARGET_RANGE * TARGET_RANGE;
        // Attack budget is updated only after higher-priority skills are considered.

        updatePhase();
        long now = level.getGameTime();
        if (action != BansheeActionState.IDLE && action != BansheeActionState.CHASE) {
            getNavigation().stop();
            tickAction(level, now);
            if (action != BansheeActionState.IDLE) return;
        }

        ServerPlayer nearest = nearby.stream().min(Comparator.comparingDouble(p -> com.eccraft.combat.TargetPreference.score(p, distanceToSqr(p)))).orElseThrow();
        getLookControl().setLookAt(nearest, 30.0F, 30.0F);
        int phase = getPhase();
        if (phase >= 3 && now >= nextSkill2Tick) beginAction(BansheeActionState.SKILL_2, null, now);
        else if (phase >= 2 && now >= nextSkill1Tick && distanceToSqr(nearest) <= meleeTriggerRangeSqr) beginAction(BansheeActionState.SKILL_1, nearest.getUUID(), now);
        else if (distanceToSqr(nearest) <= meleeTriggerRangeSqr) beginAction(BansheeActionState.ATTACK, nearest.getUUID(), now);
        else {
            if(failedMeleeAttempt()){removeWithoutLoot(level);return;}
            setPassiveAction(BansheeActionState.IDLE, now);
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

    private void beginAction(BansheeActionState next, UUID target, long now) {
        if(next==BansheeActionState.ATTACK)meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);
        action = next;
        actionTarget = target;
        actionEffectApplied = false;
        entityData.set(ACTION, next.animation());
        entityData.set(ACTION_START, now);
        getNavigation().stop();
        if (next == BansheeActionState.ATTACK || next == BansheeActionState.SKILL_1) faceAttackTargetOnce();
        if (next == BansheeActionState.SKILL_1) nextSkill1Tick = now + SKILL_1_COOLDOWN_TICKS;
        if (next == BansheeActionState.SKILL_2) nextSkill2Tick = now + SKILL_2_COOLDOWN_TICKS;
    }

    private void setPassiveAction(BansheeActionState next, long now) {
        if (action != next) {
            action = next;
            entityData.set(ACTION, next.animation());
            entityData.set(ACTION_START, now);
        }
    }

    private void tickAction(ServerLevel level, long now) {
        int elapsed = (int) (now - entityData.get(ACTION_START));
        if (!actionEffectApplied) {
            if (action == BansheeActionState.ATTACK && elapsed >= BASIC_ATTACK_HIT_TICK) applyBasicAttack(level);
            else if (action == BansheeActionState.SKILL_1 && elapsed >= SKILL_1_EFFECT_TICK) castSkill1(level);
            else if (action == BansheeActionState.SKILL_2 && elapsed >= SKILL_2_EFFECT_TICK) castSkill2(level);
        }
        if (elapsed >= action.duration()) {
            actionTarget = null;
            actionEffectApplied = false;
            setPassiveAction(BansheeActionState.IDLE, now);
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

    public static final float PROJECTILE_SPEED = 0.8F;
    public static final float SKILL_1_MULTIPLIER = 1.5F;
    public static final int PROJECTILE_LIFETIME = 100;
    public static final double PROJECTILE_HEIGHT = 3.0;
    public static final double TARGET_HEIGHT = 1.0;
    public static final double SUMMON_RADIUS = 6.0;
    public static final double SUMMON_HEIGHT_OFFSET = 0.5;

    // Player network movement is measured from server positions, in blocks per tick.
    private void samplePlayerVelocities(List<ServerPlayer> players) {
        Set<UUID> present = new HashSet<>();
        for (ServerPlayer player : players) {
            UUID id = player.getUUID();
            present.add(id);
            var previous = previousPositions.put(id, player.position());
            velocities.put(id, previous == null ? net.minecraft.world.phys.Vec3.ZERO : player.position().subtract(previous));
        }
        previousPositions.keySet().retainAll(present);
        velocities.keySet().retainAll(present);
    }

    private void applyBasicAttack(ServerLevel level) {
        actionEffectApplied = true;
        if (actionTarget == null || !(level.getEntity(actionTarget) instanceof ServerPlayer player)
                || !isCombatPlayer(player) || distanceToSqr(player) > TARGET_RANGE * TARGET_RANGE) return;
        fireProjectile(level, player, false);
        com.eccraft.sound.BansheeSounds.onAttackHit(this);
    }

    private void castSkill1(ServerLevel level) {
        actionEffectApplied = true;
        // Select at the release tick, independently of the original facing target.
        List<ServerPlayer> targets = validPlayers(level, TARGET_RANGE);
        for (ServerPlayer player : targets) fireProjectile(level, player, true);
        if (!targets.isEmpty()) com.eccraft.sound.BansheeSounds.onSkill1Damage(this);
    }

    private void fireProjectile(ServerLevel level, ServerPlayer player, boolean empowered) {
        float speed = PROJECTILE_SPEED * (empowered ? SKILL_1_MULTIPLIER : 1.0F);
        float damage = BASIC_ATTACK_DAMAGE * (empowered ? SKILL_1_MULTIPLIER : 1.0F);
        var origin = position().add(0, PROJECTILE_HEIGHT, 0);
        var projectile = new BansheeProjectile(com.eccraft.banshee.BansheeContent.PROJECTILE, level);
        projectile.setOwner(this);
        projectile.setPos(origin);
        projectile.configure(damage, PROJECTILE_LIFETIME);
        var aim = com.eccraft.banshee.BansheeAim.predict(origin, player.position().add(0, TARGET_HEIGHT, 0),
                velocities.getOrDefault(player.getUUID(), player.getDeltaMovement()), speed);
        var direction = aim.subtract(origin);
        projectile.shoot(direction.x, direction.y, direction.z, speed, 0.0F);
        if (level.addFreshEntity(projectile)) {
            summons.add(projectile.getUUID());
        }
        // Audio is played once by the action, outside the per-player projectile loop.
    }

    @Override protected boolean canRide(Entity vehicle) { return false; }
    @Override public boolean isPushable() { return false; }
    @Override public void push(Entity other) { }
    @Override public void push(double x, double y, double z) { }
    @Override public boolean isPushedByFluid() { return false; }
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction() {
        return net.minecraft.world.level.material.PushReaction.IGNORE;
    }
    @Override public void knockback(double strength, double x, double z, DamageSource source, float damage, boolean force) { }
    @Override public void travel(net.minecraft.world.phys.Vec3 input) {
        getNavigation().stop();
        setDeltaMovement(0, getDeltaMovement().y, 0);
        super.travel(net.minecraft.world.phys.Vec3.ZERO);
    }
    @Override public void move(net.minecraft.world.entity.MoverType type, net.minecraft.world.phys.Vec3 delta) {
        super.move(type, new net.minecraft.world.phys.Vec3(0, delta.y, 0));
    }

    private void castSkill2(ServerLevel level) {
        actionEffectApplied = true;
        com.eccraft.sound.BansheeSounds.onSkill2Volley(this);
        summonVanilla(level, net.minecraft.world.entity.EntityTypes.ZOMBIE);
        summonVanilla(level, net.minecraft.world.entity.EntityTypes.SKELETON);
    }

    private void summonVanilla(ServerLevel level, EntityType<? extends net.minecraft.world.entity.Mob> type) {
        var minion = type.create(level, EntitySpawnReason.MOB_SUMMONED);
        if (minion == null) return;
        boolean found = false;
        for (int attempt = 0; attempt < 64; attempt++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = Math.sqrt(random.nextDouble()) * SUMMON_RADIUS;
            minion.setPos(getX() + Math.cos(angle) * radius, getY() + SUMMON_HEIGHT_OFFSET, getZ() + Math.sin(angle) * radius);
            if (level.noCollision(minion) && !minion.getBoundingBox().intersects(getBoundingBox())) { found = true; break; }
        }
        if (!found) return;
        var group = minion instanceof net.minecraft.world.entity.monster.zombie.Zombie
                ? new net.minecraft.world.entity.monster.zombie.Zombie.ZombieGroupData(false, false) : null;
        minion.finalizeSpawn(level, level.getCurrentDifficultyAt(minion.blockPosition()), EntitySpawnReason.MOB_SUMMONED, group);
        if (minion instanceof net.minecraft.world.entity.monster.zombie.Zombie zombie) zombie.setBaby(false);
        minion.setCanPickUpLoot(false);
        minion.setPersistenceRequired();
        minion.addTag(com.eccraft.banshee.BansheeSummons.MARKER);
        minion.addTag(com.eccraft.banshee.BansheeSummons.OWNER_PREFIX + getUUID());
        if (level.addFreshEntity(minion)) summons.add(minion.getUUID());
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
        if (action == BansheeActionState.DEATH) return;
        if (level() instanceof ServerLevel level) cleanupSummons(level);
        bossEvent.setProgress(0.0F);
        super.die(source);
        action = BansheeActionState.DEATH;
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, level().getGameTime());
        getNavigation().stop();
    }

@Override
    protected void tickDeath() {
        ++deathTime;
        if (deathTime >= BansheeActionState.DEATH.duration() && level() instanceof ServerLevel serverLevel) {
            runDataFunction(serverLevel, DEFEATED_FUNCTION);
            serverLevel.broadcastEntityEvent(this, (byte) 60);
            remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return com.eccraft.sound.BansheeSounds.DEATH;
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
        output.putString("BansheeAction", action.name());
        output.putLong("BansheeActionStart", entityData.get(ACTION_START));
        output.putInt("BansheePhase", getPhase());
        output.putBoolean("BansheeActionEffect", actionEffectApplied);
        output.putInt("BansheeNoMeleeTicks", withoutMeleeTargetTicks);
        output.putLong("BansheeSkill1Ready", nextSkill1Tick);
        output.putLong("BansheeSkill2Ready", nextSkill2Tick);
        output.putBoolean("BansheeSpawnFunctionExecuted", spawnFunctionExecuted);
        output.putBoolean("BansheeSpawnPositionRecorded", spawnPositionRecorded);
        if (spawnPositionRecorded) {
            output.putDouble("BansheeSpawnX", spawnX);
            output.putDouble("BansheeSpawnY", spawnY);
            output.putDouble("BansheeSpawnZ", spawnZ);
        }
        if (actionTarget != null) output.putString("BansheeActionTarget", actionTarget.toString());
        output.putString("BansheeSummons", summons.stream().map(UUID::toString).reduce((a, b) -> a + "," + b).orElse(""));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(input.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);
        action = BansheeActionState.byName(input.getStringOr("BansheeAction", BansheeActionState.IDLE.name()));
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, input.getLongOr("BansheeActionStart", level().getGameTime()));
        entityData.set(PHASE, Mth.clamp(input.getIntOr("BansheePhase", 1), 1, 3));
        actionEffectApplied = input.getBooleanOr("BansheeActionEffect", false);
        withoutMeleeTargetTicks = input.getIntOr("BansheeNoMeleeTicks", 0);
        nextSkill1Tick = input.getLongOr("BansheeSkill1Ready", 0L);
        nextSkill2Tick = input.getLongOr("BansheeSkill2Ready", 0L);
        spawnFunctionExecuted = input.getBooleanOr("BansheeSpawnFunctionExecuted", true);
        spawnPositionRecorded = input.getBooleanOr("BansheeSpawnPositionRecorded", false);
        if (spawnPositionRecorded) {
            spawnX = input.getDoubleOr("BansheeSpawnX", getX());
            spawnY = input.getDoubleOr("BansheeSpawnY", getY());
            spawnZ = input.getDoubleOr("BansheeSpawnZ", getZ());
        }
        String target = input.getStringOr("BansheeActionTarget", "");
        if (!target.isBlank()) actionTarget = UUID.fromString(target);
        String savedSummons = input.getStringOr("BansheeSummons", "");
        if (!savedSummons.isBlank()) for (String uuid : savedSummons.split(",")) summons.add(UUID.fromString(uuid));
    }

    public int getPhase() { return entityData.get(PHASE); }
    public String bansheeAction() { return entityData.get(ACTION); }
    public long bansheeActionStartTick() { return entityData.get(ACTION_START); }
}
