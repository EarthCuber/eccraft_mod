package com.eccraft.staff;

import com.eccraft.armor.MageArmorItem;
import java.util.*;
import java.util.stream.Stream;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;

public final class StaffTableEnchantments {
    public static boolean eligible(ItemStack stack) {
        return stack.getItem() instanceof StaffItem || stack.getItem() instanceof StaffTomeItem || stack.getItem() instanceof MageArmorItem;
    }
    public static boolean visible(ItemStack stack,Holder<Enchantment> enchantment) {

        if(stack.is(Items.BOOK)||stack.is(Items.ENCHANTED_BOOK))
            return enchantment.unwrapKey().map(k->!k.identifier().getNamespace().equals("eccraft")).orElse(true);
        if(enchantment.is(com.eccraft.combat.CriticalCombat.KEY))return true;
        if(enchantment.is(com.eccraft.knight.ShieldWall.KEY)||enchantment.is(com.eccraft.combat.ExplosiveEnchantment.KEY))return false;
        if(enchantment.is(com.eccraft.combat.TargetPreference.PROVOCATION)||enchantment.is(com.eccraft.combat.TargetPreference.STEALTH))return true;
        if(stack.getItem() instanceof com.eccraft.archer.ArcherBowItem || stack.getItem() instanceof com.eccraft.archer.QuiverItem)
            return enchantment.is(Enchantments.POWER)||enchantment.is(Enchantments.UNBREAKING)||enchantment.is(Enchantments.MENDING)||enchantment.is(Enchantments.VANISHING_CURSE);
        if(enchantment.is(StaffEnchantments.FROST)||enchantment.is(StaffEnchantments.HEALING))return false;
        if(stack.getItem() instanceof StaffItem || stack.getItem() instanceof StaffTomeItem)
            return enchantment.is(StaffEnchantments.SPELL)||enchantment.is(Enchantments.UNBREAKING);
        return true;
    }
    public static List<EnchantmentInstance> select(RandomSource random,ItemStack stack,int cost,Stream<Holder<Enchantment>> pool) {
        if(com.eccraft.enchanting.EquipmentEnchanting.category(stack)!=null)return com.eccraft.enchanting.EquipmentEnchanting.select(random,stack,cost,pool);
        var selected=EnchantmentHelper.selectEnchantment(random,stack,cost,pool.filter(h->visible(stack,h)));
        var result=new ArrayList<EnchantmentInstance>();
        for(var entry:selected)result.add(entry.enchantment().is(StaffEnchantments.SPELL)
            ?new EnchantmentInstance(entry.enchantment(),Math.min(4,entry.level())):entry);
        return result;
    }
    public record Hidden(ResourceKey<Enchantment> route,int routeLevel,boolean spellV) {}
    /** Independent spell roll; a single route roll preserves exact mutually-exclusive route probabilities. */
    public static Hidden roll(RandomSource random,boolean staff,int option,EnchantingBalance.Data config) {
        if(option!=2)return new Hidden(null,0,false);
        ResourceKey<Enchantment> route=null;int rank=0;
        if(staff) {
            double value=random.nextDouble();
            if(value<config.staffFrostChance())route=StaffEnchantments.FROST;
            else if(value<config.staffFrostChance()+config.staffHealingChance())route=StaffEnchantments.HEALING;
            if(route!=null) {
                double[] weights=config.routeRankWeights();double valueRank=random.nextDouble()*Arrays.stream(weights).sum();
                rank=3;for(int i=0;i<3;i++){valueRank-=weights[i];if(valueRank<0){rank=i+1;break;}}
            }
        }
        return new Hidden(route,rank,random.nextDouble()<config.spellVChance());
    }
    public static void apply(RegistryAccess registries,ItemStack stack,int option,RandomSource random) {
        if(!eligible(stack)||option!=2)return;
        Hidden hidden=roll(random,stack.getItem() instanceof StaffItem,option,EnchantingBalance.get());
        var registry=registries.lookupOrThrow(Registries.ENCHANTMENT);
        if(hidden.route()!=null) {
            var enchantment=registry.getOrThrow(hidden.route());
            boolean compatible=stack.getEnchantments().keySet().stream().allMatch(h->Enchantment.areCompatible(h,enchantment));
            if(compatible)stack.enchant(enchantment,hidden.routeLevel());
        }
        if(hidden.spellV())stack.enchant(registry.getOrThrow(StaffEnchantments.SPELL),5);
    }
    private StaffTableEnchantments(){}
}

