package com.eccraft.mixin;

import com.eccraft.Eccraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Only the custom type changes its wording; retain vanilla magic and all other types. */
@Mixin(DamageSource.class)
public abstract class MagicDeathMessageMixin {
    @Inject(method="getLocalizedDeathMessage",at=@At("HEAD"),cancellable=true)
    private void eccraft$magicSource(LivingEntity victim,CallbackInfoReturnable<Component> cir){
        DamageSource source=(DamageSource)(Object)this;
        if(!source.is(ResourceKey.create(Registries.DAMAGE_TYPE,Eccraft.id("magic"))))return;
        Entity attacker=source.getEntity();
        if(attacker==null)attacker=source.getDirectEntity();
        if(attacker==null)attacker=victim.getKillCredit();
        cir.setReturnValue(attacker==null
                ?Component.translatable("death.attack.eccraft.magic",victim.getDisplayName())
                :Component.translatable("death.attack.eccraft.magic.player",victim.getDisplayName(),attacker.getDisplayName()));
    }
}
