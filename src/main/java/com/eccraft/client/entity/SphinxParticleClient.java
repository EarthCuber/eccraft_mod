package com.eccraft.client.entity;

import com.eccraft.sphinx.SphinxParticles;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.FallingDustParticle;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Blocks;

/** Use the actual vanilla sand falling-dust particle, then apply its spell launch velocity. */
public final class SphinxParticleClient {
    public static void register(){
        ParticleProviderRegistry.getInstance().register(SphinxParticles.SAND,sprites->{
            var vanilla=new FallingDustParticle.Provider(sprites);
            return (type,level,x,y,z,vx,vy,vz,random)->{
                var particle=vanilla.createParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST,Blocks.SAND.defaultBlockState()),level,x,y,z,vx,vy,vz,random);
                if(particle!=null){particle.setParticleSpeed(vx,vy,vz);particle.setLifetime(SphinxParticles.LIFETIME_TICKS);}
                return particle;
            };
        });
    }
    private SphinxParticleClient(){}
}
