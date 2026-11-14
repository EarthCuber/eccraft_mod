package com.eccraft.sphinx;

import com.eccraft.Eccraft;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

/** Explicit velocities are sent with count=0; each particle is generated once at the skill event. */
public final class SphinxParticles {
    public static final SimpleParticleType SAND=Registry.register(BuiltInRegistries.PARTICLE_TYPE,Eccraft.id("sphinx_sand"),FabricParticleTypes.simple());
    public static final int FORWARD_COUNT=64, RADIAL_COUNT=96, LIFETIME_TICKS=28;
    public static final double FRONT_OFFSET=1.2, BASE_HEIGHT=1.0;
    public static final double FORWARD_SPEED=0.66, RADIAL_SPEED=0.48;
    public static void register(){}
    public static void burst(ServerLevel level,RandomSource random,Vec3 position,float yaw,boolean radial){
        double angle=Math.toRadians(yaw),fx=-Math.sin(angle),fz=Math.cos(angle),rx=fz,rz=-fx;
        Vec3 origin=position.add(fx*FRONT_OFFSET,BASE_HEIGHT,fz*FRONT_OFFSET);
        int count=radial?RADIAL_COUNT:FORWARD_COUNT;
        for(int i=0;i<count;i++){
            double lateral=(random.nextDouble()-.5)*(radial?.8:2.6),height=random.nextDouble()*.8;
            double vx,vz;
            if(radial){
                double spread=(i+random.nextDouble())*Math.PI*2/count;
                double speed=RADIAL_SPEED*(.75+random.nextDouble()*.5);
                vx=Math.cos(spread)*speed;vz=Math.sin(spread)*speed;
            }else{
                double speed=FORWARD_SPEED*(.85+random.nextDouble()*.3);
                vx=fx*speed;vz=fz*speed;
            }
            level.sendParticles(SAND,origin.x+rx*lateral,origin.y+height,origin.z+rz*lateral,
                    0,vx,.015+random.nextDouble()*.025,vz,1);
        }
    }
    private SphinxParticles(){}
}
