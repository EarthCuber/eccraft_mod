package com.eccraft.darkdragon;

import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public final class DarkDragonSounds {
    public static final SoundEvent ATTACK = sound("attack"), ROAR = sound("roar"), NORMAL = sound("normal"), FLY = sound("fly");
    public static final SoundEvent FIRE = sound("fire");
    public static final float SKILL_1_VOLUME = 1.25F;
    private DarkDragonSounds() {}
    private static SoundEvent sound(String name) {
        var id=Eccraft.id("entity.darkdragon."+name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT,id,SoundEvent.createFixedRangeEvent(id,64));
    }
    public static void register() {}
}
