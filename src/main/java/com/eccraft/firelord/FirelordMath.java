package com.eccraft.firelord;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
public final class FirelordMath {
    public static final double SKILL_1_RADIUS=16;
    public static Vec3 cardinal(Vec3 home,int index){return home.add(switch(index){case 0->19;case 1->-19;default->0;},.5,switch(index){case 2->19;case 3->-19;default->0;});}
    public static Quaternionf rolled(org.joml.Quaternionfc current,Vec3 delta){
        double distance=delta.horizontalDistance();if(distance<1e-7)return new Quaternionf(current);
        // Radius 1 for a two-block-wide model. Roll about world-up cross travel direction.
        return new Quaternionf().rotationAxis((float)distance,(float)(delta.z/distance),0,(float)(-delta.x/distance)).mul(current).normalize();
    }
    public static boolean sector(Vec3 origin,float yaw,Vec3 point){var d=point.subtract(origin);return d.y>=0&&d.y<=8&&com.eccraft.darkdragon.DarkDragonMath.inSector(d.x,d.z,yaw,SKILL_1_RADIUS,false);}
    private FirelordMath(){}
}
