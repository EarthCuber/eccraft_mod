package com.eccraft.client.entity;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.entity.JunglelordEntity;

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
public final class JunglelordRenderer
        extends MobRenderer<JunglelordEntity, JunglelordRenderState, JunglelordModel> {
    private static final Identifier GLB_MODEL = Identifier.fromNamespaceAndPath("eccraft", "models/entity/junglelord.glb");
    private static final Identifier GLB_TEXTURE = Identifier.fromNamespaceAndPath("eccraft", "textures/entity/junglelord.png");
    private static final float GLB_SCALE = 0.1F;
    private final Identifier texture;
    public JunglelordRenderer(EntityRendererProvider.Context context) {
        super(context, new JunglelordModel(JunglelordModel.createLayer().bakeRoot()), 0.65f);
        this.texture = GLB_TEXTURE;
    }
    @Override public JunglelordRenderState createRenderState() { return new JunglelordRenderState(); }
    @Override public Identifier getTextureLocation(JunglelordRenderState state) { return texture; }
    @Override public void extractRenderState(JunglelordEntity entity, JunglelordRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.action = entity.junglelordAction();
        if (state.action == null) state.action = "idle";
        state.actionSeconds = Math.max(0f,
                (entity.level().getGameTime() - entity.junglelordActionStartTick() + partialTick) / 20f);
    }

    @Override
    public void submit(JunglelordRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState cameraState) {
        super.submit(state, poseStack, collector, cameraState);
        if (state.isInvisibleToPlayer) return;

        GltfModel model = GltfModelCache.get(GLB_MODEL);
        if (model == null) return;
        AnimationSelection animation = selectAnimation(state, model);
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

    private static AnimationSelection selectAnimation(JunglelordRenderState state, GltfModel model) {
        return switch (state.action) {
            case "attack" -> actionOrIdle(model, "Anim-1_attack_01", state.actionSeconds);
            case "skill_1" -> actionOrIdle(model, "Anim-1_attack_03", state.actionSeconds);
            case "skill_2" -> actionOrIdle(model, "Anim-1_attack_02", state.actionSeconds);
            case "death" -> actionOrIdle(model, "Anim-1_death", state.actionSeconds);
            case "walk" -> oneSecondLoop(model, "Anim-1_walk", state.ageInTicks / 20.0F);
            default -> oneSecondLoop(model, "Anim-1_stand", state.ageInTicks / 20.0F);
        };
    }

    private static AnimationSelection actionOrIdle(GltfModel model, String clip, float logicalTime) {
        GltfModel.Animation animation = model.animations.get(clip);
        float duration = animation == null ? 0.0F : animation.duration();
        if ("Anim-1_death".equals(clip)) {
            return new AnimationSelection(clip, Math.min(logicalTime, duration), false);
        }
        if (logicalTime >= 1.0F) {
            return oneSecondLoop(model, "Anim-1_stand", logicalTime - 1.0F);
        }
        return new AnimationSelection(clip, Math.max(0.0F, logicalTime) * duration, false);
    }

    private static AnimationSelection oneSecondLoop(GltfModel model, String clip, float seconds) {
        var animation = model.animations.get(clip);
        float duration = animation == null ? 0.0F : animation.duration();
        return new AnimationSelection(clip, seconds * duration, true);
    }

    private record AnimationSelection(String clip, float sourceTime, boolean looping) {}
    /** The clip provides the entire kneel/fall; prevent vanilla's sideways death roll. */
    @Override protected float getFlipDegrees() { return 0f; }
    /** Account for the long scythe, raised cast pose, and forward prone pose. */
    @Override protected AABB getBoundingBoxForCulling(JunglelordEntity entity) {
        return super.getBoundingBoxForCulling(entity).inflate(5.0);
    }
}

