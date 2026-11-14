package com.eccraft.npc;

import com.eccraft.Eccraft;
import com.google.gson.*;
import java.io.Reader;
import java.util.*;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

public final class NpcRegistry implements SimpleSynchronousResourceReloadListener {
 public static final NpcRegistry INSTANCE=new NpcRegistry();
 public record Definition(String name,String modelType,String model,String texture,float scale,String idle,String playerName,String dialogue){}
 public record Condition(String type,String quest,String state){}
 public record Action(String type,String quest,String dialogue){}
 public record Option(String text,List<Condition> conditions,List<Action> actions){}
 public record Dialogue(String speaker,String text,List<Option> options){}
 public final Map<String,Definition> definitions=new HashMap<>();
 public final Map<String,Dialogue> dialogues=new HashMap<>();
 @Override public Identifier getFabricId(){return Eccraft.id("npcs");}
 @Override public void onResourceManagerReload(ResourceManager manager){definitions.clear();dialogues.clear();
  load(manager,"npc",(id,r)->{JsonObject m=obj(r,"model");definitions.put(id.toString(),new Definition(str(r,"display_name",id.toString()),str(m,"type","gltf"),str(m,"model",""),str(m,"texture",""),(float)number(m,"scale",0.1),str(m,"idle_animation",""),str(m,"player_name",""),str(r,"dialogue","")));});
  load(manager,"dialogue",(id,r)->{List<Option> options=new ArrayList<>();for(JsonElement e:array(r,"options")){JsonObject o=e.getAsJsonObject();List<Condition> cs=new ArrayList<>();for(JsonElement c:array(o,"conditions")){JsonObject x=c.getAsJsonObject();cs.add(new Condition(str(x,"type",""),str(x,"quest",""),str(x,"state","")));}List<Action> as=new ArrayList<>();for(JsonElement a:array(o,"actions")){JsonObject x=a.getAsJsonObject();as.add(new Action(str(x,"type",""),str(x,"quest",""),str(x,"dialogue","")));}if(o.has("action")){JsonObject x=o.getAsJsonObject("action");as.add(new Action(str(x,"type",""),str(x,"quest",""),str(x,"dialogue","")));}options.add(new Option(str(o,"text","..."),cs,as));}dialogues.put(id.toString(),new Dialogue(str(r,"speaker",""),str(r,"text",""),options));});
  Eccraft.LOGGER.info("Loaded {} NPC definitions and {} dialogues",definitions.size(),dialogues.size());
 }
 private interface Loader{void load(Identifier id,JsonObject root);}
 private static void load(ResourceManager m,String folder,Loader l){m.listResources(folder,id->id.getPath().endsWith(".json")).forEach((rid,res)->{String p=rid.getPath(),logical=p.substring(folder.length()+1,p.length()-5);try(Reader reader=res.openAsReader()){l.load(Identifier.fromNamespaceAndPath(rid.getNamespace(),logical),JsonParser.parseReader(reader).getAsJsonObject());}catch(Exception e){Eccraft.LOGGER.error("Failed to load {} {}",folder,rid,e);}});}
 private static String str(JsonObject o,String k,String d){return o.has(k)?o.get(k).getAsString():d;}private static double number(JsonObject o,String k,double d){return o.has(k)?o.get(k).getAsDouble():d;}private static JsonArray array(JsonObject o,String k){return o.has(k)?o.getAsJsonArray(k):new JsonArray();}private static JsonObject obj(JsonObject o,String k){return o.has(k)?o.getAsJsonObject(k):new JsonObject();}
}
