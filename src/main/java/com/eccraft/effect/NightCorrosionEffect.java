package com.eccraft.effect;
import com.eccraft.Eccraft;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.*;
public final class NightCorrosionEffect extends MobEffect {
    public static final Holder<MobEffect> NIGHT_CORROSION=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Eccraft.id("night_corrosion"),new NightCorrosionEffect());
    private NightCorrosionEffect(){super(MobEffectCategory.HARMFUL,0x543A85);}
    public static void register(){}
}
