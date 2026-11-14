package com.eccraft.client.entity;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.entity.SamuraiEntity;

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
public final class SamuraiRenderer
        extends MobRenderer<SamuraiEntity, SamuraiRenderState, SamuraiModel> {
    private static final Identifier GLB_MODEL = Identifier.fromNamespaceAndPath("eccraft", "models/entity/samurai.glb");
    private static final Identifier GLB_TEXTURE = Identifier.fromNamespaceAndPath("eccraft", "textures/entity/samurai.png");
    private static final float GLB_SCALE = 0.1F;
    private final Identifier texture;
    public SamuraiRenderer(EntityRendererProvider.Context context) {
        super(context, new SamuraiModel(SamuraiModel.createLayer().bakeRoot()), 0.65f);
        this.texture = GLB_TEXTURE;
    }
    @Override public SamuraiRenderState createRenderState() { return new SamuraiRenderState(); }
    @Override public Identifier getTextureLocation(SamuraiRenderState state) { return texture; }
    @Override public void extractRenderState(SamuraiEntity entity, SamuraiRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.action = entity.samuraiAction();
        if (state.action == null) state.action = "idle";
        state.actionSeconds = Math.max(0f,
                (entity.level().getGameTime() - entity.samuraiActionStartTick() + partialTick) / 20f);
    }

    @Override
    public void submit(SamuraiRenderState state, PoseStack poseStack,
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

    private static AnimationSelection selectAnimation(SamuraiRenderState state, GltfModel model) {
        return switch (state.action) {
            case "attack" -> actionOrIdle(model, "Anim-1_attack_01", state.actionSeconds);
            case "skill_1" -> actionOrIdle(model, "Anim-1_attack_02", state.actionSeconds);
            case "skill_2" -> actionOrIdle(model, "Anim-1_attack_03", state.actionSeconds);
            case "death" -> actionOrIdle(model, "Anim-1_death", state.actionSeconds);
            case "walk" -> oneSecondLoop(model, "Anim-1_run", state.ageInTicks / 20.0F);
            default -> idle(model, state.ageInTicks / 20.0F);
        };
    }

    private static AnimationSelection idle(GltfModel model, float seconds) {
        var clip = model.animations.get("Anim-1_stand");
        float duration = clip == null ? 0.0F : clip.duration();
        return new AnimationSelection("Anim-1_stand", seconds * duration, true);
    }

    private static AnimationSelection actionOrIdle(GltfModel model, String clip, float seconds) {
        var animation = model.animations.get(clip);
        float duration = animation == null ? 0.0F : animation.duration();
        if (!"Anim-1_death".equals(clip) && seconds >= 1.0F) return idle(model, seconds - 1.0F);
        return new AnimationSelection(clip, Math.max(0, Math.min(seconds, 1.0F)) * duration, false);
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
    @Override protected AABB getBoundingBoxForCulling(SamuraiEntity entity) {
        return super.getBoundingBoxForCulling(entity).inflate(5.0);
    }
}

