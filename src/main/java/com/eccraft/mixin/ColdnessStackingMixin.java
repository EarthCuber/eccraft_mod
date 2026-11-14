package com.eccraft.mixin;

import com.eccraft.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class ColdnessStackingMixin {
    @ModifyVariable(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",
            at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private MobEffectInstance eccraft$stackColdness(MobEffectInstance incoming) {
        if (!incoming.is(ModEffects.COLDNESS)) return incoming;
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!entity.canBeAffected(incoming)) return incoming;
        MobEffectInstance current = entity.getEffect(ModEffects.COLDNESS);
        int level = Math.min(20, incoming.getAmplifier() + 1
                + (current == null ? 0 : current.getAmplifier() + 1));
        // Remove the old instance and hidden chain so even shorter durations replace it.
        if (current != null) entity.removeEffect(ModEffects.COLDNESS);
        return new MobEffectInstance(ModEffects.COLDNESS, incoming.getDuration(), level - 1,
                incoming.isAmbient(), incoming.isVisible(), incoming.showIcon());
    }
}
