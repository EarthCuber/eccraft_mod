package com.eccraft.staff.client;

import com.eccraft.Eccraft;
import com.eccraft.staff.StaffEnchantments;
import com.eccraft.staff.StaffRoute;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public final class StaffInventoryBackground {
    private static final Identifier[] WALL = textures("shieldwall"), EXPLOSIVE = textures("explosive");
    private static final Identifier[] PROVOCATION = textures("provocation"), STEALTH = textures("stealth");
    private static final Identifier[] FROST = textures("frost");
    private static final Identifier[] HEALING = textures("healing");
    private static Identifier[] textures(String name) {
        return new Identifier[] {Eccraft.id("textures/gui/staff_background/" + name + "1.png"),
                Eccraft.id("textures/gui/staff_background/" + name + "2.png"),
                Eccraft.id("textures/gui/staff_background/" + name + "3.png")};
    }
    public static Identifier texture(ItemStack stack) {
        int legend=com.eccraft.legend.LegendEquipment.stage(stack);if(legend>0)return Eccraft.id("textures/gui/staff_background/legend"+legend+".png");
        for(var entry:stack.getEnchantments().entrySet()) {
            if(stack.getItem() instanceof com.eccraft.knight.KnightShieldItem && entry.getKey().is(com.eccraft.knight.ShieldWall.KEY)) return WALL[Math.clamp(entry.getIntValue(),1,3)-1];
            if(stack.getItem() instanceof com.eccraft.archer.ArcherBowItem && entry.getKey().is(com.eccraft.combat.ExplosiveEnchantment.KEY)) return EXPLOSIVE[Math.clamp(entry.getIntValue(),1,3)-1];
        }
        int frost = 0, healing = 0;
        // Only active enchantments count, not an enchanted book's stored enchantments.
        // Holder keys work in inventory previews without depending on a client world.
        for (var entry : stack.getEnchantments().entrySet()) {
            if (entry.getKey().is(StaffEnchantments.FROST)) frost = entry.getIntValue();
            if (entry.getKey().is(StaffEnchantments.HEALING)) healing = entry.getIntValue();
        }
        return switch (StaffRoute.resolve(frost, healing)) {
            case NORMAL -> lowPriorityTexture(stack);
            case FROST -> FROST[Math.clamp(frost, 1, 3) - 1];
            case HEALING -> HEALING[Math.clamp(healing, 1, 3) - 1];
        };
    }
    private static Identifier lowPriorityTexture(ItemStack stack) {
        int p=0,s=0;
        for(var entry:stack.getEnchantments().entrySet()) {
            if(entry.getKey().is(com.eccraft.combat.TargetPreference.PROVOCATION))p=entry.getIntValue();
            if(entry.getKey().is(com.eccraft.combat.TargetPreference.STEALTH))s=entry.getIntValue();
        }
        if(p>0)return PROVOCATION[Math.clamp(p,1,3)-1];
        return s>0?STEALTH[Math.clamp(s,1,3)-1]:null;
    }
    public static void draw(GuiGraphicsExtractor graphics, ItemStack stack, int x, int y) {
        if (stack.isEmpty()) return;
        Identifier texture = texture(stack);
        // Normalized UVs retain the entire source, including the 81x82 frost-II image.
        if (texture != null) graphics.blit(texture, x, y, x + 16, y + 16, 0F, 1F, 0F, 1F);
    }
    private StaffInventoryBackground() {}
}
