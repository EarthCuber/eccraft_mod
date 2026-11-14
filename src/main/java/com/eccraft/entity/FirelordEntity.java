package com.eccraft.entity;

import com.eccraft.Eccraft;
import com.eccraft.firelord.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.Vec3;
import java.util.*;

public final class FirelordEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final double MAX_HEALTH=4000,ARMOR=8,MOVEMENT_SPEED=0,EXISTENCE_RANGE=24,PATROL_RANGE=2;
    public static final double MELEE_TRIGGER_RANGE=4.5,MELEE_HIT_RANGE=12,FLIGHT_HEIGHT=1;
    public static final int COMBAT_TIMEOUT_TICKS=240,MELEE_DURATION=40,MELEE_HIT_TICK=13;
    public static final int SKILL_1_DURATION=60,SKILL_2_DURATION=80,DEATH_DURATION=140;
    public static final int SKILL_1_TICK=10;
    public static final int SKILL_1_COOLDOWN=400,SKILL_3_COOLDOWN=800;
    public static final int INITIAL_FLIGHT_COOLDOWN=1800,FLIGHT_COOLDOWN=1800,TRANSITION_TICKS=40,HOVER_TICKS=1200;
    public static final float MELEE_DAMAGE=22;
    private static final EntityDataAccessor<String> ACTION=SynchedEntityData.defineId(FirelordEntity.class,EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> START=SynchedEntityData.defineId(FirelordEntity.class,EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> FLIGHT=SynchedEntityData.defineId(FirelordEntity.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PHASE=SynchedEntityData.defineId(FirelordEntity.class,EntityDataSerializers.INT);
    private final ServerBossEvent bar=new ServerBossEvent(UUID.randomUUID(),Component.translatable("entity.eccraft.firelord"),BossEvent.BossBarColor.PURPLE,BossEvent.BossBarOverlay.NOTCHED_10);
    private Vec3 home=Vec3.ZERO;
    private boolean initialized,spawned,clearing,effected;
    private UUID target;
    private boolean airSummonReady;
    public static final int SKILL_4_COOLDOWN=320,SKILL_4_DURATION=80,SKILL_4_EFFECT_TICK=40,LAVA_OUT_DURATION=220;
    private long nextFlight,flightStart,hoverEnd,next1,next3,next4;
    private int noPlayers;
    private boolean hasTakenOff;
    private final Set<UUID> summons=new HashSet<>();
    public FirelordEntity(EntityType<? extends FirelordEntity> type,Level level){super(type,level);xpReward=100;setPersistenceRequired();}
    public static AttributeSupplier.Builder createAttributes(){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,MAX_HEALTH).add(Attributes.ARMOR,ARMOR).add(Attributes.MOVEMENT_SPEED,MOVEMENT_SPEED).add(Attributes.FOLLOW_RANGE,EXISTENCE_RANGE).add(Attributes.KNOCKBACK_RESISTANCE,1).add(Attributes.SCALE,1);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ACTION,"idle");b.define(START,0L);b.define(FLIGHT,0);b.define(PHASE,1);}
    @Override protected void registerGoals(){}
    public static boolean valid(ServerPlayer p){return p.isAlive()&&!p.isCreative()&&!p.isSpectator();}
    public String firelordAction(){return entityData.get(ACTION);}
    public long firelordActionStartTick(){return entityData.get(START);}
    public int flight(){return entityData.get(FLIGHT);}
    public int phase(){return entityData.get(PHASE);}
    private List<ServerPlayer> players(ServerLevel level,Vec3 center,double range){return level.getPlayers(p->valid(p)&&p.position().distanceToSqr(center)<=range*range);}
    private void action(String name,long now,UUID id){if(name.equals("attack"))meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);entityData.set(ACTION,name);entityData.set(START,now);target=id;effected=false;getNavigation().stop();if(id!=null)face(id);}
    private void face(UUID id){if(level() instanceof ServerLevel level&&level.getEntity(id) instanceof ServerPlayer p){var d=p.position().subtract(position());if(d.horizontalDistanceSqr()>1e-8){float yaw=(float)Math.toDegrees(Math.atan2(d.z,d.x))-90;setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);}}}
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);long now=level.getGameTime();
        if(!initialized){initialized=true;home=position();nextFlight=now+INITIAL_FLIGHT_COOLDOWN;next1=now+100;}
        if(!spawned){spawned=true;function(level,"spawned");}if(isRemoved()||!isAlive())return;
        bar.setProgress(getHealth()/getMaxHealth());
        int phase=phase();if(getHealth()<=getMaxHealth()*.5F)phase=Math.max(phase,3);else if(getHealth()<=getMaxHealth()*.8F)phase=Math.max(phase,2);entityData.set(PHASE,phase);
        if(!hasTakenOff&&flight()==0&&getHealth()<getMaxHealth()*.7F)nextFlight=Math.min(nextFlight,now);
        boolean air=flight()!=0;
        double patrol=air?position().subtract(home).horizontalDistanceSqr():position().distanceToSqr(home);
        if(patrol>PATROL_RANGE*PATROL_RANGE){clear(level);return;}
        var nearby=players(level,air?home:position(),EXISTENCE_RANGE);syncBar(nearby);
        noPlayers=nearby.isEmpty()?noPlayers+1:0;if(noPlayers>=COMBAT_TIMEOUT_TICKS){clear(level);return;}
        if(flight()==1||flight()==3){
            long elapsed=now-flightStart;boolean rising=flight()==1;
            setPos(home.x,com.eccraft.nightgod.NightgodMath.height(rising,elapsed,TRANSITION_TICKS,home.y,FLIGHT_HEIGHT),home.z);
            if(elapsed>=TRANSITION_TICKS){entityData.set(FLIGHT,rising?2:0);if(rising){hoverEnd=now+HOVER_TICKS;airSummonReady=true;}action("idle",now,null);}
            return;
        }
        if(flight()==2)setPos(home.add(0,FLIGHT_HEIGHT,0));
        String active=firelordAction();
        if(!active.equals("idle")){tickAction(level,now,active);return;}
        if(flight()==2&&(now>=hoverEnd||phase>=3)){transition(false,now);return;}
        if(flight()==0&&phase<3&&now>=nextFlight){transition(true,now);return;}
        if(nearby.isEmpty())return;
        var nearest=nearby.stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElseThrow();
        if(flight()==0&&phase>=3&&now>=next3){next3=now+SKILL_3_COOLDOWN;action("skill_3",now,nearest.getUUID());}
        else if(flight()==2&&airSummonReady){airSummonReady=false;action("skill_2",now,null);}
        else if(flight()==2&&now>=next4){next4=now+SKILL_4_COOLDOWN;action("skill_4",now,null);}
        else if((flight()==0||flight()==2)&&now>=next1){next1=now+SKILL_1_COOLDOWN;action("skill_1",now,nearest.getUUID());}
        else if(flight()==0&&distanceToSqr(nearest)<=MELEE_TRIGGER_RANGE*MELEE_TRIGGER_RANGE)action("attack",now,nearest.getUUID());
        else if(flight()==0){if(failedMeleeAttempt()){clear(level);return;}}
    }

    private void transition(boolean up,long now){entityData.set(FLIGHT,up?1:3);flightStart=now;setNoGravity(true);setDeltaMovement(Vec3.ZERO);if(up){hasTakenOff=true;nextFlight=now+FLIGHT_COOLDOWN;}action(up?"rise":"land",now,null);}
    public static int duration(String a){return switch(a){case "attack"->40;case "skill_1"->60;case "skill_2"->80;case "skill_3"->100;case "skill_4"->SKILL_4_DURATION;case "death"->140;default->0;};}
    private void tickAction(ServerLevel level,long now,String a){
        long t=now-entityData.get(START);
        if(!effected){switch(a){
            case "attack"->{if(t>=MELEE_HIT_TICK){effected=true;if(target!=null&&level.getEntity(target) instanceof ServerPlayer p&&valid(p)&&distanceToSqr(p)<=MELEE_HIT_RANGE*MELEE_HIT_RANGE){if(p.hurtServer(level,com.eccraft.damage.BossDamageSources.melee(level,this),MELEE_DAMAGE)){p.addEffect(new MobEffectInstance(com.eccraft.effect.ArmorBreakEffect.ARMOR_BREAK,360,0),this);com.eccraft.sound.FirelordSounds.onAttackHit(this);}}}}
            case "skill_1"->{if(t>=SKILL_1_TICK){effected=true;var source=com.eccraft.firelord.FirelordDamage.magic(level,this,this);for(var p:level.getPlayers(FirelordEntity::valid))if(com.eccraft.firelord.FirelordMath.sector(position(),getYRot(),new Vec3(p.getX(),Math.clamp(getY(),p.getY(),p.getBoundingBox().maxY),p.getZ()))&&p.hurtServer(level,source,12))p.addEffect(new MobEffectInstance(com.eccraft.effect.ArmorBreakEffect.ARMOR_BREAK,360,2),this);com.eccraft.firelord.FirelordParticles.fan(level,position(),getYRot());com.eccraft.sound.FirelordSounds.skill(this,1);}}
            case "skill_2"->{if(t>=40){effected=true;for(int k=0;k<4;k++){var element=new FireElementEntity(FirelordContent.ELEMENT,level);element.setOwnerUuid(getUUID());element.setPos(com.eccraft.firelord.FirelordMath.cardinal(home,k));if(level.addFreshEntity(element))track(element);}com.eccraft.sound.FirelordSounds.skill(this,2);}}
            case "skill_3"->{if(t>=10){effected=true;var meteor=new FirelordMeteor(FirelordContent.METEOR,level);meteor.setOwnerUuid(getUUID());meteor.setPos(position().add(0,10,0));meteor.beginLanding(position());if(level.addFreshEntity(meteor))track(meteor);com.eccraft.sound.FirelordSounds.skill(this,3);}}
            case "skill_4"->{if(t>=SKILL_4_EFFECT_TICK){effected=true;for(var p:players(level,position(),32))p.addEffect(new MobEffectInstance(com.eccraft.effect.FirelordLavaOutEffect.EFFECT,LAVA_OUT_DURATION,0),this);com.eccraft.sound.FirelordSounds.skill(this,4);}}
            default->{}
        }}
        if(t>=duration(a))action("idle",now,null);
    }
    public void track(Entity e){summons.add(e.getUUID());}
    private void cleanup(ServerLevel level){for(var id:summons){var e=level.getEntityInAnyDimension(id);if(e!=null)e.discard();}summons.clear();}
    private void clear(ServerLevel level){clearing=true;cleanup(level);bar.removeAllPlayers();function(level,"cleared");discard();}
    private void function(ServerLevel level,String path){var f=level.getServer().getFunctions();f.get(Eccraft.id("firelord/"+path)).ifPresent(fn->f.execute(fn,f.getGameLoopSender().withLevel(level).withPosition(position()).withEntity(this).withSuppressedOutput()));}
    private void syncBar(List<ServerPlayer> list){for(var p:new ArrayList<>(bar.getPlayers()))if(!list.contains(p))bar.removePlayer(p);for(var p:list)bar.addPlayer(p);}
    @Override public void startSeenByPlayer(ServerPlayer p){super.startSeenByPlayer(p);bar.addPlayer(p);}
    @Override public void stopSeenByPlayer(ServerPlayer p){super.stopSeenByPlayer(p);bar.removePlayer(p);}
    @Override public boolean isInvulnerableTo(ServerLevel level,DamageSource source){return flight()!=0||super.isInvulnerableTo(level,source);}
    @Override public void travel(Vec3 input){setNoGravity(flight()!=0);if(flight()!=0){setDeltaMovement(Vec3.ZERO);return;}setDeltaMovement(0,getDeltaMovement().y,0);super.travel(Vec3.ZERO);}
    @Override public void move(MoverType type,Vec3 delta){if(flight()==0)super.move(type,new Vec3(0,delta.y,0));}
    @Override public boolean isPushable(){return false;}
    @Override public void push(Entity e){}
    @Override public void push(double x,double y,double z){}
    @Override public boolean isPushedByFluid(){return false;}
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction(){return net.minecraft.world.level.material.PushReaction.IGNORE;}
    @Override public void knockback(double strength,double x,double z,DamageSource source,float amount,boolean force){}
    @Override protected boolean canRide(Entity vehicle){return false;}
    @Override protected boolean shouldDropLoot(ServerLevel level){return !clearing&&super.shouldDropLoot(level);}
    @Override public void die(DamageSource source){if(firelordAction().equals("death"))return;if(level() instanceof ServerLevel s)cleanup(s);bar.setProgress(0);super.die(source);action("death",level().getGameTime(),null);}
    @Override protected void tickDeath(){if(++deathTime>=DEATH_DURATION&&level() instanceof ServerLevel s){bar.removeAllPlayers();function(s,"defeated");s.broadcastEntityEvent(this,(byte)60);remove(RemovalReason.KILLED);}}
    @Override protected net.minecraft.sounds.SoundEvent getDeathSound(){return com.eccraft.sound.FirelordSounds.DEATH;}
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);o.putInt("EccraftMeleeBudgetSpent",meleeBudgetSpent);o.putBoolean("FirelordHasTakenOff",hasTakenOff);o.putBoolean("FirelordAirSummonReady",airSummonReady);o.putBoolean("FirelordInitialized",initialized);o.putBoolean("FirelordSpawned",spawned);o.putDouble("FirelordHomeX",home.x);o.putDouble("FirelordHomeY",home.y);o.putDouble("FirelordHomeZ",home.z);o.putString("FirelordAction",firelordAction());o.putLong("FirelordStart",firelordActionStartTick());o.putInt("FirelordFlight",flight());o.putInt("FirelordPhase",phase());o.putBoolean("FirelordEffected",effected);o.putInt("FirelordNoPlayers",noPlayers);o.putInt("FirelordDeathTime",deathTime);o.putLong("FirelordNextFlight",nextFlight);o.putLong("FirelordFlightStart",flightStart);o.putLong("FirelordHoverEnd",hoverEnd);o.putLong("FirelordNext1",next1);o.putLong("FirelordNext3",next3);o.putLong("FirelordNext4",next4);if(target!=null)o.putString("FirelordTarget",target.toString());o.putString("FirelordSummons",String.join(",",summons.stream().map(UUID::toString).toList()));}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(i.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);hasTakenOff=i.getBooleanOr("FirelordHasTakenOff",i.getIntOr("FirelordFlight",0)!=0||i.getLongOr("FirelordFlightStart",0)>0);airSummonReady=i.getBooleanOr("FirelordAirSummonReady",false);initialized=i.getBooleanOr("FirelordInitialized",false);spawned=i.getBooleanOr("FirelordSpawned",false);home=new Vec3(i.getDoubleOr("FirelordHomeX",getX()),i.getDoubleOr("FirelordHomeY",getY()),i.getDoubleOr("FirelordHomeZ",getZ()));entityData.set(ACTION,i.getStringOr("FirelordAction","idle"));entityData.set(START,i.getLongOr("FirelordStart",0));entityData.set(FLIGHT,Math.clamp(i.getIntOr("FirelordFlight",0),0,3));entityData.set(PHASE,Math.clamp(i.getIntOr("FirelordPhase",1),1,3));effected=i.getBooleanOr("FirelordEffected",false);noPlayers=i.getIntOr("FirelordNoPlayers",0);deathTime=i.getIntOr("FirelordDeathTime",0);nextFlight=i.getLongOr("FirelordNextFlight",level().getGameTime()+INITIAL_FLIGHT_COOLDOWN);flightStart=i.getLongOr("FirelordFlightStart",0);hoverEnd=i.getLongOr("FirelordHoverEnd",0);next1=i.getLongOr("FirelordNext1",0);next3=i.getLongOr("FirelordNext3",0);next4=i.getLongOr("FirelordNext4",0);String t=i.getStringOr("FirelordTarget","");if(!t.isBlank())target=UUID.fromString(t);String saved=i.getStringOr("FirelordSummons","");if(!saved.isBlank())for(String id:saved.split(","))summons.add(UUID.fromString(id));setNoGravity(flight()!=0);}
}
