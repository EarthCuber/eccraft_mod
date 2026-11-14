package com.eccraft.quest;

import com.google.gson.*;
import com.mojang.brigadier.arguments.StringArgumentType;
import java.time.*;
import java.util.*;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvents;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public final class QuestManager {
    private static final Gson GSON = new Gson();
    private QuestManager() {}
    public static QuestPlayerData data(ServerPlayer p){return ((QuestDataHolder)p).eccraft$quests();}
    public static void register() {
        PayloadTypeRegistry.serverboundPlay().register(QuestPayloads.Request.TYPE, QuestPayloads.Request.CODEC);
        PayloadTypeRegistry.clientboundPlay().registerLarge(QuestPayloads.Sync.TYPE, QuestPayloads.Sync.CODEC, 1_000_000);
        ServerPlayNetworking.registerGlobalReceiver(QuestPayloads.Request.TYPE,(payload,ctx)->{
            ServerPlayer p=ctx.player(); ensureDaily(p);
            switch(payload.action()){
                case "accept" -> {var q=QuestRegistry.INSTANCE.quests.get(payload.quest());if(q!=null&&q.prerequisites().isEmpty())accept(p,payload.quest());}
                case "submit" -> submit(p,payload.quest());
                case "claim" -> claim(p,payload.quest());
            }
            sync(p,"open".equals(payload.action()));
        });
        ServerLivingEntityEvents.AFTER_DEATH.register((entity,source)->{
            if(source.getEntity() instanceof ServerPlayer p)
                advance(p,"kill",BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString(),1);
        });
        ServerPlayerEvents.COPY_FROM.register((oldPlayer,newPlayer,alive)->data(newPlayer).copyFrom(data(oldPlayer)));
        ServerPlayerEvents.JOIN.register(QuestManager::ensureDaily);
        ServerTickEvents.END_SERVER_TICK.register(server->{
            int tick=server.getTickCount();
            if(tick%20==0)for(ServerPlayer p:server.getPlayerList().getPlayers())ensureDaily(p);
            if(tick%10==0)for(ServerPlayer p:server.getPlayerList().getPlayers())tickLocation(p);
        });
        CommandRegistrationCallback.EVENT.register((dispatcher,context,selection)->dispatcher.register(literal("quest")
                .requires(s->s.permissions().hasPermission(net.minecraft.server.permissions.Permissions.COMMANDS_ADMIN))
                .then(literal("open").then(argument("players",net.minecraft.commands.arguments.EntityArgument.players())
                        .executes(c->{for(ServerPlayer p:net.minecraft.commands.arguments.EntityArgument.getPlayers(c,"players")){ensureDaily(p);sync(p,true);}return 1;})))
                .then(literal("start").then(argument("players",net.minecraft.commands.arguments.EntityArgument.players())
                        .then(argument("quest",net.minecraft.commands.arguments.IdentifierArgument.id()).executes(c->{String id=net.minecraft.commands.arguments.IdentifierArgument.getId(c,"quest").toString();for(ServerPlayer p:net.minecraft.commands.arguments.EntityArgument.getPlayers(c,"players")){accept(p,id);sync(p);}return 1;}))))
                .then(literal("complete").then(argument("players",net.minecraft.commands.arguments.EntityArgument.players())
                        .then(argument("quest",net.minecraft.commands.arguments.IdentifierArgument.id()).executes(c->{String id=net.minecraft.commands.arguments.IdentifierArgument.getId(c,"quest").toString();for(ServerPlayer p:net.minecraft.commands.arguments.EntityArgument.getPlayers(c,"players")){forceComplete(p,id);sync(p);}return 1;}))))
                .then(literal("reset").then(argument("players",net.minecraft.commands.arguments.EntityArgument.players())
                        .then(argument("quest",net.minecraft.commands.arguments.IdentifierArgument.id()).executes(c->{String id=net.minecraft.commands.arguments.IdentifierArgument.getId(c,"quest").toString();for(ServerPlayer p:net.minecraft.commands.arguments.EntityArgument.getPlayers(c,"players")){reset(p,id);sync(p);}return 1;}))))
                .then(literal("refresh").then(argument("players",net.minecraft.commands.arguments.EntityArgument.players())
                        .then(argument("page",net.minecraft.commands.arguments.IdentifierArgument.id()).executes(c->{String id=net.minecraft.commands.arguments.IdentifierArgument.getId(c,"page").toString();int refreshed=0;for(ServerPlayer p:net.minecraft.commands.arguments.EntityArgument.getPlayers(c,"players")){if(refreshPage(p,id)){sync(p);refreshed++;}}return refreshed;}))))));
    }
    public static void ensureDaily(ServerPlayer p) {
        QuestPlayerData d=data(p);
        QuestRegistry.Pool pool=QuestRegistry.INSTANCE.pools.values().stream().findFirst().orElse(null);
        String today=LocalDate.now(ZoneId.of(pool==null?"Asia/Shanghai":pool.timezone())).toString();
        if(today.equals(d.dailyDate)){if(activateLegendDaily(p,d))sync(p);return;}
        for(String id:new ArrayList<>(d.daily)){d.active.remove(id);d.completed.remove(id);d.claimed.remove(id);d.clearQuest(id);}
        d.daily.clear();d.dailyDate=today;
        if(pool!=null)fillDaily(p,d,pool,new Random(Objects.hash(p.getUUID(),today,pool.id())));
        activateLegendDaily(p,d);sync(p);
    }
    private static boolean activateLegendDaily(ServerPlayer p,QuestPlayerData d){
        boolean changed=false;
        for(var rule:QuestRegistry.INSTANCE.specialDaily.values()){
            var q=QuestRegistry.INSTANCE.quests.get(rule.quest());
            var page=q==null?null:QuestRegistry.INSTANCE.pages.get(q.page());
            if(page==null||!"special_daily".equals(page.type())||d.daily.contains(rule.quest()))continue;
            if(hasTriggerItem(p,rule)){d.daily.add(rule.quest());d.active.add(rule.quest());changed=true;}
        }
        return changed;
    }
    private static void fillDaily(ServerPlayer p,QuestPlayerData d,QuestRegistry.Pool pool,Random random) {
        List<QuestRegistry.Entry> choices=new ArrayList<>(pool.tasks());
        int picked=0;
        while(picked<pool.tasksPerDay()&&!choices.isEmpty()){
            int total=choices.stream().mapToInt(e->Math.max(1,e.weight())).sum(), roll=random.nextInt(total);
            QuestRegistry.Entry chosen=choices.getFirst();
            for(QuestRegistry.Entry e:choices){roll-=Math.max(1,e.weight());if(roll<0){chosen=e;break;}}
            choices.remove(chosen);
            if(QuestRegistry.INSTANCE.quests.containsKey(chosen.quest())&&!d.daily.contains(chosen.quest())){d.daily.add(chosen.quest());d.active.add(chosen.quest());picked++;}
        }
        for(QuestRegistry.SpecialDaily rule:QuestRegistry.INSTANCE.specialDaily.values()){
            if(!QuestRegistry.INSTANCE.quests.containsKey(rule.quest())||d.daily.contains(rule.quest()))continue;
            if(hasTriggerItem(p,rule)){d.daily.add(rule.quest());d.active.add(rule.quest());}
        }
    }
    private static boolean refreshPage(ServerPlayer p,String pageId){
        QuestPage page=QuestRegistry.INSTANCE.pages.get(pageId);if(page==null)return false;QuestPlayerData d=data(p);
        if("special_daily".equals(page.type())){
            for(String id:new ArrayList<>(d.daily)){var q=QuestRegistry.INSTANCE.quests.get(id);if(q!=null&&pageId.equals(q.page())){reset(p,id);d.daily.remove(id);}}
            activateLegendDaily(p,d);return true;
        }
        if("daily_pool".equals(page.type())){
            QuestRegistry.Pool pool=QuestRegistry.INSTANCE.pools.get(page.pool());if(pool==null)return false;
            for(String id:new ArrayList<>(d.daily)){var q=QuestRegistry.INSTANCE.quests.get(id);var owner=q==null?null:QuestRegistry.INSTANCE.pages.get(q.page());if(owner!=null&&"special_daily".equals(owner.type()))continue;reset(p,id);d.daily.remove(id);}
            d.dailyDate=LocalDate.now(ZoneId.of(pool.timezone())).toString();
            fillDaily(p,d,pool,new Random(Objects.hash(p.getUUID(),System.nanoTime(),pool.id())));return true;
        }
        for(String id:page.quests())reset(p,id);return true;
    }
    private static boolean hasTriggerItem(ServerPlayer player, QuestRegistry.SpecialDaily rule){
        Identifier itemId=rule.item().isBlank()?null:Identifier.tryParse(rule.item());
        Identifier tagId=rule.itemTag().isBlank()?null:Identifier.tryParse(rule.itemTag());
        TagKey<Item> tag=tagId==null?null:TagKey.create(Registries.ITEM,tagId);int found=0;
        for(ItemStack stack:player.getInventory().getNonEquipmentItems())if(matches(stack,itemId,tag))found+=stack.getCount();
        ItemStack offhand=player.getOffhandItem();if(matches(offhand,itemId,tag))found+=offhand.getCount();
        return found>=rule.count();
    }
    private static boolean matches(ItemStack stack,Identifier itemId,TagKey<Item> tag){
        if(stack.isEmpty())return false;
        return (itemId!=null&&BuiltInRegistries.ITEM.getKey(stack.getItem()).equals(itemId))||(tag!=null&&stack.is(tag));
    }
    public static boolean accept(ServerPlayer p,String id){
        QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);QuestPlayerData d=data(p);
        if(q==null||!"one_time".equals(q.type())||d.active.contains(id)||d.completed.contains(id)||d.claimed.contains(id))return false;
        if(!prerequisitesMet(d,q))return false;d.active.add(id);return true;
    }
    public static void forceComplete(ServerPlayer p,String id){QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);if(q==null)return;QuestPlayerData d=data(p);d.claimed.remove(id);d.completed.remove(id);for(var o:q.objectives())d.set(id,o.id(),o.count());finish(p,d,q);}
    private static void reset(ServerPlayer p,String id){QuestPlayerData d=data(p);d.active.remove(id);d.completed.remove(id);d.claimed.remove(id);d.clearQuest(id);}
    public static void advance(ServerPlayer p,String type,String target,int amount){
        ensureDaily(p);QuestPlayerData d=data(p);boolean changed=false;
        for(String id:new ArrayList<>(d.active)){
            QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);if(q==null)continue;
            for(var o:q.objectives())if(type.equals(o.type())&&target.equals(o.target())){
                int now=Math.min(o.count(),d.get(id,o.id())+amount);d.set(id,o.id(),now);changed=true;
            }
            if(isComplete(d,q)){finish(p,d,q);changed=true;}
        }
        if(changed)sync(p,false);
    }
    private static void tickLocation(ServerPlayer p){
        QuestPlayerData d=data(p);boolean changed=false;
        for(String id:new ArrayList<>(d.active)){
            QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);if(q==null)continue;
            for(var o:q.objectives())if("location".equals(o.type())&&p.level().dimension().identifier().toString().equals(o.dimension())){
                double dx=p.getX()-o.x(),dy=p.getY()-o.y(),dz=p.getZ()-o.z();
                if(dx*dx+dy*dy+dz*dz<=o.radius()*o.radius()){d.set(id,o.id(),o.count());changed=true;}
            }
            if(isComplete(d,q)){finish(p,d,q);changed=true;}
        }
        if(changed)sync(p,false);
    }
    public static boolean submit(ServerPlayer p,String id){
        QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);QuestPlayerData d=data(p);if(q==null||!d.active.contains(id))return false;
        Inventory inv=p.getInventory();Map<Item,Integer> required=new LinkedHashMap<>();
        for(var o:q.objectives())if("submit_item".equals(o.type())&&d.get(id,o.id())<o.count()){
            Identifier key=Identifier.tryParse(o.target());if(key==null||!BuiltInRegistries.ITEM.containsKey(key))return false;
            Item item=BuiltInRegistries.ITEM.getValue(key);if(item==net.minecraft.world.item.Items.AIR)return false;
            long total=(long)required.getOrDefault(item,0)+o.count()-d.get(id,o.id());if(total>Integer.MAX_VALUE)return false;required.put(item,(int)total);
        }
        // Preflight the full aggregate, including repeated targets, before consuming anything.
        for(var e:required.entrySet())if(inv.clearOrCountMatchingItems(stack->stack.is(e.getKey()),0,p.inventoryMenu.getCraftSlots())<e.getValue())return false;
        for(var e:required.entrySet())inv.clearOrCountMatchingItems(stack->stack.is(e.getKey()),e.getValue(),p.inventoryMenu.getCraftSlots());
        for(var o:q.objectives())if("submit_item".equals(o.type()))d.set(id,o.id(),o.count());
        if(isComplete(d,q))finish(p,d,q);return true;
    }
    public static boolean claim(ServerPlayer p,String id){
        QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);QuestPlayerData d=data(p);if(q==null||!d.completed.remove(id))return false;
        d.claimed.add(id);
        if(!q.rewardLootTable().isBlank())p.level().getServer().getCommands().performPrefixedCommand(
                p.level().getServer().createCommandSourceStack().withSuppressedOutput(),"loot give "+StringArgumentType.escapeIfRequired(p.getScoreboardName())+" loot "+q.rewardLootTable());
        if(q.experience()>0)p.giveExperiencePoints(q.experience());
        return true;
    }
    private static boolean isComplete(QuestPlayerData d,QuestDefinition q){for(var o:q.objectives())if(d.get(q.id().toString(),o.id())<o.count())return false;return true;}
    private static void finish(ServerPlayer p,QuestPlayerData d,QuestDefinition q){String id=q.id().toString();d.active.remove(id);if(d.completed.add(id))p.playSound(SoundEvents.PLAYER_LEVELUP,1.0F,1.0F);}
    private static boolean prerequisitesMet(QuestPlayerData d,QuestDefinition q){return q.prerequisites().stream().allMatch(id -> d.completed.contains(id)||d.claimed.contains(id));}
    public static String state(ServerPlayer p,String id){
        QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);if(q==null)return "not_found";
        QuestPlayerData d=data(p);
        if(d.claimed.contains(id))return "claimed";
        if(d.completed.contains(id))return "complete";
        if(d.active.contains(id))return "active";
        return prerequisitesMet(d,q)?"available":"locked";
    }
    public static void sync(ServerPlayer p){sync(p,false);}
    public static void sync(ServerPlayer p,boolean open){if(ServerPlayNetworking.canSend(p,QuestPayloads.Sync.TYPE))ServerPlayNetworking.send(p,new QuestPayloads.Sync(snapshot(p),open));}
    private static String snapshot(ServerPlayer p){
        QuestPlayerData d=data(p);JsonObject root=new JsonObject();root.addProperty("date",d.dailyDate);JsonArray pages=new JsonArray();
        QuestRegistry.INSTANCE.pages.values().stream().sorted(Comparator.comparingInt(QuestPage::order)).forEach(page->{
            JsonObject po=new JsonObject();po.addProperty("id",page.id().toString());po.addProperty("title",page.title());po.addProperty("type",page.type());JsonArray list=new JsonArray();
            List<String> ids;
            if("special_daily".equals(page.type()))ids=d.daily.stream().filter(id->{var q=QuestRegistry.INSTANCE.quests.get(id);return q!=null&&page.id().toString().equals(q.page());}).toList();
            else if("daily_pool".equals(page.type()))ids=d.daily.stream().filter(id->{var q=QuestRegistry.INSTANCE.quests.get(id);var owner=q==null?null:QuestRegistry.INSTANCE.pages.get(q.page());return owner==null||!"special_daily".equals(owner.type());}).toList();
            else ids=page.quests();
            for(String id:ids){QuestDefinition q=QuestRegistry.INSTANCE.quests.get(id);if(q==null)continue;if(!q.prerequisites().isEmpty()&&!d.active.contains(id)&&!d.completed.contains(id)&&!d.claimed.contains(id))continue;JsonObject qo=new JsonObject();
                qo.addProperty("id",id);qo.addProperty("title",q.title());qo.addProperty("description",q.description());qo.addProperty("experience",q.experience());
                String state=d.claimed.contains(id)?"claimed":d.completed.contains(id)?"complete":d.active.contains(id)?"active":prerequisitesMet(d,q)?"available":"locked";qo.addProperty("state",state);
                JsonArray os=new JsonArray();for(var o:q.objectives()){JsonObject x=new JsonObject();x.addProperty("id",o.id());x.addProperty("type",o.type());x.addProperty("target",o.target());x.addProperty("now",d.get(id,o.id()));x.addProperty("count",o.count());os.add(x);}qo.add("objectives",os);
                JsonArray rs=new JsonArray();for(var r:q.displayRewards()){JsonObject x=new JsonObject();x.addProperty("item",r.item());x.addProperty("min",r.min());x.addProperty("max",r.max());if(r.ominousBottleAmplifier()>=0)x.addProperty("ominous_bottle_amplifier",r.ominousBottleAmplifier());rs.add(x);}qo.add("rewards",rs);list.add(qo);
            }po.add("quests",list);pages.add(po);
        });root.add("pages",pages);return GSON.toJson(root);
    }
}
