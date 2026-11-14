package com.eccraft.mixin;

import com.eccraft.effect.ModEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FireColdnessMixin {
    private static final ResourceKey<DamageType> ECCRAFT_FIRE = ResourceKey.create(
            Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("eccraft", "fire"));

    @Redirect(method = "hurtServer", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z"))
    private boolean eccraft$fireIgnoresFireResistance(LivingEntity entity,
            net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> effect,
            ServerLevel level, DamageSource source, float amount) {
        if (source.is(ECCRAFT_FIRE) && effect.equals(net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE)) {
            return false;
        }
        return entity.hasEffect(effect);
    }

    @Inject(method = "hurtServer", at = @At("RETURN"))
    private void eccraft$warmOnSuccessfulHit(ServerLevel level, DamageSource source, float amount,
                                             CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player) || !cir.getReturnValueZ() || amount <= 0 || !source.is(ECCRAFT_FIRE)) return;
        MobEffectInstance current = entity.getEffect(ModEffects.COLDNESS);
        if (current == null) return;
        if (current.getAmplifier() == 0) entity.removeEffect(ModEffects.COLDNESS);
        else entity.forceAddEffect(new MobEffectInstance(ModEffects.COLDNESS, current.getDuration(),
                current.getAmplifier() - 1, current.isAmbient(), current.isVisible(), current.showIcon()), null);
    }
}
