package com.eccraft.client.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

/** Empty vanilla adapter; Samurai geometry and animation are rendered from the GLB. */
public final class SamuraiModel extends EntityModel<SamuraiRenderState> {
    public SamuraiModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(SamuraiRenderState state) {
        super.setupAnim(state);
    }

    public static LayerDefinition createLayer() {
        return LayerDefinition.create(new MeshDefinition(), 16, 16);
    }
}
