package com.eccraft.entity;

import com.eccraft.Eccraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/** Constant-speed swept collision; passes through its owner, with no other faction filtering. */
public final class BansheeProjectile extends Projectile {
    private float damage = 12.0F;
    private int age;
    private int lifetime = 100;
    public BansheeProjectile(EntityType<? extends BansheeProjectile> type, Level level) { super(type, level); setNoGravity(true); }
    public void configure(float amount, int ticks) { damage = amount; lifetime = ticks; }
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) { }
    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount) { return false; }
    @Override protected boolean canHitEntity(Entity entity) { return !ownedBy(entity) && entity.canBeHitByProjectile(); }
    @Override public void tick() {
        super.tick();
        if (isRemoved()) return;
        if (level() instanceof ServerLevel server) {
            HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hit.getType() != HitResult.Type.MISS) {
                setPos(hit.getLocation());
                if (hit instanceof EntityHitResult entityHit) {
                    var magic = server.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
                            .getOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, Eccraft.id("magic")));
                    entityHit.getEntity().hurtServer(server, new DamageSource(magic, this, getOwner()), damage);
                }
                discard();
                return;
            }
        }
        setPos(position().add(getDeltaMovement()));
        updateRotation();
        if (++age >= lifetime && !level().isClientSide()) discard();
    }
    @Override protected void addAdditionalSaveData(ValueOutput out) {
        super.addAdditionalSaveData(out);
        out.putFloat("BansheeDamage", damage); out.putInt("BansheeAge", age); out.putInt("BansheeLifetime", lifetime);
    }
    @Override protected void readAdditionalSaveData(ValueInput in) {
        super.readAdditionalSaveData(in);
        damage = in.getFloatOr("BansheeDamage", 12.0F); age = in.getIntOr("BansheeAge", 0); lifetime = in.getIntOr("BansheeLifetime", 100);
    }
}
