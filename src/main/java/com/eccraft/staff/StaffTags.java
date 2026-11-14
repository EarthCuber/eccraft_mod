package com.eccraft.staff;

import com.eccraft.Eccraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

/** Data packs decide which offhand items are valid casting tomes. */
public final class StaffTags {
    public static final TagKey<Item> TOMES = TagKey.create(Registries.ITEM, Eccraft.id("staff_tomes"));
    public static boolean hasTome(LivingEntity user) { return user.getOffhandItem().is(TOMES); }
    private StaffTags() {}
}
