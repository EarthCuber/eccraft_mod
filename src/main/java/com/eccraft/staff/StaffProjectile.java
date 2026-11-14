package com.eccraft.staff;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

public class StaffProjectile extends ThrowableItemProjectile {
    private static final EntityDataAccessor<Integer> ROUTE = SynchedEntityData.defineId(StaffProjectile.class, EntityDataSerializers.INT);
    private StaffRoute route = StaffRoute.NORMAL;
    private float amount;
    private int slowTicks, slowAmplifier, lifetime = 200, age;
    private int trailTicks;
    private boolean bossFrostShot;
    private boolean ownerSafeBossShot;
    private java.util.UUID cleanupBoss;
    private static final double TRAIL_SPREAD = 0.15;
    public StaffProjectile(EntityType<? extends StaffProjectile> type, Level level) { super(type, level); }
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ROUTE, 0);
    }
    public StaffRoute visualRoute() { return StaffRoute.values()[Math.clamp(entityData.get(ROUTE), 0, 2)]; }
    public void configure(StaffRoute route, float amount, StaffProfile profile) {
        bossFrostShot = false;
        ownerSafeBossShot=false;cleanupBoss=null;
        this.route = route; this.amount = amount;
        entityData.set(ROUTE, route.ordinal());
        slowTicks = profile.slowTicks(); slowAmplifier = profile.slowAmplifier(); lifetime = profile.projectileLifetime();
        setItem(new ItemStack(switch(route) { case NORMAL -> Items.AMETHYST_SHARD; case FROST -> Items.SNOWBALL; case HEALING -> Items.GHAST_TEAR; }));
    }
    /** Reuse the normal staff projectile without a player spell-power profile. */
    public void configureBossShot(float damage, int lifetimeTicks) {
        bossFrostShot = false;
        ownerSafeBossShot=false;cleanupBoss=null;
        route = StaffRoute.NORMAL;
        amount = damage;
        lifetime = lifetimeTicks;
        slowTicks = 0;
        slowAmplifier = 0;
        entityData.set(ROUTE, route.ordinal());
        setItem(new ItemStack(Items.AMETHYST_SHARD));
    }
    /** Frost visuals only: no status effect, explicit permanent owner exclusion. */
    public void configureBossFrostShot(float damage, int lifetimeTicks) {
        configureBossShot(damage, lifetimeTicks);
        bossFrostShot = true;
        route = StaffRoute.FROST;
        entityData.set(ROUTE, route.ordinal());
        setItem(new ItemStack(Items.SNOWBALL));
    }
    public void configureOwnerSafeBossShot(float damage,int lifetimeTicks,java.util.UUID ownerBoss) {
        configureBossShot(damage,lifetimeTicks);ownerSafeBossShot=true;cleanupBoss=ownerBoss;
    }
    @Override protected boolean canHitEntity(net.minecraft.world.entity.Entity entity) {
        return (!(bossFrostShot || ownerSafeBossShot) || !ownedBy(entity)) && super.canHitEntity(entity);
    }
    @Override protected Item getDefaultItem() { return Items.AMETHYST_SHARD; }
    @Override protected double getDefaultGravity() { return 0; }
    @Override protected float getAirDrag() { return 1; }
    @Override protected ProjectileDeflection hitTargetOrDeflectSelf(HitResult hit) {
        // All collisions resolve once, including entities which normally deflect snowballs.
        onHit(hit);
        return ProjectileDeflection.NONE;
    }
    @Override public void tick() {
        if(cleanupBoss!=null && level() instanceof ServerLevel server){var boss=server.getEntityInAnyDimension(cleanupBoss);if(boss==null||!boss.isAlive()){discard();return;}}
        double previousX = getX(), previousY = getY(), previousZ = getZ();
        super.tick();
        if (level().isClientSide() && !isRemoved()) {
            ParticleOptions particle = StaffParticles.forRoute(visualRoute());
            // Three particles per four flight ticks, versus the previous three per tick.
            if ((trailTicks++ & 3) != 3) {
                double t = random.nextDouble();
                level().addParticle(particle,
                        previousX + (getX() - previousX) * t + (random.nextDouble() * 2 - 1) * TRAIL_SPREAD,
                        previousY + (getY() - previousY) * t + (random.nextDouble() * 2 - 1) * TRAIL_SPREAD,
                        previousZ + (getZ() - previousZ) * t + (random.nextDouble() * 2 - 1) * TRAIL_SPREAD, 0, 0, 0);
            }
        }
        if (!level().isClientSide() && ++age >= lifetime) discard();
    }
    @Override protected void onHitEntity(EntityHitResult hit) {
        if (!(level() instanceof ServerLevel world)) return;
        if (route == StaffRoute.HEALING) {
            if (hit.getEntity() instanceof Player target && target.isAlive() && !target.isSpectator()) com.eccraft.combat.CombatFeedback.heal(getOwner(),target,amount);
        } else if (hit.getEntity() instanceof LivingEntity target) {
            boolean damaged = target.hurtServer(world, StaffSystem.damageSource(world, this, getOwner()), amount);
            if (damaged && route == StaffRoute.FROST && !bossFrostShot)
                target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, slowTicks, slowAmplifier), getOwner());
        }
    }
    @Override protected void onHit(HitResult hit) {
        super.onHit(hit);
        if (!level().isClientSide()) discard();
    }
    @Override protected void addAdditionalSaveData(ValueOutput out) {
        super.addAdditionalSaveData(out);
        out.putBoolean("StaffOwnerSafe",ownerSafeBossShot);
        if(cleanupBoss!=null)out.putString("StaffCleanupBoss",cleanupBoss.toString());
        out.putBoolean("StaffBossFrost", bossFrostShot);
        out.putInt("StaffRoute", route.ordinal()); out.putFloat("StaffAmount", amount);
        out.putInt("StaffSlowTicks", slowTicks); out.putInt("StaffSlowAmplifier", slowAmplifier);
        out.putInt("StaffLifetime", lifetime); out.putInt("StaffAge", age);
    }
    @Override protected void readAdditionalSaveData(ValueInput in) {
        super.readAdditionalSaveData(in);
        ownerSafeBossShot=in.getBooleanOr("StaffOwnerSafe",false);
        String ownerBoss=in.getStringOr("StaffCleanupBoss","");cleanupBoss=ownerBoss.isBlank()?null:java.util.UUID.fromString(ownerBoss);
        bossFrostShot = in.getBooleanOr("StaffBossFrost", false);
        route = StaffRoute.values()[Math.clamp(in.getIntOr("StaffRoute", 0), 0, 2)];
        entityData.set(ROUTE, route.ordinal());
        amount = in.getFloatOr("StaffAmount", 0); slowTicks = in.getIntOr("StaffSlowTicks", 0);
        slowAmplifier = in.getIntOr("StaffSlowAmplifier", 0);
        lifetime = in.getIntOr("StaffLifetime", 200); age = in.getIntOr("StaffAge", 0);
    }
}
