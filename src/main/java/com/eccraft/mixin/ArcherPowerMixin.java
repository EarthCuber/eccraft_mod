package com.eccraft.mixin;

import com.eccraft.staff.StaffAttributes;
import com.eccraft.archer.ArcherPower;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.core.Holder;
import java.util.function.BiConsumer;
import org.apache.commons.lang3.function.TriConsumer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ArcherPowerMixin {
    @Inject(method="forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V",at=@At("TAIL"))
    private void eccraft$rangedBonus(EquipmentSlot slot,BiConsumer<Holder<Attribute>,AttributeModifier> output,CallbackInfo ci) {
        ItemStack stack=(ItemStack)(Object)this;
        if(ArcherPower.slot(stack)!=slot)return;
        var modifier=ArcherPower.modifier(stack);if(modifier!=null)output.accept(StaffAttributes.RANGED_DAMAGE,modifier);
    }
    @Inject(method="forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V",at=@At("TAIL"))
    private void eccraft$rangedTooltip(EquipmentSlotGroup group,TriConsumer<Holder<Attribute>,AttributeModifier,ItemAttributeModifiers.Display> output,CallbackInfo ci) {
        ItemStack stack=(ItemStack)(Object)this;var slot=ArcherPower.slot(stack);
        if(slot==null || group!=EquipmentSlotGroup.bySlot(slot))return;
        var modifier=ArcherPower.modifier(stack);
        if(modifier!=null)output.accept(StaffAttributes.RANGED_DAMAGE,modifier,ItemAttributeModifiers.Display.attributeModifiers());
    }
}
