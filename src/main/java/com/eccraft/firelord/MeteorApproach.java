package com.eccraft.firelord;
import net.minecraft.world.phys.Vec3;
/** Deterministic geometry for the meteor approach; random samples are supplied by the server. */
public final class MeteorApproach {
    public static Vec3 landing(Vec3 center,double radialSample,double angleSample){
        double radius=Math.sqrt(64+192*Math.clamp(radialSample,0,1));
        double angle=angleSample*Math.PI*2;
        return center.add(Math.cos(angle)*radius,0,Math.sin(angle)*radius);
    }
    public static Vec3 step(Vec3 current,Vec3 target,double speed){
        var delta=target.subtract(current);double length=delta.length();
        return length<=speed?delta:delta.scale(speed/length);
    }
    private MeteorApproach(){}
}
