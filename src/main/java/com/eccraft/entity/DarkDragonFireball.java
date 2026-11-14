package com.eccraft.entity;

import com.eccraft.Eccraft;
import com.eccraft.darkdragon.DarkDragonContent;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.*;

/** Sweeps the full 2x2x2 volume rather than only tracing the projectile's center. */
public final class DarkDragonFireball extends Projectile {
    private int age;
    public DarkDragonFireball(EntityType<? extends DarkDragonFireball> type,Level level){super(type,level);setNoGravity(true);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){}
    @Override public boolean hurtServer(ServerLevel level,DamageSource source,float amount){return false;}
    @Override protected boolean canHitEntity(Entity e){return !ownedBy(e)&&e.canBeHitByProjectile();}
    @Override public void tick(){
        super.tick();if(isRemoved())return;
        Vec3 motion=getDeltaMovement(),center=position().add(0,1,0),end=center.add(motion);
        if(level() instanceof ServerLevel server){
            double nearest=Double.POSITIVE_INFINITY;Vec3 contact=null;Entity victim=null;
            var sweep=getBoundingBox().expandTowards(motion).inflate(1e-6);
            for(var shape:server.getBlockCollisions(this,sweep))for(var box:shape.toAabbs()){
                Vec3 point=impact(box.inflate(1),center,end);if(point!=null){double d=center.distanceToSqr(point);if(d<nearest){nearest=d;contact=surface(box,point);}}
            }
            for(Entity entity:server.getEntities(this,sweep,e->canHitEntity(e))){
                var box=entity.getBoundingBox();Vec3 point=impact(box.inflate(1),center,end);if(point!=null){double d=center.distanceToSqr(point);if(d<nearest){nearest=d;contact=surface(box,point);victim=entity;}}
            }
            if(contact!=null){
                if(victim!=null){var magic=server.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,Eccraft.id("magic")));victim.hurtServer(server,new DamageSource(magic,this,getOwner()),DarkDragonEntity.FIREBALL_DAMAGE);}
                makeFire(server,contact);discard();return;
            }
        }
        setPos(position().add(motion));updateRotation();if(++age>=DarkDragonEntity.FIREBALL_LIFETIME&&!level().isClientSide())discard();
    }
    private static Vec3 impact(AABB box,Vec3 start,Vec3 end){return com.eccraft.darkdragon.DarkDragonMath.impact(box,start,end);}
    private static Vec3 surface(AABB box,Vec3 p){return new Vec3(Math.clamp(p.x,box.minX,box.maxX),Math.clamp(p.y,box.minY,box.maxY),Math.clamp(p.z,box.minZ,box.maxZ));}
    private void makeFire(ServerLevel level,Vec3 hit){
        if(!(getOwner() instanceof DarkDragonEntity boss)||!boss.isAlive())return;
        for(int[] offset:new int[][]{{0,0},{1,0},{-1,0},{0,1},{0,-1},{2,0},{-2,0},{0,2},{0,-2},{1,1},{1,-1},{-1,1},{-1,-1}}){var flame=new DarkDragonFireEntity(DarkDragonContent.FIRE,level);flame.setOwnerUuid(boss.getUUID());flame.setPos(hit.add(offset[0],.5,offset[1]));if(level.addFreshEntity(flame))boss.track(flame);}
    }
    @Override protected void addAdditionalSaveData(ValueOutput out){super.addAdditionalSaveData(out);out.putInt("DragonFireballAge",age);}
    @Override protected void readAdditionalSaveData(ValueInput in){super.readAdditionalSaveData(in);age=in.getIntOr("DragonFireballAge",0);}
}
