package com.eccraft.staff;

import com.google.gson.Gson;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public final class EnchantingBalance {
    public record Data(double staffFrostChance,double staffHealingChance,double spellVChance,double[] routeRankWeights) {
        public Data {
            for(double p:new double[]{staffFrostChance,staffHealingChance,spellVChance})
                if(!Double.isFinite(p)||p<0||p>1)throw new IllegalArgumentException("Chance must be 0..1");
            if(staffFrostChance+staffHealingChance>1)throw new IllegalArgumentException("Exclusive route probabilities exceed 1");
            if(routeRankWeights==null||routeRankWeights.length!=3)throw new IllegalArgumentException("Expected three rank weights");
            routeRankWeights=routeRankWeights.clone();double sum=0;
            for(double p:routeRankWeights){if(!Double.isFinite(p)||p<0)throw new IllegalArgumentException("Invalid rank weight");sum+=p;}
            if(!Double.isFinite(sum)||sum<=0)throw new IllegalArgumentException("Empty rank distribution");
        }
        @Override public double[] routeRankWeights(){return routeRankWeights.clone();}
    }
    private static Data current=new Data(.10,.01,.12,new double[]{5,3,2});
    public static Data get(){return current;}
    public static void initialize(){current=load(StaffBalance.directory().resolve("enchanting.json"));}
    public static Data load(Path path) {
        try {
            if(!Files.exists(path)) {
                Files.createDirectories(path.toAbsolutePath().getParent());
                try(var in=EnchantingBalance.class.getResourceAsStream("/data/eccraft/enchanting_defaults.json")) {
                    if(in==null)throw new IllegalStateException("Missing enchanting defaults");Files.copy(in,path);
                }
            }
            Data result=new Gson().fromJson(Files.readString(path,StandardCharsets.UTF_8),Data.class);
            if(result==null)throw new IllegalArgumentException("Empty configuration");return result;
        }catch(Exception ex){throw new IllegalStateException("Invalid enchanting config; file retained: "+path,ex);}
    }
    private EnchantingBalance(){}
}
