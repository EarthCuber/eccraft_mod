package com.eccraft.sound;

import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public final class ModSoundEvents {
    public static final SoundEvent ANUBIS_SUMMON_FLAME = register("entity.anubis.summon_flame");
    public static final SoundEvent ANUBIS_EXPLOSION = register("entity.anubis.explosion");
    public static final SoundEvent ANUBIS_SUMMON_GUARD = register("entity.anubis.summon_guard");
    public static final SoundEvent ANUBIS_DEATH = register("entity.anubis.death");

    public static final SoundEvent STAFF_ATTACK = register("item.staff.shoot");

    private ModSoundEvents() {}

    public static final SoundEvent ICEWITCH_SKILL_1 = register("entity.icewitch.skill_1");
    public static final SoundEvent ICEWITCH_SKILL_2 = register("entity.icewitch.skill_2");
    public static final SoundEvent ICEWITCH_SKILL_3 = register("entity.icewitch.skill_3");
    public static final SoundEvent ICEWITCH_DEATH = register("entity.icewitch.death");

    private static SoundEvent register(String path) {
        Identifier id = Eccraft.id(path);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void register() {
        Eccraft.LOGGER.info("Registered Anubis sound events");
    }
}
