package com.eccraft.nightgod;
import com.eccraft.effect.NightCorrosionEffect;
import net.minecraft.world.entity.LivingEntity;
public final class NightCorrosion {
    private static final ThreadLocal<LivingEntity> NATURAL=new ThreadLocal<>();
    public static LivingEntity enterNatural(LivingEntity target){var previous=NATURAL.get();NATURAL.set(target);return previous;}
    public static void restoreNatural(LivingEntity previous){if(previous==null)NATURAL.remove();else NATURAL.set(previous);}
    public static boolean converts(LivingEntity target){return NATURAL.get()!=target&&target.hasEffect(NightCorrosionEffect.NIGHT_CORROSION);}
    private NightCorrosion(){}
}
