package com.eccraft.staff.client;

import java.util.Locale;

/** Pure display math, separate from server cooldown validation. */
public final class StaffHudMath {
    public static String seconds(int ticks) {
        return ticks <= 0 ? "" : String.format(Locale.ROOT, "%.1f", Math.ceil(ticks / 2.0) / 10.0);
    }
    public static int maskHeight(int remaining, int total, int size) {
        if (total <= 0) return 0;
        return (int)Math.ceil(size * Math.clamp((double)remaining / total, 0, 1));
    }
    public static int iconX(int width, int size, boolean offhandOnRight) {
        return Math.max(2, Math.min(width - size - 2, width / 2 + 100 + (offhandOnRight ? 29 : 0)));
    }
    private StaffHudMath() {}
}
