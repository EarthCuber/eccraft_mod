package com.eccraft.staff;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;

public final class StaffMobResistance {
    public record Defaults(String _note, double defaultValue, Map<String, Double> mobs) {
        public Defaults {
            validate(defaultValue);
            if (mobs == null) throw new IllegalArgumentException("Missing mobs map");
            for (var value : mobs.values()) { if (value == null) throw new IllegalArgumentException("Null resistance"); validate(value); }
            mobs = java.util.Collections.unmodifiableMap(new TreeMap<>(mobs));
        }
    }
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Defaults current = new Defaults("-1 uses current armor; other values set base magic resistance.", -1, Map.of());
    public static void validate(double value) {
        if (!Double.isFinite(value) || (value < 0 && value != -1) || value > 1000000)
            throw new IllegalArgumentException("Resistance must be -1 or 0..1000000");
    }
    public static void initialize() {
        initialize(StaffBalance.directory().resolve("staff_mob_resistance.json"));
    }
    public static void initialize(Path path) {
        try {
            current = new Defaults("-1 uses current armor; other values set base magic resistance.", -1, Map.of());
            if (Files.exists(path)) try (var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                current = GSON.fromJson(reader, Defaults.class);
                if (current == null) throw new IllegalArgumentException("Empty resistance configuration");
            }
            var values = new TreeMap<>(current.mobs());
            values.putIfAbsent("eccraft:fireelement",-1.0);
            values.putIfAbsent("eccraft:firelord_meteor",-1.0);
            values.putIfAbsent("eccraft:littledevil",-1.0);
            values.putIfAbsent("eccraft:death",-1.0);
            values.putIfAbsent("eccraft:death_conctrl",-1.0);
            // Default-attribute suppliers cover vanilla and modded mobs after registration.
            // Exclude the two standard non-Mob living types; only Mob receives these defaults.
            for (var type : BuiltInRegistries.ENTITY_TYPE) {
                if (type != EntityTypes.PLAYER && type != EntityTypes.ARMOR_STAND && DefaultAttributes.hasSupplier(type))
                    values.putIfAbsent(BuiltInRegistries.ENTITY_TYPE.getKey(type).toString(), current.defaultValue());
            }
            boolean changed = !values.equals(current.mobs()) || !Files.exists(path);
            current = new Defaults(current._note(), current.defaultValue(), values);
            if (changed) {
                Files.createDirectories(path.getParent());
                // Keep a user-editable backup when auto-adding newly registered entity IDs.
                if (Files.exists(path)) Files.copy(path, path.resolveSibling("staff_mob_resistance.before-update.json"), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                Files.writeString(path, GSON.toJson(current) + "\n", StandardCharsets.UTF_8);
            }
        } catch (Exception ex) { throw new IllegalStateException("Invalid mob resistance config; correct " + path, ex); }
    }
    public static void apply(Mob mob) {
        var attribute = mob.getAttribute(StaffAttributes.MAGIC_RESISTANCE);
        if (attribute != null) attribute.setBaseValue(current.mobs().getOrDefault(
                BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType()).toString(), current.defaultValue()));
    }
    public static double resolve(double resistance, double armor) { return resistance == -1 ? armor : resistance; }
    public static double effective(LivingEntity target) {
        double resistance = target.getAttribute(StaffAttributes.MAGIC_RESISTANCE) == null ? 0
                : target.getAttributeValue(StaffAttributes.MAGIC_RESISTANCE);
        return resolve(resistance, target.getAttributeValue(Attributes.ARMOR));
    }
    private StaffMobResistance() {}
}
