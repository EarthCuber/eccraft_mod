package com.eccraft.combat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.*;
import net.minecraft.client.Minecraft;
/** Uses the same movement/collision path as walking; vertical velocity and gravity are untouched. */
public final class MovementClient {
 private static net.minecraft.client.multiplayer.ClientLevel world;
 private static int remaining;private static double x,z;private static boolean running,started;
 public static void register(){
  ClientPlayNetworking.registerGlobalReceiver(MovementPayload.TYPE,(p,c)->{world=c.client().level;x=p.x();z=p.z();remaining=Math.clamp(p.ticks(),0,100);running=true;started=false;});
  ClientPlayConnectionEvents.DISCONNECT.register((h,c)->clear());
  ClientTickEvents.START_CLIENT_TICK.register(MovementClient::tick);
 }
 private static void clear(){remaining=0;running=started=false;world=null;}
 private static void tick(Minecraft c){if(!running)return;if(c.level!=world||c.player==null||!c.player.isAlive()||c.player.isSpectator()||c.player.isPassenger()){clear();return;}if(c.isPaused())return;
  var v=c.player.getDeltaMovement();
  if(remaining<=0||(started&&c.player.horizontalCollision)){c.player.setDeltaMovement(0,v.y,0);clear();return;}
  c.player.setDeltaMovement(x,v.y,z);remaining--;started=true;
 }
 private MovementClient(){}
}
