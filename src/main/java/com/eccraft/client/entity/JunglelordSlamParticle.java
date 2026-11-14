package com.eccraft.client.entity;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;

/** Supplied slam sprite, rising briefly and fading in its original colors. */
public final class JunglelordSlamParticle extends SingleQuadParticle {
    public JunglelordSlamParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z, sprites.first());
        lifetime = 16 + random.nextInt(5);
        quadSize = 0.3F;
        hasPhysics = false;
        gravity = 0;
        friction = 0.95F;
        xd = 0;
        yd = 0.04;
        zd = 0;
    }
    @Override public void tick() {
        super.tick();
        alpha = Math.max(0, 1.0F - (float) age / lifetime);
    }
    @Override protected Layer getLayer() { return Layer.TRANSLUCENT; }
}
