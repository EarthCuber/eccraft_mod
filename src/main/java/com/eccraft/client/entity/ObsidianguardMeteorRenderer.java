package com.eccraft.client.entity;

import com.eccraft.entity.ObsidianguardMeteor;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.level.block.Blocks;

public final class ObsidianguardMeteorRenderer extends EntityRenderer<ObsidianguardMeteor,DarkDragonFireRenderState>{
    public ObsidianguardMeteorRenderer(EntityRendererProvider.Context context){super(context);shadowRadius=0;}
    @Override public DarkDragonFireRenderState createRenderState(){return new DarkDragonFireRenderState();}
    @Override public void extractRenderState(ObsidianguardMeteor entity,DarkDragonFireRenderState state,float partial){
        super.extractRenderState(entity,state,partial);var pos=entity.blockPosition();state.fireBlock.randomSeedPos=pos;state.fireBlock.blockPos=pos;state.fireBlock.blockState=Blocks.MAGMA_BLOCK.defaultBlockState();
        if(entity.level() instanceof ClientLevel client){state.fireBlock.biome=client.getBiome(pos);state.fireBlock.cardinalLighting=client.cardinalLighting();state.fireBlock.lightEngine=client.getLightEngine();}
    }
    @Override public void submit(DarkDragonFireRenderState state,PoseStack poses,SubmitNodeCollector collector,CameraRenderState camera){poses.pushPose();poses.translate(-1,0,-1);poses.scale(2,2,2);collector.submitMovingBlock(poses,state.fireBlock,state.outlineColor);poses.popPose();super.submit(state,poses,collector,camera);}
}
