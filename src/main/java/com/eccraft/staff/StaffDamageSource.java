package com.eccraft.staff;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

/** Staff projectile source; defense is shared by all eccraft:magic sources. */
public final class StaffDamageSource extends DamageSource {
    public StaffDamageSource(Holder<DamageType> type, Entity direct, Entity owner) { super(type, direct, owner); }
}
