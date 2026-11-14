package com.eccraft.mixin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class BossEffectImmunityMixin {
    @Unique private static final TagKey<EntityType<?>> eccraft$bosses = TagKey.create(
            Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("eccraft", "bosses"));
    @Unique private static final TagKey<MobEffect> eccraft$immuneEffects = TagKey.create(
            Registries.MOB_EFFECT, Identifier.fromNamespaceAndPath("eccraft", "boss_immune_effects"));

    @Inject(method = "canBeAffected", at = @At("HEAD"), cancellable = true)
    private void eccraft$rejectTaggedEffect(MobEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.is(eccraft$bosses) && effect.getEffect().is(eccraft$immuneEffects)) {
            cir.setReturnValue(false);
        }
    }
}
