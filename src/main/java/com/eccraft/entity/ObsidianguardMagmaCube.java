package com.eccraft.entity;

import com.eccraft.obsidianguard.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.monster.cubemob.MagmaCube;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.*;
import java.util.UUID;

public final class ObsidianguardMagmaCube extends MagmaCube {
    public static final int FUSE_TICKS=120, PULSE_INTERVAL_TICKS=20;
    public static final double BLAST_RADIUS=24.0;
    public static final float BLAST_DAMAGE=20.0F;
    private UUID ownerUuid;
    private int age;
    private boolean exploded;
    public ObsidianguardMagmaCube(EntityType<? extends ObsidianguardMagmaCube> type,Level level){
        super(type,level);setSize(1,true);setPersistenceRequired();setCanPickUpLoot(false);
    }
    public static AttributeSupplier.Builder createAttributes(){return MagmaCube.createAttributes()
            .add(Attributes.MAX_HEALTH,10).add(Attributes.ARMOR,0).add(Attributes.MOVEMENT_SPEED,0).add(Attributes.JUMP_STRENGTH,0);}
    @Override public void setSize(int size,boolean updateHealth){
        super.setSize(1,false);
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(10);
        getAttribute(Attributes.ARMOR).setBaseValue(0);
        getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
        getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(0);
        xpReward=0;if(updateHealth)setHealth(10);
    }
    public void setOwnerUuid(UUID uuid){ownerUuid=uuid;}
    @Override protected void registerGoals(){}
    @Override public void jumpFromGround(){}
    @Override protected void jumpInLiquid(TagKey<Fluid> type){}
    @Override protected boolean isDealsDamage(){return false;}
    @Override protected boolean shouldDropLoot(ServerLevel level){return false;}
    @Override protected void dropAllDeathLoot(ServerLevel level,DamageSource source){}
    @Override public void tick(){
        super.tick();
        if(!(level() instanceof ServerLevel server)||!isAlive()||isRemoved()||exploded)return;
        Entity owner=ownerUuid==null?null:server.getEntityInAnyDimension(ownerUuid);
        if(!(owner instanceof ObsidianguardEntity)||!owner.isAlive()){discard();return;}
        ++age;
        if(age%PULSE_INTERVAL_TICKS==0){
            ObsidianguardParticles.burst(server,position().add(0,getBbHeight()/2.0,0),64,.35);
            playSound(SoundEvents.FIREWORK_ROCKET_BLAST,1.0F,1.0F);
        }
        if(age>=FUSE_TICKS){
            exploded=true;
            var source=ObsidianguardDamage.magic(server,this,owner);
            for(var player:server.getPlayers(p->ObsidianguardEntity.isCombatPlayer(p)&&distanceToSqr(p)<=BLAST_RADIUS*BLAST_RADIUS))
                player.hurtServer(server,source,BLAST_DAMAGE);
            hurtServer(server,source,BLAST_DAMAGE);
            discard();
        }
    }
    @Override protected void addAdditionalSaveData(ValueOutput out){super.addAdditionalSaveData(out);out.putInt("ObsidianFuseAge",age);out.putBoolean("ObsidianExploded",exploded);if(ownerUuid!=null)out.putString("ObsidianOwner",ownerUuid.toString());}
    @Override protected void readAdditionalSaveData(ValueInput in){
        super.readAdditionalSaveData(in);setSize(1,false);age=in.getIntOr("ObsidianFuseAge",0);exploded=in.getBooleanOr("ObsidianExploded",false);
        String owner=in.getStringOr("ObsidianOwner","");if(!owner.isBlank())ownerUuid=UUID.fromString(owner);
    }
}
