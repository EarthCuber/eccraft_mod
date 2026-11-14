package com.eccraft.combat;
import com.eccraft.Eccraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
public record MovementPayload(double x,double z,int ticks) implements CustomPacketPayload {
 public static final Type<MovementPayload> TYPE=new Type<>(Eccraft.id("combat_movement"));
 public static final StreamCodec<FriendlyByteBuf,MovementPayload> CODEC=new StreamCodec<>(){
  public MovementPayload decode(FriendlyByteBuf b){return new MovementPayload(b.readDouble(),b.readDouble(),b.readVarInt());}
  public void encode(FriendlyByteBuf b,MovementPayload p){b.writeDouble(p.x);b.writeDouble(p.z);b.writeVarInt(p.ticks);}
 };
 public Type<MovementPayload> type(){return TYPE;}
}
