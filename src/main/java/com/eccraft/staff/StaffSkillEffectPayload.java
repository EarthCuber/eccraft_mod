package com.eccraft.staff;

import com.eccraft.Eccraft;
import java.util.HashSet;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

/** One packet per viewer, rather than hundreds of server particle packets per cast. */
public record StaffSkillEffectPayload(double x, double y, double z, StaffRoute route, float radius) implements CustomPacketPayload {
    public static final Type<StaffSkillEffectPayload> TYPE = new Type<>(Eccraft.id("staff_skill_effect"));
    public static final StreamCodec<FriendlyByteBuf, StaffSkillEffectPayload> CODEC = new StreamCodec<>() {
        @Override public StaffSkillEffectPayload decode(FriendlyByteBuf buf) {
            return new StaffSkillEffectPayload(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readEnum(StaffRoute.class), buf.readFloat());
        }
        @Override public void encode(FriendlyByteBuf buf, StaffSkillEffectPayload value) {
            buf.writeDouble(value.x()); buf.writeDouble(value.y()); buf.writeDouble(value.z());
            buf.writeEnum(value.route()); buf.writeFloat(value.radius());
        }
    };
    public static void broadcast(ServerPlayer caster, StaffRoute route, double radius) {
        var payload = new StaffSkillEffectPayload(caster.getX(), caster.getY(), caster.getZ(), route, (float)radius);
        var viewers = new HashSet<>(PlayerLookup.tracking(caster));
        viewers.add(caster);
        for (ServerPlayer viewer : viewers)
            if (ServerPlayNetworking.canSend(viewer, TYPE)) ServerPlayNetworking.send(viewer, payload);
    }
    @Override public Type<StaffSkillEffectPayload> type() { return TYPE; }
}
