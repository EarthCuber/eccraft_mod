package com.eccraft.mixin;

import com.eccraft.staff.StaffSystem;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class StaffHeldSlotMixin {
    @Shadow public ServerPlayer player;
    @Inject(method = "handleSetCarriedItem", at = @At("RETURN"))
    private void staffSlotChanged(ServerboundSetCarriedItemPacket packet, CallbackInfo ci) {
        // Observe every hotbar switch even if several packets arrive in one server tick.
        StaffSystem.refresh(player);
        com.eccraft.combat.CombatSkills.observe(player);
    }
}
