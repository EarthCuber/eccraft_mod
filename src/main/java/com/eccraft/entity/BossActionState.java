package com.eccraft.entity;

public enum BossActionState {
    IDLE("idle", 0), CHASE("idle", 0), ATTACK("attack", 40),
    SKILL_1("skill_1", 20), SKILL_2("skill_2", 40), SKILL_3("skill_3", 40), DEATH("death", 40);

    private final String animation;
    private final int duration;

    BossActionState(String animation, int duration) {
        this.animation = animation;
        this.duration = duration;
    }

    public String animation() { return animation; }
    public int duration() { return duration; }

    public static BossActionState byName(String name) {
        try { return valueOf(name); }
        catch (IllegalArgumentException ignored) { return IDLE; }
    }
}
