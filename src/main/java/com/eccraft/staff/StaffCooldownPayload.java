package com.eccraft.staff;

import com.eccraft.Eccraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/** Server-authoritative HUD data only. The server still validates every cast. */
public record StaffCooldownPayload(int remainingTicks, int totalTicks) implements CustomPacketPayload {
    public static final Type<StaffCooldownPayload> TYPE = new Type<>(Eccraft.id("staff_cooldown"));
    public static final StreamCodec<FriendlyByteBuf, StaffCooldownPayload> CODEC = new StreamCodec<>() {
        @Override public StaffCooldownPayload decode(FriendlyByteBuf buf) { return new StaffCooldownPayload(buf.readVarInt(), buf.readVarInt()); }
        @Override public void encode(FriendlyByteBuf buf, StaffCooldownPayload payload) {
            buf.writeVarInt(payload.remainingTicks()); buf.writeVarInt(payload.totalTicks());
        }
    };
    @Override public Type<StaffCooldownPayload> type() { return TYPE; }
}
