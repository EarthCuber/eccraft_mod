package com.eccraft.client.entity;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.entity.AnubisAnimationSource;
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
public final class AnubisRenderer<T extends Mob & AnubisAnimationSource>
        extends MobRenderer<T, AnubisRenderState, AnubisModel> {
    private static final Identifier GLB_MODEL = Identifier.fromNamespaceAndPath("eccraft", "models/entity/anubis.glb");
    private static final Identifier GLB_TEXTURE = Identifier.fromNamespaceAndPath("eccraft", "textures/entity/anubis_gltf.png");
    private static final float GLB_SCALE = 0.1F;
    private final Identifier texture;
    public AnubisRenderer(EntityRendererProvider.Context context, AnubisClient.Assets assets) {
        super(context, new AnubisModel(context.bakeLayer(assets.layer())), 0.65f);
        this.texture = assets.texture();
    }
    @Override public AnubisRenderState createRenderState() { return new AnubisRenderState(); }
    @Override public Identifier getTextureLocation(AnubisRenderState state) { return texture; }
    @Override public void extractRenderState(T entity, AnubisRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.action = entity.anubisAction();
        if (state.action == null) state.action = "idle";
        state.actionSeconds = Math.max(0f,
                (entity.level().getGameTime() - entity.anubisActionStartTick() + partialTick) / 20f);
    }

    @Override
    public void submit(AnubisRenderState state, PoseStack poseStack,
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

    private static AnimationSelection selectAnimation(AnubisRenderState state, GltfModel model) {
        return switch (state.action) {
            case "attack" -> stretched(model, "Anim-1_clip02", state.actionSeconds, 2.0F);
            case "skill_1" -> stretched(model, "Anim-1_walk01", state.actionSeconds, 1.0F);
            case "skill_2" -> stretched(model, "Anim-1_walk02", state.actionSeconds, 2.0F);
            case "skill_3" -> stretched(model, "Anim-1_walk02", state.actionSeconds, 2.0F);
            case "death" -> stretched(model, "Anim-1_walk03", state.actionSeconds, 2.0F);
            default -> state.walkAnimationSpeed > 0.01F
                    ? new AnimationSelection("Anim-1_clip01", state.ageInTicks / 20.0F, true)
                    : new AnimationSelection("Anim-1_idle01", state.ageInTicks / 20.0F, true);
        };
    }

    private static AnimationSelection stretched(GltfModel model, String clip, float logicalTime, float logicalDuration) {
        GltfModel.Animation animation = model.animations.get(clip);
        float duration = animation == null ? 0.0F : animation.duration();
        float sourceTime = logicalDuration <= 0.0F ? logicalTime : logicalTime * duration / logicalDuration;
        return new AnimationSelection(clip, sourceTime, false);
    }

    private record AnimationSelection(String clip, float sourceTime, boolean looping) {}
    /** The clip provides the entire kneel/fall; prevent vanilla's sideways death roll. */
    @Override protected float getFlipDegrees() { return 0f; }
    /** Account for the long scythe, raised cast pose, and forward prone pose. */
    @Override protected AABB getBoundingBoxForCulling(T entity) {
        return super.getBoundingBoxForCulling(entity).inflate(5.0);
    }
}

