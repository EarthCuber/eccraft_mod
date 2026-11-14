package com.eccraft.combat;
import com.eccraft.Eccraft;
import com.eccraft.archer.ArcherBowItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
public final class ExplosiveEnchantment {
 public static final ResourceKey<Enchantment> KEY=ResourceKey.create(Registries.ENCHANTMENT,Eccraft.id("explosive"));
 public static int level(ItemStack stack){for(var e:stack.getEnchantments().entrySet())if(e.getKey().is(KEY))return Math.clamp(e.getIntValue(),0,3);return 0;}
 public static int roll(RandomSource random,int option,CombatBalance.Data c){if(option!=2||random.nextDouble()>=c.explosiveChance())return 0;double[] w=c.rankWeights();double n=random.nextDouble()*(w[0]+w[1]+w[2]);for(int i=0;i<3;i++){n-=w[i];if(n<0)return i+1;}return 3;}
 public static void apply(RegistryAccess access,ItemStack stack,int option,RandomSource random){if(!(stack.getItem() instanceof ArcherBowItem))return;int rank=roll(random,option,CombatBalance.get());if(rank>0)stack.enchant(access.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(KEY),rank);}
 private ExplosiveEnchantment(){}
}
