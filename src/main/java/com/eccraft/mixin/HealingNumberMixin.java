package com.eccraft.mixin;
import com.eccraft.combat.CombatFeedback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
@Mixin(targets="net.minecraft.world.effect.HealOrHarmMobEffect")
public abstract class HealingNumberMixin {
 @Redirect(method="applyInstantaneousEffect",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
 private void eccraft$heal(LivingEntity target,float amount,ServerLevel world,Entity direct,Entity owner,LivingEntity victim,int amplifier,double proximity){CombatFeedback.heal(owner,target,amount);}
 @Redirect(method="applyEffectTick",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
 private void eccraft$selfHeal(LivingEntity target,float amount){CombatFeedback.heal(target,target,amount);}
}
