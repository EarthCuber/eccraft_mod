package com.eccraft.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
public final class LittleDevilEntity extends NightMinionEntity {
    public LittleDevilEntity(EntityType<? extends LittleDevilEntity> type,Level level){super(type,level);}
    @Override public boolean isReaper(){return false;}
}
