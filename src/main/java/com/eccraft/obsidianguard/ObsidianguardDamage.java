package com.eccraft.obsidianguard;

import com.eccraft.Eccraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;

public final class ObsidianguardDamage {
    public static DamageSource magic(ServerLevel level,Entity direct,Entity owner){
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
                .getOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,Eccraft.id("magic"))),direct,owner);
    }
    private ObsidianguardDamage(){}
}
