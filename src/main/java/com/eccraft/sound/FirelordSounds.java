package com.eccraft.sound;

import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;

/** Sound events and call sites for the Firelord. No OGG is bundled. */
public final class FirelordSounds {
    public static final SoundEvent ATTACK = register("entity.firelord.attack");
    public static final SoundEvent SKILL_1 = register("entity.firelord.skill_1");
    public static final SoundEvent SKILL_2 = register("entity.firelord.skill_2");
    public static final SoundEvent SKILL_4=register("entity.firelord.skill_4");
    public static final SoundEvent SKILL_3=register("entity.firelord.skill_3");
    public static void skill(LivingEntity boss,int id){boss.playSound(switch(id){case 1->SKILL_1;case 2->SKILL_2;case 4->SKILL_4;default->SKILL_3;},1,1);}
    public static final SoundEvent DEATH = register("entity.firelord.death");

    private FirelordSounds() {}

    private static SoundEvent register(String path) {
        Identifier id = Eccraft.id(path);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    /** Call once when a melee hit succeeds, not when the attack animation starts. */
    public static void onAttackHit(LivingEntity boss) { boss.playSound(ATTACK, 1.0F, 1.0F); }

    /** Call once at the skill-1 damage tick. */
    public static void onSkill1Damage(LivingEntity boss) { boss.playSound(SKILL_1, 1.0F, 1.0F); }

    /** Call once when skill 2 creates its volley. */
    public static void onSkill2Volley(LivingEntity boss) { boss.playSound(SKILL_2, 1.0F, 1.0F); }

    /** Return from the boss entity's getDeathSound override. */
    public static SoundEvent deathSound() { return DEATH; }

    public static void register() { /* Access static fields to register events at mod startup. */ }
}
