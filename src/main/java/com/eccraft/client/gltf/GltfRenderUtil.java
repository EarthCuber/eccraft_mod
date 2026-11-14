package com.eccraft.client.gltf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/** Converts indexed triangles to degenerate quads accepted by entity render types. */
public final class GltfRenderUtil {
    private static final ThreadLocal<Scratch> SCRATCH = ThreadLocal.withInitial(Scratch::new);

    private GltfRenderUtil() {}

    public static void render(GltfModel model, Matrix4f[] globals, PoseStack.Pose pose,
                              VertexConsumer output, int light) {
        for (GltfModel.Mesh mesh : model.meshes) {
            Matrix4f[] bones = mesh.skinned() ? bones(model, mesh, globals) : null;
            for (int i = 0; i + 2 < mesh.indices.length; i += 3) {
                int a = mesh.indices[i];
                int b = mesh.indices[i + 1];
                int c = mesh.indices[i + 2];
                // ENTITY_* render types consume quads. Repeating the final vertex represents one triangle.
                emit(model, mesh, globals, bones, a, pose, output, light);
                emit(model, mesh, globals, bones, c, pose, output, light);
                emit(model, mesh, globals, bones, b, pose, output, light);
                emit(model, mesh, globals, bones, c, pose, output, light);
            }
        }
    }

    private static Matrix4f[] bones(GltfModel model, GltfModel.Mesh mesh, Matrix4f[] globals) {
        GltfModel.Skin skin = model.skins.get(mesh.skin);
        Matrix4f[] result = new Matrix4f[skin.joints.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Matrix4f(globals[skin.joints[i]]).mul(skin.inverseBindMatrices[i]);
        }
        return result;
    }

    private static void emit(GltfModel model, GltfModel.Mesh mesh, Matrix4f[] globals, Matrix4f[] bones,
                             int vertex, PoseStack.Pose pose, VertexConsumer output, int light) {
        int p = vertex * 3;
        if (p + 2 >= mesh.positions.length) return;
        Scratch scratch = SCRATCH.get();
        Vector3f position = scratch.position.set(mesh.positions[p], mesh.positions[p + 1], mesh.positions[p + 2]);
        Vector3f normal = mesh.normals != null && p + 2 < mesh.normals.length
                ? scratch.normal.set(mesh.normals[p], mesh.normals[p + 1], mesh.normals[p + 2])
                : scratch.normal.set(0.0F, 1.0F, 0.0F);

        if (bones != null && vertex * 4 + 3 < mesh.joints.length && vertex * 4 + 3 < mesh.weights.length) {
            skin(position, normal, mesh, vertex, bones, scratch);
        } else if (mesh.node >= 0) {
            globals[mesh.node].transformPosition(position);
            scratch.normalMatrix.set(globals[mesh.node]).transform(normal).normalize();
        }

        int uv = vertex * 2;
        float u = mesh.uvs != null && uv + 1 < mesh.uvs.length ? mesh.uvs[uv] : 0.0F;
        float v = mesh.uvs != null && uv + 1 < mesh.uvs.length ? mesh.uvs[uv + 1] : 0.0F;
        output.addVertex(pose.pose(), position.x, position.y, position.z)
                .setColor(255, 255, 255, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, normal.x, normal.y, normal.z);
    }

    private static void skin(Vector3f position, Vector3f normal, GltfModel.Mesh mesh,
                             int vertex, Matrix4f[] bones, Scratch scratch) {
        Vector4f originalPosition = scratch.originalPosition.set(position, 1.0F);
        Vector3f originalNormal = scratch.originalNormal.set(normal);
        Vector4f blendedPosition = scratch.blendedPosition.zero();
        Vector3f blendedNormal = scratch.blendedNormal.zero();
        float total = 0.0F;
        int offset = vertex * 4;
        for (int influence = 0; influence < 4; influence++) {
            int joint = mesh.joints[offset + influence];
            float weight = mesh.weights[offset + influence];
            if (weight <= 0.0F || joint < 0 || joint >= bones.length) continue;
            Vector4f transformedPosition = scratch.transformedPosition.set(originalPosition).mul(bones[joint]).mul(weight);
            blendedPosition.add(transformedPosition);
            Vector3f transformedNormal = scratch.transformedNormal.set(originalNormal);
            scratch.normalMatrix.set(bones[joint]).transform(transformedNormal).mul(weight);
            blendedNormal.add(transformedNormal);
            total += weight;
        }
        if (total > 0.0001F) {
            position.set(blendedPosition.x / total, blendedPosition.y / total, blendedPosition.z / total);
            normal.set(blendedNormal).normalize();
        }
    }

    private static final class Scratch {
        final Vector3f position = new Vector3f();
        final Vector3f normal = new Vector3f();
        final Vector3f originalNormal = new Vector3f();
        final Vector3f transformedNormal = new Vector3f();
        final Vector3f blendedNormal = new Vector3f();
        final Vector4f originalPosition = new Vector4f();
        final Vector4f transformedPosition = new Vector4f();
        final Vector4f blendedPosition = new Vector4f();
        final Matrix3f normalMatrix = new Matrix3f();
    }
}
