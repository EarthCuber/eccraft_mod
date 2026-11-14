package com.eccraft.client.entity;
import com.eccraft.entity.ArkMinionEntity;import net.minecraft.client.model.EntityModel;import net.minecraft.client.renderer.entity.*;import net.minecraft.resources.Identifier;
public final class ArkMinionRenderer extends MobRenderer<ArkMinionEntity,ArkMinionState,EntityModel<ArkMinionState>>{
 private final boolean box; public ArkMinionRenderer(EntityRendererProvider.Context c,int kind){super(c,kind==0?new ArkBoxModel(ArkBoxModel.createLayer().bakeRoot()):new ArkCoasterModel(ArkCoasterModel.createLayer().bakeRoot()),.5F);box=kind==0;}
 public ArkMinionState createRenderState(){return new ArkMinionState();}public Identifier getTextureLocation(ArkMinionState s){return Identifier.fromNamespaceAndPath("eccraft","textures/entity/"+(box?"surprise_box":"toytrain")+".png");}
 public void extractRenderState(ArkMinionEntity e,ArkMinionState s,float partial){super.extractRenderState(e,s,partial);s.action=e.visualAction();s.seconds=Math.max(0,(e.level().getGameTime()-e.visualStart()+partial)/20F);}
 protected float getFlipDegrees(){return 0;}
}