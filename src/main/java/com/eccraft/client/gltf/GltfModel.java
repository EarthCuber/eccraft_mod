package com.eccraft.client.gltf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/** Immutable CPU-side representation of the subset of glTF 2.0 used by entity models. */
public final class GltfModel {
    public final List<Node> nodes = new ArrayList<>();
    public final List<Integer> roots = new ArrayList<>();
    public final List<Mesh> meshes = new ArrayList<>();
    public final Map<String, Animation> animations = new HashMap<>();

    public static final class Node {
        public final String name;
        public final Vector3f translation;
        public final Quaternionf rotation;
        public final Vector3f scale;
        public final List<Integer> children = new ArrayList<>();
        public final List<Integer> meshes = new ArrayList<>();
        public int parent = -1;
        public int skin = -1;

        public Node(String name, Vector3f translation, Quaternionf rotation, Vector3f scale) {
            this.name = name;
            this.translation = translation;
            this.rotation = rotation;
            this.scale = scale;
        }

        public Matrix4f localMatrix() {
            return new Matrix4f().translationRotateScale(translation, rotation, scale);
        }
    }

    public static final class Skin {
        public final int[] joints;
        public final Matrix4f[] inverseBindMatrices;

        public Skin(int[] joints, Matrix4f[] inverseBindMatrices) {
            this.joints = joints;
            this.inverseBindMatrices = inverseBindMatrices;
        }
    }

    public final List<Skin> skins = new ArrayList<>();

    public static final class Mesh {
        public final float[] positions;
        public final float[] normals;
        public final float[] uvs;
        public final int[] indices;
        public final int[] joints;
        public final float[] weights;
        public int skin = -1;
        public int node = -1;

        public Mesh(float[] positions, float[] normals, float[] uvs, int[] indices,
                    int[] joints, float[] weights) {
            this.positions = positions;
            this.normals = normals;
            this.uvs = uvs;
            this.indices = indices;
            this.joints = joints;
            this.weights = weights;
        }

        public int vertexCount() {
            return positions.length / 3;
        }

        public boolean skinned() {
            return skin >= 0 && joints != null && weights != null;
        }
    }

    public enum Path { TRANSLATION, ROTATION, SCALE }
    public enum Interpolation { LINEAR, STEP, CUBICSPLINE }

    public record Channel(int node, Path path, float[] times, float[] values,
                          Interpolation interpolation) {
    }

    public record Animation(String name, float duration, List<Channel> channels) {
    }
}
