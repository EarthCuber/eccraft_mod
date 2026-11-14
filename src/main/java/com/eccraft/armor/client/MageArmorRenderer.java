package com.eccraft.armor.client;

import com.eccraft.Eccraft;
import com.eccraft.armor.*;
import com.eccraft.client.gltf.*;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

public final class MageArmorRenderer implements ArmorRenderer {
    // Original body meshes use a 13.2-unit shoulder-to-ground frame; Minecraft uses 24 pixels.
    public static final float BODY_SCALE=1.5F/13.2F;
    // The helmet opening uses the same authoring units as the body, not its decorative bounds.
    public static final float HEAD_SCALE=BODY_SCALE;
    public static final float BOOT_TOP=.35F;
    public static void register() {
        ArmorRenderer.register(new MageArmorRenderer(),MageArmorItems.all().toArray(MageArmorItem[]::new));
        net.minecraft.client.renderer.special.SpecialModelRenderers.ID_MAPPER.put(
            Eccraft.id("mage_armor_glb"),MageArmorItemRenderer.Unbaked.MAP_CODEC);
    }
    @Override public void render(PoseStack poses, SubmitNodeCollector collector, ItemStack stack,
            HumanoidRenderState state, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> context) {
        if(!(stack.getItem() instanceof MageArmorItem armor) || armor.slot()!=slot)return;
        context.setupAnim(state);
        String prefix="equipment_mage_t"+armor.tier();
        Identifier texture=Eccraft.id("textures/armor/"+prefix+".png");
        if(slot==EquipmentSlot.FEET) {
            submit(prefix+"_4_left",texture,slot,true,context,poses,collector,light,stack.hasFoil());
            submit(prefix+"_4_right",texture,slot,false,context,poses,collector,light,stack.hasFoil());
        } else {
            int number=slot==EquipmentSlot.HEAD?1:slot==EquipmentSlot.CHEST?2:3;
            submit(prefix+"_"+number,texture,slot,false,context,poses,collector,light,stack.hasFoil());
        }
    }
    @Override public boolean shouldRenderDefaultHeadItem(net.minecraft.world.entity.LivingEntity entity,ItemStack stack) { return false; }
    private static void submit(String name, Identifier texture, EquipmentSlot slot, boolean left,
            HumanoidModel<?> context, PoseStack poses, SubmitNodeCollector collector,int light,boolean foil) {
        GltfModel loaded=GltfModelCache.get(Eccraft.id("models/armor/"+name+".glb"));
        if(loaded==null)return;
        GltfModel model=MageArmorModels.safe(loaded);
        Matrix4f[] matrices=matrices(model,context,slot,left);
        collector.submitCustomGeometry(poses,RenderTypes.entityCutout(texture),
            (pose,vertices)->GltfRenderUtil.render(model,matrices,pose,vertices,light));
        if(foil)collector.submitCustomGeometry(poses,RenderTypes.entityGlint(),
            (pose,vertices)->GltfRenderUtil.render(model,matrices,pose,vertices,light));
    }
    public static Matrix4f partMatrix(ModelPart part) {
        PoseStack pose=new PoseStack(); part.translateAndRotate(pose);
        return new Matrix4f(pose.last().pose());
    }
    private static Matrix4f bodyFrame() { return new Matrix4f().translation(0,1.5F,0).scale(BODY_SCALE,-BODY_SCALE,-BODY_SCALE); }
    private static Matrix4f delta(HumanoidModel<?> context,String name) {
        if(name.contains("_l_hand"))return partMatrix(context.leftArm).translate(-5F/16,-2F/16,0);
        if(name.contains("_r_hand"))return partMatrix(context.rightArm).translate(5F/16,-2F/16,0);
        if(name.contains("_l_leg") || name.contains("_l_foot"))return partMatrix(context.leftLeg).translate(-1.9F/16,-12F/16,0);
        if(name.contains("_r_leg") || name.contains("_r_foot"))return partMatrix(context.rightLeg).translate(1.9F/16,-12F/16,0);
        // Cloth, cape and torso bones follow the body; no extra cloth simulation is invented.
        return partMatrix(context.body);
    }
    public static Matrix4f[] matrices(GltfModel model,HumanoidModel<?> context,EquipmentSlot slot,boolean left) {
        Matrix4f[] rest=MageArmorModels.rest(model), result=new Matrix4f[rest.length];
        Matrix4f root;
        if(slot==EquipmentSlot.HEAD)root=partMatrix(context.head).scale(HEAD_SCALE,-HEAD_SCALE,-HEAD_SCALE);
        else if(slot==EquipmentSlot.FEET)root=partMatrix(left?context.leftLeg:context.rightLeg)
            .translate(0,BOOT_TOP,0).scale(BODY_SCALE,-BODY_SCALE,-BODY_SCALE);
        else root=partMatrix(context.body).mul(bodyFrame());
        for(int i=0;i<rest.length;i++)result[i]=new Matrix4f(root).mul(rest[i]);
        for(int i=0;i<model.nodes.size();i++) {
            String name=model.nodes.get(i).name;
            if(name.equals(MageArmorT2Arms.LEFT))result[i]=delta(context,"bn_l_hand").mul(bodyFrame());
            if(name.equals(MageArmorT2Arms.RIGHT))result[i]=delta(context,"bn_r_hand").mul(bodyFrame());
        }
        for(var skin:model.skins)for(int i=0;i<skin.joints.length;i++) {
            int node=skin.joints[i];
            // Existing renderer multiplies this by inverseBind again. This retargets each
            // original weighted rest vertex using the corresponding animated vanilla limb.
            result[node]=delta(context,model.nodes.get(node).name).mul(bodyFrame())
                .mul(new Matrix4f(skin.inverseBindMatrices[i]).invert());
        }
        return result;
    }
}
