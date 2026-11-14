package com.eccraft.client.entity;

import com.eccraft.client.gltf.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Consumer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.*;
import net.minecraft.resources.Identifier;
import org.joml.*;

/** Reuses original armor geometry/texture for inventory, dropped and held item views. */
public record FlyingHammerRenderer(Identifier model,Identifier texture) implements NoDataSpecialModelRenderer {
    @Override public void submit(PoseStack poses,SubmitNodeCollector collector,int light,int overlay,boolean foil,int outline) {
        GltfModel source=GltfModelCache.get(model); if(source==null)return;
        GltfModel mesh=source; Matrix4f[] globals=GltfAnimationPlayer.globalMatrices(mesh,GltfAnimationPlayer.sampleLocalMatrices(mesh,null,0,false));
        collector.submitCustomGeometry(poses,RenderTypes.entityCutout(texture),(pose,vertices)->GltfRenderUtil.render(mesh,globals,pose,vertices,light));
        if(foil)collector.submitCustomGeometry(poses,RenderTypes.entityGlint(),(pose,vertices)->GltfRenderUtil.render(mesh,globals,pose,vertices,light));
    }
    @Override public void getExtents(Consumer<Vector3fc> output) {
        GltfModel source=GltfModelCache.get(model); if(source==null)return;
        GltfModel mesh=source; Matrix4f[] globals=GltfAnimationPlayer.globalMatrices(mesh,GltfAnimationPlayer.sampleLocalMatrices(mesh,null,0,false));
        for(var part:mesh.meshes)for(int i=0;i<part.positions.length;i+=3) {
            Vector3f vertex=new Vector3f(part.positions[i],part.positions[i+1],part.positions[i+2]);
            if(part.node>=0)globals[part.node].transformPosition(vertex); output.accept(vertex);
        }
    }
    public record Unbaked(Identifier model,Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC=RecordCodecBuilder.mapCodec(i->i.group(
            Identifier.CODEC.fieldOf("model").forGetter(Unbaked::model),
            Identifier.CODEC.fieldOf("texture").forGetter(Unbaked::texture)).apply(i,Unbaked::new));
        @Override public MapCodec<Unbaked> type() { return MAP_CODEC; }
        @Override public FlyingHammerRenderer bake(SpecialModelRenderer.BakingContext context) { return new FlyingHammerRenderer(model,texture); }
    }
}
