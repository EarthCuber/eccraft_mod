package com.eccraft.darkdragon;

public enum DarkDragonAction {
    IDLE(0), GROUND_ATTACK(60), AIR_ATTACK(60), SKILL_1(360), SKILL_2(60), SKILL_3(60), SKILL_4(60),
    TAKEOFF(200), LANDING(200), ENTERING(1800), ESCAPING(300), FALLING(0), DEATH(140);
    public final int ticks;
    DarkDragonAction(int ticks) { this.ticks = ticks; }
    public static DarkDragonAction parse(String value) {
        try { return valueOf(value); } catch (IllegalArgumentException e) { return IDLE; }
    }
}
