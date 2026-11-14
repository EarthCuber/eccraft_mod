package com.eccraft.entity;

import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.Vec3;
import java.util.*;

public final class FireElementEntity extends Monster {
    public static final double RANGE=32,SPAWN_HEIGHT=1.5;
    public static final float DAMAGE=15,SPEED=1;
    public static final int DURATION=60,SHOT_TICK=16,LIFETIME=100,DEATH_DURATION=60;
    private static final EntityDataAccessor<String> ACTION=SynchedEntityData.defineId(FireElementEntity.class,EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> START=SynchedEntityData.defineId(FireElementEntity.class,EntityDataSerializers.LONG);
    private UUID ownerUuid,targetUuid;
    private boolean fired;
    private final Map<UUID,Vec3> positions=new HashMap<>(),velocities=new HashMap<>();
    public FireElementEntity(EntityType<? extends FireElementEntity> type,Level level){super(type,level);xpReward=5;}
    public static AttributeSupplier.Builder createAttributes(){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,100).add(Attributes.ARMOR,8).add(Attributes.MOVEMENT_SPEED,0).add(Attributes.FOLLOW_RANGE,RANGE).add(Attributes.KNOCKBACK_RESISTANCE,1).add(com.eccraft.staff.StaffAttributes.MAGIC_RESISTANCE,-1).add(Attributes.SCALE,1);}
    @Override protected void registerGoals(){}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ACTION,"idle");b.define(START,0L);}
    public String elementAction(){return entityData.get(ACTION);}
    public long elementStart(){return entityData.get(START);}
    public void setOwnerUuid(UUID id){ownerUuid=id;setPersistenceRequired();}
    private void action(String value,long now){entityData.set(ACTION,value);entityData.set(START,now);fired=false;}
    @Override public void tick(){if(level() instanceof ServerLevel level&&ownerUuid!=null&&tickCount%20==0){var owner=level.getEntityInAnyDimension(ownerUuid);if(!(owner instanceof FirelordEntity)||!owner.isAlive()){discard();return;}}super.tick();}
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);if(!isAlive())return;long now=level.getGameTime();
        var players=level.getPlayers(p->FirelordEntity.valid(p)&&distanceToSqr(p)<=RANGE*RANGE);var seen=new HashSet<UUID>();
        for(var p:players){seen.add(p.getUUID());var last=positions.put(p.getUUID(),p.position());velocities.put(p.getUUID(),last==null?Vec3.ZERO:p.position().subtract(last));}
        positions.keySet().retainAll(seen);velocities.keySet().retainAll(seen);
        if(elementAction().equals("attack")){
            long elapsed=now-elementStart();
            if(!fired&&elapsed>=SHOT_TICK){fired=true;var p=targetUuid==null?null:level.getEntity(targetUuid);if(p instanceof ServerPlayer target&&players.contains(target))fire(level,target);}
            if(elapsed>=DURATION)action("idle",now);return;
        }
        var nearest=players.stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
        if(nearest!=null){targetUuid=nearest.getUUID();var d=nearest.position().subtract(position());float yaw=(float)Math.toDegrees(Math.atan2(d.z,d.x))-90;setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);action("attack",now);}
    }
    private void fire(ServerLevel level,ServerPlayer target){
        var shot=new com.eccraft.staff.StaffProjectile(com.eccraft.staff.StaffSystem.PROJECTILE,level);
        var origin=position().add(0,SPAWN_HEIGHT,0);shot.setOwner(this);shot.setPos(origin);shot.configureOwnerSafeBossShot(DAMAGE,LIFETIME,ownerUuid);
        var aim=com.eccraft.banshee.BansheeAim.predict(origin,target.position().add(0,1,0),velocities.getOrDefault(target.getUUID(),Vec3.ZERO),SPEED);var d=aim.subtract(origin);shot.shoot(d.x,d.y,d.z,SPEED,0);
        if(level.addFreshEntity(shot)&&ownerUuid!=null&&level.getEntityInAnyDimension(ownerUuid) instanceof FirelordEntity boss)boss.track(shot);
    }
    @Override public void travel(Vec3 input){getNavigation().stop();setDeltaMovement(0,getDeltaMovement().y,0);super.travel(Vec3.ZERO);}
    @Override public void move(MoverType type,Vec3 delta){super.move(type,new Vec3(0,delta.y,0));}
    @Override public boolean isPushable(){return false;}
    @Override public void push(Entity e){}
    @Override public void push(double x,double y,double z){}
    @Override public boolean isPushedByFluid(){return false;}
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction(){return net.minecraft.world.level.material.PushReaction.IGNORE;}
    @Override public void knockback(double strength,double x,double z,DamageSource source,float amount,boolean force){}
    @Override protected boolean canRide(Entity vehicle){return false;}
    @Override protected void dropAllDeathLoot(ServerLevel level,DamageSource source){if(ownerUuid==null)super.dropAllDeathLoot(level,source);}
    @Override public void die(DamageSource source){if(elementAction().equals("death"))return;super.die(source);action("death",level().getGameTime());}
    @Override protected void tickDeath(){if(++deathTime>=DEATH_DURATION&&level() instanceof ServerLevel level){level.broadcastEntityEvent(this,(byte)60);remove(RemovalReason.KILLED);}}
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);if(ownerUuid!=null)o.putString("FireElementOwner",ownerUuid.toString());if(targetUuid!=null)o.putString("FireElementTarget",targetUuid.toString());o.putString("FireElementAction",elementAction());o.putLong("FireElementStart",elementStart());o.putBoolean("FireElementFired",fired);o.putInt("FireElementDeath",deathTime);}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);String owner=i.getStringOr("FireElementOwner","");if(!owner.isBlank())ownerUuid=UUID.fromString(owner);String target=i.getStringOr("FireElementTarget","");if(!target.isBlank())targetUuid=UUID.fromString(target);entityData.set(ACTION,i.getStringOr("FireElementAction","idle"));entityData.set(START,i.getLongOr("FireElementStart",0));fired=i.getBooleanOr("FireElementFired",false);deathTime=i.getIntOr("FireElementDeath",0);}
}
