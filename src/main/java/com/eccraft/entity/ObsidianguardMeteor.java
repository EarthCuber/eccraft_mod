package com.eccraft.entity;

import com.eccraft.obsidianguard.*;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.*;

/** Full-volume collision, with no owner or team exclusions. */
public final class ObsidianguardMeteor extends Projectile {
    private int age;
    public ObsidianguardMeteor(EntityType<? extends ObsidianguardMeteor> type,Level level){super(type,level);setNoGravity(true);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder){}
    @Override public boolean hurtServer(ServerLevel level,DamageSource source,float amount){return false;}
    @Override protected boolean canHitEntity(Entity entity){return entity.canBeHitByProjectile();}
    @Override public void tick(){
        super.tick();if(isRemoved())return;
        Vec3 motion=getDeltaMovement(),center=position().add(0,1,0),end=center.add(motion);
        if(level() instanceof ServerLevel server){
            // Unloaded children are also removed when loaded again after their Boss is gone.
            if(!(getOwner() instanceof ObsidianguardEntity boss)||!boss.isAlive()){discard();return;}
            double nearest=Double.POSITIVE_INFINITY;Vec3 contact=null;Entity victim=null;
            var sweep=getBoundingBox().expandTowards(motion).inflate(1e-6);
            for(var shape:server.getBlockCollisions(this,sweep))for(var box:shape.toAabbs()){
                Vec3 point=com.eccraft.darkdragon.DarkDragonMath.impact(box.inflate(1),center,end);
                if(point!=null){double distance=center.distanceToSqr(point);if(distance<nearest){nearest=distance;contact=surface(box,point);}}
            }
            for(Entity entity:server.getEntities(this,sweep,this::canHitEntity)){
                var box=entity.getBoundingBox();Vec3 point=com.eccraft.darkdragon.DarkDragonMath.impact(box.inflate(1),center,end);
                if(point!=null){double distance=center.distanceToSqr(point);if(distance<nearest){nearest=distance;contact=surface(box,point);victim=entity;}}
            }
            if(contact!=null){
                if(victim!=null)victim.hurtServer(server,ObsidianguardDamage.magic(server,this,boss),ObsidianguardEntity.METEOR_DAMAGE);
                // Killing the owner cancels its summons immediately.
                if(boss.isAlive()){
                    var cube=new ObsidianguardMagmaCube(ObsidianguardContent.MAGMA_CUBE,server);
                    cube.setOwnerUuid(boss.getUUID());cube.setPos(contact.add(0,1,0));
                    if(server.addFreshEntity(cube))boss.track(cube);
                }
                discard();return;
            }
        }
        setPos(position().add(motion));updateRotation();
        if(++age>=ObsidianguardEntity.METEOR_LIFETIME&&!level().isClientSide())discard();
    }
    private static Vec3 surface(AABB box,Vec3 point){return new Vec3(Math.clamp(point.x,box.minX,box.maxX),Math.clamp(point.y,box.minY,box.maxY),Math.clamp(point.z,box.minZ,box.maxZ));}
    @Override protected void addAdditionalSaveData(ValueOutput out){super.addAdditionalSaveData(out);out.putInt("ObsidianMeteorAge",age);}
    @Override protected void readAdditionalSaveData(ValueInput in){super.readAdditionalSaveData(in);age=in.getIntOr("ObsidianMeteorAge",0);}
}
