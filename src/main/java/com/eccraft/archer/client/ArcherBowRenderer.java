package com.eccraft.archer.client;

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
public record ArcherBowRenderer(Identifier model, Identifier texture, float progress, boolean nocked) implements SpecialModelRenderer<Integer> {
    private Matrix4f[] matrices(GltfModel mesh) {
        var animation=mesh.animations.values().stream().findFirst().orElse(null);
        String clip=animation==null?"":animation.name();
        float time=animation==null?0:animation.duration()*Math.clamp(progress,0F,1F);
        return GltfAnimationPlayer.globalMatrices(mesh, GltfAnimationPlayer.sampleLocalMatrices(mesh, clip, time, false));
    }
    @Override public Integer extractArgument(net.minecraft.world.item.ItemStack stack) {
        var player=net.minecraft.client.Minecraft.getInstance().player;
        return nocked && player!=null && player.isUsingItem() && player.getUseItem()==stack
            && player.getOffhandItem().getItem() instanceof com.eccraft.archer.QuiverItem quiver ? quiver.tier() : 0;
    }
    public static float modelScale(Identifier model) {
        return switch(model.getPath()) {
            case "models/item/legend_bow_1.glb" -> 0.0500173060F;
            case "models/item/legend_bow_2.glb" -> 0.0500173060F;
            case "models/item/legend_bow_3.glb" -> 0.0500173060F;
            case "models/item/legend_bow_4.glb" -> 0.0484511039F;

            case "models/item/bow_t1.glb" -> 0.09156551318772303F;
            case "models/item/bow_t2.glb" -> 0.06972416049429067F;
            case "models/item/bow_t3.glb" -> 0.0513833890546662F;
            case "models/item/bow_t4.glb" -> 0.055319130104125924F;
            default -> 0.06046510502974791F;
        };
    }
    /** Match the diagonal plane and grip of the native generated bow before its display transforms. */
    public static Matrix4f bowPose(float scale) {
        return new Matrix4f().translation(.25F,.75F,.5F)
            .rotateZ((float)Math.toRadians(-45)).rotateY((float)Math.toRadians(-90)).scale(scale);
    }
    public static Vector3f stringPoint(GltfModel mesh,Matrix4f[] globals) {
        for(int i=0;i<mesh.nodes.size();i++)
            if(mesh.nodes.get(i).name.equals("bn_bow_03")) return globals[i].getTranslation(new Vector3f());
        throw new IllegalStateException("Bow model is missing string joint bn_bow_03");
    }
    @Override public void submit(Integer arrowTier, PoseStack poses, SubmitNodeCollector collector, int light, int overlay, boolean foil, int outline) {
        GltfModel mesh = GltfModelCache.get(model);
        if (mesh == null) return;
        Matrix4f[] globals = matrices(mesh);
        float scale=modelScale(model);
        poses.pushPose();
        poses.mulPose(bowPose(scale));
        // The supplied GLB material is double-sided. Its V coordinate is already converted.
        collector.submitCustomGeometry(poses, RenderTypes.entityCutout(texture),
                (pose, vertices) -> GltfRenderUtil.render(mesh, globals, pose, vertices, light));
        if (foil) collector.submitCustomGeometry(poses, RenderTypes.entityGlint(),
                (pose, vertices) -> GltfRenderUtil.render(mesh, globals, pose, vertices, light));
        if(arrowTier!=null && arrowTier>0) {
            var arrow=com.eccraft.client.gltf.GltfModelCache.get(com.eccraft.Eccraft.id("models/entity/archer/arrow_t"+arrowTier+".glb"));
            if(arrow!=null) {
                var arrowGlobals=GltfAnimationPlayer.globalMatrices(arrow,GltfAnimationPlayer.sampleLocalMatrices(arrow,"",0,false));
                Vector3f point=stringPoint(mesh,globals);
                poses.pushPose();poses.translate(point.x,point.y,point.z);
                // Convert the arrow's block-sized rest pose back into the bow's source units.
                poses.scale(1/scale,1/scale,1/scale);poses.translate(0,0,.4F);
                ArcherVisuals.arrowPose(poses,arrowTier);
                collector.submitCustomGeometry(poses,RenderTypes.entityCutout(com.eccraft.Eccraft.id("textures/entity/archer/arrow_t"+arrowTier+".png")),
                    (pose,vertices)->GltfRenderUtil.render(arrow,arrowGlobals,pose,vertices,light));
                poses.popPose();
            }
        }
        poses.popPose();
    }
    @Override public void getExtents(Consumer<Vector3fc> output) {
        GltfModel mesh = GltfModelCache.get(model);
        if (mesh == null) return;
        Matrix4f[] globals = matrices(mesh);
        for (GltfModel.Mesh part : mesh.meshes) {
            for (int i = 0; i < part.positions.length; i += 3) {
                Vector3f vertex = new Vector3f(part.positions[i], part.positions[i+1], part.positions[i+2]);
                if (part.node >= 0) globals[part.node].transformPosition(vertex);
                bowPose(modelScale(model)).transformPosition(vertex);
                output.accept(vertex);
            }
        }
    }
    public record Unbaked(Identifier model, Identifier texture, float progress, boolean nocked) implements SpecialModelRenderer.Unbaked<Integer> {
        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("model").forGetter(Unbaked::model),
                Identifier.CODEC.fieldOf("texture").forGetter(Unbaked::texture),
                com.mojang.serialization.Codec.floatRange(0F,1F).fieldOf("progress").forGetter(Unbaked::progress),
                com.mojang.serialization.Codec.BOOL.optionalFieldOf("nocked",false).forGetter(Unbaked::nocked)).apply(instance, Unbaked::new));
        @Override public MapCodec<Unbaked> type() { return MAP_CODEC; }
        @Override public ArcherBowRenderer bake(SpecialModelRenderer.BakingContext context) { return new ArcherBowRenderer(model, texture, progress, nocked); }
    }
}

