package com.eccraft.darkdragon;

import com.eccraft.client.gltf.GltfModel;
import com.eccraft.client.gltf.GltfLoader;
import com.eccraft.client.gltf.GltfAnimationPlayer;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

/** Pure CPU GLB sampling, usable on both the dedicated server and client. */
public final class DarkDragonPose {
    public static final float MODEL_SCALE = 0.125F;
    private static GltfModel body;
    private static boolean attempted;
    public record Selection(String clip, float seconds, boolean loop) {}
    private DarkDragonPose() {}
    public static Selection select(DarkDragonAction action, boolean airborne, float seconds) {
        String idle = airborne ? "Anim-1_fly_stand" : "Anim-1_stand";
        String clip = switch(action) {
            case GROUND_ATTACK -> "Anim-1_attack_01";
            case AIR_ATTACK -> "Anim-1_attack_04";
            case SKILL_1 -> "Anim-1_attack_05";
            case SKILL_2 -> "Anim-1_attack_03";
            case SKILL_3 -> "Anim-1_attack_06";
            case TAKEOFF, LANDING, ENTERING, ESCAPING -> "Anim-1_climb";
            case DEATH -> "Anim-1_death";
            case FALLING -> "Anim-1_fly_stand";
            default -> idle;
        };
        boolean loop = action == DarkDragonAction.IDLE || action == DarkDragonAction.SKILL_4
                || action == DarkDragonAction.TAKEOFF || action == DarkDragonAction.LANDING || action == DarkDragonAction.FALLING
                || action == DarkDragonAction.ENTERING || action == DarkDragonAction.ESCAPING;
        return new Selection(clip, seconds, loop);
    }
    public static Selection resolve(GltfModel model, DarkDragonAction action, boolean air, float seconds) {
        var selection = select(action, air, seconds);
        var clip = model.animations.get(selection.clip());
        float duration = clip == null ? 0 : clip.duration();
        if (!selection.loop() && action != DarkDragonAction.DEATH && seconds >= duration) {
            return new Selection(air ? "Anim-1_fly_stand" : "Anim-1_stand", seconds - duration, true);
        }
        return selection;
    }
    public static Vec3 mouth(DarkDragonAction action, boolean air, float seconds, float yaw, float scale) {
        if (!attempted) {
            attempted = true;
            try (var in = DarkDragonPose.class.getResourceAsStream("/assets/eccraft/models/entity/darkdragon_01.glb")) {
                if (in != null) body = GltfLoader.load(in);
            } catch (Exception e) { org.slf4j.LoggerFactory.getLogger(DarkDragonPose.class).warn("Unable to sample dragon mouth; using approximate offset", e); }
        }
        Vector3f p = new Vector3f(0, 70, 110);
        if (body != null) {
            var selected = resolve(body, action, air, seconds);
            var globals = GltfAnimationPlayer.globalMatrices(body, GltfAnimationPlayer.sampleLocalMatrices(body, selected.clip(), selected.seconds(), selected.loop()));
            for (int i = 0; i < body.nodes.size(); i++) if (body.nodes.get(i).name.equals("Bip001_Head")) {
                globals[i].transformPosition(p.set(12, 0, 0)); break;
            }
        }
        p.mul(MODEL_SCALE * scale).rotateY((float) Math.toRadians(-yaw));
        return new Vec3(p.x, p.y, p.z);
    }
}
