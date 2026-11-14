package com.eccraft.mixin;

import com.eccraft.castlecore.ArmorRendDamage;
import com.eccraft.effect.ArmorRendEffect;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntity.class)
public abstract class ArmorRendLivingMixin {
    @Inject(method="actuallyHurt",at=@At("HEAD"),cancellable=true)
    private void eccraft$rendHealth(ServerLevel level,DamageSource source,float damage,CallbackInfo ci){
        var target=(LivingEntity)(Object)this;
        if(ArmorRendDamage.active(target)){ArmorRendDamage.apply(level,target,source,damage);ci.cancel();}
    }
    @Inject(method="applyItemBlocking",at=@At("HEAD"),cancellable=true)
    private void eccraft$rendShield(ServerLevel level,DamageSource source,float damage,CallbackInfoReturnable<Float> cir){
        if(ArmorRendDamage.active((LivingEntity)(Object)this))cir.setReturnValue(0F);
    }
    @WrapOperation(method="hurtServer",at=@At(value="INVOKE",target="Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean eccraft$rendHelmet(DamageSource source,TagKey<DamageType> tag,Operation<Boolean> original){
        if(tag==DamageTypeTags.DAMAGES_HELMET&&ArmorRendDamage.active((LivingEntity)(Object)this))return false;
        return original.call(source,tag);
    }
    @ModifyVariable(method="addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",at=@At("HEAD"),argsOnly=true,ordinal=0)
    private MobEffectInstance eccraft$refreshRend(MobEffectInstance incoming){
        if(!incoming.is(ArmorRendEffect.ARMOR_REND))return incoming;
        var target=(LivingEntity)(Object)this;
        if(!target.canBeAffected(incoming))return incoming;
        target.removeEffect(ArmorRendEffect.ARMOR_REND);
        return new MobEffectInstance(ArmorRendEffect.ARMOR_REND,incoming.getDuration(),0,incoming.isAmbient(),incoming.isVisible(),incoming.showIcon());
    }
}
