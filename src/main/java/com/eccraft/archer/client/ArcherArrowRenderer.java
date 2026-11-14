package com.eccraft.archer.client;
import com.eccraft.Eccraft;
import com.eccraft.archer.ArcherArrow;
import com.eccraft.client.gltf.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;
public final class ArcherArrowRenderer extends EntityRenderer<ArcherArrow,ArcherArrowRenderer.State> {
 public static final class State extends EntityRenderState {public int tier=1;public float yaw,pitch,age;}
 public ArcherArrowRenderer(EntityRendererProvider.Context context){super(context);shadowRadius=0;}
 @Override public State createRenderState(){return new State();}
 @Override public void extractRenderState(ArcherArrow entity,State state,float partial){
  super.extractRenderState(entity,state,partial);state.tier=entity.tier();state.age=entity.tickCount+partial;state.yaw=Mth.lerp(partial,entity.yRotO,entity.getYRot());state.pitch=Mth.lerp(partial,entity.xRotO,entity.getXRot());
 }
 @Override public void submit(State state,PoseStack poses,SubmitNodeCollector collector,CameraRenderState camera){
  var model=GltfModelCache.get(Eccraft.id("models/entity/archer/arrow_t"+state.tier+".glb"));
  if(model!=null){
   var clip=model.animations.values().stream().findFirst().orElse(null);
   var globals=GltfAnimationPlayer.globalMatrices(model,GltfAnimationPlayer.sampleLocalMatrices(model,clip==null?"":clip.name(),state.age/20F,true));
   poses.pushPose();poses.mulPose(Axis.YP.rotationDegrees(state.yaw));poses.mulPose(Axis.XP.rotationDegrees(-state.pitch));
   // Asset orientation and size calibrated from the actual rendered rest geometry.
   ArcherVisuals.arrowPose(poses,state.tier);
   collector.submitCustomGeometry(poses,RenderTypes.entityCutout(Eccraft.id("textures/entity/archer/arrow_t"+state.tier+".png")),(pose,vertices)->GltfRenderUtil.render(model,globals,pose,vertices,state.lightCoords));
   poses.popPose();
  }
  super.submit(state,poses,collector,camera);
 }
}
