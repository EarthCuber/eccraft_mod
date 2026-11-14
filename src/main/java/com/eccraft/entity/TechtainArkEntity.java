package com.eccraft.entity;

import com.eccraft.Eccraft;
import com.eccraft.techtainark.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.Vec3;
import java.util.*;

public final class TechtainArkEntity extends Monster {
    private int meleeBudgetSpent;
    private boolean failedMeleeAttempt(){meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.failedAttempt(meleeBudgetSpent,TIMEOUT);return meleeBudgetSpent>=TIMEOUT;}

    public static final double EXISTENCE_RANGE=32,PATROL_RANGE=24,TRIGGER_RANGE=3.5,HIT_RANGE=12,DASH_SPEED=.75;
    public static final int TIMEOUT=240,ATTACK_TICKS=40,ATTACK_HIT=15,SKILL1_TICKS=120,SKILL1_HIT=70,SKILL2_TICKS=80,SKILL2_HIT=40;
    public static final int SKILL1_CD=720,SKILL2_CD=400,DASH_CD=100,DASH_INTRO=14,DASH_TICKS=20,DASH_TOTAL=100,STUN_TICKS=200,DEATH_TICKS=140;
    private static final EntityDataAccessor<String> ACTION=SynchedEntityData.defineId(TechtainArkEntity.class,EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> START=SynchedEntityData.defineId(TechtainArkEntity.class,EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> DASH_END=SynchedEntityData.defineId(TechtainArkEntity.class,EntityDataSerializers.INT);
    private final ServerBossEvent bar=new ServerBossEvent(UUID.randomUUID(),Component.translatable("entity.eccraft.techtain_ark"),BossEvent.BossBarColor.GREEN,BossEvent.BossBarOverlay.PROGRESS);
    private Vec3 home=Vec3.ZERO,dash=Vec3.ZERO;
    private UUID target;
    private boolean initialized,effected,clearing;
    private int noPlayers,phase=1;
    private long next1,next2,next3;
    private final Set<UUID> summons=new HashSet<>(),hit=new HashSet<>();
    public TechtainArkEntity(EntityType<? extends TechtainArkEntity> type,Level level){super(type,level);setPersistenceRequired();xpReward=100;}
    public static AttributeSupplier.Builder createAttributes(){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,2500).add(Attributes.ARMOR,3).add(Attributes.ATTACK_DAMAGE,14).add(Attributes.MOVEMENT_SPEED,0).add(Attributes.FOLLOW_RANGE,32).add(Attributes.KNOCKBACK_RESISTANCE,1).add(Attributes.SCALE,1);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ACTION,"idle");b.define(START,0L);b.define(DASH_END,34);}
    @Override protected void registerGoals(){}
    public String arkAction(){return entityData.get(ACTION);}
    public long arkStart(){return entityData.get(START);}
    public int dashEnd(){return entityData.get(DASH_END);}
    private void action(String name,long now,UUID id){if(name.equals("attack"))meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.attackStarted(meleeBudgetSpent,TIMEOUT);com.eccraft.sound.TechtainArkSounds.action(this,arkAction(),name);entityData.set(ACTION,name);entityData.set(START,now);target=id;effected=false;dash=Vec3.ZERO;hit.clear();getNavigation().stop();setDeltaMovement(0,getDeltaMovement().y,0);if(name.equals("dash"))entityData.set(DASH_END,34);if(id!=null&&level() instanceof ServerLevel l&&l.getEntity(id)!=null)ArkCombat.face(this,l.getEntity(id).position().subtract(position()));}
    public void paralyze(){if(isAlive()&&!arkAction().equals("stun"))action("stun",level().getGameTime(),null);}
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);if(!isAlive())return;long now=level.getGameTime();
        if(!initialized){initialized=true;home=position();function(level,"spawned");}if(isRemoved())return;
        var nearby=ArkCombat.players(level,this,EXISTENCE_RANGE);syncBar(nearby);bar.setProgress(getHealth()/getMaxHealth());
        noPlayers=nearby.isEmpty()?noPlayers+1:0;
        if(position().distanceToSqr(home)>PATROL_RANGE*PATROL_RANGE||noPlayers>=TIMEOUT){clear(level);return;}
        phase=ArkMath.phase(getHealth(),getMaxHealth(),phase);
        if(!arkAction().equals("idle")){tickAction(level,now);return;}
        if(nearby.isEmpty())return;
        var nearest=nearby.stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElseThrow();
        if(phase>=3&&now>=next2){next2=now+SKILL2_CD;action("skill2",now,nearest.getUUID());}
        else if(phase>=2&&now>=next1){next1=now+SKILL1_CD;action("skill1",now,nearest.getUUID());}
        else if(phase>=4&&now>=next3){next3=now+DASH_CD;action("dash",now,nearest.getUUID());}
        else if(distanceToSqr(nearest)<=TRIGGER_RANGE*TRIGGER_RANGE)action("attack",now,nearest.getUUID());
        else {if(failedMeleeAttempt()){clear(level);return;}}
    }
    private void tickAction(ServerLevel level,long now){
        int t=(int)(now-arkStart());String a=arkAction();
        if(a.equals("stun")){if(t>=STUN_TICKS)action("idle",now,null);return;}
        if(a.equals("dash")){
            if(!effected&&t>=DASH_INTRO){effected=true;var victim=target==null?null:level.getEntity(target);if(victim instanceof ServerPlayer p&&ArkCombat.valid(p)){dash=ArkMath.dash(position(),p.position().add(0,1,0),p.getDeltaMovement(),DASH_SPEED,true);ArkCombat.face(this,dash);}else entityData.set(DASH_END,t);}
            if(t>=DASH_INTRO&&t<dashEnd()&&dash.lengthSqr()>0){
                var before=getBoundingBox();var from=position();super.move(MoverType.SELF,dash);var to=position();
                for(var e:ArkCombat.hits(level,this,before,from,to,null))if(e instanceof ServerPlayer p&&hit.add(p.getUUID())){p.hurtServer(level,com.eccraft.damage.BossDamageSources.melee(level,this),(float)getAttributeValue(Attributes.ATTACK_DAMAGE)*1.5F);ArkCombat.launch(p,dash);}
                if(horizontalCollision||to.subtract(from).horizontalDistanceSqr()<dash.horizontalDistanceSqr()-.0001)entityData.set(DASH_END,t+1);
            }
            if(t>=DASH_TOTAL)action("idle",now,null);return;
        }
        if(!effected){
            if(a.equals("attack")&&t>=ATTACK_HIT){effected=true;if(target!=null&&level.getEntity(target) instanceof ServerPlayer p&&ArkCombat.valid(p)&&distanceToSqr(p)<=HIT_RANGE*HIT_RANGE)p.hurtServer(level,com.eccraft.damage.BossDamageSources.melee(level,this),(float)getAttributeValue(Attributes.ATTACK_DAMAGE));}
            if(a.equals("skill1")&&t>=SKILL1_HIT){effected=true;summon(level,TechtainArkContent.BOX,3);}
            if(a.equals("skill2")&&t>=SKILL2_HIT){effected=true;summon(level,TechtainArkContent.SPRINTER,2);}
        }
        int length=switch(a){case "attack"->ATTACK_TICKS;case "skill1"->SKILL1_TICKS;case "skill2"->SKILL2_TICKS;default->0;};
        if(t>=length)action("idle",now,null);
    }
    public void bind(Mob mob){mob.addTag(TechtainArkSummons.MARKER);mob.addTag(TechtainArkSummons.OWNER_PREFIX+getUUID());mob.setPersistenceRequired();mob.setCanPickUpLoot(false);summons.add(mob.getUUID());}
    private void summon(ServerLevel level,EntityType<ArkMinionEntity> type,int count){for(int i=0;i<count;i++){var m=type.create(level,EntitySpawnReason.MOB_SUMMONED);if(m==null)continue;boolean placed=false;for(int n=0;n<64;n++){double angle=random.nextDouble()*Math.PI*2,r=Math.sqrt(random.nextDouble())*12;m.setPos(home.x+Math.cos(angle)*r,home.y+.5,home.z+Math.sin(angle)*r);if(level.noCollision(m)&&!getBoundingBox().intersects(m.getBoundingBox())){placed=true;break;}}if(placed){bind(m);level.addFreshEntity(m);}}}
    private void cleanup(ServerLevel l){for(var id:summons){var e=l.getEntityInAnyDimension(id);if(e!=null)e.discard();}summons.clear();}
    private void clear(ServerLevel l){clearing=true;cleanup(l);bar.removeAllPlayers();function(l,"cleared");discard();}
    private void function(ServerLevel l,String path){var f=l.getServer().getFunctions();f.get(Eccraft.id("techtain_ark/"+path)).ifPresent(fn->f.execute(fn,f.getGameLoopSender().withLevel(l).withPosition(position()).withEntity(this).withSuppressedOutput()));}
    private void syncBar(List<ServerPlayer> list){for(var p:new ArrayList<>(bar.getPlayers()))if(!list.contains(p))bar.removePlayer(p);for(var p:list)bar.addPlayer(p);}
    @Override public void startSeenByPlayer(ServerPlayer p){super.startSeenByPlayer(p);bar.addPlayer(p);}
    @Override public void stopSeenByPlayer(ServerPlayer p){super.stopSeenByPlayer(p);bar.removePlayer(p);}
    @Override public void travel(Vec3 input){getNavigation().stop();setDeltaMovement(0,getDeltaMovement().y,0);super.travel(Vec3.ZERO);}
    @Override public void move(MoverType type,Vec3 delta){super.move(type,new Vec3(0,delta.y,0));}
    @Override public boolean isPushable(){return false;}
    @Override public void push(Entity e){}
    @Override public void push(double x,double y,double z){}
    @Override public boolean isPushedByFluid(){return false;}
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction(){return net.minecraft.world.level.material.PushReaction.IGNORE;}
    @Override public void knockback(double s,double x,double z,DamageSource source,float amount,boolean force){}
    @Override protected boolean canRide(Entity e){return false;}
    @Override protected boolean shouldDropLoot(ServerLevel level){return !clearing&&super.shouldDropLoot(level);}
    @Override public void die(DamageSource source){if(arkAction().equals("dead"))return;if(level() instanceof ServerLevel l)cleanup(l);bar.setProgress(0);super.die(source);action("dead",level().getGameTime(),null);}
    @Override protected void tickDeath(){if(++deathTime>=DEATH_TICKS&&level() instanceof ServerLevel l){bar.removeAllPlayers();function(l,"defeated");remove(RemovalReason.KILLED);}}
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);o.putInt("EccraftMeleeBudgetSpent",meleeBudgetSpent);o.putBoolean("ArkInitialized",initialized);o.putDouble("ArkX",home.x);o.putDouble("ArkY",home.y);o.putDouble("ArkZ",home.z);o.putString("ArkAction",arkAction());o.putLong("ArkStart",arkStart());o.putInt("ArkDashEnd",dashEnd());o.putDouble("ArkDX",dash.x);o.putDouble("ArkDZ",dash.z);o.putInt("ArkPhase",phase);o.putInt("ArkAbsent",noPlayers);o.putInt("ArkDeath",deathTime);o.putBoolean("ArkEffected",effected);o.putLong("ArkNext1",next1);o.putLong("ArkNext2",next2);o.putLong("ArkNext3",next3);if(target!=null)o.putString("ArkTarget",target.toString());o.putString("ArkSummons",String.join(",",summons.stream().map(UUID::toString).toList()));o.putString("ArkHits",String.join(",",hit.stream().map(UUID::toString).toList()));}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);meleeBudgetSpent=com.eccraft.combat.BossDisengageBudget.restore(i.getIntOr("EccraftMeleeBudgetSpent",0),TIMEOUT);initialized=i.getBooleanOr("ArkInitialized",false);home=new Vec3(i.getDoubleOr("ArkX",getX()),i.getDoubleOr("ArkY",getY()),i.getDoubleOr("ArkZ",getZ()));entityData.set(ACTION,i.getStringOr("ArkAction","idle"));entityData.set(START,i.getLongOr("ArkStart",0));entityData.set(DASH_END,i.getIntOr("ArkDashEnd",34));dash=new Vec3(i.getDoubleOr("ArkDX",0),0,i.getDoubleOr("ArkDZ",0));phase=i.getIntOr("ArkPhase",1);noPlayers=i.getIntOr("ArkAbsent",0);deathTime=i.getIntOr("ArkDeath",0);effected=i.getBooleanOr("ArkEffected",false);next1=i.getLongOr("ArkNext1",0);next2=i.getLongOr("ArkNext2",0);next3=i.getLongOr("ArkNext3",0);String t=i.getStringOr("ArkTarget","");if(!t.isEmpty())target=UUID.fromString(t);for(String s:i.getStringOr("ArkSummons","").split(","))if(!s.isEmpty())summons.add(UUID.fromString(s));for(String s:i.getStringOr("ArkHits","").split(","))if(!s.isEmpty())hit.add(UUID.fromString(s));}
}
