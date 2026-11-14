package com.eccraft.mixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
/** Raise the format/container ceiling only; individual items retain their registered stack limits. */
@Mixin(net.minecraft.world.Container.class)
public interface ContainerStackLimitMixin {
    @ModifyConstant(method="getMaxStackSize()I",constant=@Constant(intValue=99),require=1,allow=1)
    default int eccraft$stackLimit256(int original){return 256;}
}
