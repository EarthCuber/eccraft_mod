package com.eccraft.effect;
import com.eccraft.Eccraft;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import java.util.*;
public final class FirelordLavaOutEffect extends MobEffect {
 public static final Holder<MobEffect> EFFECT=Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,Eccraft.id("firelord_lava_out"),new FirelordLavaOutEffect());
 private final Map<LivingEntity,Integer> clocks=new WeakHashMap<>();
 private FirelordLavaOutEffect(){super(MobEffectCategory.HARMFUL,0xED6A15);}
 @Override public void onEffectStarted(LivingEntity entity,int amplifier){super.onEffectStarted(entity,amplifier);clocks.put(entity,0);}
 @Override public boolean shouldApplyEffectTickThisTick(int duration,int amplifier){return true;}
 @Override public boolean applyEffectTick(ServerLevel level,LivingEntity entity,int amplifier){
  int elapsed=clocks.getOrDefault(entity,0)+1;clocks.put(entity,elapsed%40);
  if(elapsed>=40){var lava=new com.eccraft.entity.FirelordLava(com.eccraft.firelord.FirelordContent.LAVA,level);lava.setPos(entity.position().add(0,.5,0));level.addFreshEntity(lava);}
  return true;
 }
 public static void register(){}
}
