package com.eccraft.client.npc;

import com.eccraft.client.gltf.GltfAnimationPlayer;
import com.eccraft.client.gltf.GltfModelCache;
import com.eccraft.client.gltf.GltfRenderUtil;
import com.eccraft.npc.NpcNormalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.entity.ClientMannequin;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.item.component.ResolvableProfile;

public final class NpcNormalRenderer extends EntityRenderer<NpcNormalEntity, NpcNormalRenderer.State> {
    public static final class State extends AvatarRenderState {
        String modelType = "gltf";
        String model = "";
        String texture = "";
        String idle = "";
        String playerName = "";
        float yaw;
        float modelScale;
        PlayerSkinRenderCache.RenderInfo skinInfo;
    }

    private final PlayerSkinRenderCache skinCache;
    private final AvatarRenderer<ClientMannequin> wideRenderer;
    private final AvatarRenderer<ClientMannequin> slimRenderer;

    public NpcNormalRenderer(EntityRendererProvider.Context context) {
        super(context);
        shadowRadius = .35F;
        skinCache = context.getPlayerSkinRenderCache();
        wideRenderer = new AvatarRenderer<>(context, false);
        slimRenderer = new AvatarRenderer<>(context, true);
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(NpcNormalEntity entity, State state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.modelType = entity.modelType();
        state.model = entity.model();
        state.texture = entity.texture();
        state.idle = entity.idle();
        state.playerName = entity.playerName();
        state.modelScale = entity.modelScale();
        state.yaw = entity.getViewYRot(partialTick);
        if ("mannequin".equals(state.modelType) && !state.playerName.isBlank()) {
            state.skinInfo = skinCache.getOrDefault(ResolvableProfile.createUnresolved(state.playerName));
            state.skin = state.skinInfo.playerSkin();
            state.bodyRot = entity.getVisualRotationYInDegrees();
            state.yRot = entity.getViewYRot(partialTick) - state.bodyRot;
            state.xRot = entity.getViewXRot(partialTick);
            state.showHat = true;
            state.showJacket = true;
            state.showLeftPants = true;
            state.showRightPants = true;
            state.showLeftSleeve = true;
            state.showRightSleeve = true;
        } else {
            state.skinInfo = null;
        }
    }

    @Override
    public void submit(State state, PoseStack poses, SubmitNodeCollector collector, CameraRenderState camera) {
        if ("mannequin".equals(state.modelType) && state.skinInfo != null) {
            AvatarRenderer<ClientMannequin> renderer = state.skin.model() == PlayerModelType.SLIM
                    ? slimRenderer : wideRenderer;
            renderer.submit(state, poses, collector, camera);
            return;
        }

        Identifier modelId = Identifier.tryParse(state.model);
        Identifier textureId = Identifier.tryParse(state.texture);
        if (modelId != null && textureId != null) {
            var model = GltfModelCache.get(modelId);
            if (model != null) {
                var globals = GltfAnimationPlayer.globalMatrices(model,
                        GltfAnimationPlayer.sampleLocalMatrices(model, state.idle, state.ageInTicks / 20F, true));
                poses.pushPose();
                poses.mulPose(Axis.YP.rotationDegrees(-state.yaw));
                poses.scale(state.modelScale, state.modelScale, state.modelScale);
                collector.submitCustomGeometry(poses, RenderTypes.entityCutout(textureId),
                        (pose, vertices) -> GltfRenderUtil.render(model, globals, pose, vertices, state.lightCoords));
                poses.popPose();
            }
        }
        super.submit(state, poses, collector, camera);
    }
}
