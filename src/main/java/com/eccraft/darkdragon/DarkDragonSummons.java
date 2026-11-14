package com.eccraft.darkdragon;

import com.eccraft.entity.DarkDragonEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import java.util.UUID;

public final class DarkDragonSummons {
    public static final String MARKER = "eccraft.darkdragon_summon";
    public static final String OWNER_PREFIX = "eccraft.darkdragon_owner:";
    private DarkDragonSummons() {}
    public static boolean isSummon(LivingEntity entity) { return entity.entityTags().contains(MARKER); }
    public static void checkOwner(LivingEntity entity) {
        if (!isSummon(entity) || !(entity.level() instanceof ServerLevel level) || entity.tickCount % 20 != 0) return;
        for (String tag : entity.entityTags()) {
            if (!tag.startsWith(OWNER_PREFIX)) continue;
            try {
                var owner = level.getEntity(UUID.fromString(tag.substring(OWNER_PREFIX.length())));
                if (!(owner instanceof DarkDragonEntity) || !owner.isAlive()) entity.discard();
            } catch (IllegalArgumentException ignored) { entity.discard(); }
            return;
        }
        entity.discard();
    }
}
