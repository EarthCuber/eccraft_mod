package com.eccraft.armor.client;

import com.eccraft.client.gltf.*;
import java.util.Map;
import java.util.WeakHashMap;
import org.joml.Matrix4f;

/** Avoid mutating the shared GLB cache when using the approved rigid fallback. */
public final class MageArmorModels {
    private static final Map<GltfModel,GltfModel> SAFE=new WeakHashMap<>();
    public static synchronized GltfModel safe(GltfModel source) {
        return SAFE.computeIfAbsent(source, original -> {
            GltfModel result=new GltfModel();
            result.nodes.addAll(original.nodes); result.roots.addAll(original.roots);
            result.skins.addAll(original.skins); result.animations.putAll(original.animations);
            for(var mesh:original.meshes) {
                if(MageArmorT2Arms.applies(original,mesh)) { MageArmorT2Arms.split(result,mesh); continue; }
                var copy=new GltfModel.Mesh(mesh.positions,mesh.normals,mesh.uvs,mesh.indices,mesh.joints,mesh.weights);
                copy.node=mesh.node; copy.skin=mesh.skin>=0 && mesh.skin<original.skins.size()?mesh.skin:-1;
                result.meshes.add(copy);
            }
            return result;
        });
    }
    public static Matrix4f[] rest(GltfModel model) {
        return GltfAnimationPlayer.globalMatrices(model,GltfAnimationPlayer.sampleLocalMatrices(model,"",0,false));
    }
    private MageArmorModels() {}
}
