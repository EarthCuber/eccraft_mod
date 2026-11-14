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

public class CastlecoreEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final Identifier SPAWNED_FUNCTION = Eccraft.id("castlecore/spawned");
    public static final Identifier DEFEATED_FUNCTION = Eccraft.id("castlecore/defeated");
    public static final Identifier CLEARED_FUNCTION = Eccraft.id("castlecore/cleared");
    public static final double MAX_HEALTH = 4000.0;
    public static final double ARMOR = 8.0;
    public static final double MOVEMENT_SPEED = 0.0;
    public static final double KNOCKBACK_RESISTANCE = 1.0;
    public static final double SCALE = 1.0;
    public static final int EXPERIENCE_REWARD = 100;
    public static final double EXISTENCE_RANGE = 32.0;
    public static final double PATROL_RANGE = 2.0;
    public static final double MELEE_TRIGGER_RANGE = 4.5;
    public static final double MELEE_HIT_RANGE = 12.0;
    public static final int COMBAT_TIMEOUT_TICKS = 160;
    public static final float BASIC_ATTACK_DAMAGE = 22.0F;
    // Action event times in server ticks; durations are defined in CastlecoreActionState.
    public static final int BASIC_ATTACK_HIT_TICK = 15;
    public static final int SKILL_1_COOLDOWN_TICKS = 340;
    public static final int SKILL_1_EFFECT_TICK = 20;
    public static final int SKILL_2_COOLDOWN_TICKS = 140;
    public static final int SKILL_2_EFFECT_TICK = 15;

    private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(CastlecoreEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> ACTION_START = SynchedEntityData.defineId(CastlecoreEntity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(CastlecoreEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            UUID.randomUUID(), Component.translatable("entity.eccraft.castlecore"),
            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_10);
    private final Set<UUID> summons = new HashSet<>();
    private CastlecoreActionState action = CastlecoreActionState.IDLE;
    private UUID actionTarget;
    private boolean actionEffectApplied;
    private int withoutMeleeTargetTicks;
    private long nextSkill1Tick;
    private long nextSkill2Tick;
    private long nextSkill3Tick;
    private float nextSkill3Damage = SKILL_3_INITIAL_DAMAGE;
    private boolean silentRemoval;
    private boolean spawnFunctionExecuted;
    private boolean spawnPositionRecorded;
    private double spawnX;
    private double spawnY;
    private double spawnZ;

    public CastlecoreEntity(EntityType<? extends CastlecoreEntity> type, Level level) {
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
        builder.define(ACTION, CastlecoreActionState.IDLE.animation());
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
        // On entering phase 3, stop phase-2 casts that have not yet resolved.
        if(getPhase()>=3&&(action==CastlecoreActionState.SKILL_1||action==CastlecoreActionState.SKILL_2)){
            actionTarget=null;actionEffectApplied=false;setPassiveAction(CastlecoreActionState.IDLE,level.getGameTime());
        }
        long now = level.getGameTime();
        if (action != CastlecoreActionState.IDLE && action != CastlecoreActionState.CHASE) {
            getNavigation().stop();
            tickAction(level, now);
            return;
        }

        ServerPlayer nearest = nearby.stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElseThrow();
        getLookControl().setLookAt(nearest, 30.0F, 30.0F);
        int phase = getPhase();
        if (phase == 2 && now >= nextSkill1Tick && !validPlayers(level,SKILL_1_RANGE).isEmpty()) beginAction(CastlecoreActionState.SKILL_1, nearest.getUUID(), now);
        else if (phase == 2 && now >= nextSkill2Tick && !validPlayers(level,SKILL_2_RANGE).isEmpty()) beginAction(CastlecoreActionState.SKILL_2, nearest.getUUID(), now);
        else if (phase >= 3 && now >= nextSkill3Tick && !validPlayers(level,SKILL_3_RANGE).isEmpty()) beginAction(CastlecoreActionState.SKILL_3, nearest.getUUID(), now);
        else if (distanceToSqr(nearest) <= meleeTriggerRangeSqr) beginAction(CastlecoreActionState.ATTACK, nearest.getUUID(), now);
        else {
            if(failedMeleeAttempt()){removeWithoutLoot(level);return;}getNavigation().stop();setPassiveAction(CastlecoreActionState.IDLE,now);}
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

    private void beginAction(CastlecoreActionState next, UUID target, long now) {
        if(next==CastlecoreActionState.ATTACK)meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);
        action = next;
        actionTarget = target; // Skill 2 locks this UUID at cast start; distance is not rechecked.
        actionEffectApplied = false;
        entityData.set(ACTION, next.animation());
        entityData.set(ACTION_START, now);
        getNavigation().stop();
        if (next == CastlecoreActionState.ATTACK || next == CastlecoreActionState.SKILL_1 || next == CastlecoreActionState.SKILL_2) faceAttackTargetOnce();
        if (next == CastlecoreActionState.SKILL_1) nextSkill1Tick = now + SKILL_1_COOLDOWN_TICKS;
        if (next == CastlecoreActionState.SKILL_2) nextSkill2Tick = now + SKILL_2_COOLDOWN_TICKS;
        if (next == CastlecoreActionState.SKILL_3) nextSkill3Tick = now + SKILL_3_COOLDOWN_TICKS;
    }

    private void setPassiveAction(CastlecoreActionState next, long now) {
        if (action != next) {
            action = next;
            entityData.set(ACTION, next.animation());
            entityData.set(ACTION_START, now);
        }
    }

    private void tickAction(ServerLevel level, long now) {
        int elapsed = (int) (now - entityData.get(ACTION_START));
        if (!actionEffectApplied) {
            if (action == CastlecoreActionState.ATTACK && elapsed >= BASIC_ATTACK_HIT_TICK) applyBasicAttack(level);
            else if (action == CastlecoreActionState.SKILL_1 && elapsed >= SKILL_1_EFFECT_TICK) castSkill1(level);
            else if (action == CastlecoreActionState.SKILL_2 && elapsed >= SKILL_2_EFFECT_TICK) castSkill2(level);
            else if (action == CastlecoreActionState.SKILL_3 && elapsed >= SKILL_3_EFFECT_TICK) castSkill3(level);
        }
        if (elapsed >= action.duration()) {
            actionTarget = null;
            actionEffectApplied = false;
            setPassiveAction(CastlecoreActionState.IDLE, now);
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
                com.eccraft.sound.CastlecoreSounds.onAttackHit(this);
            }
        }
    }

@Override
    protected boolean canRide(Entity vehicle) {
        return false;
    }

    public static final double SKILL_1_RANGE=25.0, SKILL_1_HEIGHT=2.0, SKILL_2_RANGE=3.5, SKILL_3_RANGE=25.0;
    public static final int ARMOR_REND_DURATION=400, SKILL_3_COOLDOWN_TICKS=600, SKILL_3_EFFECT_TICK=20;
    public static final float SKILL_3_INITIAL_DAMAGE=6.0F, SKILL_3_INCREMENT=5.0F;

    @Override public void travel(net.minecraft.world.phys.Vec3 input){
        getNavigation().stop();setDeltaMovement(0,getDeltaMovement().y,0);super.travel(net.minecraft.world.phys.Vec3.ZERO);
    }
    @Override public void move(net.minecraft.world.entity.MoverType type,net.minecraft.world.phys.Vec3 delta){super.move(type,new net.minecraft.world.phys.Vec3(0,delta.y,0));}
    @Override public boolean isPushable(){return false;}
    @Override public void push(Entity other){}
    @Override public void push(double x,double y,double z){}
    @Override public boolean isPushedByFluid(){return false;}
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction(){return net.minecraft.world.level.material.PushReaction.IGNORE;}
    @Override public void knockback(double strength,double x,double z,DamageSource source,float damage,boolean force){}

    private void castSkill1(ServerLevel level){
        actionEffectApplied=true;
        var target=validPlayers(level,SKILL_1_RANGE).stream().max(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
        if(target==null)return;
        target.teleportTo(getX(),getY()+SKILL_1_HEIGHT,getZ());
        com.eccraft.sound.CastlecoreSounds.onSkill1Damage(this);
    }
    private void castSkill2(ServerLevel level){
        actionEffectApplied=true;
        if(actionTarget==null)return;
        var entity=level.getEntity(actionTarget);
        if(!(entity instanceof ServerPlayer target)||!isCombatPlayer(target))return;
        target.addEffect(new net.minecraft.world.effect.MobEffectInstance(com.eccraft.effect.ArmorRendEffect.ARMOR_REND,ARMOR_REND_DURATION,0),this);
        com.eccraft.sound.CastlecoreSounds.onSkill2Volley(this);
    }
    private void castSkill3(ServerLevel level){
        actionEffectApplied=true;
        com.eccraft.sound.CastlecoreSounds.onSkill3Damage(this);
        com.eccraft.obsidianguard.ObsidianguardParticles.burst(level,position().add(0,1,0),96,0.5);
        var damage=magic(level);
        for(var player:validPlayers(level,SKILL_3_RANGE))player.hurtServer(level,damage,nextSkill3Damage);
        nextSkill3Damage=(float)Math.min(Float.MAX_VALUE,(double)nextSkill3Damage+SKILL_3_INCREMENT);
    }
    private DamageSource magic(ServerLevel level){
        return new DamageSource(level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
            .getOrThrow(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.DAMAGE_TYPE,Eccraft.id("magic"))),this);
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
        if (action == CastlecoreActionState.DEATH) return;
        if (level() instanceof ServerLevel level) cleanupSummons(level);
        bossEvent.setProgress(0.0F);
        super.die(source);
        action = CastlecoreActionState.DEATH;
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, level().getGameTime());
        getNavigation().stop();
    }

@Override
    protected void tickDeath() {
        ++deathTime;
        if (deathTime >= CastlecoreActionState.DEATH.duration() && level() instanceof ServerLevel serverLevel) {
            bossEvent.removeAllPlayers();
            runDataFunction(serverLevel, DEFEATED_FUNCTION);
            serverLevel.broadcastEntityEvent(this, (byte) 60);
            remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return com.eccraft.sound.CastlecoreSounds.DEATH;
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
        output.putInt("CastlecoreDeathTicks", deathTime);
        output.putString("CastlecoreAction", action.name());
        output.putLong("CastlecoreActionStart", entityData.get(ACTION_START));
        output.putInt("CastlecorePhase", getPhase());
        output.putBoolean("CastlecoreActionEffect", actionEffectApplied);
        output.putInt("CastlecoreNoMeleeTicks", withoutMeleeTargetTicks);
        output.putLong("CastlecoreSkill1Ready", nextSkill1Tick);
        output.putLong("CastlecoreSkill2Ready", nextSkill2Tick);
        output.putLong("CastlecoreSkill3Ready",nextSkill3Tick);
        output.putFloat("CastlecoreNextSkill3Damage",nextSkill3Damage);
        output.putBoolean("CastlecoreSpawnFunctionExecuted", spawnFunctionExecuted);
        output.putBoolean("CastlecoreSpawnPositionRecorded", spawnPositionRecorded);
        if (spawnPositionRecorded) {
            output.putDouble("CastlecoreSpawnX", spawnX);
            output.putDouble("CastlecoreSpawnY", spawnY);
            output.putDouble("CastlecoreSpawnZ", spawnZ);
        }
        if (actionTarget != null) output.putString("CastlecoreActionTarget", actionTarget.toString());
        output.putString("CastlecoreSummons", summons.stream().map(UUID::toString).reduce((a, b) -> a + "," + b).orElse(""));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(input.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);
        deathTime = input.getIntOr("CastlecoreDeathTicks", 0);
        action = CastlecoreActionState.byName(input.getStringOr("CastlecoreAction", CastlecoreActionState.IDLE.name()));
        entityData.set(ACTION, action.animation());
        entityData.set(ACTION_START, input.getLongOr("CastlecoreActionStart", level().getGameTime()));
        entityData.set(PHASE, Mth.clamp(input.getIntOr("CastlecorePhase", 1), 1, 3));
        actionEffectApplied = input.getBooleanOr("CastlecoreActionEffect", false);
        withoutMeleeTargetTicks = input.getIntOr("CastlecoreNoMeleeTicks", 0);
        nextSkill1Tick = input.getLongOr("CastlecoreSkill1Ready", 0L);
        nextSkill2Tick = input.getLongOr("CastlecoreSkill2Ready", 0L);
        nextSkill3Tick=input.getLongOr("CastlecoreSkill3Ready",0L);
        nextSkill3Damage=input.getFloatOr("CastlecoreNextSkill3Damage",SKILL_3_INITIAL_DAMAGE);
        if(!Float.isFinite(nextSkill3Damage)||nextSkill3Damage<SKILL_3_INITIAL_DAMAGE)nextSkill3Damage=SKILL_3_INITIAL_DAMAGE;
        spawnFunctionExecuted = input.getBooleanOr("CastlecoreSpawnFunctionExecuted", true);
        spawnPositionRecorded = input.getBooleanOr("CastlecoreSpawnPositionRecorded", false);
        if (spawnPositionRecorded) {
            spawnX = input.getDoubleOr("CastlecoreSpawnX", getX());
            spawnY = input.getDoubleOr("CastlecoreSpawnY", getY());
            spawnZ = input.getDoubleOr("CastlecoreSpawnZ", getZ());
        }
        String target = input.getStringOr("CastlecoreActionTarget", "");
        if (!target.isBlank()) actionTarget = UUID.fromString(target);
        String savedSummons = input.getStringOr("CastlecoreSummons", "");
        if (!savedSummons.isBlank()) for (String uuid : savedSummons.split(",")) summons.add(UUID.fromString(uuid));
    }

    public int getPhase() { return entityData.get(PHASE); }
    public String castlecoreAction() { return entityData.get(ACTION); }
    public long castlecoreActionStartTick() { return entityData.get(ACTION_START); }
}
