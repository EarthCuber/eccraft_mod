package com.eccraft.client.entity;

import com.eccraft.Eccraft;
import com.eccraft.banshee.BansheeAnimationCycle;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.entity.BansheeProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public final class BansheeProjectileRenderer extends EntityRenderer<BansheeProjectile, BansheeProjectileRenderer.State> {
    private static final Identifier MODEL = Eccraft.id("models/entity/banshee_projectile.glb");
    private static final Identifier TEXTURE = Eccraft.id("textures/entity/banshee_projectile.png");
    private static final String[] CLIPS = {"Anim-1_anim_0", "Anim-1_anim_1"};
    public static final float MODEL_SCALE = 0.1F;
    public static final class State extends EntityRenderState { public float yaw, pitch; }
    public BansheeProjectileRenderer(EntityRendererProvider.Context context) { super(context); shadowRadius = 0; }
    @Override public State createRenderState() { return new State(); }
    @Override public void extractRenderState(BansheeProjectile entity, State state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        var motion = entity.getDeltaMovement();
        state.yaw = (float) Math.toDegrees(Math.atan2(motion.x, motion.z));
        state.pitch = (float) Math.toDegrees(Math.atan2(motion.y, Math.hypot(motion.x, motion.z)));
    }
    @Override public void submit(State state, PoseStack poses, SubmitNodeCollector collector, CameraRenderState camera) {
        var model = GltfModelCache.get(MODEL);
        if (model != null) {
            var first = model.animations.get(CLIPS[0]);
            var second = model.animations.get(CLIPS[1]);
            var frame = BansheeAnimationCycle.sample(state.ageInTicks / 20.0F,
                    first == null ? 0 : first.duration(), second == null ? 0 : second.duration());
            var globals = GltfAnimationPlayer.globalMatrices(model,
                    GltfAnimationPlayer.sampleLocalMatrices(model, CLIPS[frame.index()], frame.time(), false));
            poses.pushPose();
            poses.mulPose(Axis.YP.rotationDegrees(state.yaw));
            poses.mulPose(Axis.XP.rotationDegrees(-state.pitch));
            poses.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
            collector.submitCustomGeometry(poses, RenderTypes.entityCutout(TEXTURE),
                    (pose, vertices) -> GltfRenderUtil.render(model, globals, pose, vertices, state.lightCoords));
            poses.popPose();
        }
        super.submit(state, poses, collector, camera);
    }
}
