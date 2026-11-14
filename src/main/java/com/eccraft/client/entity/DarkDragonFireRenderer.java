package com.eccraft.client.entity;

import com.eccraft.entity.DarkDragonFireEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

/** Renders the spell entity with Minecraft's animated vanilla fire block model and textures. */
public final class DarkDragonFireRenderer extends EntityRenderer<DarkDragonFireEntity, DarkDragonFireRenderState> {
    public DarkDragonFireRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override
    public DarkDragonFireRenderState createRenderState() {
        return new DarkDragonFireRenderState();
    }

    @Override
    public void extractRenderState(DarkDragonFireEntity entity, DarkDragonFireRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        BlockPos pos = entity.blockPosition();
        state.fireBlock.randomSeedPos = pos;
        state.fireBlock.blockPos = pos;
        state.fireBlock.blockState = Blocks.FIRE.defaultBlockState();
        if (entity.level() instanceof ClientLevel clientLevel) {
            state.fireBlock.biome = clientLevel.getBiome(pos);
            state.fireBlock.cardinalLighting = clientLevel.cardinalLighting();
            state.fireBlock.lightEngine = clientLevel.getLightEngine();
        }
    }

    @Override
    public void submit(DarkDragonFireRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState cameraState) {
        poseStack.pushPose();
        poseStack.translate(-0.5, 0.0, -0.5);
        collector.submitMovingBlock(poseStack, state.fireBlock, state.outlineColor);
        poseStack.popPose();
        super.submit(state, poseStack, collector, cameraState);
    }
}
