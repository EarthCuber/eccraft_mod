package com.eccraft.client.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

/** Empty vanilla adapter; Nightgod geometry and animation are rendered from the GLB. */
public final class NightgodModel extends EntityModel<NightgodRenderState> {
    public NightgodModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(NightgodRenderState state) {
        super.setupAnim(state);
    }

    public static LayerDefinition createLayer() {
        return LayerDefinition.create(new MeshDefinition(), 16, 16);
    }
}
