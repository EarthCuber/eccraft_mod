package com.eccraft.mixin;

import com.eccraft.staff.StaffAttributes;
import com.eccraft.staff.StaffSystem;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class StaffLivingEntityMixin {
    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void staffAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(StaffAttributes.SPELL_POWER).add(StaffAttributes.MAGIC_RESISTANCE).add(StaffAttributes.RANGED_DAMAGE);
    }
    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void magicDefense(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        if (!source.is(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.DAMAGE_TYPE,
                com.eccraft.Eccraft.id("magic")))) return;
        LivingEntity target = (LivingEntity)(Object)this;
        float resistance = (float)com.eccraft.staff.StaffMobResistance.effective(target);
        cir.setReturnValue(CombatRules.getDamageAfterAbsorb(target, amount, source, resistance,
                (float)target.getAttributeValue(Attributes.ARMOR_TOUGHNESS)));
    }
}
