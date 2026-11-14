package com.eccraft.client.entity;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.entity.ObsidianguardEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4f;

/** Native Minecraft 26.2 renderer; no GeckoLib dependency. */
public final class ObsidianguardRenderer
        extends MobRenderer<ObsidianguardEntity, ObsidianguardRenderState, ObsidianguardModel> {
    private static final Identifier GLB_MODEL = Identifier.fromNamespaceAndPath("eccraft", "models/entity/obsidianguard.glb");
    private static final Identifier GLB_TEXTURE = Identifier.fromNamespaceAndPath("eccraft", "textures/entity/obsidianguard.png");
    private static final float GLB_SCALE = 0.1F;
    private final Identifier texture;
    public ObsidianguardRenderer(EntityRendererProvider.Context context) {
        super(context, new ObsidianguardModel(ObsidianguardModel.createLayer().bakeRoot()), 0.65f);
        this.texture = GLB_TEXTURE;
    }
    @Override public ObsidianguardRenderState createRenderState() { return new ObsidianguardRenderState(); }
    @Override public Identifier getTextureLocation(ObsidianguardRenderState state) { return texture; }
    @Override public void extractRenderState(ObsidianguardEntity entity, ObsidianguardRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.action = entity.obsidianguardAction();
        if (state.action == null) state.action = "idle";
        state.actionSeconds = Math.max(0f,
                (entity.level().getGameTime() - entity.obsidianguardActionStartTick() + partialTick) / 20f);
    }

    @Override
    public void submit(ObsidianguardRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState cameraState) {
        super.submit(state, poseStack, collector, cameraState);
        if (state.isInvisibleToPlayer) return;

        GltfModel model = GltfModelCache.get(GLB_MODEL);
        if (model == null) return;
        var animation = com.eccraft.obsidianguard.ObsidianguardAnimations.select(model, state.action, state.actionSeconds, state.ageInTicks / 20.0F);
        Matrix4f[] locals = GltfAnimationPlayer.sampleLocalMatrices(
                model, animation.clip(), animation.sourceTime(), animation.looping());
        Matrix4f[] globals = GltfAnimationPlayer.globalMatrices(model, locals);

        poseStack.pushPose();
        // This GLB was authored with its forward axis opposite the vanilla living-entity model axis.
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.bodyRot));
        float scale = GLB_SCALE * state.scale;
        poseStack.scale(scale, scale, scale);
        collector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(GLB_TEXTURE),
                (pose, vertices) -> GltfRenderUtil.render(model, globals, pose, vertices, state.lightCoords));
        poseStack.popPose();
    }

    /** The clip provides the entire kneel/fall; prevent vanilla's sideways death roll. */
    @Override protected float getFlipDegrees() { return 0f; }
    /** Account for the long scythe, raised cast pose, and forward prone pose. */
    @Override protected AABB getBoundingBoxForCulling(ObsidianguardEntity entity) {
        return super.getBoundingBoxForCulling(entity).inflate(5.0);
    }
}

