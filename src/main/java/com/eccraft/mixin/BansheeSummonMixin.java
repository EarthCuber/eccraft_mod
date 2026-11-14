package com.eccraft.mixin;

import com.eccraft.banshee.BansheeSummons;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class BansheeSummonMixin {
    /** This encompasses loot tables, equipment and experience, including player-supplied equipment. */
    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"), cancellable = true)
    private void eccraft$bansheeNoDrops(ServerLevel level, DamageSource source, CallbackInfo ci) {
        if (BansheeSummons.isSummon((LivingEntity) (Object) this)) ci.cancel();
    }
    @Inject(method = "tick", at = @At("HEAD"))
    private void eccraft$bansheeOwner(CallbackInfo ci) {
        BansheeSummons.checkOwner((LivingEntity) (Object) this);
    }
}
