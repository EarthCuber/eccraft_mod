package com.eccraft.mixin;

import java.util.Arrays;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class ArmorDurabilityTagMixin {
    @Unique private static final TagKey<DamageType> eccraft$noArmorWear = TagKey.create(
            Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("eccraft", "no_armor_durability_loss"));

    @ModifyVariable(method = "doHurtEquipment", at = @At("HEAD"), argsOnly = true)
    private EquipmentSlot[] eccraft$protectArmor(EquipmentSlot[] slots, DamageSource source,
                                                float amount, EquipmentSlot[] original) {
        if (!source.is(eccraft$noArmorWear)) return slots;
        return Arrays.stream(slots).filter(slot -> slot == EquipmentSlot.MAINHAND
                || slot == EquipmentSlot.OFFHAND).toArray(EquipmentSlot[]::new);
    }
}
