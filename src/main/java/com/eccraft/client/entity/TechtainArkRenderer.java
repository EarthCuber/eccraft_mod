package com.eccraft.client.entity;
import com.eccraft.entity.TechtainArkEntity;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.Identifier;
public final class TechtainArkRenderer extends MobRenderer<TechtainArkEntity,TechtainArkRenderState,TechtainArkModel>{
 public TechtainArkRenderer(EntityRendererProvider.Context c){super(c,new TechtainArkModel(TechtainArkModel.createLayer().bakeRoot()),.75F);}
 public TechtainArkRenderState createRenderState(){return new TechtainArkRenderState();}
 public Identifier getTextureLocation(TechtainArkRenderState s){return Identifier.fromNamespaceAndPath("eccraft","textures/entity/techtain_ark.png");}
 public void extractRenderState(TechtainArkEntity e,TechtainArkRenderState s,float partial){super.extractRenderState(e,s,partial);s.action=e.arkAction();s.actionSeconds=Math.max(0,(e.level().getGameTime()-e.arkStart()+partial)/20F);s.dashEnd=e.dashEnd();}
 protected float getFlipDegrees(){return 0;}
 protected net.minecraft.world.phys.AABB getBoundingBoxForCulling(TechtainArkEntity e){return super.getBoundingBoxForCulling(e).inflate(5);}
}