package com.eccraft.client.entity;
import com.eccraft.entity.FirelordLava;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;
public final class FirelordLavaRenderer extends EntityRenderer<FirelordLava,EntityRenderState> {
 private static final Identifier TEXTURE=Identifier.fromNamespaceAndPath("eccraft","textures/entity/firelord_lava.png");
 public FirelordLavaRenderer(EntityRendererProvider.Context ctx){super(ctx);shadowRadius=0;}
 @Override public EntityRenderState createRenderState(){return new EntityRenderState();}
 @Override protected AABB getBoundingBoxForCulling(FirelordLava entity){return new AABB(entity.position(),entity.position()).inflate(1.5,.1,1.5);}
 @Override public void submit(EntityRenderState state,PoseStack poses,SubmitNodeCollector collector,CameraRenderState camera){
  if(state.isInvisible)return;
  collector.submitCustomGeometry(poses,RenderTypes.entityCutoutCull(TEXTURE),(pose,v)->{
   float r=1.5F;float[][] corners={{-r,0,-r,0,0},{-r,0,r,0,1},{r,0,r,1,1},{r,0,-r,1,0}};
   for(float[] p:corners)v.addVertex(pose.pose(),p[0],p[1]+.002F,p[2]).setColor(255,255,255,255).setUv(p[3],p[4]).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose,0,1,0);
  });
 }
}
