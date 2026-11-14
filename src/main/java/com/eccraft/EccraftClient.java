package com.eccraft;

import com.eccraft.client.entity.AnubisClient;
import com.eccraft.client.entity.AnubisFlameRenderer;
import com.eccraft.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.HuskRenderer;

public class EccraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        net.minecraft.client.renderer.special.SpecialModelRenderers.ID_MAPPER.put(com.eccraft.Eccraft.id("flying_hammer"),com.eccraft.client.entity.FlyingHammerRenderer.Unbaked.MAP_CODEC);
        EntityRendererRegistry.register(com.eccraft.techtainark.TechtainArkContent.ENTITY,com.eccraft.client.entity.TechtainArkRenderer::new);
        EntityRendererRegistry.register(com.eccraft.techtainark.TechtainArkContent.BOX,c->new com.eccraft.client.entity.ArkMinionRenderer(c,0));
        EntityRendererRegistry.register(com.eccraft.techtainark.TechtainArkContent.COASTER,c->new com.eccraft.client.entity.ArkMinionRenderer(c,1));
        EntityRendererRegistry.register(com.eccraft.techtainark.TechtainArkContent.SPRINTER,c->new com.eccraft.client.entity.ArkMinionRenderer(c,2));
        EntityRendererRegistry.register(com.eccraft.firelord.FirelordContent.ENTITY, com.eccraft.client.entity.FirelordRenderer::new);
        EntityRendererRegistry.register(com.eccraft.firelord.FirelordContent.ELEMENT,com.eccraft.client.entity.FireElementRenderer::new);
        EntityRendererRegistry.register(com.eccraft.firelord.FirelordContent.METEOR,com.eccraft.client.entity.FirelordMeteorRenderer::new);
        EntityRendererRegistry.register(com.eccraft.firelord.FirelordContent.LAVA,com.eccraft.client.entity.FirelordLavaRenderer::new);
        EntityRendererRegistry.register(com.eccraft.control.ControlDeathContent.ENTITY,com.eccraft.client.entity.ControlDeathRenderer::new);
        EntityRendererRegistry.register(com.eccraft.nightgod.NightgodContent.ENTITY, com.eccraft.client.entity.NightgodRenderer::new);
        EntityRendererRegistry.register(com.eccraft.nightgod.NightgodContent.PROJECTILE,com.eccraft.client.entity.NightgodProjectileRenderer::new);
        EntityRendererRegistry.register(com.eccraft.nightgod.NightgodContent.FIRE,com.eccraft.client.entity.NightgodFireRenderer::new);
        EntityRendererRegistry.register(com.eccraft.nightgod.NightgodContent.LITTLE_DEVIL,com.eccraft.client.entity.NightMinionRenderer::new);
        EntityRendererRegistry.register(com.eccraft.nightgod.NightgodContent.DEATH,com.eccraft.client.entity.NightMinionRenderer::new);
        EntityRendererRegistry.register(com.eccraft.castlecore.CastlecoreContent.ENTITY, com.eccraft.client.entity.CastlecoreRenderer::new);
        EntityRendererRegistry.register(com.eccraft.obsidianguard.ObsidianguardContent.ENTITY, com.eccraft.client.entity.ObsidianguardRenderer::new);
        EntityRendererRegistry.register(com.eccraft.obsidianguard.ObsidianguardContent.METEOR, com.eccraft.client.entity.ObsidianguardMeteorRenderer::new);
        EntityRendererRegistry.register(com.eccraft.obsidianguard.ObsidianguardContent.MAGMA_CUBE, net.minecraft.client.renderer.entity.MagmaCubeRenderer::new);
        com.eccraft.legend.client.LegendTableRenderer.register();
        com.eccraft.client.quest.QuestClient.register();
        com.eccraft.client.npc.NpcClient.register();
        EntityRendererRegistry.register(ModEntityTypes.NPC_NORMAL, com.eccraft.client.npc.NpcNormalRenderer::new);
        com.eccraft.client.entity.SphinxParticleClient.register();
        EntityRendererRegistry.register(com.eccraft.sphinx.SphinxContent.ENTITY, com.eccraft.client.entity.SphinxRenderer::new);
        com.eccraft.client.entity.DarkDragonFlightAudio.register();
        EntityRendererRegistry.register(com.eccraft.darkdragon.DarkDragonContent.ENTITY, com.eccraft.client.entity.DarkDragonRenderer::new);
        EntityRendererRegistry.register(com.eccraft.darkdragon.DarkDragonContent.FIREBALL, com.eccraft.client.entity.DarkDragonFireballRenderer::new);
        EntityRendererRegistry.register(com.eccraft.darkdragon.DarkDragonContent.FIRE, com.eccraft.client.entity.DarkDragonFireRenderer::new);
        EntityRendererRegistry.register(com.eccraft.banshee.BansheeContent.ENTITY, com.eccraft.client.entity.BansheeRenderer::new);
        EntityRendererRegistry.register(com.eccraft.banshee.BansheeContent.PROJECTILE, com.eccraft.client.entity.BansheeProjectileRenderer::new);
        EntityRendererRegistry.register(com.eccraft.samurai.SamuraiContent.ENTITY, com.eccraft.client.entity.SamuraiRenderer::new);
        EntityRendererRegistry.register(com.eccraft.samurai.SamuraiContent.BOGGED, net.minecraft.client.renderer.entity.BoggedRenderer::new);
        net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry.getInstance().register(
                com.eccraft.junglelord.JunglelordParticles.SLAM, sprites ->
                (type, level, x, y, z, dx, dy, dz, random) ->
                        new com.eccraft.client.entity.JunglelordSlamParticle(level, x, y, z, sprites));
        EntityRendererRegistry.register(com.eccraft.junglelord.JunglelordContent.ENTITY, com.eccraft.client.entity.JunglelordRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ICEWITCH, com.eccraft.client.entity.IcewitchRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ICEWITCH_STRAY, net.minecraft.client.renderer.entity.StrayRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ICEFIRE, com.eccraft.client.entity.IcefireRenderer::new);
        com.eccraft.staff.client.StaffClient.register();
        com.eccraft.armor.client.MageArmorRenderer.register();
        com.eccraft.armor.client.KnightArmorRenderer.register();
        com.eccraft.armor.client.ArcherArmorRenderer.register();
        com.eccraft.archer.client.ArcherClient.register();
        AnubisClient.register(Eccraft.MOD_ID, ModEntityTypes.ANUBIS);
        EntityRendererRegistry.register(ModEntityTypes.ANUBIS_FLAME, AnubisFlameRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ANUBIS_HUSK, HuskRenderer::new);
    }
}
