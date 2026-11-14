package com.eccraft.archer;
import com.eccraft.staff.StaffBalance;
import com.google.gson.Gson;
import java.nio.file.*;

public final class ArcherBalance {
    public record Tier(double bowPower,double quiverPower,double damageMultiplier,int bowDurability,int quiverDurability) {
        public Tier {
            for(double n:new double[]{bowPower,quiverPower,damageMultiplier})if(!Double.isFinite(n)||n<0)throw new IllegalArgumentException("Invalid archer number");
            if(bowDurability<1||quiverDurability<1)throw new IllegalArgumentException("Invalid durability");
        }
    }
    public record Data(Tier[] tiers,double[] powerBonus,int enchantability) {
        public Data {
            if(tiers==null||tiers.length!=5||powerBonus==null||powerBonus.length!=6||enchantability<1)throw new IllegalArgumentException("Expected five tiers and six power entries");
            tiers=tiers.clone();powerBonus=powerBonus.clone();
            for(Tier tier:tiers)if(tier==null)throw new IllegalArgumentException("Missing tier");
            for(double n:powerBonus)if(!Double.isFinite(n)||n<0)throw new IllegalArgumentException("Invalid power bonus");
            if(powerBonus[0]!=0)throw new IllegalArgumentException("Unenchanted bonus must be zero");
        }
        @Override public Tier[] tiers(){return tiers.clone();}
        @Override public double[] powerBonus(){return powerBonus.clone();}
    }
    private static Data data;
    public static Data get(){if(data==null)throw new IllegalStateException("Archer config not initialized");return data;}
    public static void initialize(){data=load(StaffBalance.directory().resolve("archer.json"));}
    public static Data load(Path path){
        try{
            if(!Files.exists(path)){
                Files.createDirectories(path.toAbsolutePath().getParent());
                try(var in=ArcherBalance.class.getResourceAsStream("/data/eccraft/archer_defaults.json")){if(in==null)throw new IllegalStateException("Missing defaults");Files.copy(in,path);}
            }
            var result=new Gson().fromJson(Files.readString(path),Data.class);if(result==null)throw new IllegalArgumentException("Empty configuration");return result;
        }catch(Exception e){throw new IllegalStateException("Invalid archer configuration; file retained: "+path,e);}
    }
    private ArcherBalance(){}
}
