package com.eccraft.client.entity;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.entity.IcewitchEntity;

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
public final class IcewitchRenderer
        extends MobRenderer<IcewitchEntity, IcewitchRenderState, IcewitchModel> {
    private static final Identifier GLB_MODEL = Identifier.fromNamespaceAndPath("eccraft", "models/entity/icewitch.glb");
    private static final Identifier GLB_TEXTURE = Identifier.fromNamespaceAndPath("eccraft", "textures/entity/icewitch.png");
    private static final float GLB_SCALE = 0.1F;
    private final Identifier texture;
    public IcewitchRenderer(EntityRendererProvider.Context context) {
        super(context, new IcewitchModel(IcewitchModel.createLayer().bakeRoot()), 0.65f);
        this.texture = GLB_TEXTURE;
    }
    @Override public IcewitchRenderState createRenderState() { return new IcewitchRenderState(); }
    @Override public Identifier getTextureLocation(IcewitchRenderState state) { return texture; }
    @Override public void extractRenderState(IcewitchEntity entity, IcewitchRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.action = entity.icewitchAction();
        if (state.action == null) state.action = "idle";
        state.actionSeconds = Math.max(0f,
                (entity.level().getGameTime() - entity.icewitchActionStartTick() + partialTick) / 20f);
    }

    @Override
    public void submit(IcewitchRenderState state, PoseStack poseStack,
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

    private static AnimationSelection selectAnimation(IcewitchRenderState state, GltfModel model) {
        return switch (state.action) {
            case "attack" -> actionOrIdle(model, "Anim-1_attack_01", state.actionSeconds);
            case "skill_1", "skill_4" -> skillOrIdle(model, "Anim-1_attack_03", state.actionSeconds);
            case "skill_2" -> skillOrIdle(model, "Anim-1_attack_03", state.actionSeconds);
            case "skill_3" -> skillOrIdle(model, "Anim-1_attack_02", state.actionSeconds);
            case "death" -> actionOrIdle(model, "Anim-1_death", state.actionSeconds);
            default -> new AnimationSelection("Anim-1_stand", state.ageInTicks / 20.0F, true);
        };
    }

    private static AnimationSelection skillOrIdle(GltfModel model, String clip, float logicalTime) {
        final float playbackSeconds = 1.5F;
        GltfModel.Animation animation = model.animations.get(clip);
        if (animation == null || animation.duration() <= 0.0F) {
            return new AnimationSelection("Anim-1_stand", Math.max(0.0F, logicalTime), true);
        }
        if (logicalTime >= playbackSeconds) {
            return new AnimationSelection("Anim-1_stand", logicalTime - playbackSeconds, true);
        }
        return new AnimationSelection(clip,
                Math.max(0.0F, logicalTime) / playbackSeconds * animation.duration(), false);
    }

    private static AnimationSelection actionOrIdle(GltfModel model, String clip, float logicalTime) {
        GltfModel.Animation animation = model.animations.get(clip);
        float duration = animation == null ? 0.0F : animation.duration();
        if (!"Anim-1_death".equals(clip) && logicalTime >= duration) {
            return new AnimationSelection("Anim-1_stand", Math.max(0.0F, logicalTime - duration), true);
        }
        return new AnimationSelection(clip, Math.min(logicalTime, duration), false);
    }

    private record AnimationSelection(String clip, float sourceTime, boolean looping) {}
    /** The clip provides the entire kneel/fall; prevent vanilla's sideways death roll. */
    @Override protected float getFlipDegrees() { return 0f; }
    /** Account for the long scythe, raised cast pose, and forward prone pose. */
    @Override protected AABB getBoundingBoxForCulling(IcewitchEntity entity) {
        return super.getBoundingBoxForCulling(entity).inflate(5.0);
    }
}

