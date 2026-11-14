package com.eccraft.staff;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

/** All combat tuning is loaded once, before item registration. Restart after editing. */
public final class StaffBalance {
    public record StaffDefinition(int durability, int enchantability, StaffProfile profile) {
        public StaffDefinition {
            if (durability < 1 || enchantability < 1 || profile == null) throw new IllegalArgumentException("Invalid staff definition");
        }
    }
    public record Data(int chargeTicks, int normalCooldownTicks, int frostCooldownTicks, int healingCooldownTicks,
            double skillRadius, int shotDurabilityCost, int skillDurabilityCost,
            double normalShotMultiplier, double[] frostShotMultipliers, double[] spellPowerBonus,
            double[] tomePower, double legacyTomePower, StaffDefinition[] staves, int[] tomeDurability) {
        public Data {
            for (int ticks : new int[]{chargeTicks, normalCooldownTicks, frostCooldownTicks, healingCooldownTicks})
                if (ticks < 1 || ticks > 72000) throw new IllegalArgumentException("Ticks must be 1..72000");
            if (!Double.isFinite(skillRadius) || skillRadius <= 0 || skillRadius > 128
                    || shotDurabilityCost < 0 || skillDurabilityCost < 0) throw new IllegalArgumentException("Invalid range/cost");
            nonnegative(normalShotMultiplier); nonnegative(legacyTomePower);
            frostShotMultipliers = numbers(frostShotMultipliers, 3);
            spellPowerBonus = numbers(spellPowerBonus, 6);
            if (spellPowerBonus[0] != 0) throw new IllegalArgumentException("Unenchanted spell bonus must be zero");
            tomePower = numbers(tomePower, 5);
            if (staves == null || staves.length != 5) throw new IllegalArgumentException("Expected five staves");
            staves = staves.clone();
            for (var staff : staves) if (staff == null) throw new IllegalArgumentException("Missing staff");
            // Old configurations remain valid and retain all previously customized values.
            if (tomeDurability == null) {
                tomeDurability = new int[5];
                for (int i = 0; i < 5; i++) tomeDurability[i] = staves[i].durability();
            }
            if (tomeDurability.length != 5) throw new IllegalArgumentException("Expected five tome durability values");
            tomeDurability = tomeDurability.clone();
            for (int value : tomeDurability) if (value < 1) throw new IllegalArgumentException("Tome durability must be positive");
        }
        @Override public double[] frostShotMultipliers() { return frostShotMultipliers.clone(); }
        @Override public double[] spellPowerBonus() { return spellPowerBonus.clone(); }
        @Override public double[] tomePower() { return tomePower.clone(); }
        @Override public int[] tomeDurability() { return tomeDurability.clone(); }
        @Override public StaffDefinition[] staves() { return staves.clone(); }
        public int cooldown(StaffRoute route) {
            return switch(route) { case NORMAL -> normalCooldownTicks; case FROST -> frostCooldownTicks; case HEALING -> healingCooldownTicks; };
        }
    }
    private static final Gson GSON = new Gson();
    private static final String RESOURCE = "/data/eccraft/staff_balance_defaults.json";
    private static Data current = defaults();
    private static void nonnegative(double n) {
        if (!Double.isFinite(n) || n < 0) throw new IllegalArgumentException("Expected finite nonnegative number");
    }
    private static double[] numbers(double[] values, int count) {
        if (values == null || values.length != count) throw new IllegalArgumentException("Expected " + count + " values");
        for (double n : values) nonnegative(n);
        return values.clone();
    }
    private static Data defaults() {
        try (var stream = StaffBalance.class.getResourceAsStream(RESOURCE)) {
            if (stream == null) throw new IllegalStateException("Missing " + RESOURCE);
            return GSON.fromJson(new InputStreamReader(stream, StandardCharsets.UTF_8), Data.class);
        } catch (IOException ex) { throw new IllegalStateException("Cannot read staff defaults", ex); }
    }
    public static Data get() { return current; }
    public static Path directory() { return FabricLoader.getInstance().getConfigDir().resolve("eccraft"); }
    public static void initialize() { current = load(directory().resolve("staff_balance.json")); }
    public static Data load(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.toAbsolutePath().getParent());
                try (var stream = StaffBalance.class.getResourceAsStream(RESOURCE)) {
                    if (stream == null) throw new IOException("Missing " + RESOURCE);
                    Files.copy(stream, path);
                }
            }
            String text = Files.readString(path, StandardCharsets.UTF_8);
            var json = com.google.gson.JsonParser.parseString(text).getAsJsonObject();
            // Validate before migrating so invalid user configurations are never overwritten.
            Data result = GSON.fromJson(json, Data.class);
            if (result == null) throw new IllegalArgumentException("Empty configuration");
            // Startup installs the current bundled defaults; historical migrations must not alter them.
            return result;
        } catch (Exception ex) { throw new IllegalStateException("Invalid staff config; file retained for correction: " + path, ex); }
    }
    private StaffBalance() {}
}
