package com.eccraft.sound;
import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
public final class TechtainArkSounds {
public static final SoundEvent ATTACK=event("attack");
public static final SoundEvent SKILL_1=event("skill_1");
public static final SoundEvent SKILL_2=event("skill_2");
public static final SoundEvent SKILL_3=event("skill_3");
public static final SoundEvent STUN=event("stun");
public static final SoundEvent RECOVER=event("recover");
public static final SoundEvent DEATH=event("death");
public static final SoundEvent COASTER_DASH=event("coaster_dash");
public static final SoundEvent BOX_COUNTDOWN=event("box_countdown");
private static SoundEvent event(String name){var id=Eccraft.id("entity.techtain_ark."+name);return Registry.register(BuiltInRegistries.SOUND_EVENT,id,SoundEvent.createVariableRangeEvent(id));}
public static void action(LivingEntity entity,String previous,String next){
 SoundEvent event=switch(next){case "attack"->ATTACK;case "skill1"->SKILL_1;case "skill2"->SKILL_2;case "dash"->SKILL_3;case "stun"->STUN;case "dead"->DEATH;case "idle"->previous.equals("stun")?RECOVER:null;default->null;};
 if(event!=null&&!entity.level().isClientSide())entity.playSound(event,1,1);
}
public static void register(){}
private TechtainArkSounds(){}
}