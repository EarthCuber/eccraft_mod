package com.eccraft.entity;

import java.util.UUID;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class IcewitchStrayEntity extends Stray {
    private UUID ownerUuid;
    public IcewitchStrayEntity(EntityType<? extends IcewitchStrayEntity> type, Level level) { super(type, level); }
    public static AttributeSupplier.Builder createAttributes() { return Stray.createAttributes().add(Attributes.MAX_HEALTH, 25); }
    @Override public net.minecraft.world.entity.SpawnGroupData finalizeSpawn(
            net.minecraft.world.level.ServerLevelAccessor level, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.world.entity.EntitySpawnReason reason, net.minecraft.world.entity.SpawnGroupData group) {
        var result = super.finalizeSpawn(level, difficulty, reason, group);
        ItemStack bow = getMainHandItem();
        if (bow.is(net.minecraft.world.item.Items.BOW)) {
            bow.set(net.minecraft.core.component.DataComponents.MAX_DAMAGE, 5);
            bow.setDamageValue(0);
        }
        for (var slot : net.minecraft.world.entity.EquipmentSlot.values()) setDropChance(slot, 0.0F);
        return result;
    }

    @Override protected void dropCustomDeathLoot(ServerLevel level,
            net.minecraft.world.damagesource.DamageSource source, boolean recentlyHit) {
        // Equipment drops are separate from the entity loot table; suppress them too.
        // The normal death pipeline still evaluates eccraft:entities/icewitch_stray.
    }
    public void setOwnerUuid(UUID owner) { ownerUuid = owner; }
    @Override protected AbstractArrow getArrow(ItemStack arrow, float power, ItemStack weapon) {
        // Bypass Stray.getArrow: it unconditionally adds Slowness.
        return ProjectileUtil.getMobArrow(this, arrow, power, weapon);
    }
    @Override public void tick() {
        super.tick();
        if (level() instanceof ServerLevel server && ownerUuid != null && tickCount % 20 == 0) {
            var owner = server.getEntityInAnyDimension(ownerUuid);
            if (!(owner instanceof IcewitchEntity witch) || !witch.isAlive()) discard();
        }
    }
    @Override protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        if (ownerUuid != null) output.putString("IcewitchOwner", ownerUuid.toString());
    }
    @Override protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        String owner = input.getStringOr("IcewitchOwner", "");
        if (!owner.isBlank()) ownerUuid = UUID.fromString(owner);
    }
}
