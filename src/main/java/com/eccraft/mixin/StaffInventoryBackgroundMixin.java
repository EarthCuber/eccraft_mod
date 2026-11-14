package com.eccraft.mixin;

import com.eccraft.staff.client.StaffInventoryBackground;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public abstract class StaffInventoryBackgroundMixin {
    // All normal/fake GUI item overloads converge here, so draw exactly once per item.
    @Inject(method = "item(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V", at = @At("HEAD"))
    private void eccraft$staffBackground(LivingEntity entity, Level level, ItemStack stack,
                                         int x, int y, int seed, CallbackInfo ci) {
        StaffInventoryBackground.draw((GuiGraphicsExtractor)(Object)this, stack, x, y);
    }
}
