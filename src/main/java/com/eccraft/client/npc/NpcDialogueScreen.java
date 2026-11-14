package com.eccraft.client.npc;

import com.eccraft.npc.NpcPayloads;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class NpcDialogueScreen extends Screen {
    private static final int MASK_COLOR = 0xB80A0A10;
    private static final int MASK_TOP_LINE = 0x66FFFFFF;
    private static final int SPEAKER_COLOR = 0xFFFFE2A8;
    private static final int TEXT_COLOR = 0xFFFFFFFF;
    private static final float SPEAKER_SCALE = 1.45F;
    private static final int OPTION_HEIGHT = 20;
    private static final int OPTION_GAP = 5;
    private static final int EDGE_MARGIN = 12;

    private final int entityId;
    private JsonObject data;

    public NpcDialogueScreen(int entityId, JsonObject data) {
        super(Component.literal(data.get("speaker").getAsString()));
        this.entityId = entityId;
        this.data = data;
    }

    @Override
    protected void init() {
        clearWidgets();
        JsonArray options = data.getAsJsonArray("options");
        int optionWidth = Math.clamp(width / 3, 180, 300);
        int optionX = width - optionWidth - EDGE_MARGIN;
        int maskTop = maskTop();
        int totalHeight = options.size() * OPTION_HEIGHT + Math.max(0, options.size() - 1) * OPTION_GAP;
        int firstY = Math.max(EDGE_MARGIN, maskTop - 12 - totalHeight);

        for (int i = 0; i < options.size(); i++) {
            JsonObject option = options.get(i).getAsJsonObject();
            int index = option.get("index").getAsInt();
            Component label = Component.literal(option.get("text").getAsString());
            addRenderableWidget(Button.builder(label, button -> ClientPlayNetworking.send(
                            new NpcPayloads.Choose(entityId, data.get("dialogue").getAsString(), index)))
                    .bounds(optionX, firstY + i * (OPTION_HEIGHT + OPTION_GAP), optionWidth, OPTION_HEIGHT)
                    .build());
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int maskTop = maskTop();
        graphics.fill(0, maskTop, width, height, MASK_COLOR);
        graphics.fill(0, maskTop, width, maskTop + 1, MASK_TOP_LINE);
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);

        int speakerY = maskTop + Math.max(10, (height - maskTop) / 9);
        drawScaledCenteredText(graphics, Component.literal(data.get("speaker").getAsString()), width / 2F, speakerY, SPEAKER_SCALE, SPEAKER_COLOR);

        int horizontalMargin = Math.max(24, width / 8);
        int textWidth = Math.max(80, width - horizontalMargin * 2);
        int textY = speakerY + Math.round(font.lineHeight * SPEAKER_SCALE) + 10;
        graphics.textWithWordWrap(font, Component.literal(data.get("text").getAsString()),
                horizontalMargin, textY, textWidth, TEXT_COLOR);
    }

    public int entityId(){return entityId;}
    public void updateDialogue(JsonObject next){data=next;init();}
    @Override public void extractBackground(GuiGraphicsExtractor graphics,int mouseX,int mouseY,float partialTick){}
    private int maskTop() {
        return height * 2 / 3;
    }

    private void drawScaledCenteredText(GuiGraphicsExtractor graphics, Component text,
                                        float centerX, float y, float scale, int color) {
        graphics.pose().pushMatrix();
        try {
            graphics.pose().translate(centerX, y);
            graphics.pose().scale(scale, scale);
            graphics.centeredText(font, text, 0, 0, color);
        } finally {
            graphics.pose().popMatrix();
        }
    }

    @Override
    public void onClose() {
        ClientPlayNetworking.send(new NpcPayloads.Close(entityId));
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
