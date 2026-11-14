package com.eccraft.combat;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
public final class CombatFeedback {
 public static void register(){PayloadTypeRegistry.clientboundPlay().register(CombatNumberPayload.TYPE,CombatNumberPayload.CODEC);}
 public static int displayAmount(double amount){return !Double.isFinite(amount)||amount<1?0:(int)Math.min(Integer.MAX_VALUE,Math.floor(amount));}
 public static void show(Entity owner,LivingEntity target,double amount,int style){int number=displayAmount(amount);
  if(number>0&&owner instanceof ServerPlayer p&&ServerPlayNetworking.canSend(p,CombatNumberPayload.TYPE))
   ServerPlayNetworking.send(p,new CombatNumberPayload(target.getX(),target.getY()+target.getBbHeight()*.8,target.getZ(),number,style,p==target));
 }
 public static void heal(Entity owner,LivingEntity target,float amount){if(amount<=0||!target.isAlive())return;boolean corroded=com.eccraft.nightgod.NightCorrosion.converts(target);target.heal(amount);if(!corroded)show(owner,target,amount,2);}
 private CombatFeedback(){}
}
