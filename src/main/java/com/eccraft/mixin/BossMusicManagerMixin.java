package com.eccraft.mixin;

import com.eccraft.client.music.BossBattleMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public abstract class BossMusicManagerMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void eccraft$battleMusic(CallbackInfo ci) {
        if (BossBattleMusic.tick(Minecraft.getInstance())) {
            ((MusicManager) (Object) this).stopPlaying();
            ci.cancel();
        }
    }
}
