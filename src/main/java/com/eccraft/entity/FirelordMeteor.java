package com.eccraft.entity;

import com.eccraft.firelord.FirelordMath;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.*;
import org.joml.Quaternionf;
import java.util.*;

public final class FirelordMeteor extends Monster {
    public static final double MAX_HEALTH=50,ARMOR=30,MOVEMENT_SPEED=0.15,GRAVITY=0.24,RANGE=32;
    public static final float EXPLOSION_DAMAGE=100;
    private static final EntityDataAccessor<org.joml.Quaternionfc> ROLL=SynchedEntityData.defineId(FirelordMeteor.class,EntityDataSerializers.QUATERNION);
    private UUID ownerUuid;
    private boolean exploded;
    private int arrivalStage=2; // 0: approach, 1: pause, 2: pursue. Legacy meteors remain in pursuit.
    private Vec3 landingPoint=Vec3.ZERO;
    private long pauseUntil;
    public static final int LANDING_PAUSE_TICKS=60;
    private Quaternionf previousRoll=new Quaternionf();
    public FirelordMeteor(EntityType<? extends FirelordMeteor> type,Level level){super(type,level);setPersistenceRequired();xpReward=0;}
    public static AttributeSupplier.Builder createAttributes(){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,MAX_HEALTH).add(Attributes.ARMOR,ARMOR).add(Attributes.MOVEMENT_SPEED,MOVEMENT_SPEED).add(Attributes.GRAVITY,GRAVITY).add(Attributes.FOLLOW_RANGE,RANGE).add(com.eccraft.staff.StaffAttributes.MAGIC_RESISTANCE,-1);}
    @Override protected void registerGoals(){}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ROLL,new Quaternionf());}
    public void setOwnerUuid(UUID id){ownerUuid=id;}
    public void beginLanding(Vec3 bossFeet){
        landingPoint=com.eccraft.firelord.MeteorApproach.landing(bossFeet,random.nextDouble(),random.nextDouble());
        arrivalStage=0;setNoGravity(true);getNavigation().stop();setDeltaMovement(Vec3.ZERO);
    }
    @Override public void travel(Vec3 input){
        if(arrivalStage==2){super.travel(input);return;}
        setNoGravity(true);setDeltaMovement(Vec3.ZERO);
        if(!(level() instanceof ServerLevel level)||!isAlive())return;
        getNavigation().stop();
        if(arrivalStage==1)return;
        Vec3 step=com.eccraft.firelord.MeteorApproach.step(position(),landingPoint,MOVEMENT_SPEED);
        move(MoverType.SELF,step);
        // A solid obstacle is a valid early landing; never phase through the arena.
        if(position().distanceToSqr(landingPoint)<1e-6||horizontalCollision||verticalCollision){arrivalStage=1;pauseUntil=level.getGameTime()+LANDING_PAUSE_TICKS;}
    }
    public Quaternionf roll(float partial){return new Quaternionf(previousRoll).slerp(entityData.get(ROLL),Math.clamp(partial,0,1));}
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);if(!isAlive())return;
        if(arrivalStage==1&&level.getGameTime()>=pauseUntil){arrivalStage=2;setNoGravity(false);}
        if(arrivalStage!=2){setTarget(null);getNavigation().stop();return;}
        var target=level.getPlayers(p->FirelordEntity.valid(p)&&distanceToSqr(p)<=RANGE*RANGE).stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
        setTarget(target);
        if(target==null){getNavigation().stop();setDeltaMovement(0,getDeltaMovement().y,0);return;}
        // Always aim at the current position; no path waypoint cache or velocity prediction.
        getNavigation().stop();setSpeed(0);setXxa(0);setZza(0);
        var flat=com.eccraft.firelord.MeteorPursuit.velocity(position(),target.position(),getAttributeValue(Attributes.MOVEMENT_SPEED));
        setDeltaMovement(flat.x,getDeltaMovement().y,flat.z);
    }
    @Override public void tick(){
        previousRoll=new Quaternionf(entityData.get(ROLL));Vec3 before=position();
        if(level() instanceof ServerLevel level&&ownerUuid!=null){var owner=level.getEntityInAnyDimension(ownerUuid);if(!(owner instanceof FirelordEntity)||!owner.isAlive()){discard();return;}}
        super.tick();if(!(level() instanceof ServerLevel level)||!isAlive()||isRemoved()||exploded)return;
        var delta=position().subtract(before);entityData.set(ROLL,FirelordMath.rolled(entityData.get(ROLL),delta));
        Vec3 center=before.add(0,getBbHeight()/2.0,0),end=position().add(0,getBbHeight()/2.0,0);
        for(var player:level.getPlayers(FirelordEntity::valid)){
            var expanded=player.getBoundingBox().inflate(getBbWidth()/2.0,getBbHeight()/2.0,getBbWidth()/2.0);
            if(getBoundingBox().intersects(player.getBoundingBox())||com.eccraft.darkdragon.DarkDragonMath.impact(expanded,center,end)!=null){explode(level);break;}
        }
    }
    private void explode(ServerLevel level){exploded=true;for(var p:level.getPlayers(p->FirelordEntity.valid(p)&&distanceToSqr(p)<=RANGE*RANGE))p.hurtServer(level,damageSources().fellOutOfWorld(),EXPLOSION_DAMAGE);discard();}
    @Override protected void dropAllDeathLoot(ServerLevel level,DamageSource source){}
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);o.putInt("FireMeteorArrivalStage",arrivalStage);o.putDouble("FireMeteorLandingX",landingPoint.x);o.putDouble("FireMeteorLandingY",landingPoint.y);o.putDouble("FireMeteorLandingZ",landingPoint.z);o.putLong("FireMeteorPauseUntil",pauseUntil);if(ownerUuid!=null)o.putString("FireMeteorOwner",ownerUuid.toString());o.putBoolean("FireMeteorExploded",exploded);var q=entityData.get(ROLL);o.putFloat("FireMeteorRollX",q.x());o.putFloat("FireMeteorRollY",q.y());o.putFloat("FireMeteorRollZ",q.z());o.putFloat("FireMeteorRollW",q.w());}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);arrivalStage=Math.clamp(i.getIntOr("FireMeteorArrivalStage",2),0,2);landingPoint=new Vec3(i.getDoubleOr("FireMeteorLandingX",getX()),i.getDoubleOr("FireMeteorLandingY",getY()),i.getDoubleOr("FireMeteorLandingZ",getZ()));pauseUntil=i.getLongOr("FireMeteorPauseUntil",0);setNoGravity(arrivalStage!=2);String owner=i.getStringOr("FireMeteorOwner","");if(!owner.isBlank())ownerUuid=UUID.fromString(owner);exploded=i.getBooleanOr("FireMeteorExploded",false);var q=new Quaternionf(i.getFloatOr("FireMeteorRollX",0),i.getFloatOr("FireMeteorRollY",0),i.getFloatOr("FireMeteorRollZ",0),i.getFloatOr("FireMeteorRollW",1));if(!q.isFinite()||q.lengthSquared()<1e-6F)q.identity();entityData.set(ROLL,q.normalize());previousRoll=new Quaternionf(q);}
}
