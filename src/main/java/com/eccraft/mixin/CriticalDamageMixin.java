package com.eccraft.mixin;
import com.eccraft.combat.*;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
@Mixin(LivingEntity.class)
public abstract class CriticalDamageMixin {
 @WrapMethod(method="hurtServer")
 private boolean eccraft$damage(ServerLevel world,DamageSource source,float amount,Operation<Boolean> original){
  LivingEntity target=(LivingEntity)(Object)this;
  boolean crit=CriticalCombat.critical(source),visual=crit||CriticalCombat.vanillaCritical(source);
  float before=target.getHealth(),absorption=target.getAbsorptionAmount();
  boolean result=original.call(world,source,crit?(float)Math.min(Float.MAX_VALUE,(double)amount*2):amount);
  double dealt=Math.max(0,before-target.getHealth())+Math.max(0,absorption-target.getAbsorptionAmount());
  CombatFeedback.show(source.getEntity(),target,dealt,visual?1:0);return result;
 }
}
