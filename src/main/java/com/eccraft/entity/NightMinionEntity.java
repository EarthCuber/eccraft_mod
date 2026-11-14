package com.eccraft.entity;

import com.eccraft.nightgod.NightgodMath;
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

/** Normal hostile mobs. Only instances assigned an owner get Boss cleanup/no-drop behavior. */
public abstract class NightMinionEntity extends Monster {
    private static final EntityDataAccessor<String> ACTION=SynchedEntityData.defineId(NightMinionEntity.class,EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> START=SynchedEntityData.defineId(NightMinionEntity.class,EntityDataSerializers.LONG);
    private UUID ownerUuid,attackTarget;
    private boolean effected;
    private long nextSkill=-1;
    protected NightMinionEntity(EntityType<? extends NightMinionEntity> type,Level level){super(type,level);xpReward=5;}
    public abstract boolean isReaper();
    public static AttributeSupplier.Builder attributes(boolean reaper){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,reaper?100:20).add(Attributes.ARMOR,reaper?8:5).add(Attributes.MOVEMENT_SPEED,reaper?.3:.2).add(Attributes.FOLLOW_RANGE,reaper?12:8).add(com.eccraft.staff.StaffAttributes.MAGIC_RESISTANCE,-1).add(Attributes.SCALE,1);}
    @Override protected void registerGoals(){}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ACTION,"idle");b.define(START,0L);}
    public String minionAction(){return entityData.get(ACTION);}
    public long minionActionStartTick(){return entityData.get(START);}
    public void setOwnerUuid(UUID uuid){ownerUuid=uuid;setPersistenceRequired();}
    private List<ServerPlayer> players(ServerLevel level,double range){return level.getPlayers(p->NightgodEntity.valid(p)&&distanceToSqr(p)<=range*range);}
    private void setAction(String value,long now){if(!value.equals(minionAction())){entityData.set(ACTION,value);entityData.set(START,now);}effected=false;}
    private void face(ServerPlayer target){var d=target.position().subtract(position());if(d.horizontalDistanceSqr()>1e-8){float yaw=(float)Math.toDegrees(Math.atan2(d.z,d.x))-90;setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);}}
    @Override public void tick(){
        if(level() instanceof ServerLevel level&&ownerUuid!=null&&tickCount%20==0){var owner=level.getEntityInAnyDimension(ownerUuid);if(!(owner instanceof NightgodEntity)||!owner.isAlive()){discard();return;}}
        super.tick();
    }
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);if(!isAlive())return;long now=level.getGameTime();if(nextSkill<0)nextSkill=now+40;
        String action=minionAction();
        if(action.equals("attack")||action.equals("skill_1")){
            getNavigation().stop();long t=now-minionActionStartTick();
            if(!effected&&action.equals("attack")&&t>=(isReaper()?16:14)){
                effected=true;var victim=attackTarget==null?null:level.getEntity(attackTarget);
                if(victim instanceof ServerPlayer p&&NightgodEntity.valid(p)&&distanceToSqr(p)<=Math.pow(isReaper()?4:2,2))p.hurtServer(level,com.eccraft.damage.BossDamageSources.melee(level,this),isReaper()?12:4);
            }
            if(!effected&&action.equals("skill_1")&&t>=30){
                effected=true;var target=players(level,24).stream().max(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
                if(target!=null){Vec3 to=NightgodMath.behind(target.position(),target.getYRot());teleportTo(to.x,to.y,to.z);setTarget(target);attackTarget=target.getUUID();face(target);}
            }
            if(t>=50)setAction("idle",now);return;
        }
        if(isReaper()&&getHealth()<getMaxHealth()*.8F&&now>=nextSkill&&!players(level,24).isEmpty()){
            nextSkill=now+160;setAction("skill_1",now);getNavigation().stop();var p=players(level,24).stream().max(Comparator.comparingDouble(this::distanceToSqr)).orElseThrow();face(p);return;
        }
        double follow=isReaper()?12:8;
        ServerPlayer target=getTarget() instanceof ServerPlayer p&&NightgodEntity.valid(p)&&distanceToSqr(p)<=follow*follow?p:null;
        if(target==null)target=players(level,follow).stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
        setTarget(target);
        if(target==null){getNavigation().stop();setAction("idle",now);return;}
        if(distanceToSqr(target)<=Math.pow(isReaper()?3:1.8,2)){attackTarget=target.getUUID();setAction("attack",now);getNavigation().stop();face(target);}
        else{setAction("walk",now);getLookControl().setLookAt(target,30,30);getNavigation().moveTo(target,1);}
    }
    private boolean locked(){return minionAction().equals("attack")||minionAction().equals("skill_1")||minionAction().equals("death");}
    @Override public void travel(Vec3 input){if(locked()){getNavigation().stop();setDeltaMovement(0,getDeltaMovement().y,0);super.travel(Vec3.ZERO);}else super.travel(input);}
    @Override public void move(MoverType type,Vec3 delta){super.move(type,locked()?new Vec3(0,delta.y,0):delta);}
    @Override protected void dropAllDeathLoot(ServerLevel level,DamageSource source){if(ownerUuid==null)super.dropAllDeathLoot(level,source);}
    @Override public void die(DamageSource source){if(minionAction().equals("death"))return;super.die(source);setAction("death",level().getGameTime());getNavigation().stop();}
    @Override protected void tickDeath(){if(++deathTime>=(isReaper()?80:60)&&level() instanceof ServerLevel level){level.broadcastEntityEvent(this,(byte)60);remove(RemovalReason.KILLED);}}
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);if(ownerUuid!=null)o.putString("NightOwner",ownerUuid.toString());if(attackTarget!=null)o.putString("NightTarget",attackTarget.toString());if(getTarget()!=null)o.putString("NightAggro",getTarget().getUUID().toString());o.putString("NightMinionAction",minionAction());o.putLong("NightMinionStart",minionActionStartTick());o.putLong("NightMinionSkill",nextSkill);o.putBoolean("NightMinionEffected",effected);o.putInt("NightMinionDeath",deathTime);}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);String owner=i.getStringOr("NightOwner","");if(!owner.isBlank())ownerUuid=UUID.fromString(owner);String t=i.getStringOr("NightTarget","");if(!t.isBlank())attackTarget=UUID.fromString(t);entityData.set(ACTION,i.getStringOr("NightMinionAction","idle"));entityData.set(START,i.getLongOr("NightMinionStart",0));nextSkill=i.getLongOr("NightMinionSkill",-1);effected=i.getBooleanOr("NightMinionEffected",false);deathTime=i.getIntOr("NightMinionDeath",0);String aggro=i.getStringOr("NightAggro","");if(!aggro.isBlank()&&level() instanceof ServerLevel level&&level.getEntity(UUID.fromString(aggro)) instanceof LivingEntity target)setTarget(target);}
}
