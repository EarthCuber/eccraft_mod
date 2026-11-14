package com.eccraft.mixin;
import com.eccraft.combat.AnvilEnchantmentRules;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
@Mixin(AnvilMenu.class)
public abstract class AnvilEnchantmentMixin {
 // Only filter the donor slot. The base item's existing enchantments survive repairs and renames.
 @Redirect(method="createResult",at=@At(value="INVOKE",target="Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getEnchantmentsForCrafting(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/enchantment/ItemEnchantments;",ordinal=1))
 private ItemEnchantments eccraft$donor(ItemStack donor){return AnvilEnchantmentRules.transferable(EnchantmentHelper.getEnchantmentsForCrafting(donor));}
}
