package com.eccraft.client.entity;

import com.eccraft.Eccraft;
import com.eccraft.client.gltf.*;
import com.eccraft.darkdragon.*;
import com.eccraft.entity.DarkDragonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;

public final class DarkDragonRenderer extends MobRenderer<DarkDragonEntity,DarkDragonRenderState,DarkDragonModel>{
    private static final Identifier BODY=Eccraft.id("models/entity/darkdragon_01.glb"),WINGS=Eccraft.id("models/entity/darkdragon_02.glb"),TEXTURE=Eccraft.id("textures/entity/darkdragon.png");
    public DarkDragonRenderer(EntityRendererProvider.Context context){super(context,new DarkDragonModel(DarkDragonModel.createLayer().bakeRoot()),2);}
    @Override public DarkDragonRenderState createRenderState(){return new DarkDragonRenderState();}
    @Override public Identifier getTextureLocation(DarkDragonRenderState state){return TEXTURE;}
    @Override public void extractRenderState(DarkDragonEntity e,DarkDragonRenderState s,float partial){super.extractRenderState(e,s,partial);s.action=e.dragonAction().name();s.air=e.airborne();s.actionSeconds=Math.max(0,(e.level().getGameTime()-e.actionStart()+partial)/20f);}
    @Override public void submit(DarkDragonRenderState state,PoseStack poses,SubmitNodeCollector collector,CameraRenderState camera){
        super.submit(state,poses,collector,camera);if(state.isInvisibleToPlayer)return;
        var body=GltfModelCache.get(BODY);if(body==null)return;
        // Resolve once from the body, then use identical clip/time for both meshes.
        var selected=DarkDragonPose.resolve(body,DarkDragonAction.parse(state.action),state.air,state.actionSeconds);
        poses.pushPose();poses.mulPose(Axis.YP.rotationDegrees(-state.bodyRot));float scale=DarkDragonPose.MODEL_SCALE*state.scale;poses.scale(scale,scale,scale);
        for(var id:new Identifier[]{BODY,WINGS}){var model=GltfModelCache.get(id);if(model==null)continue;var globals=GltfAnimationPlayer.globalMatrices(model,GltfAnimationPlayer.sampleLocalMatrices(model,selected.clip(),selected.seconds(),selected.loop()));
            collector.submitCustomGeometry(poses,RenderTypes.entityCutout(TEXTURE),(pose,vertices)->GltfRenderUtil.render(model,globals,pose,vertices,state.lightCoords));}
        poses.popPose();
    }
    @Override protected float getFlipDegrees(){return 0;}
    @Override protected AABB getBoundingBoxForCulling(DarkDragonEntity entity){return super.getBoundingBoxForCulling(entity).inflate(24 * (DarkDragonPose.MODEL_SCALE / 0.05F) * entity.getScale());}
}
