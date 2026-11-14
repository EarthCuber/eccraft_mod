package com.eccraft.entity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.*;
public final class NightgodProjectile extends Projectile {
    private int age;
    public NightgodProjectile(EntityType<? extends NightgodProjectile> type,Level level){super(type,level);setNoGravity(true);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){}
    @Override public boolean hurtServer(ServerLevel level,DamageSource source,float amount){return false;}
    @Override protected boolean canHitEntity(Entity e){return !ownedBy(e)&&e.canBeHitByProjectile();}
    @Override public void tick(){
        super.tick();if(isRemoved())return;
        if(level() instanceof ServerLevel level){
            if(!(getOwner() instanceof NightgodEntity boss)||!boss.isAlive()){discard();return;}
            HitResult hit=ProjectileUtil.getHitResultOnMoveVector(this,this::canHitEntity);
            if(hit.getType()!=HitResult.Type.MISS){
                setPos(hit.getLocation());var source=com.eccraft.nightgod.NightgodDamage.magic(level,this,getOwner());
                Entity direct=hit instanceof EntityHitResult h?h.getEntity():null;
                if(direct!=null)direct.hurtServer(level,source,NightgodEntity.PROJECTILE_DAMAGE);
                // Contact targets receive the blast even when their feet lie outside its radius.
                for(var e:level.getEntities(this,new AABB(position(),position()).inflate(2),e->!ownedBy(e)&&e.isAlive())){
                    if(e==direct||e.position().distanceToSqr(position())<=4)e.hurtServer(level,source,NightgodEntity.EXPLOSION_DAMAGE);
                }
                level.sendParticles(ParticleTypes.EXPLOSION,getX(),getY(),getZ(),1,0,0,0,0);
                level.playSound(null,getX(),getY(),getZ(),com.eccraft.sound.NightgodSounds.PROJECTILE_EXPLODE,SoundSource.HOSTILE,1,1);
                discard();return;
            }
        }
        setPos(position().add(getDeltaMovement()));updateRotation();if(++age>=NightgodEntity.PROJECTILE_LIFETIME&&!level().isClientSide())discard();
    }
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);o.putInt("NightShotAge",age);}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);age=i.getIntOr("NightShotAge",0);}
}
