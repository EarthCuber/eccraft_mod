package com.eccraft.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
public final class NightDeathEntity extends NightMinionEntity {
    public NightDeathEntity(EntityType<? extends NightDeathEntity> type,Level level){super(type,level);}
    @Override public boolean isReaper(){return true;}
    @Override public boolean isPushable(){return false;}
    @Override public void push(net.minecraft.world.entity.Entity entity){}
    @Override public void push(double x,double y,double z){}
    @Override public boolean isPushedByFluid(){return false;}
    @Override public net.minecraft.world.level.material.PushReaction getPistonPushReaction(){return net.minecraft.world.level.material.PushReaction.IGNORE;}
}
