package com.eccraft.staff;

/** One clock per player. Observing a different held stack/slot/route resets it. */
public final class StaffCooldown {
    private Object held;
    private int slot = -1;
    private StaffRoute route;
    private long readyAt;
    public void observe(Object held, int slot, StaffRoute route, long now) {
        if ((held != this.held || slot != this.slot || route != this.route) && route != null)
            readyAt = now + route.cooldownTicks();
        this.held = held; this.slot = slot; this.route = route;
    }
    public int remaining(long now) { return (int)Math.max(0, readyAt - now); }
    public boolean cast(long now) {
        if (route == null || remaining(now) > 0) return false;
        readyAt = now + route.cooldownTicks();
        return true;
    }
}
