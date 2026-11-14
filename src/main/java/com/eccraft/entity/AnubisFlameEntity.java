package com.eccraft.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import java.util.UUID;

public class AnubisFlameEntity extends Entity {
    public static final float DAMAGE = 1.0F;
    public static final double DAMAGE_RADIUS = 1.0;
    public static final float FIRE_SECONDS = 2.0F;
    public static final double FALL_ACCELERATION = 0.05;
    public static final int LIFETIME_TICKS = 600;
    public static final int DAMAGE_INTERVAL_TICKS = 12;
    private int age;
    private UUID ownerUuid;

    public AnubisFlameEntity(EntityType<? extends AnubisFlameEntity> type, Level level) { super(type, level); }
    public void setOwnerUuid(UUID ownerUuid) { this.ownerUuid = ownerUuid; }

    @Override public void tick() {
        super.tick();
        if (!level().isClientSide() && ++age >= LIFETIME_TICKS) { discard(); return; }
        if (!onGround()) {
            setDeltaMovement(getDeltaMovement().add(0.0, -FALL_ACCELERATION, 0.0));
            move(MoverType.SELF, getDeltaMovement());
        } else setDeltaMovement(Vec3.ZERO);

        if (level().isClientSide()) {
            level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX(), getY() + 0.15, getZ(), 0.0, 0.015, 0.0);
            level().addParticle(ParticleTypes.SMOKE, getX(), getY() + 0.35, getZ(), 0.0, 0.02, 0.0);
            return;
        }
        ServerLevel serverLevel = (ServerLevel) level();
        Entity owner = ownerUuid == null ? null : serverLevel.getEntityInAnyDimension(ownerUuid);
        if (tickCount > 20 && owner == null) {
            discard();
            return;
        }
        double damageRadiusSqr = DAMAGE_RADIUS * DAMAGE_RADIUS;
        for (ServerPlayer player : serverLevel.getPlayers(p -> AnubisEntity.isCombatPlayer(p) && distanceToSqr(p) <= damageRadiusSqr)) {
            var magic = serverLevel.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                    .getOrThrow(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.DAMAGE_TYPE, com.eccraft.Eccraft.id("magic")));
            DamageSource damageSource = new DamageSource(magic, this, owner);
            if (age % DAMAGE_INTERVAL_TICKS == 0) player.hurtServer(serverLevel, damageSource, DAMAGE);
            player.igniteForSeconds(FIRE_SECONDS);
        }
    }

    @Override protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {}

    /** This is a spell marker rather than an attackable living target. */
    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount) { return false; }

    @Override protected void readAdditionalSaveData(ValueInput input) {
        age = Math.clamp(input.getIntOr("FireAge", 0), 0, LIFETIME_TICKS);
        String value = input.getStringOr("AnubisOwner", "");
        if (!value.isBlank()) ownerUuid = UUID.fromString(value);
    }
    @Override protected void addAdditionalSaveData(ValueOutput output) {
        output.putInt("FireAge", age);
        if (ownerUuid != null) output.putString("AnubisOwner", ownerUuid.toString());
    }
    @Override public boolean isPickable() { return false; }
    @Override public boolean isPushable() { return false; }
}
