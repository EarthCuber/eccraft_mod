package com.eccraft.entity;

/** Confirmed action timings: attacks 40 ticks, skill 3 60 ticks, death 140 ticks. */
public enum CastlecoreActionState {
    IDLE("idle", 0), CHASE("walk", 0), ATTACK("attack", 40),
    SKILL_1("skill_1", 40), SKILL_2("skill_2", 40), SKILL_3("skill_3",60), DEATH("death", 140);

    private final String animation;
    private final int duration;

    CastlecoreActionState(String animation, int duration) {
        this.animation = animation;
        this.duration = duration;
    }

    public String animation() { return animation; }
    public int duration() { return duration; }

    public static CastlecoreActionState byName(String name) {
        try { return valueOf(name); }
        catch (IllegalArgumentException ignored) { return IDLE; }
    }
}
