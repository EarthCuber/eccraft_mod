package com.eccraft.mixin;
import com.eccraft.effect.FirelordLavaOutEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
@Mixin(LivingEntity.class)
public abstract class FirelordLavaEffectMixin {
 @ModifyVariable(method="addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",at=@At("HEAD"),argsOnly=true,ordinal=0)
 private MobEffectInstance eccraft$singleLava(MobEffectInstance incoming){
  if(!incoming.is(FirelordLavaOutEffect.EFFECT))return incoming;
  var entity=(LivingEntity)(Object)this;if(!entity.canBeAffected(incoming))return incoming;
  entity.removeEffect(FirelordLavaOutEffect.EFFECT);
  return new MobEffectInstance(FirelordLavaOutEffect.EFFECT,incoming.getDuration(),0,incoming.isAmbient(),incoming.isVisible(),incoming.showIcon());
 }
}
