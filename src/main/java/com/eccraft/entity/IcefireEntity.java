package com.eccraft.entity;

import java.util.UUID;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class IcefireEntity extends Entity {
    public static final int LIFETIME_TICKS = 240;
    public static final int DAMAGE_INTERVAL = 5;
    public static final float DAMAGE = 2;
    public static final double DAMAGE_RADIUS = 1;
    public static final ResourceKey<DamageType> FIRE = ResourceKey.create(
            Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("eccraft", "fire"));
    private UUID ownerUuid;
    private int age;
    public IcefireEntity(EntityType<? extends IcefireEntity> type, Level level) { super(type, level); }
    public void setOwnerUuid(UUID owner) { ownerUuid = owner; }
    @Override public void tick() {
        super.tick();
        ++age;
        if (level() instanceof ServerLevel server) {
            if (age >= LIFETIME_TICKS) { discard(); return; }
            if (ownerUuid != null) {
                Entity owner = server.getEntityInAnyDimension(ownerUuid);
                if (!(owner instanceof IcewitchEntity witch) || !witch.isAlive()) { discard(); return; }
            }
        }
        if (!onGround()) {
            setDeltaMovement(getDeltaMovement().add(0, -0.05, 0));
            move(MoverType.SELF, getDeltaMovement());
        } else setDeltaMovement(Vec3.ZERO);
        if (level() instanceof ServerLevel server && age % DAMAGE_INTERVAL == 0) {
            // Environmental source preserves in_fire difficulty behavior: 2 damage on all difficulties.
            DamageSource source = new DamageSource(server.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(FIRE));
            for (var player : server.getPlayers(p -> IcewitchEntity.isCombatPlayer(p)
                    && distanceToSqr(p) <= DAMAGE_RADIUS * DAMAGE_RADIUS)) {
                player.hurtServer(server, source, DAMAGE);
            }
        }
    }
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {}
    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount) { return false; }
    @Override public boolean isPickable() { return false; }
    @Override public boolean isPushable() { return false; }
    @Override protected void addAdditionalSaveData(ValueOutput output) {
        output.putInt("IcefireAge", age);
        if (ownerUuid != null) output.putString("IcewitchOwner", ownerUuid.toString());
    }
    @Override protected void readAdditionalSaveData(ValueInput input) {
        age = input.getIntOr("IcefireAge", 0);
        String owner = input.getStringOr("IcewitchOwner", "");
        if (!owner.isBlank()) ownerUuid = UUID.fromString(owner);
    }
}
