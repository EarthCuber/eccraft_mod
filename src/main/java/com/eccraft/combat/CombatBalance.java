package com.eccraft.combat;
import com.eccraft.staff.StaffBalance;
import com.google.gson.Gson;
import java.nio.file.*;
public final class CombatBalance {
 public record Data(int cooldownTicks,int movementTicks,double movementDistance,int durabilityCost,
                    double explosionRadius,double[] explosionMultipliers,double explosiveChance,double[] rankWeights,int arrowLifetimeTicks,Double swordDistance,Double bowDistance,Integer swordCooldownTicks,Integer bowCooldownTicks){
  public Data {
   for(Integer ticks:new Integer[]{swordCooldownTicks,bowCooldownTicks})if(ticks!=null&&ticks<1)throw new IllegalArgumentException("Invalid weapon cooldown");
   if(cooldownTicks<1||movementTicks<1||movementTicks>100||durabilityCost<0||arrowLifetimeTicks<1)throw new IllegalArgumentException("Invalid combat ticks/cost");
   if(!Double.isFinite(movementDistance)||movementDistance<=0||movementDistance/movementTicks>3.9||!Double.isFinite(explosionRadius)||explosionRadius<=0||explosionRadius>64)throw new IllegalArgumentException("Invalid movement/radius");
   if(!Double.isFinite(explosiveChance)||explosiveChance<0||explosiveChance>1||explosionMultipliers==null||explosionMultipliers.length!=3||rankWeights==null||rankWeights.length!=3)throw new IllegalArgumentException("Invalid explosive config");
   for(Double v:new Double[]{swordDistance,bowDistance})if(v!=null&&(!Double.isFinite(v)||v<=0||v/movementTicks>3.9))throw new IllegalArgumentException("Invalid weapon distance");
   explosionMultipliers=explosionMultipliers.clone();rankWeights=rankWeights.clone();double sum=0;
   for(double n:explosionMultipliers)if(!Double.isFinite(n)||n<0)throw new IllegalArgumentException("Invalid multiplier");
   for(double n:rankWeights){if(!Double.isFinite(n)||n<0)throw new IllegalArgumentException("Invalid weight");sum+=n;}if(!Double.isFinite(sum)||sum<=0)throw new IllegalArgumentException("Empty weights");
  }
  public int cooldown(boolean bow){Integer ticks=bow?bowCooldownTicks:swordCooldownTicks;return ticks==null?(bow?100:160):ticks;}
  public double distance(boolean bow){Double v=bow?bowDistance:swordDistance;return v==null?(bow?7:8):v;}
  @Override public double[] explosionMultipliers(){return explosionMultipliers.clone();}
  @Override public double[] rankWeights(){return rankWeights.clone();}
  public double multiplier(int rank){return rank<=0?0:explosionMultipliers[Math.min(3,rank)-1];}
 }
 private static Data data;
 public static Data get(){if(data==null)throw new IllegalStateException("Combat config not initialized");return data;}
 public static void initialize(){data=load(StaffBalance.directory().resolve("combat_skills.json"));}
 public static Data load(Path path){try{
  if(!Files.exists(path)){Files.createDirectories(path.toAbsolutePath().getParent());try(var in=CombatBalance.class.getResourceAsStream("/data/eccraft/combat_skills_defaults.json")){if(in==null)throw new IllegalStateException("Missing defaults");Files.copy(in,path);}}
  var value=new Gson().fromJson(Files.readString(path),Data.class);if(value==null)throw new IllegalArgumentException("Empty config");return value;
 }catch(Exception e){throw new IllegalStateException("Invalid combat config; original file retained: "+path,e);}}
 private CombatBalance(){}
}
