package com.eccraft.client.entity;
import com.eccraft.entity.FirelordMeteor;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.level.block.Blocks;
import org.joml.Quaternionf;
public final class FirelordMeteorRenderer extends EntityRenderer<FirelordMeteor,FirelordMeteorRenderer.State> {
    public static final class State extends EntityRenderState{public final MovingBlockRenderState block=new MovingBlockRenderState();public Quaternionf roll=new Quaternionf();}
    public FirelordMeteorRenderer(EntityRendererProvider.Context ctx){super(ctx);shadowRadius=1;}
    @Override public State createRenderState(){return new State();}
    @Override public void extractRenderState(FirelordMeteor e,State s,float partial){super.extractRenderState(e,s,partial);s.roll=e.roll(partial);var p=e.blockPosition();s.block.randomSeedPos=p;s.block.blockPos=p;s.block.blockState=Blocks.MAGMA_BLOCK.defaultBlockState();if(e.level() instanceof ClientLevel l){s.block.biome=l.getBiome(p);s.block.cardinalLighting=l.cardinalLighting();s.block.lightEngine=l.getLightEngine();}}
    @Override public void submit(State s,PoseStack p,SubmitNodeCollector collector,CameraRenderState camera){p.pushPose();p.translate(0,1,0);p.mulPose(s.roll);p.scale(2,2,2);p.translate(-.5,-.5,-.5);collector.submitMovingBlock(p,s.block,s.outlineColor);p.popPose();super.submit(s,p,collector,camera);}
}
