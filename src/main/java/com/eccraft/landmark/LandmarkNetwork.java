package com.eccraft.landmark;
import com.eccraft.Eccraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
public final class LandmarkNetwork {
 public record Request(String json) implements CustomPacketPayload {
  public static final Type<Request> TYPE=new Type<>(Eccraft.id("landmark_request"));
  public static final StreamCodec<RegistryFriendlyByteBuf,Request> CODEC=StreamCodec.ofMember((v,b)->b.writeUtf(v.json,8192),b->new Request(b.readUtf(8192)));
  public Type<? extends CustomPacketPayload> type(){return TYPE;}
 }
 public record Sync(String json) implements CustomPacketPayload {
  public static final Type<Sync> TYPE=new Type<>(Eccraft.id("landmark_sync"));
  public static final StreamCodec<RegistryFriendlyByteBuf,Sync> CODEC=StreamCodec.ofMember((v,b)->b.writeUtf(v.json,262144),b->new Sync(b.readUtf(262144)));
  public Type<? extends CustomPacketPayload> type(){return TYPE;}
 }
}
