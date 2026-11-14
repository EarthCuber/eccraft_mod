package com.eccraft.mixin;
import com.eccraft.armor.KnightArmorItem;
import com.eccraft.knight.KnightShieldItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Enchantment.class)
public abstract class InactiveSharpnessMixin {
 @Inject(method="modifyDamage",at=@At("HEAD"),cancellable=true)
 private void eccraft$inactive(ServerLevel world,int level,ItemStack stack,Entity target,DamageSource source,MutableFloat damage,CallbackInfo ci){
  if((stack.getItem() instanceof KnightShieldItem||stack.getItem() instanceof KnightArmorItem)
    &&world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS).value()==(Object)this)ci.cancel();
 }
}
