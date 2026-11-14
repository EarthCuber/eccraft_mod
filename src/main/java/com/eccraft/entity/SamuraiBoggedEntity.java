package com.eccraft.entity;

import java.util.UUID;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/** Vanilla Bogged AI, poison arrows, equipment and sounds with a Boss owner and 20 HP. */
public final class SamuraiBoggedEntity extends Bogged {
    private UUID ownerUuid;
    public SamuraiBoggedEntity(EntityType<? extends SamuraiBoggedEntity> type, Level level) { super(type, level); }
    public static AttributeSupplier.Builder createAttributes() {
        return Bogged.createAttributes().add(Attributes.MAX_HEALTH, 20.0);
    }
    @Override protected void dropCustomDeathLoot(ServerLevel level, net.minecraft.world.damagesource.DamageSource source, boolean recentlyHit) {
        // Normal death still evaluates eccraft:entities/samurai_bogged. Equipment is separate; suppress it.
    }
    public void setOwnerUuid(UUID owner) { ownerUuid = owner; }
    @Override public void tick() {
        super.tick();
        if (level() instanceof ServerLevel server && ownerUuid != null && tickCount % 20 == 0) {
            var owner = server.getEntityInAnyDimension(ownerUuid);
            if (!(owner instanceof SamuraiEntity samurai) || !samurai.isAlive()) discard();
        }
    }
    @Override protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        if (ownerUuid != null) output.putString("SamuraiOwner", ownerUuid.toString());
    }
    @Override protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        String owner = input.getStringOr("SamuraiOwner", "");
        if (!owner.isBlank()) ownerUuid = UUID.fromString(owner);
    }
}
