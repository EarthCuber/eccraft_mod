package com.eccraft.client.entity;
import com.eccraft.entity.AnubisAnimationSource;
import com.eccraft.client.gltf.GltfModelCache;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

public final class AnubisClient {
    private AnubisClient() {}
    public record Assets(ModelLayerLocation layer, Identifier texture) {}
    /** Call once from your ClientModInitializer, after your entity type is registered. */
    public static <T extends Mob & AnubisAnimationSource> void register(String modId, EntityType<T> type) {
        GltfModelCache.register();
        Assets assets = new Assets(
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(modId, "anubis"), "main"),
            Identifier.fromNamespaceAndPath(modId, "textures/entity/anubis_gltf.png"));
        ModelLayerRegistry.registerModelLayer(assets.layer(), AnubisModel::createLayer);
        EntityRendererRegistry.register(type, context -> new AnubisRenderer<T>(context, assets));
    }
}

