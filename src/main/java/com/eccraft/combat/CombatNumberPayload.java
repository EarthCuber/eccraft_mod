package com.eccraft.combat;
import com.eccraft.Eccraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
public record CombatNumberPayload(double x,double y,double z,int amount,int style,boolean self) implements CustomPacketPayload {
 public static final Type<CombatNumberPayload> TYPE=new Type<>(Eccraft.id("combat_number"));
 public static final StreamCodec<FriendlyByteBuf,CombatNumberPayload> CODEC=new StreamCodec<>(){
  public CombatNumberPayload decode(FriendlyByteBuf b){return new CombatNumberPayload(b.readDouble(),b.readDouble(),b.readDouble(),b.readVarInt(),b.readVarInt(),b.readBoolean());}
  public void encode(FriendlyByteBuf b,CombatNumberPayload p){b.writeDouble(p.x);b.writeDouble(p.y);b.writeDouble(p.z);b.writeVarInt(p.amount);b.writeVarInt(p.style);b.writeBoolean(p.self);}
 };
 public Type<CombatNumberPayload> type(){return TYPE;}
}
