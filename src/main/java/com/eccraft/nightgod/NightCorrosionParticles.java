package com.eccraft.nightgod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.particles.*;
public final class NightCorrosionParticles {
 public static final double RADIUS=32;
 public static final int PARTICLES_PER_PULSE=24;
 public static void emit(ServerLevel level,Vec3 center){
  var particle=ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT,0xFF000000|MobEffects.WITHER.value().getColor());
  for(int n=0;n<PARTICLES_PER_PULSE;n++){
   double radius=RADIUS*Math.sqrt(level.getRandom().nextDouble()),angle=level.getRandom().nextDouble()*Math.PI*2;
   level.sendParticles(particle,center.x+radius*Math.cos(angle),center.y,center.z+radius*Math.sin(angle),0,0,.15,0,1);
  }
 }
 private NightCorrosionParticles(){}
}
