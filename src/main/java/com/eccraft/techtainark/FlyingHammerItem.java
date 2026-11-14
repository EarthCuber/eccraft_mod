package com.eccraft.techtainark;
import net.minecraft.world.item.*;import net.minecraft.world.entity.*;import net.minecraft.server.level.ServerLevel;import net.minecraft.core.registries.Registries;import net.minecraft.resources.ResourceKey;import net.minecraft.world.item.enchantment.EnchantmentHelper;
public final class FlyingHammerItem extends MaceItem{
 public static final ThreadLocal<Boolean> FULL=ThreadLocal.withInitial(()->false);
 public FlyingHammerItem(Properties p){super(p);}
 @Override public void hurtEnemy(ItemStack stack,LivingEntity target,LivingEntity attacker){boolean trigger=FULL.get()&&canSmashAttack(attacker);super.hurtEnemy(stack,target,attacker);if(trigger&&attacker.level() instanceof ServerLevel l){var enchant=l.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,com.eccraft.Eccraft.id("dizziness")));if(EnchantmentHelper.getItemEnchantmentLevel(enchant,stack)>0)target.addEffect(new net.minecraft.world.effect.MobEffectInstance(com.eccraft.effect.DizzinessEffect.EFFECT,100,0),attacker);}}
}