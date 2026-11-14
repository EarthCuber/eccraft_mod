package com.eccraft.armor;

import com.google.gson.Gson;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import net.fabricmc.loader.api.FabricLoader;

/** Independent per-tier/per-slot armor tuning; changes take effect after restart. */
public final class KnightArmorBalance {
    public record Piece(double armor, double health, double magicResistance, int durability, @com.google.gson.annotations.SerializedName(value="armor_toughness", alternate={"armorToughness"}) double armorToughness, @com.google.gson.annotations.SerializedName(value="knockback_resistance", alternate={"knockbackResistance"}) Double knockbackResistance) {
 public Piece(double armor, double health, double magicResistance, int durability, double armorToughness){this(armor,health,magicResistance,durability,armorToughness,null);}

        public Piece(double armor, double health, double magicResistance, int durability) { this(armor, health, magicResistance, durability, 0); }
        public Piece {
 knockbackResistance=knockbackResistance==null?(0.16):knockbackResistance;
 if(!Double.isFinite(knockbackResistance)||knockbackResistance<0||knockbackResistance>1)throw new IllegalArgumentException("Invalid knockback resistance");
            if (!Double.isFinite(armorToughness) || armorToughness < 0 || !Double.isFinite(armor) || armor < 0 || !Double.isFinite(health) || health < 0 || !Double.isFinite(magicResistance) || magicResistance < 0 || durability < 1)
                throw new IllegalArgumentException("Invalid knight armor values");
        }
    }
    public record Tier(Piece helmet, Piece chestplate, Piece leggings, Piece boots) {
        public Tier {
            if (helmet == null || chestplate == null || leggings == null || boots == null)
                throw new IllegalArgumentException("Expected four armor pieces");
        }
        public Piece piece(int slot) { return switch(slot) { case 0 -> helmet; case 1 -> chestplate; case 2 -> leggings; case 3 -> boots; default -> throw new IllegalArgumentException(); }; }
    }
    public record Data(Tier[] tiers) {
        public Data {
            // Read old eight-tier configurations without discarding user tuning for T1-T5.
            if (tiers != null && tiers.length == 8) tiers = java.util.Arrays.copyOf(tiers,5);
            if (tiers == null || tiers.length != 5) throw new IllegalArgumentException("Expected five knight armor tiers");
            tiers = tiers.clone();
            for (Tier tier : tiers) if (tier == null) throw new IllegalArgumentException("Missing tier");
        }
        @Override public Tier[] tiers() { return tiers.clone(); }
    }
    public static Data load(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.toAbsolutePath().getParent());
                try (var in=KnightArmorBalance.class.getResourceAsStream("/data/eccraft/knight_armor_defaults.json")) {
                    if(in==null)throw new IllegalStateException("Missing knight armor defaults");
                    Files.copy(in,path);
                }
            }
            Data data=new Gson().fromJson(Files.readString(path,StandardCharsets.UTF_8),Data.class);
            if(data==null)throw new IllegalArgumentException("Empty configuration");
            return data;
        } catch(Exception ex) { throw new IllegalStateException("Invalid knight armor configuration (file retained): "+path,ex); }
    }
    public static Data load() { return load(FabricLoader.getInstance().getConfigDir().resolve("eccraft/knight_armor.json")); }
    private KnightArmorBalance() {}
}

