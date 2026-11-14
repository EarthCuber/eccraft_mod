package com.eccraft.mixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
/** Raise the format/container ceiling only; individual items retain their registered stack limits. */
@Mixin(net.minecraft.world.item.ItemStack.class)
public abstract class StackCountLimitMixin {
    @ModifyConstant(method="lambda$static$1",constant=@Constant(intValue=99),require=1,allow=1)
    private static int eccraft$stackLimit256(int original){return 256;}
}
