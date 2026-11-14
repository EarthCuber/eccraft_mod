package com.eccraft.mixin;

import com.eccraft.effect.ModEffects;
import com.eccraft.entity.IcewitchStrayEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Arrow.class)
public abstract class IcewitchArrowMixin {
    @Inject(method = "doPostHurtEffects", at = @At("TAIL"))
    private void eccraft$coldArrow(LivingEntity target, CallbackInfo ci) {
        Arrow arrow = (Arrow) (Object) this;
        if (target instanceof Player && arrow.getOwner() instanceof IcewitchStrayEntity) {
            target.addEffect(new MobEffectInstance(ModEffects.COLDNESS, 640, 0), arrow.getOwner());
        }
    }
}
