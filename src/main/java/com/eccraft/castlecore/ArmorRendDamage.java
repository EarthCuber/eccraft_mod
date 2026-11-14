package com.eccraft.castlecore;

import com.eccraft.effect.ArmorRendEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;

/** Called only from actuallyHurt, after ordinary immunity and invulnerability-frame checks. */
public final class ArmorRendDamage {
    public static boolean active(LivingEntity target){return target.hasEffect(ArmorRendEffect.ARMOR_REND);}
    public static float doubled(float amount){
        if(amount<=0)return 0;
        return Float.isFinite(amount)?(float)Math.min(Float.MAX_VALUE,(double)amount*2):Float.MAX_VALUE;
    }
    public static void apply(ServerLevel level,LivingEntity target,DamageSource source,float amount){
        if(target.isInvulnerableTo(level,source))return;
        float damage=doubled(amount);
        if(damage==0)return;
        if(target instanceof Player player)player.causeFoodExhaustion(source.getFoodExhaustion());
        target.getCombatTracker().recordDamage(source,damage);
        target.setHealth(target.getHealth()-damage);
        if(target instanceof Player player && damage<3.4028235E37F)
            player.awardStat(Stats.DAMAGE_TAKEN,Math.round(damage*10));
        target.gameEvent(GameEvent.ENTITY_DAMAGE);
        // Do not touch absorption. hurtServer still owns totems, death, attribution and hurt callbacks.
    }
    private ArmorRendDamage(){}
}
