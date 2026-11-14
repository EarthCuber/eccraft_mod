package com.eccraft.mixin;
import com.eccraft.knight.ShieldWall;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ShieldWallMixin {
    @Inject(method="getDamageAfterMagicAbsorb",at=@At("RETURN"),cancellable=true)
    private void eccraft$wall(DamageSource source,float amount,CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(ShieldWall.damage((LivingEntity)(Object)this,source,cir.getReturnValue()));
    }
}
