package com.eccraft.mixin;
import com.eccraft.combat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.storage.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Projectile.class)
public abstract class CriticalProjectileMixin implements CriticalCarrier {
 @Unique private boolean eccraft$critical,eccraft$rolled;
 public boolean eccraft$isCritical(){return eccraft$critical;}
 @Inject(method="setOwner(Lnet/minecraft/world/entity/Entity;)V",at=@At("TAIL"))
 private void eccraft$snapshot(Entity owner,CallbackInfo ci){if(!eccraft$rolled&&owner instanceof net.minecraft.server.level.ServerPlayer p){eccraft$critical=CriticalCombat.roll(p);eccraft$rolled=true;}}
 @Inject(method="addAdditionalSaveData",at=@At("TAIL")) private void eccraft$save(ValueOutput out,CallbackInfo ci){out.putBoolean("eccraft_critical",eccraft$critical);out.putBoolean("eccraft_crit_rolled",eccraft$rolled);}
 @Inject(method="readAdditionalSaveData",at=@At("TAIL")) private void eccraft$load(ValueInput in,CallbackInfo ci){eccraft$critical=in.getBooleanOr("eccraft_critical",false);eccraft$rolled=in.getBooleanOr("eccraft_crit_rolled",true);}
}
