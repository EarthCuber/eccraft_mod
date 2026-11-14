package com.eccraft.staff;

import com.eccraft.Eccraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/** The client requests a cast; it never supplies damage, route, targets or cooldown. */
public record StaffSkillPayload() implements CustomPacketPayload {
    public static final StaffSkillPayload INSTANCE = new StaffSkillPayload();
    public static final Type<StaffSkillPayload> TYPE = new Type<>(Eccraft.id("staff_skill"));
    public static final StreamCodec<FriendlyByteBuf, StaffSkillPayload> CODEC = StreamCodec.unit(INSTANCE);
    @Override public Type<StaffSkillPayload> type() { return TYPE; }
}
