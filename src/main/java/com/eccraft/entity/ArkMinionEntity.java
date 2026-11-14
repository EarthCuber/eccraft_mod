package com.eccraft.entity;

import com.eccraft.techtainark.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.phys.Vec3;
import java.util.*;

/** Three registered minions share persistence and combat; vanilla AI cannot merge them into blocks or despawn them. */
public final class ArkMinionEntity extends Monster {
    public static final int BOX_FUSE=200,CHARGE_TICKS=20,SPRINT_TICKS=16,SPRINT_CD=80,STUN_TICKS=200,STUN_CD=280,COASTER_CD=60;
    private static final net.minecraft.network.syncher.EntityDataAccessor<String> VISUAL=net.minecraft.network.syncher.SynchedEntityData.defineId(ArkMinionEntity.class,net.minecraft.network.syncher.EntityDataSerializers.STRING);
    private static final net.minecraft.network.syncher.EntityDataAccessor<Long> VISUAL_START=net.minecraft.network.syncher.SynchedEntityData.defineId(ArkMinionEntity.class,net.minecraft.network.syncher.EntityDataSerializers.LONG);
    private boolean exploded;
    private boolean bossInteractionUsed;
    @Override protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(VISUAL,"idle");b.define(VISUAL_START,0L);}
    public String visualAction(){return entityData.get(VISUAL);} public long visualStart(){return entityData.get(VISUAL_START);}
    private void visual(String s){if(!s.equals(visualAction())||visualStart()==0){entityData.set(VISUAL,s);entityData.set(VISUAL_START,level().getGameTime());}}
    private int age,mode; // 0 idle, 1 charge, 2 sprint, 3 stun
    private long start,next;
    private Vec3 dash=Vec3.ZERO;
    public ArkMinionEntity(EntityType<? extends ArkMinionEntity> type,Level level){super(type,level);xpReward=0;setPersistenceRequired();}
    public boolean box(){return getType()==TechtainArkContent.BOX;}
    public boolean sprinter(){return getType()==TechtainArkContent.SPRINTER;}
    public static AttributeSupplier.Builder attributes(int kind){return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,kind==0?30:kind==1?20:40).add(Attributes.ARMOR,0).add(Attributes.ATTACK_DAMAGE,kind==1?8:18).add(Attributes.MOVEMENT_SPEED,kind==1?.22:0).add(Attributes.KNOCKBACK_RESISTANCE,kind==1?0:1).add(Attributes.FOLLOW_RANGE,32);}
    @Override protected void registerGoals(){}
    private TechtainArkEntity owner(ServerLevel l){for(String tag:entityTags())if(tag.startsWith(TechtainArkSummons.OWNER_PREFIX)){try{var e=l.getEntityInAnyDimension(UUID.fromString(tag.substring(TechtainArkSummons.OWNER_PREFIX.length())));if(e instanceof TechtainArkEntity boss&&boss.isAlive())return boss;}catch(IllegalArgumentException ignored){}}return null;}
    @Override public void tick(){setNoGravity(sprinter());if(exploded){if(level() instanceof ServerLevel&&level().getGameTime()-visualStart()>=19)discard();return;}super.tick();if(level() instanceof ServerLevel&&isAlive()){String desired=box()?"idle":sprinter()?(mode==1?"dash_intro":mode==2?"dash_loop":"idle"):getDeltaMovement().horizontalDistanceSqr()>.00001?"dash_loop":"idle";if(sprinter()&&visualAction().equals("dash_loop")&&!desired.equals("dash_loop"))visual("dash_end");else if(!visualAction().equals("dash_end")||level().getGameTime()-visualStart()>=10)visual(desired);}}
    @Override public boolean isInvulnerableTo(ServerLevel l,DamageSource s){return exploded||super.isInvulnerableTo(l,s);}
    @Override protected void tickDeath(){if(++deathTime>=(box()?19:20)&&level() instanceof ServerLevel)remove(RemovalReason.KILLED);}
    @Override protected void customServerAiStep(ServerLevel l){
        super.customServerAiStep(l);if(!isAlive())return;long now=l.getGameTime();age++;
        if(box()){
            if(age==100)playSound(com.eccraft.sound.TechtainArkSounds.BOX_COUNTDOWN,1,1);
            if(age>=BOX_FUSE){var damage=com.eccraft.firelord.FirelordDamage.magic(l,this,this);for(var p:ArkCombat.players(l,this,24))p.hurtServer(l,damage,(float)getAttributeValue(Attributes.ATTACK_DAMAGE));exploded=true;visual("dead");getNavigation().stop();setDeltaMovement(Vec3.ZERO);}return;
        }
        if(sprinter()){
            if(mode==3){if(now-start>=STUN_TICKS)mode=0;return;}
            if(mode==1){if(now-start>=CHARGE_TICKS){var p=ArkCombat.players(l,this,32).stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);if(p==null){mode=0;return;}dash=ArkMath.dash(position(),p.position(),Vec3.ZERO,1,false);ArkCombat.face(this,dash);mode=2;start=now;}return;}
            if(mode==2){
                if(now-start>SPRINT_TICKS){mode=0;return;}
                Vec3 from=position();var before=getBoundingBox();super.move(MoverType.SELF,dash);Vec3 to=position();var boss=owner(l);
                var hits=ArkCombat.hits(l,this,before,from,to,bossInteractionUsed?null:boss);
                if(!hits.isEmpty()){
                    var victim=hits.getFirst();
                    if(victim instanceof ServerPlayer p){p.hurtServer(l,com.eccraft.damage.BossDamageSources.melee(l,this),(float)getAttributeValue(Attributes.ATTACK_DAMAGE));ArkCombat.launch(p,dash);ArkCombat.shuffle(p);}
                    else if(victim==boss){bossInteractionUsed=true;boss.paralyze();boss.hurtServer(l,com.eccraft.damage.BossDamageSources.melee(l,this),(float)getAttributeValue(Attributes.ATTACK_DAMAGE));}
                    mode=3;start=now;next=now+STUN_CD;dash=Vec3.ZERO;setDeltaMovement(Vec3.ZERO);addEffect(new MobEffectInstance(MobEffects.GLOWING,STUN_TICKS,0));return;
                }
                if(horizontalCollision||to.subtract(from).horizontalDistanceSqr()<dash.horizontalDistanceSqr()-.0001)mode=0;return;
            }
            if(now>=next&&!ArkCombat.players(l,this,32).isEmpty()){mode=1;start=now;next=now+SPRINT_CD;playSound(com.eccraft.sound.TechtainArkSounds.COASTER_DASH,1,1);}
            return;
        }
        var p=ArkCombat.players(l,this,32).stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
        if(p==null){getNavigation().stop();return;}
        setTarget(p);ArkCombat.face(this,p.position().subtract(position()));
        if(distanceToSqr(p)<=1.5*1.5){getNavigation().stop();if(now>=next){next=now+COASTER_CD;if(distanceToSqr(p)<=1.5*1.5){p.hurtServer(l,com.eccraft.damage.BossDamageSources.melee(l,this),(float)getAttributeValue(Attributes.ATTACK_DAMAGE));ArkCombat.shuffle(p);}}}
        else getNavigation().moveTo(p,1);
    }
    @Override public void travel(Vec3 input){if(box()||sprinter()){getNavigation().stop();setDeltaMovement(0,sprinter()?0:getDeltaMovement().y,0);if(!sprinter())super.travel(Vec3.ZERO);}else super.travel(input);}
    @Override public void move(MoverType type,Vec3 d){super.move(type,box()?new Vec3(0,d.y,0):sprinter()?Vec3.ZERO:d);}
    @Override public boolean isPushable(){return !box()&&!sprinter()&&super.isPushable();}
    @Override public void push(Entity e){if(!box()&&!sprinter())super.push(e);}
    @Override public void push(double x,double y,double z){if(!box()&&!sprinter())super.push(x,y,z);}
    @Override public boolean isPushedByFluid(){return !box()&&!sprinter();}
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction(){return box()||sprinter()?net.minecraft.world.level.material.PushReaction.IGNORE:super.getPistonPushReaction();}
    @Override public void knockback(double s,double x,double z,DamageSource d,float a,boolean force){if(!box()&&!sprinter())super.knockback(s,x,z,d,a,force);}
    @Override protected boolean canRide(Entity e){return false;}
    @Override protected void dropAllDeathLoot(ServerLevel l,DamageSource d){}
    @Override public void die(DamageSource source){
        if(!isAlive()&&deathTime>0)return;boolean surprise=box()&&age<BOX_FUSE&&!dead;super.die(source);visual("dead");
        if(surprise&&level() instanceof ServerLevel l){
            EntityType<? extends Mob> type=switch(random.nextInt(5)){case 0->TechtainArkContent.COASTER;case 1->EntityTypes.VINDICATOR;case 2->EntityTypes.PILLAGER;case 3->EntityTypes.BREEZE;default->EntityTypes.PIGLIN_BRUTE;};
            var mob=type.create(l,EntitySpawnReason.MOB_SUMMONED);if(mob!=null){mob.setPos(position());mob.finalizeSpawn(l,l.getCurrentDifficultyAt(blockPosition()),EntitySpawnReason.MOB_SUMMONED,null);var boss=owner(l);if(boss!=null)boss.bind(mob);l.addFreshEntity(mob);}
        }
    }
    @Override protected void addAdditionalSaveData(ValueOutput o){super.addAdditionalSaveData(o);o.putBoolean("ArkBossInteractionUsed",bossInteractionUsed);o.putBoolean("ArkExploded",exploded);o.putString("ArkVisual",visualAction());o.putLong("ArkVisualStart",visualStart());o.putInt("ArkAge",age);o.putInt("ArkMode",mode);o.putLong("ArkStart",start);o.putLong("ArkNext",next);o.putDouble("ArkDX",dash.x);o.putDouble("ArkDZ",dash.z);}
    @Override protected void readAdditionalSaveData(ValueInput i){super.readAdditionalSaveData(i);bossInteractionUsed=i.getBooleanOr("ArkBossInteractionUsed",false);exploded=i.getBooleanOr("ArkExploded",false);entityData.set(VISUAL,i.getStringOr("ArkVisual","idle"));entityData.set(VISUAL_START,i.getLongOr("ArkVisualStart",0));age=i.getIntOr("ArkAge",0);mode=i.getIntOr("ArkMode",0);start=i.getLongOr("ArkStart",0);next=i.getLongOr("ArkNext",0);dash=new Vec3(i.getDoubleOr("ArkDX",0),0,i.getDoubleOr("ArkDZ",0));}
}
