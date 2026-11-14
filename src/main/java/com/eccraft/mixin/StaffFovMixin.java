package com.eccraft.mixin;

import com.eccraft.staff.StaffItem;
import net.minecraft.client.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class StaffFovMixin {
    @Inject(method = "getFieldOfViewModifier", at = @At("RETURN"), cancellable = true)
    private void staffFov(boolean firstPerson, float scale, CallbackInfoReturnable<Float> cir) {
        AbstractClientPlayer player = (AbstractClientPlayer)(Object)this;
        if (player.isUsingItem() && player.getUseItem().getItem() instanceof StaffItem) {
            float charge = Math.min(player.getTicksUsingItem() / (float)StaffItem.chargeTicks(), 1);
            cir.setReturnValue(cir.getReturnValue() - (cir.getReturnValue() - 1 + scale) * charge * charge * 0.15F);
        }
    }
}
