package com.eccraft.mixin;
import com.eccraft.effect.NightCorrosionEffect;
import com.eccraft.nightgod.NightCorrosion;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(LivingEntity.class)
public abstract class NightCorrosionMixin {
    @Inject(method="heal",at=@At("HEAD"),cancellable=true)
    private void eccraft$corrodeHealing(float amount,CallbackInfo ci){
        var entity=(LivingEntity)(Object)this;
        if(amount>0&&entity.isAlive()&&entity.level() instanceof ServerLevel level&&NightCorrosion.converts(entity)){
            ci.cancel();entity.hurtServer(level,com.eccraft.damage.VoidDamage.source(level),Math.min(Float.MAX_VALUE,amount*10.0F));
        }
    }
    @ModifyVariable(method="addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",at=@At("HEAD"),argsOnly=true,ordinal=0)
    private MobEffectInstance eccraft$refreshCorrosion(MobEffectInstance effect){
        var entity=(LivingEntity)(Object)this;
        if(!effect.is(NightCorrosionEffect.NIGHT_CORROSION)||!entity.canBeAffected(effect))return effect;
        entity.removeEffect(NightCorrosionEffect.NIGHT_CORROSION);
        return new MobEffectInstance(NightCorrosionEffect.NIGHT_CORROSION,effect.getDuration(),0,effect.isAmbient(),effect.isVisible(),effect.showIcon());
    }
}
