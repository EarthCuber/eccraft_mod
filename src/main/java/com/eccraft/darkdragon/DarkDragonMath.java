package com.eccraft.darkdragon;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;

public final class DarkDragonMath {
    public static final int BREATH_START_TICK = 110, BREATH_END_TICK = 310, BREATH_INTERVAL = 10, BREATH_FADE_TICKS = 20;
    private DarkDragonMath() {}
    public static Vec3 impact(AABB box, Vec3 start, Vec3 end) { return box.contains(start) ? start : box.clip(start, end).orElse(null); }
    public static boolean inSector(double dx, double dz, float yaw, double radius, boolean rear) {
        double d2 = dx * dx + dz * dz;
        if (d2 > radius * radius) return false;
        if (d2 < 1e-10) return true;
        double angle = Math.toRadians(yaw + (rear ? 180 : 0));
        return (-Math.sin(angle) * dx + Math.cos(angle) * dz) >= Math.sqrt(d2) * 0.5 - 1e-7;
    }
    public static Vec3 transition(Vec3 start, Vec3 end, int elapsed, int duration) {
        return start.lerp(end, Math.clamp((double) elapsed / duration, 0, 1));
    }
    public static boolean breathHit(int elapsed) { return elapsed >= BREATH_START_TICK && elapsed < BREATH_END_TICK && (elapsed - BREATH_START_TICK) % BREATH_INTERVAL == 0; }
    public static float breathDensity(int elapsed) {
        if (elapsed < BREATH_START_TICK || elapsed >= BREATH_END_TICK + BREATH_FADE_TICKS) return 0;
        return elapsed < BREATH_END_TICK ? 1 : (BREATH_END_TICK + BREATH_FADE_TICKS - elapsed) / (float) BREATH_FADE_TICKS;
    }
}
