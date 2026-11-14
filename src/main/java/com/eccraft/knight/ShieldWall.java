package com.eccraft.knight;

import com.eccraft.Eccraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public final class ShieldWall {
    public static final ResourceKey<Enchantment> KEY=ResourceKey.create(Registries.ENCHANTMENT,Eccraft.id("shield_wall"));
    public static int roll(RandomSource random,int option,KnightBalance.Data data){
        if(option!=2||random.nextDouble()>=data.shieldWallChance())return 0;
        double[] weights=data.rankWeights();double sum=weights[0]+weights[1]+weights[2],n=random.nextDouble()*sum;
        for(int i=0;i<3;i++){n-=weights[i];if(n<0)return i+1;}return 3;
    }
    public static void apply(RegistryAccess access,ItemStack stack,int option,RandomSource random){
        if(!(stack.getItem() instanceof KnightShieldItem))return;
        int rank=roll(random,option,KnightBalance.get());
        if(rank>0)stack.enchant(access.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(KEY),rank);
    }
    /** Native resistance has already been applied. Only supply the missing reduction. */
    public static float adjust(float afterNative,double wall,double resistance){
        if(afterNative<=0||resistance>=wall||resistance>=1)return afterNative;
        return (float)(afterNative*(1-wall)/(1-resistance));
    }
    public static float damage(LivingEntity entity,DamageSource source,float afterNative){
        if(!(entity instanceof Player)||source.is(DamageTypeTags.BYPASSES_EFFECTS)||source.is(DamageTypeTags.BYPASSES_RESISTANCE)
            ||source.is(DamageTypeTags.BYPASSES_ENCHANTMENTS))return afterNative;
        ItemStack shield=entity.getOffhandItem();if(!(shield.getItem() instanceof KnightShieldItem))return afterNative;
        int rank=0;for(var h:shield.getEnchantments().keySet())if(h.is(KEY))rank=shield.getEnchantments().getLevel(h);
        if(rank<=0)return afterNative;
        var effect=entity.getEffect(MobEffects.RESISTANCE);
        double resistance=effect==null?0:Math.min(1,.2*(effect.getAmplifier()+1));
        return adjust(afterNative,KnightBalance.get().reductions()[Math.min(3,rank)-1],resistance);
    }
    private ShieldWall(){}
}
