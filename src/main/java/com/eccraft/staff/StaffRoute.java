package com.eccraft.staff;

public enum StaffRoute {
    NORMAL, FROST, HEALING;
    public int cooldownTicks() { return StaffBalance.get().cooldown(this); }
    public static StaffRoute resolve(int frost, int healing) {
        return frost > 0 ? FROST : healing > 0 ? HEALING : NORMAL;
    }
}
