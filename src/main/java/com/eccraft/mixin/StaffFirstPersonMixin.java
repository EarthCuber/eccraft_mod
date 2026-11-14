package com.eccraft.mixin;

import com.eccraft.staff.StaffItem;
import com.eccraft.staff.client.StaffChargePose;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class StaffFirstPersonMixin {
    @Shadow private void applyItemArmTransform(PoseStack poses, HumanoidArm arm, float equipProgress) { throw new AssertionError(); }
    @Shadow private void swingArm(float swing, PoseStack poses, int sign, HumanoidArm arm) { throw new AssertionError(); }
    @Shadow public abstract void renderItem(LivingEntity player, ItemStack stack, ItemDisplayContext context,
            PoseStack poses, SubmitNodeCollector collector, int light);

    @Inject(method = "submitArmWithItem", at = @At("HEAD"), cancellable = true)
    private void eccraft$chargePose(AbstractClientPlayer player, float partialTick, float pitch,
            InteractionHand hand, float swing, ItemStack stack, float equipProgress,
            PoseStack poses, SubmitNodeCollector collector, int light, CallbackInfo ci) {
        if (hand != InteractionHand.MAIN_HAND || !(stack.getItem() instanceof StaffItem)) return;
        ci.cancel();
        if (player.isScoping()) return;
        HumanoidArm arm = player.getMainArm();
        boolean left = arm == HumanoidArm.LEFT;
        boolean charging = player.isUsingItem() && player.getUsedItemHand() == hand
                && player.getUseItem().getItem() == stack.getItem();
        float progress = charging ? StaffChargePose.progress(player.getTicksUsingItem(), partialTick, StaffItem.chargeTicks()) : 0;
        poses.pushPose();
        try {
            applyItemArmTransform(poses, arm, equipProgress);
            if (!charging) swingArm(swing, poses, left ? -1 : 1, arm);
            poses.translate(0F, StaffChargePose.lift(progress), 0F);
            // Move only the pitch pivot rearward; retain the established pitch trajectory.
            poses.translate(0F, 0F, StaffChargePose.GRIP_Z + StaffChargePose.PIVOT_BACK_OFFSET);
            poses.mulPose(Axis.XP.rotationDegrees(StaffChargePose.angle(progress, left)));
            // Roll about the shaft through the original grip, not the offset pitch pivot.
            poses.translate(0F, 0F, -StaffChargePose.PIVOT_BACK_OFFSET);
            poses.mulPose(Axis.YP.rotationDegrees(StaffChargePose.SHAFT_ROLL_DEGREES));
            poses.translate(0F, 0F, -StaffChargePose.GRIP_Z);
            renderItem(player, stack, left ? ItemDisplayContext.FIRST_PERSON_LEFT_HAND : ItemDisplayContext.FIRST_PERSON_RIGHT_HAND,
                    poses, collector, light);
        } finally { poses.popPose(); }
    }
}
