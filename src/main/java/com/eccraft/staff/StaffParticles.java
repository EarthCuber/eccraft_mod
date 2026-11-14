package com.eccraft.staff;

import com.eccraft.Eccraft;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

public final class StaffParticles {
    public static final SimpleParticleType HEAL = Registry.register(
            BuiltInRegistries.PARTICLE_TYPE, Eccraft.id("staff_heal"), FabricParticleTypes.simple());
    public static void register() {}
    public static ParticleOptions forRoute(StaffRoute route) {
        return switch (route) {
            case NORMAL -> ParticleTypes.END_ROD;
            case FROST -> ParticleTypes.SNOWFLAKE;
            case HEALING -> HEAL;
        };
    }
    private StaffParticles() {}
}
