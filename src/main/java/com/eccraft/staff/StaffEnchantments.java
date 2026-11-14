package com.eccraft.staff;

import com.eccraft.Eccraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public final class StaffEnchantments {
    public static final ResourceKey<Enchantment> SPELL = key("spell");
    public static final ResourceKey<Enchantment> FROST = key("frost");
    public static final ResourceKey<Enchantment> HEALING = key("healing");
    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Eccraft.id(name));
    }
    public static int level(Level world, ItemStack stack, ResourceKey<Enchantment> key) {
        return world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(key)
                .map(h -> EnchantmentHelper.getItemEnchantmentLevel(h, stack)).orElse(0);
    }
    public static StaffRoute route(Level world, ItemStack stack) {
        return StaffRoute.resolve(level(world, stack, FROST), level(world, stack, HEALING));
    }
    public static int routeLevel(Level world, ItemStack stack, StaffRoute route) {
        return route == StaffRoute.NORMAL ? 0 : level(world, stack, route == StaffRoute.FROST ? FROST : HEALING);
    }
    public static double spellBonus(int level) { return StaffBalance.get().spellPowerBonus()[Math.clamp(level, 0, 5)]; }
    private StaffEnchantments() {}
}
