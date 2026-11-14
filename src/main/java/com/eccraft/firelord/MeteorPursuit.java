package com.eccraft.firelord;
import net.minecraft.world.phys.Vec3;
public final class MeteorPursuit {
 public static Vec3 velocity(Vec3 current,Vec3 target,double speed){
  Vec3 delta=new Vec3(target.x-current.x,0,target.z-current.z);double distance=delta.length();
  return distance<1e-8?Vec3.ZERO:delta.scale(Math.min(speed,distance)/distance);
 }
 private MeteorPursuit(){}
}
