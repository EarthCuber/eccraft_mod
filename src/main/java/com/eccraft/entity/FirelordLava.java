package com.eccraft.entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.*;
import net.minecraft.core.particles.ParticleTypes;
public final class FirelordLava extends Mob {
 public static final int ERUPT_TICK=60,REMOVE_TICK=80;
 public static final double RADIUS=1.5;
 public static final float DAMAGE=6;
 private int age;private boolean erupted;
 public FirelordLava(EntityType<? extends FirelordLava> type,Level level){super(type,level);setNoGravity(false);setInvulnerable(true);setPersistenceRequired();xpReward=0;}
 public static AttributeSupplier.Builder createAttributes(){return Mob.createMobAttributes().add(Attributes.MAX_HEALTH,1).add(Attributes.MOVEMENT_SPEED,0).add(Attributes.GRAVITY,0.08);}
 @Override protected void registerGoals(){}
 @Override public boolean hurtServer(ServerLevel level,DamageSource source,float amount){return false;}
 @Override public boolean isPushable(){return false;}
 // Physical size is zero, so vanilla's size-derived render distance would always reject this entity.
 @Override public boolean shouldRenderAtSqrDistance(double distance){double range=64.0*getViewScale();return distance<range*range;}
 @Override public boolean isPickable(){return false;}
 @Override public boolean canBeCollidedWith(Entity other){return false;}
 @Override public boolean canCollideWith(Entity other){return false;}
 @Override public void push(Entity e){}
 @Override public void push(double x,double y,double z){}
 @Override public void travel(Vec3 input){setDeltaMovement(0,getDeltaMovement().y,0);super.travel(Vec3.ZERO);}
 @Override public void move(MoverType type,Vec3 delta){
  // A zero-volume box cannot reliably intersect block shapes. Use a tiny central
  // support probe only during this entity's movement; never expose a solid body.
  setBoundingBox(new AABB(getX()-.001,getY(),getZ()-.001,getX()+.001,getY()+.001,getZ()+.001));
  try{super.move(type,new Vec3(0,delta.y,0));}
  finally{setBoundingBox(new AABB(position(),position()));}
 }
 @Override protected boolean canRide(Entity vehicle){return false;}
 @Override public void tick(){
  super.tick();if(!(level() instanceof ServerLevel level)||isRemoved())return;
  age++;
  if(!erupted&&age>=ERUPT_TICK){
   erupted=true;var source=com.eccraft.firelord.FirelordDamage.magic(level,this,this);
   for(var entity:level.getEntities(this,new AABB(position(),position()).inflate(RADIUS),e->e.isAlive()))
    if(entity.position().distanceToSqr(position())<=RADIUS*RADIUS)entity.hurtServer(level,source,DAMAGE);
   for(int n=0;n<48;n++){double radius=RADIUS*Math.sqrt((n+.5)/48),angle=n*Math.PI*(3-Math.sqrt(5));level.sendParticles(ParticleTypes.LAVA,getX()+radius*Math.cos(angle),getY(),getZ()+radius*Math.sin(angle),0,0,.35,0,1);}
  }
  if(age>=REMOVE_TICK)discard();
 }
 @Override protected void dropAllDeathLoot(ServerLevel level,DamageSource source){}
 @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);o.putInt("FirelordLavaAge",age);o.putBoolean("FirelordLavaErupted",erupted);}
 @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);setNoGravity(false);if(getAttributeBaseValue(Attributes.GRAVITY)==0)getAttribute(Attributes.GRAVITY).setBaseValue(.08);age=i.getIntOr("FirelordLavaAge",0);erupted=i.getBooleanOr("FirelordLavaErupted",false);}
}
