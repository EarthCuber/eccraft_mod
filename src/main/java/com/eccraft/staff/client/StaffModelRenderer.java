package com.eccraft.staff.client;

import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Consumer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

/** Item adapter only: existing entity GLB renderer/loader are left unchanged. */
public record StaffModelRenderer(Identifier model, Identifier texture) implements NoDataSpecialModelRenderer {
    private Matrix4f[] matrices(GltfModel mesh) {
        return GltfAnimationPlayer.globalMatrices(mesh, GltfAnimationPlayer.sampleLocalMatrices(mesh, "", 0, false));
    }
    @Override public void submit(PoseStack poses, SubmitNodeCollector collector, int light, int overlay, boolean foil, int outline) {
        GltfModel mesh = GltfModelCache.get(model);
        if (mesh == null) return;
        Matrix4f[] globals = matrices(mesh);
        // The supplied GLB material is double-sided. Its V coordinate is already converted.
        collector.submitCustomGeometry(poses, RenderTypes.entityCutout(texture),
                (pose, vertices) -> GltfRenderUtil.render(mesh, globals, pose, vertices, light));
        if (foil) collector.submitCustomGeometry(poses, RenderTypes.entityGlint(),
                (pose, vertices) -> GltfRenderUtil.render(mesh, globals, pose, vertices, light));
    }
    @Override public void getExtents(Consumer<Vector3fc> output) {
        GltfModel mesh = GltfModelCache.get(model);
        if (mesh == null) return;
        Matrix4f[] globals = matrices(mesh);
        for (GltfModel.Mesh part : mesh.meshes) {
            for (int i = 0; i < part.positions.length; i += 3) {
                Vector3f vertex = new Vector3f(part.positions[i], part.positions[i+1], part.positions[i+2]);
                if (part.node >= 0) globals[part.node].transformPosition(vertex);
                output.accept(vertex);
            }
        }
    }
    public record Unbaked(Identifier model, Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("model").forGetter(Unbaked::model),
                Identifier.CODEC.fieldOf("texture").forGetter(Unbaked::texture)).apply(instance, Unbaked::new));
        @Override public MapCodec<Unbaked> type() { return MAP_CODEC; }
        @Override public StaffModelRenderer bake(SpecialModelRenderer.BakingContext context) { return new StaffModelRenderer(model, texture); }
    }
}
