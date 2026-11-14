package com.eccraft.entity;

/** Provisional timings until the GLB animation specifications are supplied. */
public enum SamuraiActionState {
    IDLE("idle", 0), CHASE("walk", 0), ATTACK("attack", 30),
    SKILL_1("skill_1", 30), SKILL_2("skill_2", 30), DEATH("death", 60);

    private final String animation;
    private final int duration;

    SamuraiActionState(String animation, int duration) {
        this.animation = animation;
        this.duration = duration;
    }

    public String animation() { return animation; }
    public int duration() { return duration; }

    public static SamuraiActionState byName(String name) {
        try { return valueOf(name); }
        catch (IllegalArgumentException ignored) { return IDLE; }
    }
}
