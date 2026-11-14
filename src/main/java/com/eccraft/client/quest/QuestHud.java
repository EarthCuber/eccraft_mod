package com.eccraft.client.quest;

import com.eccraft.Eccraft;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public final class QuestHud {
    private static final Identifier ICON=Eccraft.id("textures/gui/quest.png");
    private static final int SIZE=22;
    private static final float TEXT_SCALE=SIZE/28F;
    public static void draw(GuiGraphicsExtractor graphics,DeltaTracker delta){
        Minecraft client=Minecraft.getInstance();
        if(client.player==null||client.gui.hud.isHidden()||client.player.isSpectator()||QuestClient.OPEN_QUESTS==null)return;
        int x=Math.clamp(graphics.guiWidth()/2+132,2,graphics.guiWidth()-SIZE-2);
        int y=graphics.guiHeight()-SIZE;
        graphics.blit(ICON,x,y,x+SIZE,y+SIZE,0F,1F,0F,1F);
        String key="["+QuestClient.OPEN_QUESTS.getTranslatedKeyMessage().getString()+"]";
        graphics.pose().pushMatrix();
        try{
            graphics.pose().translate(x+SIZE/2F,y-10*TEXT_SCALE);
            graphics.pose().scale(TEXT_SCALE,TEXT_SCALE);
            graphics.centeredText(client.font,key,0,0,0xFFFFFFFF);
        }finally{graphics.pose().popMatrix();}
    }
    private QuestHud(){}
}
