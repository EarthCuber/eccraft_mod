package com.eccraft.combat;
import com.eccraft.Eccraft;
import com.eccraft.archer.*;
import com.eccraft.knight.KnightShieldItem;
import com.eccraft.staff.StaffCooldownPayload;
import java.util.*;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.Vec3;
public final class CombatSkills {
 public static final TagKey<Item> SWORDS=TagKey.create(Registries.ITEM,Eccraft.id("skill_swords"));
 public static int kind(ItemStack stack){return stack.is(SWORDS)?1:stack.getItem() instanceof ArcherBowItem?2:0;}
 public static boolean supported(ItemStack stack){return kind(stack)!=0;}
 public static boolean paired(LivingEntity p){return switch(kind(p.getMainHandItem())){case 1->p.getOffhandItem().getItem() instanceof KnightShieldItem;case 2->p.getOffhandItem().getItem() instanceof QuiverItem;default->false;};}
 public static final class Clock {
  private Object held;private int slot=-1;private long readyAt;
  public void observe(Object next,int nextSlot,boolean active,long now,int cooldown){if(active&&(held!=next||slot!=nextSlot))readyAt=now+cooldown;held=next;slot=nextSlot;}
  public int remaining(long now){return (int)Math.max(0,readyAt-now);}
  public boolean cast(long now,int cooldown){if(remaining(now)>0)return false;readyAt=now+cooldown;return true;}
 }
 private static final class State {final Clock clock=new Clock();int remaining=-1,total=-1;}
 private static final Map<ServerPlayer,State> STATES=new WeakHashMap<>();
 private static long now(ServerPlayer p){return p.level().getServer().overworld().getGameTime();}
 public static void register(){CombatBalance.initialize();TargetPreference.initialize();CombatFeedback.register();PayloadTypeRegistry.clientboundPlay().register(MovementPayload.TYPE,MovementPayload.CODEC);
  ServerTickEvents.END_SERVER_TICK.register(server->server.getPlayerList().getPlayers().forEach(CombatSkills::refresh));}
 public static void observe(ServerPlayer p){refresh(p);}
 private static State refresh(ServerPlayer p){State s=STATES.computeIfAbsent(p,k->new State());boolean active=supported(p.getMainHandItem());int total=CombatBalance.get().cooldown(kind(p.getMainHandItem())==2);
  s.clock.observe(p.getMainHandItem(),p.getInventory().getSelectedSlot(),active,now(p),total);
  if(active)sync(p,s,total);else{s.remaining=-1;s.total=-1;}return s;}
 private static void sync(ServerPlayer p,State s,int total){int remaining=s.clock.remaining(now(p));if((remaining!=s.remaining||total!=s.total)&&ServerPlayNetworking.canSend(p,StaffCooldownPayload.TYPE)){
  ServerPlayNetworking.send(p,new StaffCooldownPayload(remaining,total));s.remaining=remaining;s.total=total;}}
 public static Vec3 direction(float yaw,boolean backward,double speed){return Vec3.directionFromRotation(0,yaw).scale(backward?-speed:speed);}
 public static boolean cast(ServerPlayer p){
  if(!p.isAlive()||p.isSpectator()||p.isPassenger()||!paired(p)||!ServerPlayNetworking.canSend(p,MovementPayload.TYPE))return false;
  State s=refresh(p);var c=CombatBalance.get();int cooldown=c.cooldown(kind(p.getMainHandItem())==2);if(!s.clock.cast(now(p),cooldown))return false;
  Vec3 v=direction(p.getYRot(),kind(p.getMainHandItem())==2,c.distance(kind(p.getMainHandItem())==2)/c.movementTicks());
  p.stopUsingItem();ServerPlayNetworking.send(p,new MovementPayload(v.x,v.z,c.movementTicks()));
  p.getMainHandItem().hurtAndBreak(c.durabilityCost(),p,EquipmentSlot.MAINHAND);p.getOffhandItem().hurtAndBreak(c.durabilityCost(),p,EquipmentSlot.OFFHAND);
  sync(p,s,cooldown);return true;
 }
 private CombatSkills(){}
}
