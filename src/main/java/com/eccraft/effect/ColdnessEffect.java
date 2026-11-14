package com.eccraft.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public final class ColdnessEffect extends MobEffect {
    public static final ResourceKey<DamageType> MAGIC = ResourceKey.create(
            Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("eccraft", "magic"));

    public ColdnessEffect() {
        super(MobEffectCategory.HARMFUL, 0x8ED8F8);
        addAttributeModifier(Attributes.MOVEMENT_SPEED,
                Identifier.fromNamespaceAndPath("eccraft", "coldness_speed"),
                -0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return amplifier >= 19;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        // Entity time keeps the cadence stable even when the duration is refreshed.
        if (entity instanceof Player && entity.tickCount % 10 == 0) {
            entity.hurtServer(level, new DamageSource(
                    level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(MAGIC)), 20.0F);
        }
        return true;
    }
}
