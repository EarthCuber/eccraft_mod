package com.eccraft.staff.client;

import com.eccraft.Eccraft;
import com.eccraft.staff.StaffItem;
import com.eccraft.staff.StaffSkillPayload;
import com.eccraft.staff.StaffSystem;
import com.eccraft.staff.StaffTags;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import com.eccraft.staff.StaffParticles;
import com.eccraft.staff.StaffCooldownPayload;
import com.eccraft.staff.StaffSkillEffectPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

public final class StaffClient {
    public static final KeyMapping SKILL = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.eccraft.staff_skill", InputConstants.Type.KEYSYM, InputConstants.KEY_F,
            KeyMapping.Category.register(Eccraft.id("staff"))));
    private static boolean wasDown;
    public static void register() {
        com.eccraft.combat.MovementClient.register();
        com.eccraft.combat.client.CombatNumbers.register();
        ClientPlayNetworking.registerGlobalReceiver(StaffSkillEffectPayload.TYPE,
                (payload, context) -> StaffSkillEffect.add(context.client().level, payload));
        ClientTickEvents.END_CLIENT_TICK.register(StaffSkillEffect::tick);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> StaffSkillEffect.clear());
        ClientPlayNetworking.registerGlobalReceiver(StaffCooldownPayload.TYPE, (payload, context) -> StaffSkillHud.update(payload));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> StaffSkillHud.clear());
        HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR, Eccraft.id("staff_skill"), StaffSkillHud::draw);
        net.minecraft.client.renderer.special.SpecialModelRenderers.ID_MAPPER.put(
                Eccraft.id("staff_glb"), StaffModelRenderer.Unbaked.MAP_CODEC);
        EntityRendererRegistry.register(StaffSystem.PROJECTILE, StaffProjectileRenderer::new);
        ParticleProviderRegistry.getInstance().register(StaffParticles.HEAL, sprites ->
                (type, level, x, y, z, dx, dy, dz, random) -> new StaffHealParticle(level, x, y, z, dx, dy, dz, sprites));
    }
    /** Run before vanilla handles swap-offhand; use a press edge to reject key repeat. */
    public static void handleKeys(Minecraft client) {
        boolean down = SKILL.isDown();
        boolean clicked = false;
        while (SKILL.consumeClick()) clicked = true;
        if (client.player != null && client.gui.screen() == null
                && (client.player.getMainHandItem().getItem() instanceof StaffItem || com.eccraft.combat.CombatSkills.supported(client.player.getMainHandItem()))) {
            if (SKILL.same(client.options.keySwapOffhand)) while (client.options.keySwapOffhand.consumeClick()) {}
            if (!wasDown && (down || clicked)) {
                boolean staff=client.player.getMainHandItem().getItem() instanceof StaffItem;
                boolean bow=client.player.getMainHandItem().getItem() instanceof com.eccraft.archer.ArcherBowItem;
                if(staff&&!StaffTags.hasTome(client.player)) client.player.sendOverlayMessage(net.minecraft.network.chat.Component.translatable("message.eccraft.missing_tome"));
                if(bow&&!com.eccraft.archer.ArcherBowItem.hasQuiver(client.player)) client.player.sendOverlayMessage(net.minecraft.network.chat.Component.translatable("message.eccraft.missing_quiver"));
            }
            if (!wasDown && (down || clicked) && (client.player.getMainHandItem().getItem() instanceof StaffItem ? StaffTags.hasTome(client.player) : com.eccraft.combat.CombatSkills.paired(client.player))
                    && ClientPlayNetworking.canSend(StaffSkillPayload.TYPE))
                ClientPlayNetworking.send(StaffSkillPayload.INSTANCE);
        }
        wasDown = down;
    }
    private StaffClient() {}
}
