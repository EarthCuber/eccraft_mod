package com.eccraft.darkdragon;

import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

/** Scripted flight parameters, measured in blocks and server ticks (20 ticks = 1 second). */
public final class DarkDragonCinematics {
    public static final int ENTRANCE_TICKS=1800, ESCAPE_TICKS=300;
    public static final double ENTRANCE_RADIUS=45, ENTRANCE_HEIGHT=60, ENTRANCE_TURNS=2;
    public static final double ESCAPE_DISTANCE=96, ESCAPE_RISE=24, ESCAPE_HEALTH_RATIO=0.30;
    private static final class Tickets {
        static final TicketType FLIGHT=Registry.register(BuiltInRegistries.TICKET_TYPE,Eccraft.id("dragon_cinematic"),new TicketType(40,14));
    }
    public static void register() { var registered=Tickets.FLIGHT; }
    public static boolean shouldEscape(float health,float maximum,boolean won){return !won&&health>0&&health<maximum*ESCAPE_HEALTH_RATIO;}
    public static Vec3 entrance(Vec3 center,double startAngle,int tick){
        double u=Math.clamp(tick/(double)Math.max(1,ENTRANCE_TICKS),0,1);
        double radius=ENTRANCE_RADIUS*(1-u),angle=startAngle+u*Math.PI*2*ENTRANCE_TURNS;
        return center.add(Math.cos(angle)*radius,ENTRANCE_HEIGHT*(1-u),Math.sin(angle)*radius);
    }
    public static Vec3 escape(Vec3 start,double yaw,int tick){
        double u=Math.clamp(tick/(double)Math.max(1,ESCAPE_TICKS),0,1);
        return start.add(-Math.sin(yaw)*ESCAPE_DISTANCE*u,ESCAPE_RISE*u*u,Math.cos(yaw)*ESCAPE_DISTANCE*u);
    }
    // Short-lived, shared tickets expire by themselves: no permanent forced chunks, including after crashes.
    public static void keepLoaded(ServerLevel level,Vec3 position){level.getChunkSource().addTicketWithRadius(Tickets.FLIGHT,new ChunkPos((int)Math.floor(position.x)>>4,(int)Math.floor(position.z)>>4),2);}
    private DarkDragonCinematics() {}
}
