package com.eccraft.armor;

import net.minecraft.world.item.Item;
import net.minecraft.world.entity.EquipmentSlot;

public final class KnightArmorItem extends Item {
    private final int tier;
    private final EquipmentSlot slot;
    public KnightArmorItem(Properties properties, int tier, EquipmentSlot slot) {
        super(properties); this.tier=tier; this.slot=slot;
    }
    public int tier() { return tier; }
    public EquipmentSlot slot() { return slot; }
}
