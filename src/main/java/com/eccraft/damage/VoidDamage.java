package com.eccraft.damage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
public final class VoidDamage {
 public static DamageSource source(ServerLevel level){return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,com.eccraft.Eccraft.id("void"))));}
 private VoidDamage(){}
}
