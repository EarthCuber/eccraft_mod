package com.eccraft.mixin;
import com.eccraft.archer.ArcherBowItem;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

/** Vanilla subtracts these values as floats; an unlimited integer duration loses precision. */
@Mixin(ItemInHandRenderer.class)
public abstract class ArcherFirstPersonDurationMixin {
    @Redirect(method="submitArmWithItem",at=@At(value="INVOKE",target="Lnet/minecraft/world/item/ItemStack;getUseDuration(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int eccraft$visualDuration(ItemStack stack,LivingEntity user){
        return stack.getItem() instanceof ArcherBowItem?72000:stack.getUseDuration(user);
    }
    @Redirect(method="submitArmWithItem",at=@At(value="INVOKE",target="Lnet/minecraft/client/player/AbstractClientPlayer;getUseItemRemainingTicks()I"))
    private int eccraft$visualRemaining(AbstractClientPlayer player){
        return player.getUseItem().getItem() instanceof ArcherBowItem
            ?72000-(player.getTicksUsingItem()>=ArcherBowItem.CHARGE_TICKS?ArcherBowItem.CHARGE_TICKS+1:player.getTicksUsingItem()):player.getUseItemRemainingTicks();
    }
    /** Ordinal 1 is the bow shake; crossbow and spear use separate calls. */
    @Redirect(method="submitArmWithItem",at=@At(value="INVOKE",target="Lnet/minecraft/util/Mth;sin(D)F",ordinal=1))
    private float eccraft$steadyBow(double angle, AbstractClientPlayer player, float partial, float pitch,
            net.minecraft.world.InteractionHand hand, float swing, ItemStack stack, float equip,
            com.mojang.blaze3d.vertex.PoseStack poses, net.minecraft.client.renderer.SubmitNodeCollector collector, int light) {
        return stack.getItem() instanceof ArcherBowItem ? 0F : net.minecraft.util.Mth.sin(angle);
    }
}
