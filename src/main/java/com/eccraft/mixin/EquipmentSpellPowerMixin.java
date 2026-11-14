package com.eccraft.mixin;

import com.eccraft.staff.*;
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
public abstract class EquipmentSpellPowerMixin {
    @Inject(method="forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V",at=@At("TAIL"))
    private void eccraft$spellBonus(EquipmentSlot slot,BiConsumer<Holder<Attribute>,AttributeModifier> output,CallbackInfo ci) {
        ItemStack stack=(ItemStack)(Object)this;
        if(EquipmentSpellPower.slot(stack)!=slot)return;
        var modifier=EquipmentSpellPower.modifier(stack);if(modifier!=null)output.accept(StaffAttributes.SPELL_POWER,modifier);
    }
    @Inject(method="forEachModifier(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V",at=@At("TAIL"))
    private void eccraft$spellTooltip(EquipmentSlotGroup group,TriConsumer<Holder<Attribute>,AttributeModifier,ItemAttributeModifiers.Display> output,CallbackInfo ci) {
        ItemStack stack=(ItemStack)(Object)this;var slot=EquipmentSpellPower.slot(stack);
        if(slot==null || group!=EquipmentSlotGroup.bySlot(slot))return;
        var modifier=EquipmentSpellPower.modifier(stack);
        if(modifier!=null)output.accept(StaffAttributes.SPELL_POWER,modifier,ItemAttributeModifiers.Display.attributeModifiers());
    }
}
