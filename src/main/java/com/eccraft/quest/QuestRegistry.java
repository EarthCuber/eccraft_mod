package com.eccraft.quest;

import com.eccraft.Eccraft;
import com.google.gson.*;
import java.io.Reader;
import java.util.*;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

public final class QuestRegistry implements SimpleSynchronousResourceReloadListener {
    public static final QuestRegistry INSTANCE = new QuestRegistry();
    private static final Gson GSON = new Gson();
    public final Map<String, QuestDefinition> quests = new LinkedHashMap<>();
    public final Map<String, QuestPage> pages = new LinkedHashMap<>();
    public final Map<String, Pool> pools = new LinkedHashMap<>();
    public final Map<String, SpecialDaily> specialDaily = new LinkedHashMap<>();
    public record Entry(String quest, int weight) {}
    public record Pool(String id, int tasksPerDay, String timezone, List<Entry> tasks) {}
    public record SpecialDaily(String id, String quest, String item, String itemTag, int count) {}
    @Override public Identifier getFabricId() { return Eccraft.id("quests"); }

    @Override public void onResourceManagerReload(ResourceManager manager) {
        quests.clear(); pages.clear(); pools.clear(); specialDaily.clear();
        load(manager, "quest", (id, root) -> {
            List<QuestDefinition.Objective> objectives = new ArrayList<>();
            for (JsonElement element : array(root, "objectives")) {
                JsonObject o = element.getAsJsonObject();
                objectives.add(new QuestDefinition.Objective(str(o,"id","objective"), str(o,"type",""),
                        str(o,"target",""), integer(o,"count",1), str(o,"dimension","minecraft:overworld"),
                        number(o,"x",0), number(o,"y",0), number(o,"z",0), number(o,"radius",3)));
            }
            List<QuestDefinition.DisplayItem> rewards = new ArrayList<>();
            for (JsonElement element : array(root, "display_rewards")) {
                JsonObject r = element.getAsJsonObject();
                int min = integer(r,"min",integer(r,"count",1));
                rewards.add(new QuestDefinition.DisplayItem(str(r,"item","minecraft:air"), min,
                        integer(r,"max",min), integer(r,"ominous_bottle_amplifier",-1)));
            }
            quests.put(id.toString(), new QuestDefinition(id, str(root,"type","one_time"), str(root,"page",""),
                    str(root,"title",id.toString()), str(root,"description",""), strings(root,"prerequisites"),
                    objectives, str(root,"reward_loot_table",""), rewards, Math.max(0,integer(root,"experience",0))));
        });
        load(manager, "quest_page", (id, root) -> pages.put(id.toString(), new QuestPage(id,
                str(root,"title",id.toString()), str(root,"icon","minecraft:book"), integer(root,"order",0),
                str(root,"type","one_time"), str(root,"pool",""), strings(root,"quests"))));
        load(manager, "quest_pool", (id, root) -> {
            List<Entry> entries = new ArrayList<>();
            for (JsonElement element : array(root,"tasks")) {
                JsonObject o=element.getAsJsonObject(); entries.add(new Entry(str(o,"quest",""), integer(o,"weight",1)));
            }
            pools.put(id.toString(), new Pool(id.toString(), integer(root,"tasks_per_day",3),
                    str(root,"timezone","Asia/Shanghai"), entries));
        });
        load(manager, "special_daily", (id, root) -> specialDaily.put(id.toString(), new SpecialDaily(
                id.toString(), str(root,"quest",""), str(root,"item",""), str(root,"item_tag",""),
                Math.max(1,integer(root,"count",1)))));
        Eccraft.LOGGER.info("Loaded {} quests, {} pages, {} daily pools and {} special daily rules",
                quests.size(), pages.size(), pools.size(), specialDaily.size());
    }
    private interface Loader { void accept(Identifier id, JsonObject root); }
    private static void load(ResourceManager manager, String folder, Loader loader) {
        manager.listResources(folder, id -> id.getPath().endsWith(".json")).forEach((resourceId, resource) -> {
            String path=resourceId.getPath(); String logical=path.substring(folder.length()+1,path.length()-5);
            Identifier id=Identifier.fromNamespaceAndPath(resourceId.getNamespace(),logical);
            try (Reader reader=resource.openAsReader()) { loader.accept(id, JsonParser.parseReader(reader).getAsJsonObject()); }
            catch (Exception e) { Eccraft.LOGGER.error("Failed to load {} {}", folder, resourceId, e); }
        });
    }
    private static String str(JsonObject o,String k,String d){return o.has(k)?o.get(k).getAsString():d;}
    private static int integer(JsonObject o,String k,int d){return o.has(k)?o.get(k).getAsInt():d;}
    private static double number(JsonObject o,String k,double d){return o.has(k)?o.get(k).getAsDouble():d;}
    private static JsonArray array(JsonObject o,String k){return o.has(k)?o.getAsJsonArray(k):new JsonArray();}
    private static List<String> strings(JsonObject o,String k){List<String> r=new ArrayList<>();for(JsonElement e:array(o,k))r.add(e.getAsString());return r;}
}
