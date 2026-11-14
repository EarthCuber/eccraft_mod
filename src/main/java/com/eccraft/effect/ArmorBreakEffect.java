package com.eccraft.effect;

import com.eccraft.Eccraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public final class ArmorBreakEffect extends MobEffect {
    public static final int MAX_LEVEL = 15;
    public static final Holder<MobEffect> ARMOR_BREAK = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT, Eccraft.id("armor_break"), new ArmorBreakEffect());
    private ArmorBreakEffect() {
        super(MobEffectCategory.HARMFUL, 0x928E80);
        addAttributeModifier(Attributes.ARMOR, Eccraft.id("armor_break"),
                -2.0, AttributeModifier.Operation.ADD_VALUE);
    }
    public static void register() {}
}
