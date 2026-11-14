package com.eccraft.mixin;

import com.eccraft.client.gui.BossBarProfiles;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;

@Mixin(BossHealthOverlay.class)
public abstract class SharedBossBarMixin {
    private static final int TOTAL_WIDTH = 256;
    private static final int AVATAR_SIZE = 32;
    private static final int BAR_WIDTH = 219;
    private static final int BAR_HEIGHT = 25;
    private static final int BAR_INSET = 3;
    private static final RenderPipeline PIPELINE = RenderPipelines.GUI_TEXTURED;
    private static final Identifier FRAME = texture("bar_frame.png");
    private static final Identifier GREEN = texture("bar_green.png");
    private static final Identifier ORANGE = texture("bar_orange.png");
    private static final Identifier RED = texture("bar_red.png");

    @Inject(
            method = "extractBar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IILnet/minecraft/world/BossEvent;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void eccraft$extractSharedBossBar(
            GuiGraphicsExtractor graphics, int vanillaX, int vanillaY, BossEvent event, CallbackInfo callback) {
        Identifier avatar = BossBarProfiles.avatarFor(event.getName());
        if (avatar == null) return;
        int avatarTextureSize = BossBarProfiles.avatarSize(avatar);

        float progress = Mth.clamp(event.getProgress(), 0.0F, 1.0F);
        int left = (graphics.guiWidth() - TOTAL_WIDTH) / 2;
        int top = Math.max(2, vanillaY - 10);
        int barX = left + AVATAR_SIZE + 5;
        int barY = top + 4;

        graphics.blit(PIPELINE, avatar, left, top, 0.0F, 0.0F,
                AVATAR_SIZE, AVATAR_SIZE, avatarTextureSize, avatarTextureSize,
                avatarTextureSize, avatarTextureSize);
        graphics.blit(PIPELINE, FRAME, barX, barY, 0.0F, 0.0F,
                BAR_WIDTH, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);

        int innerWidth = BAR_WIDTH - BAR_INSET * 2;
        int filled = Mth.floor(innerWidth * progress);
        if (filled > 0) {
            Identifier fillTexture = progress > 0.8F ? GREEN : progress >= 0.5F ? ORANGE : RED;
            graphics.enableScissor(barX + BAR_INSET, barY + BAR_INSET,
                    barX + BAR_INSET + filled, barY + BAR_HEIGHT - BAR_INSET);
            graphics.blit(PIPELINE, fillTexture, barX, barY, 0.0F, 0.0F,
                    BAR_WIDTH, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
            graphics.disableScissor();
        }

        String percentage = String.format(Locale.ROOT, "%.1f%%", progress * 100.0F);
        graphics.centeredText(net.minecraft.client.Minecraft.getInstance().font,
                percentage, barX + BAR_WIDTH / 2, barY + 8, 0xFFFFFFFF);
        callback.cancel();
    }

    @Redirect(
            method = "extractRenderState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V")
    )
    private void eccraft$hideStyledBossTitle(
            GuiGraphicsExtractor graphics, Font font, Component name, int x, int y, int color) {
        if (BossBarProfiles.avatarFor(name) == null) graphics.text(font, name, x, y, color);
    }

    private static Identifier texture(String file) {
        return Identifier.fromNamespaceAndPath("eccraft", "textures/gui/boss_bar/" + file);
    }
}
