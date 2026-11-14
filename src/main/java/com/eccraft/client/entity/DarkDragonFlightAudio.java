package com.eccraft.client.entity;

import com.eccraft.darkdragon.DarkDragonAction;
import com.eccraft.darkdragon.DarkDragonSounds;
import com.eccraft.entity.DarkDragonEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import java.util.*;

/** One positional loop per loaded dragon; never issue a new sound packet every server tick. */
public final class DarkDragonFlightAudio {
    private record Playing(Flight sound, long started) {}
    private static final Map<UUID,Playing> loops=new HashMap<>();
    private static long ticks;
    private DarkDragonFlightAudio() {}
    public static void register(){ClientTickEvents.END_CLIENT_TICK.register(DarkDragonFlightAudio::tick);}
    private static boolean wanted(DarkDragonEntity dragon){
        var action=dragon.dragonAction();
        return dragon.isAlive()&&!dragon.isRemoved()&&(dragon.airborne()||action==DarkDragonAction.TAKEOFF||action==DarkDragonAction.LANDING);
    }
    private static void tick(Minecraft client){
        if(client.isPaused())return;
        ticks++;var manager=client.getSoundManager();Set<UUID> present=new HashSet<>();
        if(client.level!=null)for(var entity:client.level.entitiesForRendering())if(entity instanceof DarkDragonEntity dragon&&wanted(dragon)){
            UUID id=dragon.getUUID();present.add(id);var active=loops.get(id);
            if(active!=null&&(active.sound().dragon!=dragon||active.sound().isStopped()||ticks-active.started()>40&&!manager.isActive(active.sound()))){manager.stop(active.sound());loops.remove(id);active=null;}
            if(active==null){var sound=new Flight(dragon);loops.put(id,new Playing(sound,ticks));manager.play(sound);}
        }
        var it=loops.entrySet().iterator();while(it.hasNext()){var entry=it.next();if(!present.contains(entry.getKey())){manager.stop(entry.getValue().sound());it.remove();}}
    }
    private static final class Flight extends AbstractTickableSoundInstance {
        final DarkDragonEntity dragon;
        Flight(DarkDragonEntity dragon){super(DarkDragonSounds.FLY,SoundSource.HOSTILE,RandomSource.create());this.dragon=dragon;looping=true;delay=0;volume=1;pitch=1;relative=false;updatePosition();}
        private void updatePosition(){x=dragon.getX();y=dragon.getY();z=dragon.getZ();}
        @Override public void tick(){if(!wanted(dragon)){stop();return;}updatePosition();}
    }
}
