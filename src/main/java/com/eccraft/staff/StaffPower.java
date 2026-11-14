package com.eccraft.staff;

import com.eccraft.Eccraft;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;

/** The same contribution is used by server equipment logic and the hover tooltip. */
public final class StaffPower {
    public static double contribution(ItemStack stack) {
        if (!(stack.getItem() instanceof StaffItem staff)) return 0;
        int rank = 0;
        for (var entry : stack.getEnchantments().entrySet())
            if (entry.getKey().is(StaffEnchantments.SPELL)) rank = entry.getIntValue();
        return staff.profile().basePower() + StaffEnchantments.spellBonus(rank);
    }
    public static void tooltip(ItemStack stack, TooltipDisplay display, Consumer<Component> lines) {
        double power = contribution(stack);
        if (power == 0 || display.hideTooltip() || !display.shows(DataComponents.ATTRIBUTE_MODIFIERS)) return;
        lines.accept(Component.empty());
        lines.accept(Component.translatable("item.modifiers.mainhand").withStyle(ChatFormatting.GRAY));
        // Use vanilla formatting, translation, color and number precision. This is display-only:
        // the existing transient modifier remains the sole source of the staff's gameplay bonus.
        ItemAttributeModifiers.Display.attributeModifiers().apply(lines, null, StaffAttributes.SPELL_POWER,
                new AttributeModifier(Eccraft.id("held_staff_power"), power, AttributeModifier.Operation.ADD_VALUE));
    }
    private StaffPower() {}
}
