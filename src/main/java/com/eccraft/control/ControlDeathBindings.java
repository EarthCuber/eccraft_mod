package com.eccraft.control;
import com.eccraft.Eccraft;
import com.mojang.serialization.Codec;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.*;
import java.util.*;
public final class ControlDeathBindings extends SavedData {
 private final Map<String,String> values;
 private ControlDeathBindings(){values=new HashMap<>();}
 private ControlDeathBindings(Map<String,String> values){this.values=new HashMap<>(values);}
 private static final Codec<ControlDeathBindings> CODEC=Codec.unboundedMap(Codec.STRING,Codec.STRING).xmap(ControlDeathBindings::new,data->data.values);
 private static final SavedDataType<ControlDeathBindings> TYPE=new SavedDataType<>(Eccraft.id("control_death_bindings"),ControlDeathBindings::new,CODEC,null);
 public static ControlDeathBindings get(MinecraftServer server){return server.overworld().getDataStorage().computeIfAbsent(TYPE);}
 public UUID bound(UUID owner){var id=values.get(owner.toString());return id==null?null:UUID.fromString(id);}
 public void bind(UUID owner,UUID entity){values.put(owner.toString(),entity.toString());setDirty();}
 public void clear(UUID owner,UUID expected){if(Objects.equals(bound(owner),expected)){values.remove(owner.toString());setDirty();}}
}
