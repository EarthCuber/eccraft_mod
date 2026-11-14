package com.eccraft.death;

import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** Server-side allowlist; never changes any game rule. Reload on application restart. */
public final class DimensionKeepInventory {
    private static Set<String> dimensions = Set.of();
    public static void initialize() {
        dimensions = load(FabricLoader.getInstance().getConfigDir().resolve("eccraft/dimension_keep_inventory.json"));
    }
    public static Set<String> load(Path file) {
        try {
            if (!Files.exists(file)) {
                Files.createDirectories(file.toAbsolutePath().getParent());
                Files.writeString(file, new com.google.gson.GsonBuilder().setPrettyPrinting().create().toJson(Map.of("dimensions", List.of())), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            }
            var json = JsonParser.parseString(Files.readString(file, StandardCharsets.UTF_8)).getAsJsonObject();
            if (!json.has("dimensions") || !json.get("dimensions").isJsonArray()) throw new IllegalArgumentException("dimensions must be an array");
            Set<String> result = new HashSet<>();
            for (var value : json.getAsJsonArray("dimensions")) {
                if (!value.isJsonPrimitive() || !value.getAsJsonPrimitive().isString()) throw new IllegalArgumentException("Dimension IDs must be strings");
                String id = value.getAsString();
                if (!id.contains(":") || Identifier.tryParse(id) == null) throw new IllegalArgumentException("Invalid namespaced dimension ID: " + id);
                result.add(id);
            }
            return Set.copyOf(result);
        } catch (Exception ex) {
            throw new IllegalStateException("Invalid dimension keep-inventory configuration; original file retained: " + file, ex);
        }
    }
    public static boolean protects(Level level) { return dimensions.contains(level.dimension().identifier().toString()); }
    private DimensionKeepInventory() {}
}
