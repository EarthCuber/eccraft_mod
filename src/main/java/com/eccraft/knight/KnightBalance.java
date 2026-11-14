package com.eccraft.knight;

import com.eccraft.staff.StaffBalance;
import com.google.gson.Gson;
import java.nio.file.*;

/** Restart after editing config/eccraft/knight.json. */
public final class KnightBalance {
    public record Tier(double swordDamage,int swordDurability,int shieldDurability,double health,double armor,double magicResistance, @com.google.gson.annotations.SerializedName(value="knockback_resistance", alternate={"knockbackResistance"}) Double knockbackResistance) {
 public Tier(double swordDamage,int swordDurability,int shieldDurability,double health,double armor,double magicResistance){this(swordDamage,swordDurability,shieldDurability,health,armor,magicResistance,null);}

        public Tier {
 knockbackResistance=knockbackResistance==null?(0.16):knockbackResistance;
 if(!Double.isFinite(knockbackResistance)||knockbackResistance<0||knockbackResistance>1)throw new IllegalArgumentException("Invalid knockback resistance");
            for(double x:new double[]{swordDamage,health,armor,magicResistance}) if(!Double.isFinite(x)||x<0)throw new IllegalArgumentException("Negative/nonfinite knight attribute");
            if(swordDamage<1||swordDurability<1||shieldDurability<1)throw new IllegalArgumentException("Invalid damage/durability");
        }
    }
    public record Data(Tier[] tiers,int enchantability,double shieldWallChance,double[] rankWeights,double[] reductions) {
        public Data {
            if(tiers==null||tiers.length!=5||enchantability<1)throw new IllegalArgumentException("Expected five tiers and positive enchantability");
            tiers=tiers.clone();for(var t:tiers)if(t==null)throw new IllegalArgumentException("Missing tier");
            if(!Double.isFinite(shieldWallChance)||shieldWallChance<0||shieldWallChance>1)throw new IllegalArgumentException("Invalid chance");
            if(rankWeights==null||rankWeights.length!=3||reductions==null||reductions.length!=3)throw new IllegalArgumentException("Expected three ranks");
            rankWeights=rankWeights.clone();reductions=reductions.clone();double sum=0;
            for(double x:rankWeights){if(!Double.isFinite(x)||x<0)throw new IllegalArgumentException("Invalid weights");sum+=x;}
            if(!Double.isFinite(sum)||sum<=0)throw new IllegalArgumentException("Empty weights");
            for(double x:reductions)if(!Double.isFinite(x)||x<0||x>1)throw new IllegalArgumentException("Invalid reduction");
        }
        @Override public Tier[] tiers(){return tiers.clone();}
        @Override public double[] rankWeights(){return rankWeights.clone();}
        @Override public double[] reductions(){return reductions.clone();}
    }
    private static Data current;
    public static Data get(){if(current==null)throw new IllegalStateException("Knight config not initialized");return current;}
    public static void initialize(){current=load(StaffBalance.directory().resolve("knight.json"));}
    public static Data load(Path path){
        try{
            if(!Files.exists(path)){
                Files.createDirectories(path.toAbsolutePath().getParent());
                try(var in=KnightBalance.class.getResourceAsStream("/data/eccraft/knight_defaults.json")){
                    if(in==null)throw new IllegalStateException("Missing knight defaults");Files.copy(in,path);
                }
            }
            var data=new Gson().fromJson(Files.readString(path),Data.class);
            if(data==null)throw new IllegalArgumentException("Empty knight config");return data;
        }catch(Exception e){throw new IllegalStateException("Invalid knight config (retained): "+path,e);}
    }
    private KnightBalance(){}
}
