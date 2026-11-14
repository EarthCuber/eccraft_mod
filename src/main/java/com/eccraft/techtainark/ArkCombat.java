package com.eccraft.techtainark;

import com.eccraft.entity.TechtainArkEntity;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.*;
import java.util.*;

public final class ArkCombat {
    public static boolean valid(ServerPlayer p){return p.isAlive()&&!p.isCreative()&&!p.isSpectator();}
    public static List<ServerPlayer> players(ServerLevel level,Entity e,double range){return level.getPlayers(p->valid(p)&&e.distanceToSqr(p)<=range*range);}
    public static void shuffle(ServerPlayer p){
        // Server tick is serial with inventory clicks. Preserve stack references, selected slot and carried stack.
        ArkMath.rotateHotbar(p.getInventory().getNonEquipmentItems());
        p.getInventory().setChanged();
        p.inventoryMenu.broadcastChanges();
        if(p.containerMenu!=p.inventoryMenu)p.containerMenu.broadcastChanges();
    }
    public static void face(Mob mob,Vec3 d){if(d.horizontalDistanceSqr()>1e-9){float yaw=(float)Math.toDegrees(Math.atan2(d.z,d.x))-90;mob.setYRot(yaw);mob.setYBodyRot(yaw);mob.setYHeadRot(yaw);}}
    /** Swept moving AABB versus each target, sorted along the actually travelled segment. */
    public static List<Entity> hits(ServerLevel level,Mob mob,AABB before,Vec3 from,Vec3 to,TechtainArkEntity owner){
        Vec3 delta=to.subtract(from);double half=before.getXsize()/2,height=before.getYsize();
        var result=level.getEntities(mob,before.expandTowards(delta).inflate(.001),e->e.isAlive()&&((e instanceof ServerPlayer p&&valid(p))||e==owner));
        result.removeIf(e->{var b=e.getBoundingBox();var expanded=new AABB(b.minX-half,b.minY-height,b.minZ-half,b.maxX+half,b.maxY,b.maxZ+half);return !expanded.contains(from)&&expanded.clip(from,to).isEmpty();});
        result.sort(Comparator.comparingDouble(e->{var b=e.getBoundingBox();var expanded=new AABB(b.minX-half,b.minY-height,b.minZ-half,b.maxX+half,b.maxY,b.maxZ+half);return expanded.contains(from)?0:expanded.clip(from,to).map(from::distanceToSqr).orElse(Double.MAX_VALUE);}));
        return result;
    }
    public static void launch(ServerPlayer p,Vec3 direction){Vec3 d=new Vec3(direction.x,0,direction.z).normalize();p.setDeltaMovement(d.scale(1.2).add(0,.5,0));p.hurtMarked=true;}
}
