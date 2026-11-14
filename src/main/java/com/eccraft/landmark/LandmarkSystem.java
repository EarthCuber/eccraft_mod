package com.eccraft.landmark;
import com.google.gson.*;
import com.eccraft.Eccraft;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.*;
import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public final class LandmarkSystem implements ModInitializer {
 public static final List<String> TAGS=List.of("基地","仓库","机器","景点","其他");
 private static final Gson GSON=new GsonBuilder().setPrettyPrinting().create();
 private static Store store=new Store();private static Path file;private static boolean ready;
 private static final Map<UUID,Long> tracking=new HashMap<>();
 private static final Map<UUID,Long> requests=new HashMap<>();
 public static final class Entry {
  public long id,created;public String owner,creator,dimension,name,description,tag;public int x,y,z;
  public Set<String> likes=new HashSet<>(),favorites=new HashSet<>();
 }
 public static final class Store {public long nextId=1;public List<Entry> entries=new ArrayList<>();}
 public void onInitialize(){
  PayloadTypeRegistry.serverboundPlay().register(LandmarkNetwork.Request.TYPE,LandmarkNetwork.Request.CODEC);
  PayloadTypeRegistry.clientboundPlay().registerLarge(LandmarkNetwork.Sync.TYPE,LandmarkNetwork.Sync.CODEC,262144);
  ServerLifecycleEvents.SERVER_STARTED.register(s->{tracking.clear();requests.clear();ready=false;file=s.getWorldPath(LevelResource.ROOT).resolve("data/eccraft_landmarks.json");try{store=Files.exists(file)?GSON.fromJson(Files.readString(file),Store.class):new Store();if(store==null||store.entries==null)throw new IllegalStateException("Invalid landmark store");store.nextId=Math.max(store.nextId,store.entries.stream().mapToLong(e->e.id).max().orElse(0)+1);ready=true;}catch(Exception e){Eccraft.LOGGER.error("Landmark data could not be loaded; writes disabled",e);}});
  ServerLifecycleEvents.SERVER_STOPPED.register(s->{tracking.clear();requests.clear();ready=false;});
  ServerPlayConnectionEvents.DISCONNECT.register((h,s)->{tracking.remove(h.player.getUUID());requests.remove(h.player.getUUID());});
  ServerPlayConnectionEvents.JOIN.register((h,sender,s)->{if(!ready)return;boolean dirty=false;for(Entry e:store.entries)if(e.owner.equals(h.player.getUUID().toString())&&!e.creator.equals(h.player.getScoreboardName())){e.creator=h.player.getScoreboardName();dirty=true;}if(dirty)save();});
  ServerPlayNetworking.registerGlobalReceiver(LandmarkNetwork.Request.TYPE,(payload,ctx)->{try{handle(ctx.player(),JsonParser.parseString(payload.json()).getAsJsonObject());}catch(Exception e){message(ctx.player(),"路标操作失败，请检查输入");Eccraft.LOGGER.warn("Landmark request failed",e);}});
  ServerTickEvents.END_SERVER_TICK.register(s->{if(s.getTickCount()%5!=0)return;for(ServerPlayer p:s.getPlayerList().getPlayers()){Long id=tracking.get(p.getUUID());if(id==null)continue;Entry e=find(id);String reason=e==null?"地标已移除，追踪已取消":!dimension(p).equals(e.dimension)?"维度改变，追踪已取消":p.position().distanceToSqr(new net.minecraft.world.phys.Vec3(e.x,e.y,e.z))<64?"已接近目标，追踪已结束":null;if(reason!=null){tracking.remove(p.getUUID());message(p,reason);sendTrack(p,null,"已接近目标，追踪已结束".equals(reason));}}});
 }
 private static String dimension(ServerPlayer p){return p.level().dimension().identifier().toString();}
 private static Entry find(long id){return store.entries.stream().filter(e->e.id==id).findFirst().orElse(null);}
 private static void message(ServerPlayer p,String s){p.sendOverlayMessage(Component.literal(s));}
 private static void save(){try{Files.createDirectories(file.getParent());Path temp=file.resolveSibling(file.getFileName()+".tmp");Files.writeString(temp,GSON.toJson(store),StandardCharsets.UTF_8);try{Files.move(temp,file,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.ATOMIC_MOVE);}catch(AtomicMoveNotSupportedException e){Files.move(temp,file,StandardCopyOption.REPLACE_EXISTING);}}catch(Exception e){throw new IllegalStateException("Unable to save landmarks",e);}}
 private static JsonObject publicEntry(Entry e,ServerPlayer p){JsonObject o=new JsonObject();o.addProperty("id",e.id);o.addProperty("created",e.created);o.addProperty("owner",e.owner);o.addProperty("creator",e.creator);o.addProperty("name",e.name);o.addProperty("description",e.description);o.addProperty("tag",e.tag);o.addProperty("dimension",e.dimension);o.addProperty("x",e.x);o.addProperty("y",e.y);o.addProperty("z",e.z);o.addProperty("likes",e.likes.size());o.addProperty("liked",e.likes.contains(p.getUUID().toString()));o.addProperty("favorite",e.favorites.contains(p.getUUID().toString()));o.addProperty("mine",e.owner.equals(p.getUUID().toString()));return o;}
 private static void send(ServerPlayer p,JsonObject o){ServerPlayNetworking.send(p,new LandmarkNetwork.Sync(o.toString()));}
 private static void sendTrack(ServerPlayer p,Entry e){sendTrack(p,e,false);}
 private static void sendTrack(ServerPlayer p,Entry e,boolean arrived){JsonObject o=new JsonObject();o.addProperty("arrived",arrived);o.addProperty("kind","track");if(e!=null)o.add("target",publicEntry(e,p));send(p,o);}
 private static String str(JsonObject o,String key,String fallback){return o.has(key)?o.get(key).getAsString():fallback;}
 private static void toggle(Set<String> s,String id){if(!s.remove(id))s.add(id);}
 private static void handle(ServerPlayer p,JsonObject q){if(!ready){message(p,"路标数据不可用，请联系管理员");return;}long tick=p.level().getGameTime();Long last=requests.put(p.getUUID(),tick);if(last!=null&&tick-last<2)return;
  String op=str(q,"op","list"),uuid=p.getUUID().toString();long id=q.has("id")?q.get("id").getAsLong():0;Entry e=find(id);boolean admin=p.createCommandSourceStack().permissions().hasPermission(Permissions.COMMANDS_ADMIN);
  if(op.equals("track")){if(Objects.equals(tracking.get(p.getUUID()),id)){tracking.remove(p.getUUID());sendTrack(p,null);return;}if(e==null)return;if(!dimension(p).equals(e.dimension)){message(p,"不在地标所在维度，无法追踪");return;}tracking.put(p.getUUID(),id);sendTrack(p,e);return;}
  Store before=GSON.fromJson(GSON.toJson(store),Store.class);boolean dirty=false;
  if(op.equals("create")||op.equals("edit")){String name=str(q,"name","").strip(),description=str(q,"description",""),tag=str(q,"tag","其他");if(name.isBlank()||name.codePointCount(0,name.length())>24||description.codePointCount(0,description.length())>200||!TAGS.contains(tag)){message(p,"名称1–24字，简介最多200字，请选择有效标签");return;}if(op.equals("edit")&&(e==null||!e.owner.equals(uuid)))return;if(op.equals("create")){e=new Entry();e.id=store.nextId++;e.created=System.currentTimeMillis();e.owner=uuid;e.creator=p.getScoreboardName();e.dimension=dimension(p);var pos=p.blockPosition();e.x=pos.getX();e.y=pos.getY();e.z=pos.getZ();store.entries.add(e);}e.name=name;e.description=description;e.tag=tag;dirty=true;}
  else if(e!=null&&op.equals("like")){if(e.owner.equals(uuid)){message(p,"不能给自己的地标点赞");return;}toggle(e.likes,uuid);dirty=true;}
  else if(e!=null&&op.equals("favorite")){toggle(e.favorites,uuid);dirty=true;}
  else if(e!=null&&op.equals("delete")){if(!admin)return;store.entries.remove(e);dirty=true;}
  if(dirty){try{save();}catch(Exception ex){store=before;throw ex;}}
  String tag=str(q,"filterTag",""),creator=str(q,"creator",""),sort=str(q,"sort","newest");boolean favorite=q.has("onlyFavorites")&&q.get("onlyFavorites").getAsBoolean();
  var stream=store.entries.stream().filter(a->(tag.isEmpty()||a.tag.equals(tag))&&(!favorite||a.favorites.contains(uuid))&&(creator.isEmpty()||a.creator.equalsIgnoreCase(creator)||a.owner.equals(creator)));
  Comparator<Entry> cmp=sort.equals("likes")?Comparator.<Entry>comparingInt(a->a.likes.size()).reversed().thenComparingLong(a->a.id):sort.equals("oldest")?Comparator.comparingLong(a->a.id):Comparator.<Entry>comparingLong(a->a.id).reversed();List<Entry> all=stream.sorted(cmp).toList();int offset=Math.clamp(q.has("offset")?q.get("offset").getAsInt():0,0,Math.max(0,all.size()-1));JsonArray rows=new JsonArray();for(Entry a:all.subList(offset,Math.min(offset+40,all.size())))rows.add(publicEntry(a,p));JsonObject result=new JsonObject();result.addProperty("kind","list");result.addProperty("admin",admin);result.addProperty("offset",offset);result.addProperty("total",all.size());result.add("rows",rows);send(p,result);
 }
}

