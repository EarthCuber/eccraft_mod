package com.eccraft.sound;

import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;

/** Sound events and call sites for the Banshee. No OGG is bundled. */
public final class BansheeSounds {
    public static final SoundEvent ATTACK = register("entity.banshee.attack");
    public static final SoundEvent SKILL_1 = register("entity.banshee.skill_1");
    public static final SoundEvent SKILL_2 = register("entity.banshee.skill_2");
    public static final SoundEvent DEATH = register("entity.banshee.death");

    private BansheeSounds() {}

    private static SoundEvent register(String path) {
        Identifier id = Eccraft.id(path);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    /** Call once when the ordinary projectile is fired. */
    public static void onAttackHit(LivingEntity boss) { boss.playSound(ATTACK, 1.0F, 1.0F); }

    /** Call once when the skill-1 projectile is fired. */
    public static void onSkill1Damage(LivingEntity boss) { boss.playSound(SKILL_1, 1.0F, 1.0F); }

    /** Call once when skill 2 summons its mobs. */
    public static void onSkill2Volley(LivingEntity boss) { boss.playSound(SKILL_2, 1.0F, 1.0F); }

    /** Return from the boss entity's getDeathSound override. */
    public static SoundEvent deathSound() { return DEATH; }

    public static void register() { /* Access static fields to register events at mod startup. */ }
}
