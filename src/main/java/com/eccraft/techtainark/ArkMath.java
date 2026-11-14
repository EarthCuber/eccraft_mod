package com.eccraft.techtainark;

import java.util.List;
import net.minecraft.world.phys.Vec3;

public final class ArkMath {
    private ArkMath() {}
    /** Only permutes existing references; does not copy or mutate any stack. */
    public static <T> void rotateHotbar(List<T> slots) {
        if(slots.size()<9) throw new IllegalArgumentException("Nine hotbar slots required");
        var snapshot=new java.util.ArrayList<T>(slots.subList(0,9));
        for(int i=0;i<9;i++) slots.set(i,snapshot.get((i+1)%9));
    }
    public static Vec3 dash(Vec3 origin,Vec3 target,Vec3 velocity,double speed,boolean predict) {
        double time=origin.distanceTo(target)/speed;
        Vec3 aim=predict?target.add(velocity.x*time,velocity.y*time*.08,velocity.z*time):target;
        Vec3 flat=new Vec3(aim.x-origin.x,0,aim.z-origin.z);
        return flat.horizontalDistanceSqr()<1e-10?Vec3.ZERO:flat.normalize().scale(speed);
    }
    public static int phase(float health,float max,int previous) {
        return Math.max(previous,health<=max*.3F?4:health<=max*.5F?3:health<=max*.8F?2:1);
    }
}
