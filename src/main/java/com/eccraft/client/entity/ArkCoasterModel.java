package com.eccraft.client.entity;
import net.minecraft.client.model.EntityModel;import net.minecraft.client.model.geom.*;import net.minecraft.client.model.geom.builders.*;import net.minecraft.client.animation.*;
public final class ArkCoasterModel extends EntityModel<ArkMinionState>{
 private final java.util.Map<String,KeyframeAnimation> clips=new java.util.HashMap<>();
 public ArkCoasterModel(ModelPart root){super(root);ArkCoasterAnimations.ALL.forEach((k,v)->clips.put(k,v.bake(root)));}
 public static LayerDefinition createLayer(){MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition toytrain1 = partdefinition.addOrReplaceChild("toytrain1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.475F, 0.175F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(8, 23).addBox(-0.975F, -0.825F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 14).addBox(-2.675F, -1.425F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3914F, 20.02F, -0.329F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r1 = toytrain1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.625F, -1.925F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition smallwheel = toytrain1.addOrReplaceChild("smallwheel", CubeListBuilder.create().texOffs(18, 8).addBox(-1.5F, -1.5F, 1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 18).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.025F, 2.4F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition bone20 = toytrain1.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(0, 8).addBox(-4.4F, -1.6F, -1.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-1.9F, -4.6F, -0.1F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-1.6F, -4.1F, 0.4F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.725F, -1.825F, -1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
 @Override public void setupAnim(ArkMinionState s){super.setupAnim(s);String action=s.action;float t=s.seconds;if(action.equals("dash_intro"))t*=1.125F;if(action.equals("dash_end")&&t>=.5F){action="idle";t-=.5F;}var def=ArkCoasterAnimations.ALL.getOrDefault(action,ArkCoasterAnimations.idle);if(action.equals("idle"))t%=def.lengthInSeconds();clips.getOrDefault(action,clips.get("idle")).apply((long)(t*1000),1);}
}