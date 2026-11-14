package com.eccraft.mixin;

import com.eccraft.techtainark.TechtainArkSummons;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class TechtainArkSummonMixin {
    @Shadow protected abstract void dropExperience(ServerLevel level,Entity killer);
    @Inject(method="dropAllDeathLoot",at=@At("HEAD"),cancellable=true)
    private void eccraft$techtain_arkNoItems(ServerLevel level,DamageSource source,CallbackInfo ci){
        if(TechtainArkSummons.isSummon((LivingEntity)(Object)this)){
            // Bound summons drop neither items nor experience.
            ci.cancel();
        }
    }
    @Inject(method="tick",at=@At("HEAD"))
    private void eccraft$techtain_arkOwner(CallbackInfo ci){TechtainArkSummons.checkOwner((LivingEntity)(Object)this);}
}
