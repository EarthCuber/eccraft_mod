package com.eccraft.mixin;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Health is stored as a float: use its finite maximum instead of the vanilla 1024 cap. */
@Mixin(RangedAttribute.class)
public abstract class MaxHealthLimitMixin {
    @Shadow @Final @Mutable private double maxValue;
    @Inject(method="<init>", at=@At("RETURN"))
    private void eccraft$expandMaxHealth(String descriptionId, double defaultValue, double minValue, double maximum, CallbackInfo ci) {
        if ("attribute.name.max_health".equals(descriptionId)) maxValue = Float.MAX_VALUE;
    }
}
