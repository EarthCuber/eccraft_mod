package com.eccraft.combat;
import com.eccraft.Eccraft;
import com.eccraft.staff.StaffBalance;
import com.google.gson.Gson;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
public final class TargetPreference {
 public static final ResourceKey<Enchantment> PROVOCATION=ResourceKey.create(Registries.ENCHANTMENT,Eccraft.id("provocation"));
 public static final ResourceKey<Enchantment> STEALTH=ResourceKey.create(Registries.ENCHANTMENT,Eccraft.id("stealth"));
 public record Config(double perLevelFactor){public Config{if(!Double.isFinite(perLevelFactor)||perLevelFactor<1||perLevelFactor>2)throw new IllegalArgumentException("perLevelFactor must be 1..2");}}
 private static Config config;
 public static Config load(Path p){try{if(!Files.exists(p)){Files.createDirectories(p.toAbsolutePath().getParent());Files.writeString(p,new Gson().toJson(new Config(1.05)));}var result=new Gson().fromJson(Files.readString(p),Config.class);if(result==null)throw new IllegalArgumentException("Missing config");return result;}catch(Exception e){throw new IllegalStateException("Invalid target preference config; file retained: "+p,e);}}
 public static void initialize(){config=load(StaffBalance.directory().resolve("target_preference.json"));}
 public static int levelDifference(Iterable<ItemStack> equipment){int p=0,s=0;for(var stack:equipment)for(var e:stack.getEnchantments().entrySet()){
  if(e.getKey().is(PROVOCATION))p+=Math.clamp(e.getIntValue(),0,3);if(e.getKey().is(STEALTH))s+=Math.clamp(e.getIntValue(),0,3);
 }return Math.clamp(p-s,-18,18);}
 public static int levelDifference(LivingEntity player){return levelDifference(java.util.stream.Stream.of(EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND,EquipmentSlot.HEAD,EquipmentSlot.CHEST,EquipmentSlot.LEGS,EquipmentSlot.FEET).map(player::getItemBySlot).toList());}
 public static double score(double distanceSquared,int netLevels,double factor){return distanceSquared/Math.pow(factor,2*Math.clamp(netLevels,-18,18));}
 public static double score(LivingEntity player,double distanceSquared){return score(distanceSquared,levelDifference(player),config.perLevelFactor());}
 /** Stable ordering preserves vanilla ties, and no filtering or distance gates are changed. */
 public static <T> T choose(List<T> candidates,ToDoubleFunction<T> distance,ToIntFunction<T> levels,double factor){
  if(candidates.isEmpty())return null;if(candidates.size()==1)return candidates.getFirst();T best=null;double bestScore=Double.POSITIVE_INFINITY;
  for(T candidate:candidates){double value=score(distance.applyAsDouble(candidate),levels.applyAsInt(candidate),factor);if(value<bestScore){bestScore=value;best=candidate;}}return best;
 }
 public static Player find(ServerLevel world,TargetingConditions conditions,LivingEntity source,double x,double y,double z,Player original){
  if(original==null||!(source instanceof Mob mob)||mob.getTarget()!=null)return original;
  var candidates=world.players().stream().filter(p->conditions.test(world,source,p)).toList();
  return candidates.size()<2?original:choose(candidates,p->p.distanceToSqr(x,y,z),TargetPreference::levelDifference,config.perLevelFactor());
 }
 public static <T extends Player> List<T> sorted(LivingEntity source,List<T> candidates){
  if(candidates.size()<2)return candidates;var result=new ArrayList<>(candidates);result.sort(Comparator.comparingDouble(p->score(p,source.distanceToSqr(p))));return List.copyOf(result);
 }
 private TargetPreference(){}
}
