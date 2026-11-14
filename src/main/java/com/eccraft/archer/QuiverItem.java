package com.eccraft.archer;
import net.minecraft.world.item.Item;
public final class QuiverItem extends Item {
    private final int tier;
    public QuiverItem(Properties properties,int tier){super(properties);this.tier=tier;}
    public int tier(){return tier;}
}
