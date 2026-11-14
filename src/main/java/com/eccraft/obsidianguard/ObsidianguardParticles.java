package com.eccraft.obsidianguard;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

/** Fibonacci sphere: flame particles travel outward in all directions like a firework. */
public final class ObsidianguardParticles {
    public static Vec3 direction(int index,int count){
        double y=1.0-2.0*(index+.5)/count;
        double radius=Math.sqrt(Math.max(0,1-y*y));
        double angle=index*Math.PI*(3-Math.sqrt(5));
        return new Vec3(Math.cos(angle)*radius,y,Math.sin(angle)*radius);
    }
    public static void burst(ServerLevel level,Vec3 center,int count,double speed){
        for(int i=0;i<count;i++){
            var v=direction(i,count).scale(speed);
            level.sendParticles(ParticleTypes.FLAME,center.x,center.y,center.z,0,v.x,v.y,v.z,1.0);
        }
    }
    private ObsidianguardParticles(){}
}
