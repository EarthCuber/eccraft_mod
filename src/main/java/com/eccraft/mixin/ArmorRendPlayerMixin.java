package com.eccraft.mixin;

import com.eccraft.castlecore.ArmorRendDamage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Player overrides actuallyHurt, so it needs its own hook as well. */
@Mixin(Player.class)
public abstract class ArmorRendPlayerMixin {
    @Inject(method="actuallyHurt",at=@At("HEAD"),cancellable=true)
    private void eccraft$rendPlayerHealth(ServerLevel level,DamageSource source,float damage,CallbackInfo ci){
        var target=(Player)(Object)this;
        if(ArmorRendDamage.active(target)){ArmorRendDamage.apply(level,target,source,damage);ci.cancel();}
    }
}
