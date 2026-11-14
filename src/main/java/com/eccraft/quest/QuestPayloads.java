package com.eccraft.quest;

import com.eccraft.Eccraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public final class QuestPayloads {
    private QuestPayloads() {}
    public record Request(String action, String quest) implements CustomPacketPayload {
        public static final Type<Request> TYPE = new Type<>(Eccraft.id("quest_request"));
        public static final StreamCodec<RegistryFriendlyByteBuf,Request> CODEC=StreamCodec.ofMember(
                (v,b)->{b.writeUtf(v.action);b.writeUtf(v.quest);}, b->new Request(b.readUtf(),b.readUtf()));
        @Override public Type<? extends CustomPacketPayload> type(){return TYPE;}
    }
    public record Sync(String json, boolean open) implements CustomPacketPayload {
        public static final Type<Sync> TYPE = new Type<>(Eccraft.id("quest_sync"));
        public static final StreamCodec<RegistryFriendlyByteBuf,Sync> CODEC=StreamCodec.ofMember(
                (v,b)->{b.writeUtf(v.json,1_000_000);b.writeBoolean(v.open);}, b->new Sync(b.readUtf(1_000_000),b.readBoolean()));
        @Override public Type<? extends CustomPacketPayload> type(){return TYPE;}
    }
}
