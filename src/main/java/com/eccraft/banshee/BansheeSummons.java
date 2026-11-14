package com.eccraft.banshee;

import com.eccraft.entity.BansheeEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import java.util.UUID;

public final class BansheeSummons {
    public static final String MARKER = "eccraft.banshee_summon";
    public static final String OWNER_PREFIX = "eccraft.banshee_owner:";
    private BansheeSummons() {}
    public static boolean isSummon(LivingEntity entity) { return entity.entityTags().contains(MARKER); }
    public static void checkOwner(LivingEntity entity) {
        if (!isSummon(entity) || !(entity.level() instanceof ServerLevel level) || entity.tickCount % 20 != 0) return;
        for (String tag : entity.entityTags()) {
            if (!tag.startsWith(OWNER_PREFIX)) continue;
            try {
                var owner = level.getEntity(UUID.fromString(tag.substring(OWNER_PREFIX.length())));
                if (!(owner instanceof BansheeEntity) || !owner.isAlive()) entity.discard();
            } catch (IllegalArgumentException ignored) { entity.discard(); }
            return;
        }
        entity.discard();
    }
}
