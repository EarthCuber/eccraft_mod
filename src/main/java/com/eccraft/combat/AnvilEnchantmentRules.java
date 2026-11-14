package com.eccraft.combat;
import net.minecraft.world.item.enchantment.ItemEnchantments;
public final class AnvilEnchantmentRules {
 public static ItemEnchantments transferable(ItemEnchantments original){var result=new ItemEnchantments.Mutable(original);result.removeIf(h->h.unwrapKey().map(k->k.identifier().getNamespace().equals("eccraft")).orElse(false));return result.toImmutable();}
 private AnvilEnchantmentRules(){}
}
