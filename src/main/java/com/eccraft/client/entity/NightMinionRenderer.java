package com.eccraft.client.entity;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.entity.NightMinionEntity;

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
public final class NightMinionRenderer
        extends MobRenderer<NightMinionEntity, NightMinionRenderState, NightMinionModel> {
    private static final float GLB_SCALE = 0.1F;
    public NightMinionRenderer(EntityRendererProvider.Context context) {
        super(context, new NightMinionModel(NightMinionModel.createLayer().bakeRoot()), 0.65f);
    }
    @Override public NightMinionRenderState createRenderState() { return new NightMinionRenderState(); }
    @Override public Identifier getTextureLocation(NightMinionRenderState state) { return Identifier.fromNamespaceAndPath("eccraft",state.reaper?"textures/entity/death.png":"textures/entity/littledevil.png"); }
    @Override public void extractRenderState(NightMinionEntity entity, NightMinionRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.reaper=entity.isReaper();
        state.action = entity.minionAction();
        if (state.action == null) state.action = "idle";
        state.actionSeconds = Math.max(0f,
                (entity.level().getGameTime() - entity.minionActionStartTick() + partialTick) / 20f);
    }

    @Override
    public void submit(NightMinionRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState cameraState) {
        super.submit(state, poseStack, collector, cameraState);
        if (state.isInvisibleToPlayer) return;

        GltfModel model = GltfModelCache.get(Identifier.fromNamespaceAndPath("eccraft",state.reaper?"models/entity/death.glb":"models/entity/littledevil.glb"));
        if (model == null) return;
        var animation = com.eccraft.nightgod.NightMinionAnimations.select(model, state.action, state.actionSeconds, state.ageInTicks / 20.0F, state.reaper);
        Matrix4f[] locals = GltfAnimationPlayer.sampleLocalMatrices(
                model, animation.clip(), animation.sourceTime(), animation.looping());
        Matrix4f[] globals = GltfAnimationPlayer.globalMatrices(model, locals);

        poseStack.pushPose();
        // This GLB was authored with its forward axis opposite the vanilla living-entity model axis.
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.bodyRot));
        float scale = GLB_SCALE * state.scale;
        poseStack.scale(scale, scale, scale);
        collector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(getTextureLocation(state)),
                (pose, vertices) -> GltfRenderUtil.render(model, globals, pose, vertices, state.lightCoords));
        poseStack.popPose();
    }

    /** The clip provides the entire kneel/fall; prevent vanilla's sideways death roll. */
    @Override protected float getFlipDegrees() { return 0f; }
    /** Account for the long scythe, raised cast pose, and forward prone pose. */
    @Override protected AABB getBoundingBoxForCulling(NightMinionEntity entity) {
        return super.getBoundingBoxForCulling(entity).inflate(5.0);
    }
}

