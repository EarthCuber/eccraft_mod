package com.eccraft.staff;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;

/** Register concrete items with a profile, durability and enchantability later. */
public class StaffItem extends Item {
    public static int chargeTicks() { return StaffBalance.get().chargeTicks(); }
    private final StaffProfile profile;
    public StaffItem(Properties properties, StaffProfile profile) { super(properties); this.profile = profile; }
    public StaffProfile profile() { return profile; }
    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext context,
            net.minecraft.world.item.component.TooltipDisplay display,
            java.util.function.Consumer<net.minecraft.network.chat.Component> lines,
            net.minecraft.world.item.TooltipFlag flag) {
        super.appendHoverText(stack, context, display, lines, flag);
        StaffPower.tooltip(stack, display, lines);
    }
    @Override public int getUseDuration(ItemStack stack, LivingEntity user) { return Integer.MAX_VALUE; }
    @Override public ItemUseAnimation getUseAnimation(ItemStack stack) { return ItemUseAnimation.BOW; }
    @Override public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        if (!StaffTags.hasTome(player)) { player.sendOverlayMessage(net.minecraft.network.chat.Component.translatable("message.eccraft.missing_tome")); return InteractionResult.FAIL; }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }
    @Override public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remaining) {
        // Losing the tome cancels charging without releasing a shot or consuming durability.
        if (!StaffTags.hasTome(user)) user.stopUsingItem();
    }
    @Override public boolean releaseUsing(ItemStack stack, Level level, LivingEntity user, int remaining) {
        if (getUseDuration(stack, user) - remaining < chargeTicks()) return false;
        if (!(user instanceof ServerPlayer player) || !(level instanceof ServerLevel world)) return false;
        if (player.getMainHandItem() != stack || !player.isAlive() || player.isSpectator()) return false;
        if (!StaffTags.hasTome(player)) return false;
        StaffSystem.refresh(player);
        StaffRoute route = StaffEnchantments.route(world, stack);
        int rank = StaffEnchantments.routeLevel(world, stack, route);
        StaffProjectile projectile = new StaffProjectile(StaffSystem.PROJECTILE, world);
        projectile.setOwner(player);
        projectile.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        projectile.configure(route, (float)(player.getAttributeValue(StaffAttributes.SPELL_POWER)
                * profile.shotMultiplier(route, rank)), profile);
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, profile.projectileSpeed(), 0);
        if (!world.addFreshEntity(projectile)) return false;
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                com.eccraft.sound.ModSoundEvents.STAFF_ATTACK, net.minecraft.sounds.SoundSource.PLAYERS, 0.8F, 1.1F);
        StaffDurability.consume(player, stack, StaffBalance.get().shotDurabilityCost());
        return true;
    }
}
