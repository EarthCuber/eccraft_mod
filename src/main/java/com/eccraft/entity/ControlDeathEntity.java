package com.eccraft.entity;
import com.eccraft.control.*;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.Vec3;
import java.util.*;
public final class ControlDeathEntity extends PathfinderMob {
 public static final int ATTACK_TICKS=50,HIT_TICK=16,SKILL_TICKS=20,TELEPORT_TICK=18,SKILL_COOLDOWN=60,DEATH_TICKS=80;
 public static final double ATTACK_RANGE=4;
 private static final EntityDataAccessor<String> ACTION=SynchedEntityData.defineId(ControlDeathEntity.class,EntityDataSerializers.STRING);
 private static final EntityDataAccessor<Long> START=SynchedEntityData.defineId(ControlDeathEntity.class,EntityDataSerializers.LONG);
 private UUID ownerUuid,orderedTarget,castTarget;private long nextSkill;private boolean effected;
 public ControlDeathEntity(EntityType<? extends ControlDeathEntity> type,Level level){super(type,level);setPersistenceRequired();xpReward=0;}
 public static AttributeSupplier.Builder attributes(){return Mob.createMobAttributes().add(Attributes.MAX_HEALTH,500).add(Attributes.ATTACK_DAMAGE,1).add(Attributes.ARMOR,8).add(Attributes.MOVEMENT_SPEED,.3).add(Attributes.FOLLOW_RANGE,32).add(com.eccraft.staff.StaffAttributes.MAGIC_RESISTANCE,-1).add(Attributes.SCALE,1);}
 @Override protected void registerGoals(){}
 @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ACTION,"idle");b.define(START,0L);}
 public String controlAction(){return entityData.get(ACTION);}
 public long controlStart(){return entityData.get(START);}
 public UUID ownerUuid(){return ownerUuid;}
 public void bind(UUID owner){ownerUuid=owner;}
 public boolean order(Entity entity){if(!isAlive()||entity==this||entity.getUUID().equals(ownerUuid)||!entity.isAlive())return false;orderedTarget=entity.getUUID();return true;}
 private boolean valid(Entity e){return e!=null&&e.isAlive()&&!e.isRemoved()&&e!=this&&!e.getUUID().equals(ownerUuid);}
 private void action(String a,long now,UUID target){if(!controlAction().equals(a)){entityData.set(ACTION,a);entityData.set(START,now);}castTarget=target;effected=false;}
 private void face(Entity target){var d=target.position().subtract(position());if(d.horizontalDistanceSqr()>1e-8){float yaw=(float)Math.toDegrees(Math.atan2(d.z,d.x))-90;setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);}}
 @Override public void tick(){
  if(isAlive()&&level() instanceof ServerLevel level){var owner=ownerUuid==null?null:level.getServer().getPlayerList().getPlayer(ownerUuid);
   if(owner==null||!owner.isAlive()||owner.level()!=level||!getUUID().equals(ControlDeathBindings.get(level.getServer()).bound(ownerUuid))){revoke();discard();return;}}
  super.tick();
 }
 private void revoke(){if(ownerUuid!=null&&level() instanceof ServerLevel level)ControlDeathBindings.get(level.getServer()).clear(ownerUuid,getUUID());}
 @Override protected void customServerAiStep(ServerLevel level){
  super.customServerAiStep(level);if(!isAlive())return;long now=level.getGameTime();var a=controlAction();
  if(a.equals("attack")||a.equals("skill_1")){
   getNavigation().stop();long t=now-controlStart();var target=castTarget==null?null:level.getEntity(castTarget);
   if(!effected&&t>=(a.equals("attack")?HIT_TICK:TELEPORT_TICK)){effected=true;if(valid(target)){
    if(a.equals("attack")){
     var owner=ownerUuid==null?null:level.getServer().getPlayerList().getPlayer(ownerUuid);
     if(owner!=null&&owner.isAlive()&&owner.level()==level&&distanceToSqr(target)<=ATTACK_RANGE*ATTACK_RANGE)
      target.hurtServer(level,com.eccraft.damage.BossDamageSources.melee(level,owner),(float)getAttributeValue(Attributes.ATTACK_DAMAGE));
    }
    else{var p=com.eccraft.nightgod.NightgodMath.behind(target.position(),target.getYRot());teleportTo(p.x,p.y,p.z);face(target);}
   }}
   if(t>=(a.equals("attack")?ATTACK_TICKS:SKILL_TICKS))action("idle",now,null);return;
  }
  Entity target=orderedTarget==null?null:level.getEntity(orderedTarget);if(!valid(target)){orderedTarget=null;target=null;}
  setTarget(target instanceof LivingEntity living?living:null);
  if(target!=null){
   face(target);
   if(distanceToSqr(target)<=ATTACK_RANGE*ATTACK_RANGE){getNavigation().stop();action("attack",now,target.getUUID());}
   else if(now>=nextSkill){nextSkill=now+SKILL_COOLDOWN;getNavigation().stop();action("skill_1",now,target.getUUID());}
   else{getNavigation().moveTo(target,1);action("walk",now,null);}return;
  }
  var owner=level.getServer().getPlayerList().getPlayer(ownerUuid);if(owner==null)return;
  if(distanceToSqr(owner)>4){getNavigation().moveTo(owner,1);action("walk",now,null);}else{getNavigation().stop();action("idle",now,null);}
 }
 private boolean locked(){return controlAction().equals("attack")||controlAction().equals("skill_1")||controlAction().equals("death");}
 @Override public void travel(Vec3 input){if(locked()){setDeltaMovement(0,getDeltaMovement().y,0);super.travel(Vec3.ZERO);}else super.travel(input);}
 @Override public void move(MoverType type,Vec3 delta){super.move(type,locked()?new Vec3(0,delta.y,0):delta);}
 @Override protected void dropAllDeathLoot(ServerLevel level,DamageSource source){}
 @Override public void die(DamageSource source){if(controlAction().equals("death"))return;revoke();super.die(source);action("death",level().getGameTime(),null);getNavigation().stop();}
 @Override protected void tickDeath(){if(++deathTime>=DEATH_TICKS&&level() instanceof ServerLevel level){level.broadcastEntityEvent(this,(byte)60);remove(RemovalReason.KILLED);}}
 @Override protected boolean canRide(Entity other){return false;}
 @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);if(ownerUuid!=null)o.putString("ControlOwner",ownerUuid.toString());if(orderedTarget!=null)o.putString("ControlTarget",orderedTarget.toString());if(castTarget!=null)o.putString("ControlCastTarget",castTarget.toString());o.putString("ControlAction",controlAction());o.putLong("ControlStart",controlStart());o.putLong("ControlNextSkill",nextSkill);o.putBoolean("ControlEffected",effected);o.putInt("ControlDeathTicks",deathTime);}
 @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);ownerUuid=uuid(i,"ControlOwner");orderedTarget=uuid(i,"ControlTarget");castTarget=uuid(i,"ControlCastTarget");entityData.set(ACTION,i.getStringOr("ControlAction","idle"));entityData.set(START,i.getLongOr("ControlStart",0));nextSkill=i.getLongOr("ControlNextSkill",0);effected=i.getBooleanOr("ControlEffected",false);deathTime=i.getIntOr("ControlDeathTicks",0);}
 private static UUID uuid(ValueInput i,String key){String value=i.getStringOr(key,"");return value.isBlank()?null:UUID.fromString(value);}
}
