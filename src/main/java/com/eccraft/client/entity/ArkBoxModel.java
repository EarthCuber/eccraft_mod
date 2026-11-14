package com.eccraft.client.entity;
import net.minecraft.client.model.EntityModel;import net.minecraft.client.model.geom.*;import net.minecraft.client.model.geom.builders.*;import net.minecraft.client.animation.*;
public final class ArkBoxModel extends EntityModel<ArkMinionState>{
 private final java.util.Map<String,KeyframeAnimation> clips=new java.util.HashMap<>();
 public ArkBoxModel(ModelPart root){super(root);ArkBoxAnimations.ALL.forEach((k,v)->clips.put(k,v.bake(root)));}
 public static LayerDefinition createLayer(){MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.4592F, 11.2013F, -0.3152F));

		PartDefinition part_1 = main.addOrReplaceChild("1", CubeListBuilder.create(), PartPose.offset(4.7518F, 0.9737F, -6.8528F));

		PartDefinition cube_r1 = part_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(88, 66).addBox(-7.0F, -7.5F, -1.0F, 14.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.1219F, 0.0F, 0.0F, 0.0F, 0.624F, 0.0F));

		PartDefinition part_2 = main.addOrReplaceChild("2", CubeListBuilder.create(), PartPose.offset(4.7518F, 0.9737F, -6.8528F));

		PartDefinition cube_r2 = part_2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 83).addBox(-7.0F, -7.5F, -1.0F, 14.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.624F, 0.0F));

		PartDefinition part_3 = main.addOrReplaceChild("3", CubeListBuilder.create(), PartPose.offset(4.7518F, 0.9737F, -6.8528F));

		PartDefinition cube_r3 = part_3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 66).addBox(-7.0F, -7.5F, -1.0F, 14.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1511F, 0.0F, 9.6266F, 0.0F, -1.885F, 0.0F));

		PartDefinition part_4 = main.addOrReplaceChild("4", CubeListBuilder.create().texOffs(32, 83).addBox(-12.061F, -7.5F, 14.593F, 14.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.7518F, 0.9737F, -6.8528F));

		PartDefinition part_5 = main.addOrReplaceChild("5", CubeListBuilder.create(), PartPose.offset(4.7518F, 0.9737F, -6.8528F));

		PartDefinition cube_r4 = part_5.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 75).addBox(-7.0F, -7.5F, -1.0F, 14.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.273F, 0.0F, 9.6266F, 0.0F, 1.885F, 0.0F));

		PartDefinition up = main.addOrReplaceChild("up", CubeListBuilder.create().texOffs(96, 83).addBox(-13.061F, 5.5F, 14.593F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 37).addBox(-12.061F, 6.325F, 0.593F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(4.7518F, -12.9263F, -6.8528F));

		PartDefinition cube_r5 = up.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 20).addBox(-7.0F, -7.5F, 0.0F, 14.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(96, 21).addBox(-7.0F, -8.4F, -2.0F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.273F, 13.9F, 9.6266F, 0.0F, 1.885F, 0.0F));

		PartDefinition cube_r6 = up.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(56, 52).addBox(-7.0F, -7.5F, 0.0F, 14.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(96, 27).addBox(-7.0F, -8.4F, -2.0F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1511F, 13.9F, 9.6266F, 0.0F, -1.885F, 0.0F));

		PartDefinition cube_r7 = up.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(60, 12).addBox(-7.0F, -7.5F, 0.0F, 14.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(60, 21).addBox(-7.0F, -8.4F, -2.0F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.9F, 0.0F, 0.0F, -0.624F, 0.0F));

		PartDefinition cube_r8 = up.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(60, 27).addBox(-7.0F, 5.5F, -2.0F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.1219F, 0.0F, 0.0F, 0.0F, 0.624F, 0.0F));

		PartDefinition down = main.addOrReplaceChild("down", CubeListBuilder.create().texOffs(100, 0).addBox(-2.939F, 5.5F, 14.593F, 15.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 56).addBox(-1.939F, 6.1F, 0.593F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.3701F, 2.9737F, -6.8528F));

		PartDefinition cube_r9 = down.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 37).addBox(-7.0F, -3.5F, 0.0F, 14.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 100).addBox(-7.0F, -4.1F, -2.0F, 15.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.273F, 9.6F, 9.6266F, 0.0F, -1.885F, 0.0F));

		PartDefinition cube_r10 = down.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(58, 0).addBox(-7.0F, -3.5F, 0.0F, 14.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(96, 94).addBox(-7.0F, -4.1F, -2.0F, 15.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.1219F, 9.6F, 0.0F, 0.0F, -0.624F, 0.0F));

		PartDefinition cube_r11 = down.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -3.5F, 0.0F, 14.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(96, 99).addBox(-7.0F, -4.1F, -2.0F, 15.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1511F, 9.6F, 9.6266F, 0.0F, 1.885F, 0.0F));

		PartDefinition cube_r12 = down.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(96, 89).addBox(-7.0F, 5.5F, -2.0F, 15.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.624F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}
 @Override public void setupAnim(ArkMinionState s){super.setupAnim(s);String action=s.action;float t=s.seconds;if(action.equals("dash_intro"))t*=1.125F;if(action.equals("dash_end")&&t>=.5F){action="idle";t-=.5F;}var def=ArkBoxAnimations.ALL.getOrDefault(action,ArkBoxAnimations.idle);if(action.equals("idle"))t%=def.lengthInSeconds();clips.getOrDefault(action,clips.get("idle")).apply((long)(t*1000),1);}
}