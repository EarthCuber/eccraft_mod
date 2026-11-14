package com.eccraft.archer;
import com.eccraft.Eccraft;
import com.eccraft.armor.ArcherArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public final class ArcherPower {
    public static EquipmentSlot slot(ItemStack stack){
        if(stack.getItem() instanceof ArcherBowItem)return EquipmentSlot.MAINHAND;
        if(stack.getItem() instanceof QuiverItem)return EquipmentSlot.OFFHAND;
        return stack.getItem() instanceof ArcherArmorItem armor?armor.slot():null;
    }
    public static AttributeModifier modifier(ItemStack stack){
        var slot=slot(stack);if(slot==null)return null;
        int rank=0;for(var e:stack.getEnchantments().entrySet())if(e.getKey().is(Enchantments.POWER))rank=e.getIntValue();
        double bonus=ArcherBalance.get().powerBonus()[Math.clamp(rank,0,5)];
        return bonus==0?null:new AttributeModifier(Eccraft.id("archer_power_"+slot.getName()),bonus,AttributeModifier.Operation.ADD_VALUE);
    }
    private ArcherPower(){}
}
