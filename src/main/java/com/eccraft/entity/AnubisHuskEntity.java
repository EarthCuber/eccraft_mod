package com.eccraft.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import java.util.UUID;

public class AnubisHuskEntity extends Husk {
    public static final double MAX_HEALTH = 50.0;
    private UUID ownerUuid;
    public AnubisHuskEntity(EntityType<? extends AnubisHuskEntity> type, Level level) { super(type, level); }
    public static AttributeSupplier.Builder createAttributes() { return Zombie.createAttributes().add(Attributes.MAX_HEALTH, MAX_HEALTH); }
    public void setOwnerUuid(UUID ownerUuid) { this.ownerUuid = ownerUuid; }

    @Override public void tick() {
        super.tick();
        if (!level().isClientSide() && tickCount > 40 && tickCount % 20 == 0) {
            ServerLevel serverLevel = (ServerLevel) level();
            if (ownerUuid == null || serverLevel.getEntityInAnyDimension(ownerUuid) == null) discard();
        }
    }
    @Override protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        if (ownerUuid != null) output.putString("AnubisOwner", ownerUuid.toString());
    }
    @Override protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        String value = input.getStringOr("AnubisOwner", "");
        if (!value.isBlank()) ownerUuid = UUID.fromString(value);
    }
}
