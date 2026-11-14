package com.eccraft.damage;

import com.eccraft.Eccraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public final class BossDamageSources {
    public static DamageSource melee(ServerLevel level,Entity attacker){
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
                .getOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,Eccraft.id("melee_attack"))),attacker);
    }
    private BossDamageSources() {}
}
