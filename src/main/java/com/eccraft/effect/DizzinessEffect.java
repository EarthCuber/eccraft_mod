package com.eccraft.effect;
import net.minecraft.world.effect.*;import net.minecraft.world.entity.ai.attributes.*;
public final class DizzinessEffect extends MobEffect{
 public static final net.minecraft.core.Holder<MobEffect> EFFECT=net.minecraft.core.Registry.registerForHolder(net.minecraft.core.registries.BuiltInRegistries.MOB_EFFECT,com.eccraft.Eccraft.id("dizziness"),new DizzinessEffect());
 private DizzinessEffect(){super(MobEffectCategory.HARMFUL,0xEAC85A);addAttributeModifier(Attributes.MOVEMENT_SPEED,com.eccraft.Eccraft.id("dizziness_speed"),-1,AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);addAttributeModifier(Attributes.JUMP_STRENGTH,com.eccraft.Eccraft.id("dizziness_jump"),-1,AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);}
 public static boolean active(net.minecraft.world.entity.Entity e){return e instanceof net.minecraft.world.entity.LivingEntity l&&l.hasEffect(EFFECT);}public static void register(){}
}