package com.eccraft.staff.client;

import com.eccraft.staff.StaffParticles;
import com.eccraft.staff.StaffSkillEffectPayload;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

public final class StaffSkillEffect {
    private static final int MAX_ACTIVE_WAVES = 32;
    private static final ArrayList<Wave> WAVES = new ArrayList<>();
    private static final class Wave {
        final ClientLevel level;
        final StaffSkillEffectPayload payload;
        Wave(ClientLevel level, StaffSkillEffectPayload payload) { this.level = level; this.payload = payload; }
    }
    public static void add(ClientLevel level, StaffSkillEffectPayload payload) {
        if (level == null || !Double.isFinite(payload.x()) || !Double.isFinite(payload.y()) || !Double.isFinite(payload.z())
                || !Float.isFinite(payload.radius()) || payload.radius() <= 0 || payload.radius() > 128) return;
        if (WAVES.size() >= MAX_ACTIVE_WAVES) WAVES.removeFirst();
        WAVES.add(new Wave(level, payload));
    }
    public static void clear() { WAVES.clear(); }
    public static void tick(Minecraft client) {
        if (client.level == null) { clear(); return; }
        if (client.isPaused()) return;
        var iterator = WAVES.iterator();
        while (iterator.hasNext()) {
            var wave = iterator.next();
            if (wave.level != client.level) { iterator.remove(); continue; }
            var p = wave.payload;
            var particle = StaffParticles.forRoute(p.route());
            // A single radial burst, with varied speeds, instead of successive expanding shells.
            int points=144;
            for(int i=0;i<points;i++){
                double y=1-2*(i+.5)/points;
                double horizontal=Math.sqrt(1-y*y);
                double angle=i*Math.PI*(3-Math.sqrt(5));
                double x=horizontal*Math.cos(angle),z=horizontal*Math.sin(angle);
                double speed=p.radius()*.12*(.65+wave.level.getRandom().nextDouble()*.7);
                wave.level.addParticle(particle,p.x()+x*.15,p.y()+y*.15,p.z()+z*.15,
                        x*speed,y*speed,z*speed);
            }
            iterator.remove();
        }
    }
    private StaffSkillEffect() {}
}
