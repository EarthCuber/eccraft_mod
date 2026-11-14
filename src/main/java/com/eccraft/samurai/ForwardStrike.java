package com.eccraft.samurai;

/** SAT overlap between a yaw-oriented attack box and a player's axis-aligned box. */
public final class ForwardStrike {
    private ForwardStrike() {}
    public static boolean intersects(double x, double y, double z, double yaw,
            double width, double length, double height,
            double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (maxY < y || minY > y + height) return false;
        double angle = Math.toRadians(yaw);
        double fx = -Math.sin(angle), fz = Math.cos(angle), rx = fz, rz = -fx;
        double a = length / 2, b = width / 2;
        double dx = (minX + maxX) / 2 - (x + fx * a);
        double dz = (minZ + maxZ) / 2 - (z + fz * a);
        double hx = (maxX - minX) / 2, hz = (maxZ - minZ) / 2;
        return Math.abs(dx * fx + dz * fz) <= a + hx * Math.abs(fx) + hz * Math.abs(fz)
                && Math.abs(dx * rx + dz * rz) <= b + hx * Math.abs(rx) + hz * Math.abs(rz)
                && Math.abs(dx) <= hx + a * Math.abs(fx) + b * Math.abs(rx)
                && Math.abs(dz) <= hz + a * Math.abs(fz) + b * Math.abs(rz);
    }
}
