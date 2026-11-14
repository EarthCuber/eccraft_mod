package com.eccraft.client.gltf;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Lazy GLB cache; F3+T invalidates all parsed models. */
public final class GltfModelCache implements IdentifiableResourceReloadListener, ResourceManagerReloadListener {
    private static final GltfModelCache INSTANCE = new GltfModelCache();
    private static final Logger LOGGER = LoggerFactory.getLogger("eccraft/glb");
    private static final Identifier RELOAD_LISTENER_ID = Identifier.fromNamespaceAndPath("eccraft", "gltf_models");
    private final Map<Identifier, GltfModel> models = new HashMap<>();
    private final Map<Identifier, Boolean> failed = new HashMap<>();

    private GltfModelCache() {}

    public static void register() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(INSTANCE);
    }

    public static GltfModel get(Identifier id) {
        if (INSTANCE.failed.containsKey(id)) return null;
        GltfModel cached = INSTANCE.models.get(id);
        if (cached != null) return cached;
        try (InputStream input = Minecraft.getInstance().getResourceManager().open(id)) {
            GltfModel loaded = GltfLoader.load(input);
            INSTANCE.models.put(id, loaded);
            LOGGER.info("Loaded GLB model {} ({} meshes, {} animations)", id,
                    loaded.meshes.size(), loaded.animations.size());
            return loaded;
        } catch (Exception exception) {
            INSTANCE.failed.put(id, Boolean.TRUE);
            LOGGER.error("Unable to load GLB model {}", id, exception);
            return null;
        }
    }

    @Override
    public Identifier getFabricId() {
        return RELOAD_LISTENER_ID;
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        models.clear();
        failed.clear();
    }
}
