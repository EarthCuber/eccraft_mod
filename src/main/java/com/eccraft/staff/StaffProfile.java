package com.eccraft.staff;

/** basePower is the weapon's independent spell-power contribution, not the player's total.
 * Parameters are supplied by each concrete staff; no unapproved skill/healing balance defaults. */
public record StaffProfile(double basePower, double normalSkillMultiplier,
        double[] frostSkillMultipliers, double[] healingShotMultipliers,
        double[] healingSkillMultipliers, int slowTicks, int slowAmplifier,
        float projectileSpeed, int projectileLifetime) {
    public StaffProfile {
        finite(basePower); finite(normalSkillMultiplier);
        frostSkillMultipliers = checked(frostSkillMultipliers);
        healingShotMultipliers = checked(healingShotMultipliers);
        healingSkillMultipliers = checked(healingSkillMultipliers);
        if (slowTicks <= 0 || slowAmplifier < 0 || projectileSpeed <= 0
                || !Float.isFinite(projectileSpeed) || projectileLifetime <= 0)
            throw new IllegalArgumentException("Invalid staff projectile/slow parameters");
    }
    private static void finite(double n) {
        if (!Double.isFinite(n) || n < 0) throw new IllegalArgumentException("Expected finite nonnegative value");
    }
    private static double[] checked(double[] values) {
        if (values.length != 3) throw new IllegalArgumentException("Expected three enchantment levels");
        for (double n : values) finite(n);
        return values.clone();
    }
    @Override public double[] frostSkillMultipliers() { return frostSkillMultipliers.clone(); }
    @Override public double[] healingShotMultipliers() { return healingShotMultipliers.clone(); }
    @Override public double[] healingSkillMultipliers() { return healingSkillMultipliers.clone(); }
    public double shotMultiplier(StaffRoute route, int level) {
        return switch (route) {
            case NORMAL -> StaffBalance.get().normalShotMultiplier();
            case FROST -> StaffBalance.get().frostShotMultipliers()[index(level)];
            case HEALING -> healingShotMultipliers[index(level)];
        };
    }
    public double skillMultiplier(StaffRoute route, int level) {
        return switch (route) {
            case NORMAL -> normalSkillMultiplier;
            case FROST -> frostSkillMultipliers[index(level)];
            case HEALING -> healingSkillMultipliers[index(level)];
        };
    }
    private static int index(int level) { return Math.clamp(level, 1, 3) - 1; }
}
