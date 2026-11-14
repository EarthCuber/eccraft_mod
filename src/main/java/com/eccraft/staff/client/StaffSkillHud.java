package com.eccraft.staff.client;

import com.eccraft.Eccraft;
import com.eccraft.staff.StaffCooldownPayload;
import com.eccraft.staff.StaffItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;

public final class StaffSkillHud {
    private static final Identifier ICON = Eccraft.id("textures/gui/staff_skill.png");
    private static final int SIZE = 22;
    private static final float TEXT_SCALE = SIZE / 28F;
    private static int remaining, total;
    public static void update(StaffCooldownPayload payload) {
        total = Math.max(0, payload.totalTicks());
        remaining = Math.clamp(payload.remainingTicks(), 0, total);
    }
    public static void clear() { remaining = total = 0; }
    public static void draw(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.gui.hud.isHidden() || client.player.isSpectator()
                || (!(client.player.getMainHandItem().getItem() instanceof StaffItem) && !com.eccraft.combat.CombatSkills.supported(client.player.getMainHandItem()))) return;
        boolean offhandOnRight = client.player.getMainArm() == HumanoidArm.LEFT && !client.player.getOffhandItem().isEmpty();
        int x = StaffHudMath.iconX(graphics.guiWidth(), SIZE, offhandOnRight);
        int y = graphics.guiHeight() - SIZE;
        Identifier icon = switch(com.eccraft.combat.CombatSkills.kind(client.player.getMainHandItem())) { case 1 -> Eccraft.id("textures/gui/sword_skill.png"); case 2 -> Eccraft.id("textures/gui/bow_skill.png"); default -> ICON; };
        graphics.blit(icon, x, y, x + SIZE, y + SIZE, 0F, 1F, 0F, 1F);
        int height = StaffHudMath.maskHeight(remaining, total, SIZE);
        // A circular grey mask rises from the bottom; it never paints square corners.
        double radius = SIZE / 2.0 - 1;
        for (int row = SIZE - height; row < SIZE; row++) {
            double dy = row + .5 - SIZE / 2.0;
            if (Math.abs(dy) > radius) continue;
            int halfWidth = (int)Math.floor(Math.sqrt(radius * radius - dy * dy));
            graphics.fill(x + SIZE / 2 - halfWidth, y + row, x + SIZE / 2 + halfWidth, y + row + 1, 0xA0606060);
        }
        String key = "[" + StaffClient.SKILL.getTranslatedKeyMessage().getString() + "]";
        int keyHalfWidth = (int)Math.ceil(client.font.width(key) * TEXT_SCALE / 2);
        int keyX = Math.clamp(x + SIZE / 2, keyHalfWidth + 2, Math.max(keyHalfWidth + 2, graphics.guiWidth() - keyHalfWidth - 2));
        scaledText(graphics, client, key, keyX, y - 10 * TEXT_SCALE);
        String countdown = StaffHudMath.seconds(remaining);
        if (!countdown.isEmpty()) scaledText(graphics, client, countdown, x + SIZE / 2F, y + (SIZE - 9 * TEXT_SCALE) / 2);
    }
    private static void scaledText(GuiGraphicsExtractor graphics, Minecraft client, String text, float centerX, float y) {
        graphics.pose().pushMatrix();
        try {
            graphics.pose().translate(centerX, y);
            graphics.pose().scale(TEXT_SCALE, TEXT_SCALE);
            graphics.centeredText(client.font, text, 0, 0, 0xFFFFFFFF);
        } finally { graphics.pose().popMatrix(); }
    }
    private StaffSkillHud() {}
}
