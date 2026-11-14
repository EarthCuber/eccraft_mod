package com.eccraft.mixin;

import com.eccraft.staff.StaffTableEnchantments;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentMenu.class)
public abstract class StaffEnchantingMenuMixin {
    @Shadow @Final public int[] enchantClue;
    @Redirect(method="getEnchantmentList",at=@At(value="INVOKE",target="Lnet/minecraft/world/item/enchantment/EnchantmentHelper;selectEnchantment(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/item/ItemStack;ILjava/util/stream/Stream;)Ljava/util/List;"))
    private List<EnchantmentInstance> eccraft$visiblePool(RandomSource random,ItemStack stack,int cost,Stream<Holder<Enchantment>> pool) {
        return StaffTableEnchantments.select(random,stack,cost,pool);
    }
    // This instruction only executes after a nonempty result has been applied and XP charged.
    // Preview generation never reaches this hook; unsuccessful clicks cannot roll hidden results.
    @Inject(method="lambda$clickMenuButton$0",at=@At(value="INVOKE",target="Lnet/minecraft/world/item/ItemStack;consume(ILnet/minecraft/world/entity/LivingEntity;)V"))
    private void eccraft$hiddenResult(ItemStack stack,int option,Player player,int lapisCost,ItemStack lapis,Level world,BlockPos pos,CallbackInfo ci) {
        if(!world.isClientSide()){
            var preview=world.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).get(enchantClue[option]).orElse(null);
            com.eccraft.enchanting.EquipmentEnchanting.apply(world.registryAccess(),stack,option,world.getRandom(),preview);
            com.eccraft.enchanting.EnchantAnnouncements.broadcast((net.minecraft.server.level.ServerLevel)world,player,stack);
        }
    }
}
