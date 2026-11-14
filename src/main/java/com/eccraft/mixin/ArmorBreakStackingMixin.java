package com.eccraft.mixin;

import com.eccraft.effect.ArmorBreakEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class ArmorBreakStackingMixin {
    @ModifyVariable(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",
            at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private MobEffectInstance eccraft$stackArmorBreak(MobEffectInstance incoming) {
        if (!incoming.is(ArmorBreakEffect.ARMOR_BREAK)) return incoming;
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!entity.canBeAffected(incoming)) return incoming;
        var previous = entity.getEffect(ArmorBreakEffect.ARMOR_BREAK);
        int level = Math.min(ArmorBreakEffect.MAX_LEVEL,
                incoming.getAmplifier() + 1 + (previous == null ? 0 : previous.getAmplifier() + 1));
        if (previous != null) entity.removeEffect(ArmorBreakEffect.ARMOR_BREAK);
        return new MobEffectInstance(ArmorBreakEffect.ARMOR_BREAK, incoming.getDuration(), level - 1,
                incoming.isAmbient(), incoming.isVisible(), incoming.showIcon());
    }
}
