package com.eccraft.staff.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;

/** A short, slowly fading trail using the supplied cross sprite at its original colors. */
public final class StaffHealParticle extends SingleQuadParticle {
    public StaffHealParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz, SpriteSet sprites) {
        super(level, x, y, z, sprites.first());
        this.lifetime = 12 + random.nextInt(9);
        this.quadSize = 0.09F;
        this.hasPhysics = false;
        this.gravity = 0;
        this.friction = 0.9F;
        this.xd = (random.nextDouble() - 0.5) * 0.015;
        this.yd = 0.01;
        this.zd = (random.nextDouble() - 0.5) * 0.015;
        if(dx!=0||dy!=0||dz!=0){this.xd=dx;this.yd=dy;this.zd=dz;}
    }
    @Override public void tick() {
        super.tick();
        this.alpha = Math.max(0, 1F - (float)age / lifetime);
    }
    @Override protected Layer getLayer() { return Layer.TRANSLUCENT; }
}
