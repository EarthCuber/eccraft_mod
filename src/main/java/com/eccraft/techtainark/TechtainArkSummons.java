package com.eccraft.techtainark;

import com.eccraft.entity.TechtainArkEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import java.util.UUID;

public final class TechtainArkSummons {
    public static final String MARKER = "eccraft.techtain_ark_summon";
    public static final String OWNER_PREFIX = "eccraft.techtain_ark_owner:";
    private TechtainArkSummons() {}
    public static boolean isSummon(LivingEntity entity) { return entity.entityTags().contains(MARKER); }
    public static void checkOwner(LivingEntity entity) {
        if (!isSummon(entity) || !(entity.level() instanceof ServerLevel level) || entity.tickCount % 20 != 0) return;
        for (String tag : entity.entityTags()) {
            if (!tag.startsWith(OWNER_PREFIX)) continue;
            try {
                var owner = level.getEntityInAnyDimension(UUID.fromString(tag.substring(OWNER_PREFIX.length())));
                if (!(owner instanceof TechtainArkEntity) || !owner.isAlive()) entity.discard();
            } catch (IllegalArgumentException ignored) { entity.discard(); }
            return;
        }
        entity.discard();
    }
}
