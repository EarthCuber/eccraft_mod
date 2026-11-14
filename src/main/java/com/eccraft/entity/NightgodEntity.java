package com.eccraft.entity;

import com.eccraft.Eccraft;
import com.eccraft.nightgod.*;
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

public final class NightgodEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);return meleeBudgetSpent>=COMBAT_TIMEOUT_TICKS;}

    public static final double MAX_HEALTH=4000,ARMOR=8,MOVEMENT_SPEED=0,EXISTENCE_RANGE=48,PATROL_RANGE=8;
    public static final double MELEE_TRIGGER_RANGE=4.5,MELEE_HIT_RANGE=12,FLIGHT_HEIGHT=10;
    public static final int COMBAT_TIMEOUT_TICKS=240,MELEE_DURATION=40,MELEE_HIT_TICK=17,AIR_ATTACK_DURATION=100,AIR_SHOT_TICK=17;
    public static final int SKILL_1_DURATION=60,SKILL_2_DURATION=60,SUMMON_DURATION=100,DEATH_DURATION=140;
    public static final int SKILL_1_TICK=12,SKILL_2_TICK=18,SUMMON_TICK=20,CORROSION_DURATION=240;
    public static final int SKILL_1_COOLDOWN=500,SKILL_2_COOLDOWN=280,SKILL_3_COOLDOWN=480,SKILL_4_COOLDOWN=800;
    public static final int INITIAL_FLIGHT_COOLDOWN=1800,FLIGHT_COOLDOWN=4000,TRANSITION_TICKS=100,HOVER_TICKS=1960;
    public static final float MELEE_DAMAGE=22,PROJECTILE_SPEED=.32F,PROJECTILE_DAMAGE=24,EXPLOSION_DAMAGE=15;
    public static final int PROJECTILE_LIFETIME=200;
    private static final EntityDataAccessor<String> ACTION=SynchedEntityData.defineId(NightgodEntity.class,EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> START=SynchedEntityData.defineId(NightgodEntity.class,EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> FLIGHT=SynchedEntityData.defineId(NightgodEntity.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PHASE=SynchedEntityData.defineId(NightgodEntity.class,EntityDataSerializers.INT);
    private final ServerBossEvent bar=new ServerBossEvent(UUID.randomUUID(),Component.translatable("entity.eccraft.nightgod"),BossEvent.BossBarColor.PURPLE,BossEvent.BossBarOverlay.NOTCHED_10);
    private Vec3 home=Vec3.ZERO;
    private boolean initialized,spawned,clearing,effected;
    private UUID target;
    private long nextFlight,flightStart,hoverEnd,next1,next2,next3,next4;
    private int noPlayers;
    private boolean hasTakenOff;
    private final Set<UUID> summons=new HashSet<>();
    private final Map<UUID,Vec3> previous=new HashMap<>(),velocities=new HashMap<>();
    public NightgodEntity(EntityType<? extends NightgodEntity> type,Level level){super(type,level);xpReward=100;setPersistenceRequired();}
    public static AttributeSupplier.Builder createAttributes(){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,MAX_HEALTH).add(Attributes.ARMOR,ARMOR).add(Attributes.MOVEMENT_SPEED,MOVEMENT_SPEED).add(Attributes.FOLLOW_RANGE,EXISTENCE_RANGE).add(Attributes.KNOCKBACK_RESISTANCE,1).add(Attributes.SCALE,1);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ACTION,"idle");b.define(START,0L);b.define(FLIGHT,0);b.define(PHASE,1);}
    @Override protected void registerGoals(){}
    public static boolean valid(ServerPlayer p){return p.isAlive()&&!p.isCreative()&&!p.isSpectator();}
    public String nightgodAction(){return entityData.get(ACTION);}
    public long nightgodActionStartTick(){return entityData.get(START);}
    public int flight(){return entityData.get(FLIGHT);}
    public int phase(){return entityData.get(PHASE);}
    private List<ServerPlayer> players(ServerLevel level,Vec3 center,double range){return level.getPlayers(p->valid(p)&&p.position().distanceToSqr(center)<=range*range);}
    private void action(String name,long now,UUID id){if(name.equals("attack")||name.equals("air_attack"))meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,COMBAT_TIMEOUT_TICKS);entityData.set(ACTION,name);entityData.set(START,now);target=id;effected=false;getNavigation().stop();if(id!=null)face(id);}
    private void face(UUID id){if(level() instanceof ServerLevel level&&level.getEntity(id) instanceof ServerPlayer p){var d=p.position().subtract(position());if(d.horizontalDistanceSqr()>1e-8){float yaw=(float)Math.toDegrees(Math.atan2(d.z,d.x))-90;setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);}}}
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);long now=level.getGameTime();
        if(!initialized){initialized=true;home=position();nextFlight=now+INITIAL_FLIGHT_COOLDOWN;next2=now+100;}
        if(!spawned){spawned=true;function(level,"spawned");}if(isRemoved()||!isAlive())return;
        bar.setProgress(getHealth()/getMaxHealth());
        int phase=phase();if(getHealth()<=getMaxHealth()*.5F)phase=Math.max(phase,3);else if(getHealth()<=getMaxHealth()*.8F)phase=Math.max(phase,2);entityData.set(PHASE,phase);
        if(!hasTakenOff&&flight()==0&&getHealth()<getMaxHealth()*.7F)nextFlight=Math.min(nextFlight,now);
        boolean air=flight()!=0;
        double patrol=air?position().subtract(home).horizontalDistanceSqr():position().distanceToSqr(home);
        if(patrol>PATROL_RANGE*PATROL_RANGE){clear(level);return;}
        var nearby=players(level,air?home:position(),EXISTENCE_RANGE);syncBar(nearby);sample(nearby);
        noPlayers=nearby.isEmpty()?noPlayers+1:0;if(noPlayers>=COMBAT_TIMEOUT_TICKS){clear(level);return;}
        if(flight()==1||flight()==3){
            long elapsed=now-flightStart;boolean rising=flight()==1;
            setPos(home.x,NightgodMath.height(rising,elapsed,TRANSITION_TICKS,home.y,FLIGHT_HEIGHT),home.z);
            if(elapsed>=TRANSITION_TICKS){entityData.set(FLIGHT,rising?2:0);if(rising)hoverEnd=now+HOVER_TICKS;action("idle",now,null);}
            return;
        }
        if(flight()==2)setPos(home.add(0,FLIGHT_HEIGHT,0));
        String active=nightgodAction();
        if(!active.equals("idle")){tickAction(level,now,active);return;}
        if(flight()==2&&(now>=hoverEnd||phase>=3)){transition(false,now);return;}
        if(flight()==0&&phase<3&&now>=nextFlight){transition(true,now);return;}
        if(nearby.isEmpty())return;
        var nearest=nearby.stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElseThrow();
        if(flight()==0&&phase>=2&&now>=next1){next1=now+SKILL_1_COOLDOWN;action("skill_1",now,nearest.getUUID());}
        else if(flight()==0&&now>=next2){next2=now+SKILL_2_COOLDOWN;action("skill_2",now,nearest.getUUID());}
        else if(flight()==2&&now>=next3){next3=now+SKILL_3_COOLDOWN;action("skill_3",now,nearest.getUUID());}
        else if(flight()==0&&phase>=3&&now>=next4){next4=now+SKILL_4_COOLDOWN;action("skill_4",now,nearest.getUUID());}
        else if(flight()==2)action("air_attack",now,nearest.getUUID());
        else if(distanceToSqr(nearest)<=MELEE_TRIGGER_RANGE*MELEE_TRIGGER_RANGE)action("attack",now,nearest.getUUID());
        else {if(failedMeleeAttempt()){clear(level);return;}}
    }
    private void transition(boolean up,long now){entityData.set(FLIGHT,up?1:3);flightStart=now;setNoGravity(true);setDeltaMovement(Vec3.ZERO);if(up){hasTakenOff=true;nextFlight=now+FLIGHT_COOLDOWN;}action(up?"rise":"land",now,null);}
    public static int duration(String a){return switch(a){case "attack"->MELEE_DURATION;case "air_attack"->AIR_ATTACK_DURATION;case "skill_1"->SKILL_1_DURATION;case "skill_2"->SKILL_2_DURATION;case "skill_3","skill_4"->SUMMON_DURATION;case "death"->DEATH_DURATION;default->0;};}
    private void tickAction(ServerLevel level,long now,String a){
        long t=now-entityData.get(START);
        if(a.equals("skill_1")&&t<SKILL_1_DURATION&&t%5==0)com.eccraft.nightgod.NightCorrosionParticles.emit(level,position());
        if(!effected){switch(a){
            case "attack"->{if(t>=MELEE_HIT_TICK){effected=true;if(target!=null&&level.getEntity(target) instanceof ServerPlayer p&&valid(p)&&distanceToSqr(p)<=MELEE_HIT_RANGE*MELEE_HIT_RANGE)if(p.hurtServer(level,com.eccraft.damage.BossDamageSources.melee(level,this),MELEE_DAMAGE))com.eccraft.sound.NightgodSounds.onAttackHit(this);}}
            case "air_attack"->{if(t>=AIR_SHOT_TICK){effected=true;volley(level);}}
            case "skill_1"->{if(t>=SKILL_1_TICK){effected=true;var list=players(level,position(),32);if(!list.isEmpty())list.get(random.nextInt(list.size())).addEffect(new MobEffectInstance(com.eccraft.effect.NightCorrosionEffect.NIGHT_CORROSION,CORROSION_DURATION,0),this);com.eccraft.sound.NightgodSounds.skill(this,1);}}
            case "skill_2"->{if(t>=SKILL_2_TICK){effected=true;for(var p:players(level,position(),32)){var fire=new NightgodFire(NightgodContent.FIRE,level);fire.setOwnerUuid(getUUID());fire.setPos(p.position().add(0,2,0));if(level.addFreshEntity(fire))track(fire);}com.eccraft.sound.NightgodSounds.skill(this,2);}}
            case "skill_3","skill_4"->{if(t>=SUMMON_TICK){effected=true;summon(level);com.eccraft.sound.NightgodSounds.skill(this,a.equals("skill_3")?3:4);}}
            default->{}
        }}
        if(t>=duration(a))action("idle",now,null);
    }
    private void sample(List<ServerPlayer> players){Set<UUID> seen=new HashSet<>();for(var p:players){seen.add(p.getUUID());var old=previous.put(p.getUUID(),p.position());velocities.put(p.getUUID(),old==null?Vec3.ZERO:p.position().subtract(old));}previous.keySet().retainAll(seen);velocities.keySet().retainAll(seen);}
    private void volley(ServerLevel level){var origin=position().add(0,2.5,0);for(var p:players(level,home,32)){var shot=new NightgodProjectile(NightgodContent.PROJECTILE,level);shot.setOwner(this);shot.setPos(origin);var aim=com.eccraft.banshee.BansheeAim.predict(origin,p.position().add(0,1,0),velocities.getOrDefault(p.getUUID(),Vec3.ZERO),PROJECTILE_SPEED);var d=aim.subtract(origin);shot.shoot(d.x,d.y,d.z,PROJECTILE_SPEED,0);if(level.addFreshEntity(shot))track(shot);}com.eccraft.sound.NightgodSounds.onAttackHit(this);}
    private void summon(ServerLevel level){
        var death=new NightDeathEntity(NightgodContent.DEATH,level);death.setOwnerUuid(getUUID());death.setPos(NightgodMath.randomDisk(new Vec3(home.x,getY()+1,home.z),5,random.nextDouble(),random.nextDouble()));if(level.addFreshEntity(death))track(death);
        for(var p:players(level,home,32)){var devil=new LittleDevilEntity(NightgodContent.LITTLE_DEVIL,level);devil.setOwnerUuid(getUUID());devil.setPos(NightgodMath.randomDisk(p.position(),3,random.nextDouble(),random.nextDouble()));if(level.addFreshEntity(devil))track(devil);}
    }
    public void track(Entity e){summons.add(e.getUUID());}
    private void cleanup(ServerLevel level){for(var id:summons){var e=level.getEntityInAnyDimension(id);if(e!=null)e.discard();}summons.clear();}
    private void clear(ServerLevel level){clearing=true;cleanup(level);bar.removeAllPlayers();function(level,"cleared");discard();}
    private void function(ServerLevel level,String path){var f=level.getServer().getFunctions();f.get(Eccraft.id("nightgod/"+path)).ifPresent(fn->f.execute(fn,f.getGameLoopSender().withLevel(level).withPosition(position()).withEntity(this).withSuppressedOutput()));}
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
    @Override public void die(DamageSource source){if(nightgodAction().equals("death"))return;if(level() instanceof ServerLevel s)cleanup(s);bar.setProgress(0);super.die(source);action("death",level().getGameTime(),null);}
    @Override protected void tickDeath(){if(++deathTime>=DEATH_DURATION&&level() instanceof ServerLevel s){bar.removeAllPlayers();function(s,"defeated");s.broadcastEntityEvent(this,(byte)60);remove(RemovalReason.KILLED);}}
    @Override protected net.minecraft.sounds.SoundEvent getDeathSound(){return com.eccraft.sound.NightgodSounds.DEATH;}
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);o.putInt("EccraftMeleeBudgetSpent",meleeBudgetSpent);o.putBoolean("NightHasTakenOff",hasTakenOff);o.putBoolean("NightInitialized",initialized);o.putBoolean("NightSpawned",spawned);o.putDouble("NightHomeX",home.x);o.putDouble("NightHomeY",home.y);o.putDouble("NightHomeZ",home.z);o.putString("NightAction",nightgodAction());o.putLong("NightStart",nightgodActionStartTick());o.putInt("NightFlight",flight());o.putInt("NightPhase",phase());o.putBoolean("NightEffected",effected);o.putInt("NightNoPlayers",noPlayers);o.putInt("NightDeathTime",deathTime);o.putLong("NightNextFlight",nextFlight);o.putLong("NightFlightStart",flightStart);o.putLong("NightHoverEnd",hoverEnd);o.putLong("NightNext1",next1);o.putLong("NightNext2",next2);o.putLong("NightNext3",next3);o.putLong("NightNext4",next4);if(target!=null)o.putString("NightTarget",target.toString());o.putString("NightSummons",String.join(",",summons.stream().map(UUID::toString).toList()));}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(i.getIntOr("EccraftMeleeBudgetSpent",0),COMBAT_TIMEOUT_TICKS);hasTakenOff=i.getBooleanOr("NightHasTakenOff",i.getIntOr("NightFlight",0)!=0||i.getLongOr("NightFlightStart",0)>0);initialized=i.getBooleanOr("NightInitialized",false);spawned=i.getBooleanOr("NightSpawned",false);home=new Vec3(i.getDoubleOr("NightHomeX",getX()),i.getDoubleOr("NightHomeY",getY()),i.getDoubleOr("NightHomeZ",getZ()));entityData.set(ACTION,i.getStringOr("NightAction","idle"));entityData.set(START,i.getLongOr("NightStart",0));entityData.set(FLIGHT,Math.clamp(i.getIntOr("NightFlight",0),0,3));entityData.set(PHASE,Math.clamp(i.getIntOr("NightPhase",1),1,3));effected=i.getBooleanOr("NightEffected",false);noPlayers=i.getIntOr("NightNoPlayers",0);deathTime=i.getIntOr("NightDeathTime",0);nextFlight=i.getLongOr("NightNextFlight",level().getGameTime()+INITIAL_FLIGHT_COOLDOWN);flightStart=i.getLongOr("NightFlightStart",0);hoverEnd=i.getLongOr("NightHoverEnd",0);next1=i.getLongOr("NightNext1",0);next2=i.getLongOr("NightNext2",0);next3=i.getLongOr("NightNext3",0);next4=i.getLongOr("NightNext4",0);String t=i.getStringOr("NightTarget","");if(!t.isBlank())target=UUID.fromString(t);String saved=i.getStringOr("NightSummons","");if(!saved.isBlank())for(String id:saved.split(","))summons.add(UUID.fromString(id));setNoGravity(flight()!=0);}
}
