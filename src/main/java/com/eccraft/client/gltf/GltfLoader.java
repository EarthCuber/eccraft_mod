package com.eccraft.client.gltf;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/** GLB 2.0 reader for triangle meshes, skins and TRS animation channels. */
public final class GltfLoader {
    private static final int GLB_MAGIC = 0x46546C67;
    private static final int JSON_CHUNK = 0x4E4F534A;
    private static final int BIN_CHUNK = 0x004E4942;

    private GltfLoader() {}

    public static GltfModel load(InputStream input) throws IOException {
        ByteBuffer file = ByteBuffer.wrap(input.readAllBytes()).order(ByteOrder.LITTLE_ENDIAN);
        if (file.remaining() < 12 || file.getInt() != GLB_MAGIC) {
            throw new IOException("Not a binary glTF file");
        }
        int version = file.getInt();
        int declaredLength = file.getInt();
        if (version != 2 || declaredLength > file.capacity()) {
            throw new IOException("Unsupported or truncated GLB: version=" + version);
        }

        JsonObject json = null;
        ByteBuffer binary = null;
        while (file.position() + 8 <= declaredLength) {
            int length = file.getInt();
            int type = file.getInt();
            if (length < 0 || file.position() + length > declaredLength) {
                throw new IOException("Invalid GLB chunk length");
            }
            byte[] bytes = new byte[length];
            file.get(bytes);
            if (type == JSON_CHUNK) {
                json = JsonParser.parseString(new String(bytes, StandardCharsets.UTF_8).trim()).getAsJsonObject();
            } else if (type == BIN_CHUNK) {
                binary = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
            }
        }
        if (json == null || binary == null) {
            throw new IOException("GLB must contain JSON and BIN chunks");
        }
        return parse(json, binary);
    }

    private static GltfModel parse(JsonObject root, ByteBuffer binary) throws IOException {
        JsonArray viewJson = array(root, "bufferViews");
        JsonArray accessorJson = array(root, "accessors");
        View[] views = new View[viewJson.size()];
        for (int i = 0; i < views.length; i++) {
            JsonObject value = viewJson.get(i).getAsJsonObject();
            views[i] = new View(integer(value, "byteOffset", 0), integer(value, "byteLength", 0),
                    integer(value, "byteStride", 0));
        }
        Accessor[] accessors = new Accessor[accessorJson.size()];
        for (int i = 0; i < accessors.length; i++) {
            JsonObject value = accessorJson.get(i).getAsJsonObject();
            accessors[i] = new Accessor(integer(value, "bufferView", -1), integer(value, "byteOffset", 0),
                    integer(value, "componentType", 0), integer(value, "count", 0),
                    value.get("type").getAsString(), value.has("normalized") && value.get("normalized").getAsBoolean());
        }

        GltfModel model = new GltfModel();
        JsonArray nodes = array(root, "nodes");
        for (int i = 0; i < nodes.size(); i++) {
            JsonObject node = nodes.get(i).getAsJsonObject();
            Transform transform = transform(node);
            model.nodes.add(new GltfModel.Node(node.has("name") ? node.get("name").getAsString() : "node_" + i,
                    transform.translation, transform.rotation, transform.scale));
        }
        for (int i = 0; i < nodes.size(); i++) {
            JsonObject node = nodes.get(i).getAsJsonObject();
            GltfModel.Node target = model.nodes.get(i);
            target.skin = integer(node, "skin", -1);
            if (node.has("children")) {
                for (JsonElement child : node.getAsJsonArray("children")) {
                    int childIndex = child.getAsInt();
                    target.children.add(childIndex);
                    model.nodes.get(childIndex).parent = i;
                }
            }
        }

        if (root.has("skins")) {
            for (JsonElement element : root.getAsJsonArray("skins")) {
                JsonObject skin = element.getAsJsonObject();
                int[] joints = ints(skin.getAsJsonArray("joints"));
                Matrix4f[] inverse = new Matrix4f[joints.length];
                if (skin.has("inverseBindMatrices")) {
                    float[] values = readFloats(accessors[skin.get("inverseBindMatrices").getAsInt()], views, binary);
                    for (int i = 0; i < inverse.length; i++) {
                        inverse[i] = new Matrix4f().set(values, i * 16);
                    }
                } else {
                    Arrays.setAll(inverse, ignored -> new Matrix4f());
                }
                model.skins.add(new GltfModel.Skin(joints, inverse));
            }
        }

        List<List<Integer>> meshMap = new ArrayList<>();
        if (root.has("meshes")) {
            for (JsonElement meshElement : root.getAsJsonArray("meshes")) {
                List<Integer> primitiveMap = new ArrayList<>();
                JsonArray primitives = meshElement.getAsJsonObject().getAsJsonArray("primitives");
                for (JsonElement primitiveElement : primitives) {
                    JsonObject primitive = primitiveElement.getAsJsonObject();
                    int mode = integer(primitive, "mode", 4);
                    if (mode != 4) throw new IOException("Only TRIANGLES primitives are supported; mode=" + mode);
                    JsonObject attributes = primitive.getAsJsonObject("attributes");
                    float[] positions = floats(attributes, "POSITION", accessors, views, binary, true);
                    float[] normals = floats(attributes, "NORMAL", accessors, views, binary, false);
                    float[] uvs = floats(attributes, "TEXCOORD_0", accessors, views, binary, false);
                    int[] joints = integerValues(attributes, "JOINTS_0", accessors, views, binary);
                    float[] weights = floats(attributes, "WEIGHTS_0", accessors, views, binary, false);
                    int[] indices;
                    if (primitive.has("indices")) {
                        indices = readInts(accessors[primitive.get("indices").getAsInt()], views, binary);
                    } else {
                        indices = new int[positions.length / 3];
                        Arrays.setAll(indices, value -> value);
                    }
                    primitiveMap.add(model.meshes.size());
                    model.meshes.add(new GltfModel.Mesh(positions, normals, uvs, indices, joints, weights));
                }
                meshMap.add(primitiveMap);
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            JsonObject nodeJson = nodes.get(i).getAsJsonObject();
            if (!nodeJson.has("mesh")) continue;
            GltfModel.Node node = model.nodes.get(i);
            for (int meshIndex : meshMap.get(nodeJson.get("mesh").getAsInt())) {
                node.meshes.add(meshIndex);
                GltfModel.Mesh mesh = model.meshes.get(meshIndex);
                mesh.node = i;
                mesh.skin = node.skin;
            }
        }

        JsonArray sceneArray = array(root, "scenes");
        int sceneIndex = integer(root, "scene", 0);
        if (!sceneArray.isEmpty()) {
            JsonObject scene = sceneArray.get(Math.min(sceneIndex, sceneArray.size() - 1)).getAsJsonObject();
            if (scene.has("nodes")) for (JsonElement element : scene.getAsJsonArray("nodes")) model.roots.add(element.getAsInt());
        }
        if (model.roots.isEmpty()) {
            for (int i = 0; i < model.nodes.size(); i++) if (model.nodes.get(i).parent < 0) model.roots.add(i);
        }

        if (root.has("animations")) {
            int unnamed = 0;
            for (JsonElement animationElement : root.getAsJsonArray("animations")) {
                JsonObject animationJson = animationElement.getAsJsonObject();
                String name = animationJson.has("name") ? animationJson.get("name").getAsString() : "animation_" + unnamed++;
                JsonArray samplers = animationJson.getAsJsonArray("samplers");
                List<GltfModel.Channel> channels = new ArrayList<>();
                float duration = 0.0F;
                for (JsonElement channelElement : animationJson.getAsJsonArray("channels")) {
                    JsonObject channelJson = channelElement.getAsJsonObject();
                    JsonObject sampler = samplers.get(channelJson.get("sampler").getAsInt()).getAsJsonObject();
                    JsonObject target = channelJson.getAsJsonObject("target");
                    float[] times = readFloats(accessors[sampler.get("input").getAsInt()], views, binary);
                    float[] values = readFloats(accessors[sampler.get("output").getAsInt()], views, binary);
                    if (times.length > 0) duration = Math.max(duration, times[times.length - 1]);
                    GltfModel.Path path = GltfModel.Path.valueOf(target.get("path").getAsString().toUpperCase());
                    GltfModel.Interpolation interpolation = GltfModel.Interpolation.valueOf(
                            sampler.has("interpolation") ? sampler.get("interpolation").getAsString() : "LINEAR");
                    channels.add(new GltfModel.Channel(target.get("node").getAsInt(), path, times, values, interpolation));
                }
                model.animations.put(name, new GltfModel.Animation(name, duration, List.copyOf(channels)));
            }
        }
        return model;
    }

    private static float[] floats(JsonObject attributes, String key, Accessor[] accessors, View[] views,
                                  ByteBuffer binary, boolean required) throws IOException {
        if (!attributes.has(key)) {
            if (required) throw new IOException("Missing required attribute " + key);
            return null;
        }
        return readFloats(accessors[attributes.get(key).getAsInt()], views, binary);
    }

    private static int[] integerValues(JsonObject attributes, String key, Accessor[] accessors, View[] views,
                                       ByteBuffer binary) throws IOException {
        return attributes.has(key) ? readInts(accessors[attributes.get(key).getAsInt()], views, binary) : null;
    }

    private static float[] readFloats(Accessor accessor, View[] views, ByteBuffer source) throws IOException {
        View view = checkedView(accessor, views);
        int components = components(accessor.type);
        int size = componentSize(accessor.componentType);
        int stride = view.stride == 0 ? components * size : view.stride;
        float[] result = new float[accessor.count * components];
        for (int element = 0; element < accessor.count; element++) {
            int base = view.offset + accessor.offset + element * stride;
            for (int component = 0; component < components; component++) {
                result[element * components + component] = readFloat(source, base + component * size,
                        accessor.componentType, accessor.normalized);
            }
        }
        return result;
    }

    private static int[] readInts(Accessor accessor, View[] views, ByteBuffer source) throws IOException {
        View view = checkedView(accessor, views);
        int components = components(accessor.type);
        int size = componentSize(accessor.componentType);
        int stride = view.stride == 0 ? components * size : view.stride;
        int[] result = new int[accessor.count * components];
        for (int element = 0; element < accessor.count; element++) {
            int base = view.offset + accessor.offset + element * stride;
            for (int component = 0; component < components; component++) {
                result[element * components + component] = readInt(source, base + component * size, accessor.componentType);
            }
        }
        return result;
    }

    private static View checkedView(Accessor accessor, View[] views) throws IOException {
        if (accessor.view < 0 || accessor.view >= views.length) throw new IOException("Sparse or missing accessors are unsupported");
        return views[accessor.view];
    }

    private static float readFloat(ByteBuffer source, int offset, int type, boolean normalized) throws IOException {
        return switch (type) {
            case 5120 -> normalized ? Math.max(-1.0F, source.get(offset) / 127.0F) : source.get(offset);
            case 5121 -> normalized ? Byte.toUnsignedInt(source.get(offset)) / 255.0F : Byte.toUnsignedInt(source.get(offset));
            case 5122 -> normalized ? Math.max(-1.0F, source.getShort(offset) / 32767.0F) : source.getShort(offset);
            case 5123 -> normalized ? Short.toUnsignedInt(source.getShort(offset)) / 65535.0F : Short.toUnsignedInt(source.getShort(offset));
            case 5125 -> Integer.toUnsignedLong(source.getInt(offset));
            case 5126 -> source.getFloat(offset);
            default -> throw new IOException("Unsupported accessor component type " + type);
        };
    }

    private static int readInt(ByteBuffer source, int offset, int type) throws IOException {
        return switch (type) {
            case 5120 -> source.get(offset);
            case 5121 -> Byte.toUnsignedInt(source.get(offset));
            case 5122 -> source.getShort(offset);
            case 5123 -> Short.toUnsignedInt(source.getShort(offset));
            case 5125 -> source.getInt(offset);
            default -> throw new IOException("Accessor is not integer typed: " + type);
        };
    }

    private static int componentSize(int type) throws IOException {
        return switch (type) {
            case 5120, 5121 -> 1;
            case 5122, 5123 -> 2;
            case 5125, 5126 -> 4;
            default -> throw new IOException("Unsupported component type " + type);
        };
    }

    private static int components(String type) throws IOException {
        return switch (type) {
            case "SCALAR" -> 1;
            case "VEC2" -> 2;
            case "VEC3" -> 3;
            case "VEC4" -> 4;
            case "MAT4" -> 16;
            default -> throw new IOException("Unsupported accessor type " + type);
        };
    }

    private static Transform transform(JsonObject node) {
        if (node.has("matrix")) {
            float[] values = floats(node.getAsJsonArray("matrix"));
            Matrix4f matrix = new Matrix4f().set(values);
            Vector3f translation = matrix.getTranslation(new Vector3f());
            Quaternionf rotation = matrix.getUnnormalizedRotation(new Quaternionf()).normalize();
            Vector3f scale = matrix.getScale(new Vector3f());
            return new Transform(translation, rotation, scale);
        }
        return new Transform(vector(node, "translation", new Vector3f()),
                quaternion(node, "rotation", new Quaternionf()), vector(node, "scale", new Vector3f(1.0F)));
    }

    private static Vector3f vector(JsonObject object, String key, Vector3f fallback) {
        if (!object.has(key)) return fallback;
        JsonArray value = object.getAsJsonArray(key);
        return new Vector3f(value.get(0).getAsFloat(), value.get(1).getAsFloat(), value.get(2).getAsFloat());
    }

    private static Quaternionf quaternion(JsonObject object, String key, Quaternionf fallback) {
        if (!object.has(key)) return fallback;
        JsonArray value = object.getAsJsonArray(key);
        return new Quaternionf(value.get(0).getAsFloat(), value.get(1).getAsFloat(),
                value.get(2).getAsFloat(), value.get(3).getAsFloat()).normalize();
    }

    private static float[] floats(JsonArray array) {
        float[] result = new float[array.size()];
        for (int i = 0; i < result.length; i++) result[i] = array.get(i).getAsFloat();
        return result;
    }

    private static int[] ints(JsonArray array) {
        int[] result = new int[array.size()];
        for (int i = 0; i < result.length; i++) result[i] = array.get(i).getAsInt();
        return result;
    }

    private static JsonArray array(JsonObject object, String key) {
        return object.has(key) ? object.getAsJsonArray(key) : new JsonArray();
    }

    private static int integer(JsonObject object, String key, int fallback) {
        return object.has(key) ? object.get(key).getAsInt() : fallback;
    }

    private record View(int offset, int length, int stride) {}
    private record Accessor(int view, int offset, int componentType, int count, String type, boolean normalized) {}
    private record Transform(Vector3f translation, Quaternionf rotation, Vector3f scale) {}
}
