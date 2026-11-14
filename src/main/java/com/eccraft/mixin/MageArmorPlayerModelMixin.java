package com.eccraft.mixin;

import com.eccraft.armor.MageArmorItem;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public abstract class MageArmorPlayerModelMixin {
    @Inject(method="setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V",at=@At("TAIL"))
    private void eccraft$replaceT2Arms(AvatarRenderState state,CallbackInfo ci) {
        com.eccraft.armor.client.MageArmorT2Arms.hidePlayerArms((PlayerModel)(Object)this,state.chestEquipment);
        // Vanilla setupAnim resets visibility on every call, including after removing the chestplate.
    }
}
