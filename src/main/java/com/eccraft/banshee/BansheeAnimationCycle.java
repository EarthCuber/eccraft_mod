package com.eccraft.banshee;

/** Select sequential clips without relying on the GLB loader's unordered animation map. */
public final class BansheeAnimationCycle {
    private BansheeAnimationCycle() {}
    public record Frame(int index, float time) {}
    public static Frame sample(float seconds, float firstDuration, float secondDuration) {
        float first = Math.max(0, firstDuration), second = Math.max(0, secondDuration);
        float total = first + second;
        if (total <= 0) return new Frame(0, 0);
        float t = seconds % total;
        if (t < 0) t += total;
        return t < first ? new Frame(0, t) : new Frame(1, t - first);
    }
}
