package com.eccraft.effect;

import com.eccraft.Eccraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/** One mechanical level: incoming damage bypasses mitigation and is doubled. */
public final class ArmorRendEffect extends MobEffect {
    public static final Holder<MobEffect> ARMOR_REND = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT, Eccraft.id("armor_rend"), new ArmorRendEffect());
    private ArmorRendEffect(){super(MobEffectCategory.HARMFUL,0xB44135);}
    public static void register(){}
}
