package com.eccraft.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public final class ModEffects {
    public static final Holder<MobEffect> COLDNESS = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath("eccraft", "coldness"),
            new ColdnessEffect());

    private ModEffects() {}
    public static void register() {}
}
