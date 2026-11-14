package com.eccraft.archer.client;
import com.eccraft.Eccraft;
import com.eccraft.archer.ArcherItems;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
public final class ArcherClient {
 public static void register(){
  net.minecraft.client.renderer.special.SpecialModelRenderers.ID_MAPPER.put(Eccraft.id("archer_bow_glb"),ArcherBowRenderer.Unbaked.MAP_CODEC);
  EntityRendererRegistry.register(ArcherItems.ARROW,ArcherArrowRenderer::new);
 }
 private ArcherClient(){}
}
