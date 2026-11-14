package com.eccraft.staff.client;

import com.eccraft.Eccraft;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.staff.StaffProjectile;
import com.eccraft.staff.StaffRoute;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public final class StaffProjectileRenderer extends EntityRenderer<StaffProjectile, StaffProjectileRenderer.State> {
    private static final Identifier[] TEXTURES = {
        Eccraft.id("textures/entity/staff/projectile_magic.png"),
        Eccraft.id("textures/entity/staff/projectile_frost.png"),
        Eccraft.id("textures/entity/staff/projectile_heal.png")
    };
    private static final Identifier[] MODELS = {
        Eccraft.id("models/entity/staff/projectile_magic.glb"),
        Eccraft.id("models/entity/staff/projectile_frost.glb"),
        Eccraft.id("models/entity/staff/projectile_heal.glb")
    };
    public static final class State extends EntityRenderState {
        public StaffRoute route = StaffRoute.NORMAL;
        public float yaw, pitch;
    }
    public StaffProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        shadowRadius = 0;
    }
    @Override public State createRenderState() { return new State(); }
    @Override public void extractRenderState(StaffProjectile entity, State state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.route = entity.visualRoute();
        var motion = entity.getDeltaMovement();
        state.yaw = (float)Math.toDegrees(Math.atan2(motion.x, motion.z));
        state.pitch = (float)Math.toDegrees(Math.atan2(motion.y, Math.hypot(motion.x, motion.z)));
    }
    @Override public void submit(State state, PoseStack poses, SubmitNodeCollector collector, CameraRenderState camera) {
        GltfModel mesh = GltfModelCache.get(MODELS[state.route.ordinal()]);
        if (mesh != null) {
            var globals = GltfAnimationPlayer.globalMatrices(mesh, GltfAnimationPlayer.sampleLocalMatrices(mesh, "", 0, false));
            poses.pushPose();
            poses.mulPose(Axis.YP.rotationDegrees(state.yaw));
            poses.mulPose(Axis.XP.rotationDegrees(-state.pitch));
            // Source models use different units; keep both about 0.6 blocks long.
            float scale = state.route == StaffRoute.HEALING ? 0.215F : 0.75F;
            poses.scale(scale, scale, scale);
            collector.submitCustomGeometry(poses, RenderTypes.entityCutout(TEXTURES[state.route.ordinal()]),
                    (pose, vertices) -> GltfRenderUtil.render(mesh, globals, pose, vertices, state.lightCoords));
            poses.popPose();
        }
        super.submit(state, poses, collector, camera);
    }
}
