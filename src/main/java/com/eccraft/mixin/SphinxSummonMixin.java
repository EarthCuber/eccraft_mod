package com.eccraft.mixin;

import com.eccraft.sphinx.SphinxSummons;
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
public abstract class SphinxSummonMixin {
    @Shadow protected abstract void dropExperience(ServerLevel level,Entity killer);
    @Inject(method="dropAllDeathLoot",at=@At("HEAD"),cancellable=true)
    private void eccraft$sphinxNoItems(ServerLevel level,DamageSource source,CallbackInfo ci){
        if(SphinxSummons.isSummon((LivingEntity)(Object)this)){
            // Preserve the native experience conditions, but bypass all item and equipment drops.
            dropExperience(level,source.getEntity());ci.cancel();
        }
    }
    @Inject(method="tick",at=@At("HEAD"))
    private void eccraft$sphinxOwner(CallbackInfo ci){SphinxSummons.checkOwner((LivingEntity)(Object)this);}
}
