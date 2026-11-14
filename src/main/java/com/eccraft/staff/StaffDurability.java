package com.eccraft.staff;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

/** Called only after a successful projectile spawn or skill execution. */
public final class StaffDurability {
    public static void consume(ServerPlayer player, ItemStack staff, int cost) {
        ItemStack tome = player.getOffhandItem();
        // Capture both participating stacks before either can break.
        staff.hurtAndBreak(cost, player, InteractionHand.MAIN_HAND);
        tome.hurtAndBreak(cost, player, InteractionHand.OFF_HAND);
    }
    private StaffDurability() {}
}
