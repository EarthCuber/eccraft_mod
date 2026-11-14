package com.eccraft.client.quest;

import com.eccraft.Eccraft;
import com.eccraft.quest.QuestPayloads;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.sounds.SoundEvents;

public final class QuestClient {
    public static KeyMapping OPEN_QUESTS;
    private QuestClient() {}
    public static void register(){
        OPEN_QUESTS=KeyMappingHelper.registerKeyMapping(new KeyMapping("key.eccraft.open_quests",
                InputConstants.Type.KEYSYM, InputConstants.KEY_J,
                com.eccraft.staff.client.StaffClient.SKILL.getCategory()));
        HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR,Eccraft.id("quest"),QuestHud::draw);
        ClientTickEvents.END_CLIENT_TICK.register(client->{while(OPEN_QUESTS.consumeClick())if(client.player!=null)
            ClientPlayNetworking.send(new QuestPayloads.Request("open",""));});
        ClientPlayNetworking.registerGlobalReceiver(QuestPayloads.Sync.TYPE,(payload,ctx)->ctx.client().execute(()->{
            if(payload.open() || ctx.client().gui.screen() instanceof QuestScreen){
                QuestScreen old=ctx.client().gui.screen() instanceof QuestScreen screen?screen:null;
                if(payload.open()&&ctx.client().player!=null)ctx.client().player.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE,1.0F,1.0F);
                ctx.client().setScreenAndShow(old==null?new QuestScreen(payload.json()):
                        new QuestScreen(payload.json(),old.selectedPageId(),old.selectedQuestId()));
            }
        }));
    }
}
