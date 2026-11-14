package com.eccraft.entity;

/** Confirmed action timings: attacks 40 ticks, death 100 ticks. */
public enum ObsidianguardActionState {
    IDLE("idle", 0), CHASE("walk", 0), ATTACK("attack", 40),
    SKILL_1("skill_1", 40), SKILL_2("skill_2", 40), DEATH("death", 100);

    private final String animation;
    private final int duration;

    ObsidianguardActionState(String animation, int duration) {
        this.animation = animation;
        this.duration = duration;
    }

    public String animation() { return animation; }
    public int duration() { return duration; }

    public static ObsidianguardActionState byName(String name) {
        try { return valueOf(name); }
        catch (IllegalArgumentException ignored) { return IDLE; }
    }
}
