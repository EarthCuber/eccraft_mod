package com.eccraft.enchanting;
import com.eccraft.staff.StaffBalance;
import com.google.gson.Gson;
import java.nio.file.*;
import java.util.*;
public final class TableBalance {
 public record Normal(int weight,int maxLevel){public Normal{if(weight<1||weight>10000||maxLevel<1||maxLevel>255)throw new IllegalArgumentException("Invalid weight/level");}}
 public record Rare(double chance,int level,String group){public Rare{if(!Double.isFinite(chance)||chance<0||chance>1||level<0||level>255)throw new IllegalArgumentException("Invalid rare chance/level");group=group==null?"":group;}}
 public record Profile(String vanillaReference,Map<String,Normal> normal,Map<String,Rare> rare){public Profile{
  if(!Set.of("self","bow","sword","chest","armor").contains(vanillaReference)||normal==null||normal.isEmpty()||rare==null)throw new IllegalArgumentException("Invalid profile");
  normal=Collections.unmodifiableMap(new LinkedHashMap<>(normal));rare=Collections.unmodifiableMap(new LinkedHashMap<>(rare));
  Map<String,Double> groups=new HashMap<>();
  for(var e:normal.entrySet())if(net.minecraft.resources.Identifier.tryParse(e.getKey())==null||e.getValue()==null)throw new IllegalArgumentException("Invalid normal entry");
  for(var e:rare.entrySet()){if(net.minecraft.resources.Identifier.tryParse(e.getKey())==null||e.getValue()==null)throw new IllegalArgumentException("Invalid rare entry");if(!e.getValue().group().isEmpty())groups.merge(e.getValue().group(),e.getValue().chance(),Double::sum);}
  if(groups.values().stream().anyMatch(v->v>1))throw new IllegalArgumentException("Exclusive probabilities exceed one");
 }}
 public record Data(int extraVanillaWeight,double[] rankWeights,Map<String,Profile> profiles){public Data{
  if(extraVanillaWeight<1||extraVanillaWeight>10000||rankWeights==null||rankWeights.length!=3||profiles==null)throw new IllegalArgumentException("Invalid table config");
  rankWeights=rankWeights.clone();double sum=0;for(double x:rankWeights){if(!Double.isFinite(x)||x<0)throw new IllegalArgumentException("Invalid rank weights");sum+=x;}if(!Double.isFinite(sum)||sum<=0)throw new IllegalArgumentException("Empty rank weights");
  profiles=Collections.unmodifiableMap(new LinkedHashMap<>(profiles));for(String name:List.of("staff","tome","mage_armor","bow","quiver","archer_armor","sword","shield","knight_armor"))if(profiles.get(name)==null)throw new IllegalArgumentException("Missing profile "+name);
 }@Override public double[] rankWeights(){return rankWeights.clone();}}
 private static Data data;
 public static Data get(){if(data==null)throw new IllegalStateException("Table config not initialized");return data;}
 public static void initialize(){data=load(StaffBalance.directory().resolve("enchanting_table.json"));}
 public static Data load(Path path){try{if(!Files.exists(path)){Files.createDirectories(path.toAbsolutePath().getParent());try(var in=TableBalance.class.getResourceAsStream("/data/eccraft/enchanting_table_defaults.json")){if(in==null)throw new IllegalStateException("Missing defaults");Files.copy(in,path);}}var result=new Gson().fromJson(Files.readString(path),Data.class);if(result==null)throw new IllegalArgumentException("Empty config");return result;}catch(Exception e){throw new IllegalStateException("Invalid enchanting_table configuration; file retained: "+path,e);}}
 private TableBalance(){}
}
