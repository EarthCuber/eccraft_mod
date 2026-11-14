package com.eccraft.mixin;

import com.eccraft.staff.client.StaffClient;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class StaffKeyMixin {
    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void staffKeys(CallbackInfo ci) { StaffClient.handleKeys((Minecraft)(Object)this); }
}
