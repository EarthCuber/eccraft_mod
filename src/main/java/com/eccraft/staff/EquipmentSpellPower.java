package com.eccraft.staff;

import com.eccraft.Eccraft;
import com.eccraft.armor.MageArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

public final class EquipmentSpellPower {
    public static EquipmentSlot slot(ItemStack stack) {
        if(stack.getItem() instanceof StaffTomeItem)return EquipmentSlot.OFFHAND;
        return stack.getItem() instanceof MageArmorItem armor?armor.slot():null;
    }
    public static AttributeModifier modifier(ItemStack stack) {
        var slot=slot(stack); if(slot==null)return null;
        int rank=0;
        for(var entry:stack.getEnchantments().entrySet())if(entry.getKey().is(StaffEnchantments.SPELL))rank=entry.getIntValue();
        double bonus=StaffEnchantments.spellBonus(rank); if(bonus==0)return null;
        return new AttributeModifier(Eccraft.id("equipment_spell_"+slot.getName()),bonus,AttributeModifier.Operation.ADD_VALUE);
    }
    private EquipmentSpellPower() {}
}
