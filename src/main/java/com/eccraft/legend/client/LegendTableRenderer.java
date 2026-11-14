package com.eccraft.legend.client;
import com.eccraft.Eccraft;
import com.eccraft.legend.*;
import com.eccraft.client.gltf.*;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
public final class LegendTableRenderer implements BlockEntityRenderer<LegendTables.TableEntity,LegendTableRenderer.State> {
 public static class State extends BlockEntityRenderState {public String kind="sword";public float seconds;}
 public static void register(){net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry.register(LegendTables.TYPE,context->new LegendTableRenderer());}
 @Override public State createRenderState(){return new State();}
 @Override public void extractRenderState(LegendTables.TableEntity entity,State state,float partial,Vec3 camera,ModelFeatureRenderer.CrumblingOverlay overlay){
  BlockEntityRenderState.extractBase(entity,state,overlay);state.kind=((LegendTableBlock)entity.getBlockState().getBlock()).kind();
  state.seconds=((entity.getLevel().getGameTime()%120000)+partial)/20F;
 }
 @Override public void submit(State state,PoseStack poses,SubmitNodeCollector collector,CameraRenderState camera){
  var model=GltfModelCache.get(Eccraft.id("models/block/legend_table.glb"));if(model==null)return;
  var clip=model.animations.values().stream().findFirst().orElse(null);
  var globals=GltfAnimationPlayer.globalMatrices(model,GltfAnimationPlayer.sampleLocalMatrices(model,clip==null?"":clip.name(),state.seconds,true));
  poses.pushPose();poses.translate(.5,0,.5);poses.scale(1F/16,1F/16,1F/16);
  collector.submitCustomGeometry(poses,RenderTypes.entityCutout(Eccraft.id("textures/block/legend_table_"+state.kind+".png")),(pose,out)->GltfRenderUtil.render(model,globals,pose,out,state.lightCoords));poses.popPose();
 }
}
