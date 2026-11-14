package com.eccraft.client.gltf;

import java.util.Arrays;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/** Samples glTF node TRS channels without retaining entity references. */
public final class GltfAnimationPlayer {
    private GltfAnimationPlayer() {}

    public static Matrix4f[] sampleLocalMatrices(GltfModel model, String clip, float time, boolean looping) {
        Vector3f[] translations = new Vector3f[model.nodes.size()];
        Quaternionf[] rotations = new Quaternionf[model.nodes.size()];
        Vector3f[] scales = new Vector3f[model.nodes.size()];
        for (int i = 0; i < model.nodes.size(); i++) {
            GltfModel.Node node = model.nodes.get(i);
            translations[i] = new Vector3f(node.translation);
            rotations[i] = new Quaternionf(node.rotation);
            scales[i] = new Vector3f(node.scale);
        }

        GltfModel.Animation animation = model.animations.get(clip);
        if (animation != null && animation.duration() > 0.0F) {
            float sampledTime = looping ? positiveModulo(time, animation.duration())
                    : Math.min(Math.max(0.0F, time), animation.duration());
            for (GltfModel.Channel channel : animation.channels()) {
                if (channel.node() < 0 || channel.node() >= model.nodes.size() || channel.times().length == 0) continue;
                switch (channel.path()) {
                    case TRANSLATION -> sampleVector(channel, sampledTime, translations[channel.node()]);
                    case SCALE -> sampleVector(channel, sampledTime, scales[channel.node()]);
                    case ROTATION -> sampleQuaternion(channel, sampledTime, rotations[channel.node()]);
                }
            }
        }

        Matrix4f[] locals = new Matrix4f[model.nodes.size()];
        Arrays.setAll(locals, i -> new Matrix4f().translationRotateScale(translations[i], rotations[i], scales[i]));
        return locals;
    }

    public static Matrix4f[] globalMatrices(GltfModel model, Matrix4f[] locals) {
        Matrix4f[] globals = new Matrix4f[model.nodes.size()];
        boolean[] visiting = new boolean[model.nodes.size()];
        for (int i = 0; i < globals.length; i++) resolveGlobal(model, locals, globals, visiting, i);
        return globals;
    }

    private static Matrix4f resolveGlobal(GltfModel model, Matrix4f[] locals, Matrix4f[] globals,
                                          boolean[] visiting, int nodeIndex) {
        if (globals[nodeIndex] != null) return globals[nodeIndex];
        if (visiting[nodeIndex]) throw new IllegalStateException("Cycle in glTF node hierarchy at " + nodeIndex);
        visiting[nodeIndex] = true;
        int parent = model.nodes.get(nodeIndex).parent;
        globals[nodeIndex] = parent < 0 ? new Matrix4f(locals[nodeIndex])
                : new Matrix4f(resolveGlobal(model, locals, globals, visiting, parent)).mul(locals[nodeIndex]);
        visiting[nodeIndex] = false;
        return globals[nodeIndex];
    }

    private static void sampleVector(GltfModel.Channel channel, float time, Vector3f destination) {
        int left = leftKey(channel.times(), time);
        int right = Math.min(left + 1, channel.times().length - 1);
        int stride = channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE ? 9 : 3;
        int valueOffset = left * stride + (channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE ? 3 : 0);
        destination.set(channel.values()[valueOffset], channel.values()[valueOffset + 1], channel.values()[valueOffset + 2]);
        if (right == left || channel.interpolation() == GltfModel.Interpolation.STEP) return;
        int rightOffset = right * stride + (channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE ? 3 : 0);
        float amount = fraction(channel.times(), left, right, time);
        if (channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE) {
            float keyLength = channel.times()[right] - channel.times()[left];
            float[] values = channel.values();
            for (int component = 0; component < 3; component++) {
                destination.setComponent(component, hermite(
                        values[valueOffset + component], values[left * stride + 6 + component],
                        values[rightOffset + component], values[right * stride + component],
                        amount, keyLength));
            }
            return;
        }
        destination.lerp(new Vector3f(channel.values()[rightOffset], channel.values()[rightOffset + 1],
                channel.values()[rightOffset + 2]), amount);
    }

    private static void sampleQuaternion(GltfModel.Channel channel, float time, Quaternionf destination) {
        int left = leftKey(channel.times(), time);
        int right = Math.min(left + 1, channel.times().length - 1);
        int stride = channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE ? 12 : 4;
        int valueOffset = left * stride + (channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE ? 4 : 0);
        destination.set(channel.values()[valueOffset], channel.values()[valueOffset + 1],
                channel.values()[valueOffset + 2], channel.values()[valueOffset + 3]).normalize();
        if (right == left || channel.interpolation() == GltfModel.Interpolation.STEP) return;
        int rightOffset = right * stride + (channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE ? 4 : 0);
        float amount = fraction(channel.times(), left, right, time);
        if (channel.interpolation() == GltfModel.Interpolation.CUBICSPLINE) {
            float keyLength = channel.times()[right] - channel.times()[left];
            float[] values = channel.values();
            destination.set(
                    hermite(values[valueOffset], values[left * stride + 8], values[rightOffset],
                            values[right * stride], amount, keyLength),
                    hermite(values[valueOffset + 1], values[left * stride + 9], values[rightOffset + 1],
                            values[right * stride + 1], amount, keyLength),
                    hermite(values[valueOffset + 2], values[left * stride + 10], values[rightOffset + 2],
                            values[right * stride + 2], amount, keyLength),
                    hermite(values[valueOffset + 3], values[left * stride + 11], values[rightOffset + 3],
                            values[right * stride + 3], amount, keyLength)).normalize();
            return;
        }
        Quaternionf target = new Quaternionf(channel.values()[rightOffset], channel.values()[rightOffset + 1],
                channel.values()[rightOffset + 2], channel.values()[rightOffset + 3]).normalize();
        destination.slerp(target, amount).normalize();
    }

    private static float hermite(float start, float outTangent, float end, float inTangent,
                                 float amount, float keyLength) {
        float squared = amount * amount;
        float cubed = squared * amount;
        return (2.0F * cubed - 3.0F * squared + 1.0F) * start
                + (cubed - 2.0F * squared + amount) * keyLength * outTangent
                + (-2.0F * cubed + 3.0F * squared) * end
                + (cubed - squared) * keyLength * inTangent;
    }

    private static int leftKey(float[] times, float time) {
        int low = 0;
        int high = times.length - 1;
        while (low < high) {
            int middle = (low + high + 1) >>> 1;
            if (times[middle] <= time) low = middle;
            else high = middle - 1;
        }
        return low;
    }

    private static float fraction(float[] times, int left, int right, float time) {
        float length = times[right] - times[left];
        return length <= 0.0F ? 0.0F : Math.min(1.0F, Math.max(0.0F, (time - times[left]) / length));
    }

    private static float positiveModulo(float value, float modulus) {
        float result = value % modulus;
        return result < 0.0F ? result + modulus : result;
    }
}
