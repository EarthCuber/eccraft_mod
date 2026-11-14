package com.eccraft.nightgod;
import net.minecraft.world.phys.Vec3;
public final class NightgodMath {
    public static double height(boolean rising,long elapsed,int duration,double base,double rise){
        double t=Math.clamp((double)elapsed/duration,0,1);return base+rise*(rising?t:1-t);
    }
    public static Vec3 behind(Vec3 feet,float yaw){double a=Math.toRadians(yaw);return feet.add(Math.sin(a),0,-Math.cos(a));}
    public static Vec3 randomDisk(Vec3 center,double radius,double angleUnit,double radiusUnit){double a=angleUnit*Math.PI*2,r=Math.sqrt(radiusUnit)*radius;return center.add(Math.cos(a)*r,0,Math.sin(a)*r);}
    private NightgodMath(){}
}
