package com.eccraft.mixin;

import com.eccraft.darkdragon.DarkDragonSummons;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class DarkDragonSummonMixin {
    /** This encompasses loot tables, equipment and experience, including player-supplied equipment. */
    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"), cancellable = true)
    private void eccraft$darkdragonNoDrops(ServerLevel level, DamageSource source, CallbackInfo ci) {
        if (DarkDragonSummons.isSummon((LivingEntity) (Object) this)) ci.cancel();
    }
    @Inject(method = "tick", at = @At("HEAD"))
    private void eccraft$darkdragonOwner(CallbackInfo ci) {
        DarkDragonSummons.checkOwner((LivingEntity) (Object) this);
    }
}
