package com.eccraft.entity;

import com.eccraft.Eccraft;
import com.eccraft.darkdragon.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.*;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.*;
import java.util.*;

public class DarkDragonEntity extends Monster {
    public static final float SHIELD_DISABLE_SECONDS = 20.0F;
    /** Only ordinary melee attacks disable blocking, using vanilla shield handling. */
    @Override public float getSecondsToDisableBlocking() {
        return action == DarkDragonAction.GROUND_ATTACK ? SHIELD_DISABLE_SECONDS : super.getSecondsToDisableBlocking();
    }

    public static final double MAX_HEALTH=15000, ARMOR=2, MOVEMENT_SPEED=0, SCALE=1;
    public static final double EXISTENCE_RANGE=128, PATROL_RANGE=64, COMBAT_RANGE=24;
    public static final int COMBAT_TIMEOUT_TICKS=400, STATE_DURATION=2400;
    public static final int INITIAL_BREATH_COOLDOWN=600, FIRST_TRANSITION_DELAY=300;
    public static final double AIR_TARGET_RANGE=48, BREATH_RADIUS=48, BREATH_BELOW=6, TAIL_RADIUS=32;
    public static final double FLIGHT_HEIGHT=30, MELEE_TRIGGER_RANGE=14, MELEE_HIT_RANGE=24;
    public static final int GROUND_HIT_TICK=16, AIR_SHOT_TICK=20, SKILL_2_HIT_TICK=17, SKILL_3_HIT_TICK=15, SUMMON_TICK=20;
    public static final int SKILL_1_COOLDOWN=800, SKILL_2_COOLDOWN=300, SKILL_3_COOLDOWN=300, SKILL_4_COOLDOWN=1200;
    public static final float MELEE_DAMAGE=18, BREATH_DAMAGE=6, TAIL_DAMAGE=18, ROAR_DAMAGE=6;
    public static final double SECTOR_HEIGHT=4, TAIL_PUSH_HORIZONTAL=8, TAIL_PUSH_VERTICAL=0.3;
    public static final float FIREBALL_SPEED=1, FIREBALL_DAMAGE=12;
    public static final int FIREBALL_LIFETIME=100;
    public static final int EVOKER_COUNT=4, VINDICATOR_COUNT=8, PILLAGER_COUNT=16;
    public static final double SUMMON_RADIUS=28, ROAR_PARTICLE_RADIUS=30;
    public static final double BREATH_FORWARD_OFFSET=2.4, BREATH_VERTICAL_OFFSET=-2.6;
    public static final int ROAR_PARTICLE_COUNT=256, MELEE_ARMOR_BREAK_TICKS=600;
    public static final double ROAR_PARTICLE_UP_SPEED=0.12;
    private static final EntityDataAccessor<String> ACTION=SynchedEntityData.defineId(DarkDragonEntity.class,EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Long> START=SynchedEntityData.defineId(DarkDragonEntity.class,EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Boolean> AIR=SynchedEntityData.defineId(DarkDragonEntity.class,EntityDataSerializers.BOOLEAN);
    private final ServerBossEvent bar=new ServerBossEvent(UUID.randomUUID(),Component.translatable("entity.eccraft.darkdragon"),BossEvent.BossBarColor.PURPLE,BossEvent.BossBarOverlay.PROGRESS);
    private DarkDragonAction action=DarkDragonAction.IDLE;
    private int phase=1, stateTicks, absentTicks;
    private long skill1Ready,skill2Ready,skill3Ready,skill4Ready;
    private boolean recorded,spawned,effected,clearing;
    private Vec3 home=Vec3.ZERO,transitionStart=Vec3.ZERO;
    private int cinematicTicks;
    private double cinematicAngle;
    private boolean victory;
    private UUID target;
    private final Set<UUID> children=new HashSet<>();
    private final Map<UUID,Vec3> previous=new HashMap<>(),velocities=new HashMap<>();

    public DarkDragonEntity(EntityType<? extends DarkDragonEntity> type,Level level){super(type,level);xpReward=100;setPersistenceRequired();}
    public static AttributeSupplier.Builder createAttributes(){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,MAX_HEALTH).add(Attributes.ARMOR,ARMOR)
            .add(Attributes.MOVEMENT_SPEED,MOVEMENT_SPEED).add(Attributes.ATTACK_DAMAGE,MELEE_DAMAGE).add(Attributes.FOLLOW_RANGE,EXISTENCE_RANGE)
            .add(Attributes.KNOCKBACK_RESISTANCE,1).add(Attributes.SCALE,SCALE);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(ACTION,"IDLE");b.define(START,0L);b.define(AIR,false);}
    @Override protected void registerGoals(){}
    public static boolean isCombatPlayer(ServerPlayer p){return p.isAlive()&&!p.isCreative()&&!p.isSpectator();}
    public DarkDragonAction dragonAction(){return DarkDragonAction.parse(entityData.get(ACTION));}
    public boolean airborne(){return entityData.get(AIR);}
    public long actionStart(){return entityData.get(START);}
    public Vec3 spawnPosition(){return home;}
    public void track(Entity entity){children.add(entity.getUUID());}
    public Vec3 mouth(){return position().add(DarkDragonPose.mouth(action,airborne(),(level().getGameTime()-actionStart())/20f,getYRot(),getScale()));}
    private boolean cinematic(){return action==DarkDragonAction.ENTERING||action==DarkDragonAction.ESCAPING;}
    /** Called before adding an altar summon, so its first spawn packet is already in the sky. */
    public void startEntrance(Vec3 center){
        home=center;recorded=true;spawned=false;cinematicTicks=0;absentTicks=0;
        cinematicAngle=Math.toRadians(getYRot());
        setAction(DarkDragonAction.ENTERING,level().getGameTime());entityData.set(AIR,true);
        setNoGravity(true);setDeltaMovement(Vec3.ZERO);
        Vec3 first=DarkDragonCinematics.entrance(home,cinematicAngle,0);
        setPos(first);faceMovement(DarkDragonCinematics.entrance(home,cinematicAngle,1).subtract(first));
        if(level() instanceof ServerLevel server)DarkDragonCinematics.keepLoaded(server,first);
    }
    @Override public boolean hurtServer(ServerLevel server,DamageSource source,float amount){
        if(cinematic()||!recorded)return false;
        return super.hurtServer(server,source,amount);
    }
    private void faceMovement(Vec3 direction){
        if(direction.horizontalDistanceSqr()>1e-10){float yaw=(float)Math.toDegrees(Math.atan2(direction.z,direction.x))-90;
            setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);}
    }
    private void beginEscape(ServerLevel server){
        victory=true;cinematicTicks=0;transitionStart=position();cinematicAngle=Math.toRadians(getYRot());
        target=null;getNavigation().stop();setAction(DarkDragonAction.ESCAPING,server.getGameTime());
        entityData.set(AIR,true);setNoGravity(true);setDeltaMovement(Vec3.ZERO);
        cleanup(server);bar.setProgress(0);bar.removeAllPlayers();
        // Victory is committed before running the user function; escaping never calls cleared or die.
        functionAt(server,"defeated",home);
    }
    private void tickCinematic(ServerLevel server){
        if(isRemoved()||!isAlive())return;
        boolean entering=action==DarkDragonAction.ENTERING;
        if(entering){
            var nearby=players(server,home,EXISTENCE_RANGE);updatePlayers(nearby);bar.setProgress(getHealth()/getMaxHealth());
            absentTicks=nearby.isEmpty()?absentTicks+1:0;
            if(absentTicks>=COMBAT_TIMEOUT_TICKS){clear(server);return;}
        }
        Vec3 next=entering?DarkDragonCinematics.entrance(home,cinematicAngle,++cinematicTicks)
                :DarkDragonCinematics.escape(transitionStart,cinematicAngle,++cinematicTicks);
        if(cinematicTicks%10==1)DarkDragonCinematics.keepLoaded(server,next);
        faceMovement(next.subtract(position()));setPos(next);setDeltaMovement(Vec3.ZERO);
        if(entering&&cinematicTicks>=DarkDragonCinematics.ENTRANCE_TICKS){
            setPos(home);entityData.set(AIR,false);setNoGravity(false);stateTicks=0;absentTicks=0;
            skill1Ready=server.getGameTime()+INITIAL_BREATH_COOLDOWN;
            setAction(DarkDragonAction.IDLE,server.getGameTime());spawned=true;function(server,"spawned");
        }else if(!entering&&cinematicTicks>=DarkDragonCinematics.ESCAPE_TICKS){bar.removeAllPlayers();discard();}
    }
    private boolean transitioning(){return action==DarkDragonAction.TAKEOFF||action==DarkDragonAction.LANDING;}
    private Vec3 searchCenter(){return airborne()||transitioning()?home:position();}
    private List<ServerPlayer> players(ServerLevel level,Vec3 center,double range){return level.getPlayers(p->isCombatPlayer(p)&&p.position().distanceToSqr(center)<=range*range);}
    private void updatePlayers(List<ServerPlayer> list){
        var expected=new HashSet<>(list);for(var p:new ArrayList<>(bar.getPlayers()))if(!expected.contains(p))bar.removePlayer(p);
        Set<UUID> ids=new HashSet<>();for(var p:list){bar.addPlayer(p);ids.add(p.getUUID());var old=previous.put(p.getUUID(),p.position());velocities.put(p.getUUID(),old==null?Vec3.ZERO:p.position().subtract(old));}
        previous.keySet().retainAll(ids);velocities.keySet().retainAll(ids);
    }
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);
        if(!recorded)startEntrance(position());
        if(cinematic()){tickCinematic(level);return;}
        if(!spawned){spawned=true;function(level,"spawned");}
        if(isRemoved()||!isAlive())return;
        bar.setProgress(getHealth()/getMaxHealth());
        double patrol=airborne()||transitioning()?new Vec3(getX()-home.x,0,getZ()-home.z).lengthSqr():position().distanceToSqr(home);
        if(patrol>PATROL_RANGE*PATROL_RANGE){clear(level);return;}
        var nearby=players(level,searchCenter(),EXISTENCE_RANGE);updatePlayers(nearby);
        if(nearby.isEmpty()){clear(level);return;}
        var combat=players(level,searchCenter(),airborne()||transitioning()?AIR_TARGET_RANGE:Math.max(COMBAT_RANGE,Math.max(BREATH_RADIUS,TAIL_RADIUS)));
        absentTicks=combat.isEmpty()?absentTicks+1:0;
        if(absentTicks>=COMBAT_TIMEOUT_TICKS){clear(level);return;}
        int oldPhase=phase;
        if(getHealth()<=getMaxHealth()*.5f)phase=Math.max(phase,3);else if(getHealth()<=getMaxHealth()*.8f)phase=Math.max(phase,2);
        if(oldPhase<2&&phase>=2)stateTicks=STATE_DURATION-FIRST_TRANSITION_DELAY;
        else if(phase>=2&&!transitioning())stateTicks++;
        long now=level.getGameTime();
        if(transitioning()){tickTransition(level,now);return;}
        if(action!=DarkDragonAction.IDLE){tickAction(level,now);if(action!=DarkDragonAction.IDLE)return;}
        if(DarkDragonCinematics.shouldEscape(getHealth(),getMaxHealth(),victory)){beginEscape(level);return;}
        if(phase>=2&&stateTicks>=STATE_DURATION){begin(airborne()?DarkDragonAction.LANDING:DarkDragonAction.TAKEOFF,null,now);return;}
        if(combat.isEmpty())return;
        var nearest=combat.stream().min(Comparator.comparingDouble(p->com.eccraft.combat.TargetPreference.score(p,p.position().distanceToSqr(searchCenter())))).orElseThrow();
        if(phase>=3&&now>=skill4Ready)begin(DarkDragonAction.SKILL_4,nearest,now);
        else if(airborne())begin(DarkDragonAction.AIR_ATTACK,nearest,now);
        else if(now>=skill1Ready)begin(DarkDragonAction.SKILL_1,nearest,now);
        else if(phase>=2&&now>=skill3Ready)begin(DarkDragonAction.SKILL_3,nearest,now);
        else if(phase>=2&&now>=skill2Ready)begin(DarkDragonAction.SKILL_2,nearest,now);
        else if(distanceToSqr(nearest)<=MELEE_TRIGGER_RANGE*MELEE_TRIGGER_RANGE)begin(DarkDragonAction.GROUND_ATTACK,nearest,now);
    }
    private void setAction(DarkDragonAction next,long now){action=next;entityData.set(ACTION,next.name());entityData.set(START,now);effected=false;}
    private void begin(DarkDragonAction next,ServerPlayer player,long now){
        setAction(next,now);target=player==null?null:player.getUUID();getNavigation().stop();
        if(next==DarkDragonAction.SKILL_1||next==DarkDragonAction.SKILL_2){float yaw=random.nextFloat()*360f;setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);}
        else if(player!=null){double dx=player.getX()-getX(),dz=player.getZ()-getZ();if(dx*dx+dz*dz>1e-8){float yaw=(float)Math.toDegrees(Math.atan2(dz,dx))-90;setYRot(yaw);setYBodyRot(yaw);setYHeadRot(yaw);}}
        switch(next){case SKILL_1->skill1Ready=now+SKILL_1_COOLDOWN;case SKILL_2->skill2Ready=now+SKILL_2_COOLDOWN;case SKILL_3->skill3Ready=now+SKILL_3_COOLDOWN;case SKILL_4->skill4Ready=now+SKILL_4_COOLDOWN;default->{}}
        if(next==DarkDragonAction.GROUND_ATTACK||next==DarkDragonAction.AIR_ATTACK)playSound(DarkDragonSounds.ATTACK,1,1);
        else if(next==DarkDragonAction.SKILL_1)playSound(DarkDragonSounds.FIRE,DarkDragonSounds.SKILL_1_VOLUME,1);
        else if(next==DarkDragonAction.SKILL_3)playSound(DarkDragonSounds.ROAR,1,1);
        else if(next==DarkDragonAction.SKILL_4)playSound(DarkDragonSounds.NORMAL,1,1);
        if(transitioning()){transitionStart=position();setNoGravity(true);setDeltaMovement(Vec3.ZERO);}
    }
    private void tickTransition(ServerLevel level,long now){
        int elapsed=(int)(now-actionStart());Vec3 destination=action==DarkDragonAction.TAKEOFF?home.add(0,FLIGHT_HEIGHT,0):home;
        setPos(DarkDragonMath.transition(transitionStart,destination,elapsed,action.ticks));setDeltaMovement(Vec3.ZERO);
        if(elapsed>=action.ticks){entityData.set(AIR,action==DarkDragonAction.TAKEOFF);setNoGravity(airborne());stateTicks=0;setAction(DarkDragonAction.IDLE,now);}
    }
    private void tickAction(ServerLevel level,long now){
        int t=(int)(now-actionStart());
        if(action==DarkDragonAction.SKILL_1){
            if(DarkDragonMath.breathHit(t))for(var p:sectorPlayers(level,false))p.hurtServer(level,magic(level),BREATH_DAMAGE);
            float density=DarkDragonMath.breathDensity(t);if(density>0)breathParticles(level,density);
        }else if(!effected){
            switch(action){
                case GROUND_ATTACK->{if(t>=GROUND_HIT_TICK){effected=true;var e=target==null?null:level.getEntity(target);if(e instanceof ServerPlayer p&&isCombatPlayer(p)&&distanceToSqr(p)<=MELEE_HIT_RANGE*MELEE_HIT_RANGE
                        &&p.hurtServer(level,com.eccraft.damage.BossDamageSources.melee(level,this),MELEE_DAMAGE)){
                    p.addEffect(new MobEffectInstance(com.eccraft.effect.ArmorBreakEffect.ARMOR_BREAK,MELEE_ARMOR_BREAK_TICKS,0),this);
                }}}
                case AIR_ATTACK->{if(t>=AIR_SHOT_TICK){effected=true;fireVolley(level);}}
                case SKILL_2->{if(t>=SKILL_2_HIT_TICK){effected=true;for(var p:sectorPlayers(level,true))if(p.hurtServer(level,damageSources().mobAttack(this),TAIL_DAMAGE)){var direction=p.position().subtract(position()).multiply(1,0,1).normalize();p.push(direction.x*TAIL_PUSH_HORIZONTAL,TAIL_PUSH_VERTICAL,direction.z*TAIL_PUSH_HORIZONTAL);p.hurtMarked=true;}}}
                case SKILL_3->{if(t>=SKILL_3_HIT_TICK){effected=true;roarParticles(level);for(var p:players(level,position(),COMBAT_RANGE)){p.hurtServer(level,magic(level),ROAR_DAMAGE);p.addEffect(new MobEffectInstance(MobEffects.SLOWNESS,100,2),this);}}}
                case SKILL_4->{if(t>=SUMMON_TICK){effected=true;summon(level,EntityTypes.EVOKER,EVOKER_COUNT);summon(level,EntityTypes.VINDICATOR,VINDICATOR_COUNT);summon(level,EntityTypes.PILLAGER,PILLAGER_COUNT);}}
                default->{}
            }
        }
        if(t>=action.ticks){target=null;setAction(DarkDragonAction.IDLE,now);}
    }
    private List<ServerPlayer> sectorPlayers(ServerLevel level,boolean rear){return level.getPlayers(p->isCombatPlayer(p)&&p.getBoundingBox().maxY>=getY()-(rear?0:BREATH_BELOW)&&p.getBoundingBox().minY<=getY()+SECTOR_HEIGHT&&DarkDragonMath.inSector(p.getX()-getX(),p.getZ()-getZ(),getYRot(),rear?TAIL_RADIUS:BREATH_RADIUS,rear));}
    private DamageSource magic(ServerLevel level){return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,Eccraft.id("magic"))),this);}
    private void breathParticles(ServerLevel level,float density){
        double facing=Math.toRadians(getYRot());
        var origin=mouth().add(-Math.sin(facing)*BREATH_FORWARD_OFFSET,BREATH_VERTICAL_OFFSET,Math.cos(facing)*BREATH_FORWARD_OFFSET);
        int count=Math.round(12*density);
        for(int i=0;i<count;i++){double angle=Math.toRadians(getYRot()+(random.nextDouble()-.5)*120);var end=position().add(-Math.sin(angle)*BREATH_RADIUS,-BREATH_BELOW+random.nextDouble()*(BREATH_BELOW+SECTOR_HEIGHT),Math.cos(angle)*BREATH_RADIUS);var v=end.subtract(origin).normalize();
            level.sendParticles(ParticleTypes.FLAME,origin.x,origin.y,origin.z,0,v.x,v.y,v.z,1.0);}
    }
    private void roarParticles(ServerLevel level){
        // Evenly distribute a single burst over a horizontal disk; use explicit upward velocity.
        double rotation=random.nextDouble()*Math.PI*2;
        for(int i=0;i<ROAR_PARTICLE_COUNT;i++){
            double radius=ROAR_PARTICLE_RADIUS*Math.sqrt((i+0.5)/ROAR_PARTICLE_COUNT);
            double angle=rotation+i*Math.PI*(3-Math.sqrt(5));
            level.sendParticles(ParticleTypes.RAID_OMEN,getX()+Math.cos(angle)*radius,getY()+0.15,getZ()+Math.sin(angle)*radius,
                    0,0,ROAR_PARTICLE_UP_SPEED,0,1.0);
        }
    }
    private void fireVolley(ServerLevel level){
        var origin=mouth();
        for(var player:players(level,home,AIR_TARGET_RANGE)){
            var shot=new DarkDragonFireball(DarkDragonContent.FIREBALL,level);shot.setOwner(this);shot.setPos(origin.add(0,-1,0));
            var point=com.eccraft.banshee.BansheeAim.predict(origin,player.position().add(0,1,0),velocities.getOrDefault(player.getUUID(),Vec3.ZERO),FIREBALL_SPEED);
            var d=point.subtract(origin);shot.shoot(d.x,d.y,d.z,FIREBALL_SPEED,0);
            if(level.addFreshEntity(shot))track(shot);
        }
    }
    private void summon(ServerLevel level,EntityType<? extends Mob> type,int count){
        for(int i=0;i<count;i++){var mob=type.create(level,EntitySpawnReason.MOB_SUMMONED);if(mob==null)continue;boolean placed=false;
            for(int attempt=0;attempt<96;attempt++){double a=random.nextDouble()*Math.PI*2,r=Math.sqrt(random.nextDouble())*SUMMON_RADIUS;mob.setPos(home.x+Math.cos(a)*r,home.y+.5,home.z+Math.sin(a)*r);if(level.noCollision(mob)&&!mob.getBoundingBox().intersects(getBoundingBox())){placed=true;break;}}
            if(!placed)continue;mob.finalizeSpawn(level,level.getCurrentDifficultyAt(mob.blockPosition()),EntitySpawnReason.MOB_SUMMONED,null);mob.setCanPickUpLoot(false);mob.setPersistenceRequired();mob.addTag(DarkDragonSummons.MARKER);mob.addTag(DarkDragonSummons.OWNER_PREFIX+getUUID());if(level.addFreshEntity(mob))track(mob);
        }
    }
    private void cleanup(ServerLevel level){for(UUID id:new ArrayList<>(children)){var e=level.getEntityInAnyDimension(id);if(e!=null)e.discard();}children.clear();}
    private void clear(ServerLevel level){clearing=true;function(level,"cleared");cleanup(level);bar.removeAllPlayers();discard();}
    private void function(ServerLevel level,String name){functionAt(level,name,position());}
    private void functionAt(ServerLevel level,String name,Vec3 origin){var manager=level.getServer().getFunctions();manager.get(Eccraft.id("darkdragon/"+name)).ifPresent(f->manager.execute(f,manager.getGameLoopSender().withLevel(level).withPosition(origin).withEntity(this).withSuppressedOutput()));}
    @Override public void die(DamageSource source){
        if(action==DarkDragonAction.FALLING||action==DarkDragonAction.DEATH)return;
        if(level() instanceof ServerLevel server)cleanup(server);bar.setProgress(0);super.die(source);getNavigation().stop();setDeltaMovement(Vec3.ZERO);
        boolean falling=airborne()||transitioning()||!onGround();setNoGravity(falling);setAction(falling?DarkDragonAction.FALLING:DarkDragonAction.DEATH,level().getGameTime());deathTime=0;
    }
    @Override protected void tickDeath(){
        if(!(level() instanceof ServerLevel server))return;
        if(action==DarkDragonAction.FALLING){
            setDeltaMovement(getDeltaMovement().add(0,-.08,0).scale(.98));super.move(MoverType.SELF,getDeltaMovement());
            if(onGround()||getY()<level().getMinY()-64){setDeltaMovement(Vec3.ZERO);setNoGravity(true);entityData.set(AIR,false);setAction(DarkDragonAction.DEATH,level().getGameTime());deathTime=0;}
            return;
        }
        if(++deathTime>=DarkDragonAction.DEATH.ticks){if(!victory){victory=true;function(server,"defeated");}bar.removeAllPlayers();server.broadcastEntityEvent(this,(byte)60);remove(RemovalReason.KILLED);}
    }
    @Override public void travel(Vec3 input){if(isDeadOrDying())return;if(airborne()||transitioning()){setDeltaMovement(Vec3.ZERO);return;}setDeltaMovement(0,getDeltaMovement().y,0);super.travel(Vec3.ZERO);}
    @Override public void move(MoverType type,Vec3 delta){if(airborne()||transitioning()||isDeadOrDying())return;super.move(type,new Vec3(0,delta.y,0));}
    @Override public boolean isPushable(){return false;}
    @Override public void push(Entity e){}
    @Override public void push(double x,double y,double z){}
    @Override public boolean isPushedByFluid(){return false;}
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction(){return net.minecraft.world.level.material.PushReaction.IGNORE;}
    @Override public void knockback(double strength,double x,double z,DamageSource source,float damage,boolean force){}
    @Override protected boolean canRide(Entity vehicle){return false;}
    @Override protected boolean shouldDropLoot(ServerLevel level){return !clearing&&super.shouldDropLoot(level);}
    @Override public void startSeenByPlayer(ServerPlayer p){super.startSeenByPlayer(p);if(!victory)bar.addPlayer(p);}
    @Override public void stopSeenByPlayer(ServerPlayer p){super.stopSeenByPlayer(p);bar.removePlayer(p);}
    @Override protected void addAdditionalSaveData(ValueOutput out){
        super.addAdditionalSaveData(out);out.putString("DragonAction",action.name());out.putLong("DragonStart",actionStart());out.putBoolean("DragonAir",airborne());out.putInt("DragonPhase",phase);out.putInt("DragonStateTicks",stateTicks);out.putInt("DragonAbsent",absentTicks);out.putBoolean("DragonEffect",effected);out.putBoolean("DragonRecorded",recorded);out.putBoolean("DragonSpawned",spawned);out.putInt("DragonDeathTicks",deathTime);
        out.putInt("DragonCinematicTicks",cinematicTicks);out.putDouble("DragonCinematicAngle",cinematicAngle);out.putBoolean("DragonVictory",victory);
        out.putDouble("DragonX",home.x);out.putDouble("DragonY",home.y);out.putDouble("DragonZ",home.z);out.putDouble("DragonTX",transitionStart.x);out.putDouble("DragonTY",transitionStart.y);out.putDouble("DragonTZ",transitionStart.z);
        out.putLong("DragonSkill1",skill1Ready);out.putLong("DragonSkill2",skill2Ready);out.putLong("DragonSkill3",skill3Ready);out.putLong("DragonSkill4",skill4Ready);
        if(target!=null)out.putString("DragonTarget",target.toString());out.putString("DragonChildren",children.stream().map(UUID::toString).reduce((a,b)->a+","+b).orElse(""));
    }
    @Override protected void readAdditionalSaveData(ValueInput in){
        super.readAdditionalSaveData(in);action=DarkDragonAction.parse(in.getStringOr("DragonAction","IDLE"));entityData.set(ACTION,action.name());entityData.set(START,in.getLongOr("DragonStart",level().getGameTime()));entityData.set(AIR,in.getBooleanOr("DragonAir",false));phase=Math.clamp(in.getIntOr("DragonPhase",1),1,3);stateTicks=in.getIntOr("DragonStateTicks",0);absentTicks=in.getIntOr("DragonAbsent",0);effected=in.getBooleanOr("DragonEffect",false);recorded=in.getBooleanOr("DragonRecorded",false);spawned=in.getBooleanOr("DragonSpawned",false);deathTime=in.getIntOr("DragonDeathTicks",0);
        cinematicTicks=in.getIntOr("DragonCinematicTicks",0);cinematicAngle=in.getDoubleOr("DragonCinematicAngle",0);victory=in.getBooleanOr("DragonVictory",false);
        home=new Vec3(in.getDoubleOr("DragonX",getX()),in.getDoubleOr("DragonY",getY()),in.getDoubleOr("DragonZ",getZ()));transitionStart=new Vec3(in.getDoubleOr("DragonTX",getX()),in.getDoubleOr("DragonTY",getY()),in.getDoubleOr("DragonTZ",getZ()));
        skill1Ready=in.getLongOr("DragonSkill1",0);skill2Ready=in.getLongOr("DragonSkill2",0);skill3Ready=in.getLongOr("DragonSkill3",0);skill4Ready=in.getLongOr("DragonSkill4",0);String id=in.getStringOr("DragonTarget","");if(!id.isBlank())target=UUID.fromString(id);String list=in.getStringOr("DragonChildren","");if(!list.isBlank())for(String child:list.split(","))children.add(UUID.fromString(child));setNoGravity(airborne()||transitioning()||isDeadOrDying());
    }
}
