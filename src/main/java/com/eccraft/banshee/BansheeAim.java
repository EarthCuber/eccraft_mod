package com.eccraft.banshee;

import net.minecraft.world.phys.Vec3;

public final class BansheeAim {
    public static final double VERTICAL_VELOCITY_WEIGHT = 0.08;
    private BansheeAim() {}
    /** Speed is blocks/tick; velocity uses all three axes in the same units. */
    public static Vec3 predict(Vec3 origin, Vec3 target, Vec3 velocity, double speed) {
        if (!(speed > 0) || !Double.isFinite(speed)) return target;
        double flightTicks = origin.distanceTo(target) / speed;
        return target.add(velocity.x * flightTicks,
                velocity.y * VERTICAL_VELOCITY_WEIGHT * flightTicks, velocity.z * flightTicks);
    }
}
