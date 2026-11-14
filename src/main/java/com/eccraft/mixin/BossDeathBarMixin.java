package com.eccraft.mixin;

import com.eccraft.entity.AnubisEntity;
import com.eccraft.entity.IcewitchEntity;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({AnubisEntity.class, IcewitchEntity.class})
public abstract class BossDeathBarMixin {
    @Shadow(remap = false) @Final private ServerBossEvent bossEvent;
    @Inject(method = "die", at = @At("HEAD"))
    private void eccraft$emptyDeathBar(DamageSource source, CallbackInfo ci) {
        bossEvent.setProgress(0.0F);
    }
}
