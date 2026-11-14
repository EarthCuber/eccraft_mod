package com.eccraft.firelord;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
public final class FirelordParticles {
    public static void fan(ServerLevel level,Vec3 origin,float yaw){
        for(int ring=1;ring<=6;ring++)for(int step=0;step<=12;step++){
            double a=Math.toRadians(yaw-60+step*10),x=-Math.sin(a),z=Math.cos(a),radius=FirelordMath.SKILL_1_RADIUS*ring/6;
            for(double height:new double[]{.3,1.5})level.sendParticles(ParticleTypes.FLAME,origin.x+x*radius,origin.y+height,origin.z+z*radius,0,x*.15,.04,z*.15,1);
        }
    }
    private FirelordParticles(){}
}
