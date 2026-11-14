package com.eccraft.junglelord;

import com.eccraft.Eccraft;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;

public final class JunglelordParticles {
    public static final SimpleParticleType SLAM = Registry.register(
            BuiltInRegistries.PARTICLE_TYPE, Eccraft.id("junglelord_slam"), FabricParticleTypes.simple());
    public static final int INTERIOR_COUNT = 20;
    public static final int EDGE_COUNT = 8;

    private JunglelordParticles() {}
    public static void register() {}

    /** A uniform disk and an edge ring show the horizontal area at the damage tick. */
    public static void spawnSlam(ServerLevel level, RandomSource random,
                                 double x, double y, double z, double radius) {
        for (int i = 0; i < INTERIOR_COUNT; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double distance = Math.sqrt(random.nextDouble()) * radius;
            spawn(level, x + Math.cos(angle) * distance, y + 0.1, z + Math.sin(angle) * distance);
        }
        for (int i = 0; i < EDGE_COUNT; i++) {
            double angle = i * Math.PI * 2 / EDGE_COUNT;
            spawn(level, x + Math.cos(angle) * radius, y + 0.1, z + Math.sin(angle) * radius);
        }
    }

    private static void spawn(ServerLevel level, double x, double y, double z) {
        level.sendParticles(SLAM, x, y, z, 1, 0, 0, 0, 0);
    }
}
