package com.eccraft.archer;
import com.eccraft.Eccraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.network.syncher.*;

/** Native arrow flight/collision, with damage snapshotted at release. */
public final class ArcherArrow extends AbstractArrow {
    public static final ResourceKey<DamageType> DAMAGE=ResourceKey.create(Registries.DAMAGE_TYPE,Eccraft.id("arrow"));
    private static final EntityDataAccessor<Integer> TIER=SynchedEntityData.defineId(ArcherArrow.class,EntityDataSerializers.INT);
    private float shotDamage;
    private int age;
    private float explosionMultiplier;
    private boolean exploded, combineImpact;
    public void setExplosion(int rank){explosionMultiplier=(float)Math.min(Float.MAX_VALUE,com.eccraft.combat.CombatBalance.get().multiplier(rank));}
    public float impactDamage(){return combineImpact?(float)Math.min(Float.MAX_VALUE,(double)shotDamage*(1+explosionMultiplier)):shotDamage;}
    @Override public void tick(){
        if(!level().isClientSide() && ++age>=com.eccraft.combat.CombatBalance.get().arrowLifetimeTicks()){discard();return;}
        super.tick();
    }
    @Override protected void onHitEntity(net.minecraft.world.phys.EntityHitResult hit){
        Entity target=hit.getEntity();
        combineImpact=!exploded && explosionMultiplier>0 && target instanceof LivingEntity && !(target instanceof Player) && target!=getOwner();
        try{super.onHitEntity(hit);}finally{combineImpact=false;}
        explodeAt(hit.getLocation().subtract(getDeltaMovement().normalize().scale(.02)),target);
    }
    @Override protected void onHitBlock(net.minecraft.world.phys.BlockHitResult hit){
        super.onHitBlock(hit);
        var face=hit.getDirection();
        explodeAt(hit.getLocation().add(face.getStepX()*.02,face.getStepY()*.02,face.getStepZ()*.02),null);
    }
    private void explodeAt(net.minecraft.world.phys.Vec3 center,Entity direct){
        if(exploded||explosionMultiplier<=0||!(level() instanceof net.minecraft.server.level.ServerLevel world))return;
        exploded=true;
        double radius=com.eccraft.combat.CombatBalance.get().explosionRadius();
        var bounds=new net.minecraft.world.phys.AABB(center,center).inflate(radius);
        for(LivingEntity target:world.getEntitiesOfClass(LivingEntity.class,bounds)){
            if(target==direct||target==getOwner()||target instanceof Player||!target.isAlive()||target.isSpectator())continue;
            var point=target.getBoundingBox().getCenter();
            if(point.distanceToSqr(center)>radius*radius)continue;
            var ray=world.clip(new net.minecraft.world.level.ClipContext(center,point,net.minecraft.world.level.ClipContext.Block.COLLIDER,net.minecraft.world.level.ClipContext.Fluid.NONE,this));
            if(ray.getType()!=net.minecraft.world.phys.HitResult.Type.MISS)continue;
            target.hurtServer(world,shotSource(),(float)Math.min(Float.MAX_VALUE,(double)shotDamage*explosionMultiplier));
        }
        // A uniformly sampled spherical burst; visual flames do not ignite blocks or entities.
        for(int i=0;i<72;i++){
            double y=1-2*(i+.5)/72,ring=Math.sqrt(1-y*y),angle=i*2.399963229728653;
            world.sendParticles(net.minecraft.core.particles.ParticleTypes.FLAME,center.x,center.y,center.z,0,Math.cos(angle)*ring,y,Math.sin(angle)*ring,.25);
        }
        discard();
    }
    public ArcherArrow(EntityType<? extends ArcherArrow> type,Level level){super(type,level);pickup=Pickup.DISALLOWED;}
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder){super.defineSynchedData(builder);builder.define(TIER,1);}
    public int tier(){return entityData.get(TIER);}
    public void configure(int tier,float damage){entityData.set(TIER,Math.clamp(tier,1,7));shotDamage=Float.isFinite(damage)?Math.max(0,damage):0;setCritArrow(false);pickup=Pickup.DISALLOWED;}
    public float shotDamage(){return shotDamage;}
    public DamageSource shotSource(){return new DamageSource(level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DAMAGE),this,getOwner());}
    @Override protected ItemStack getDefaultPickupItem(){return new ItemStack(Items.ARROW);}
    @Override protected boolean tryPickup(Player player){return false;}
    @Override protected void addAdditionalSaveData(ValueOutput out){super.addAdditionalSaveData(out);out.putInt("eccraft_tier",tier());out.putFloat("eccraft_damage",shotDamage);out.putInt("eccraft_age",age);out.putFloat("eccraft_explosion",explosionMultiplier);out.putBoolean("eccraft_exploded",exploded);}
    @Override protected void readAdditionalSaveData(ValueInput in){super.readAdditionalSaveData(in);configure(in.getIntOr("eccraft_tier",1),in.getFloatOr("eccraft_damage",0));age=Math.max(0,in.getIntOr("eccraft_age",0));explosionMultiplier=in.getFloatOr("eccraft_explosion",0);if(!Float.isFinite(explosionMultiplier)||explosionMultiplier<0)explosionMultiplier=0;exploded=in.getBooleanOr("eccraft_exploded",false);}
}
