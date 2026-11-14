package com.eccraft.mixin;
import com.eccraft.combat.CriticalCombat;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
@Mixin(Player.class)
public abstract class CriticalMeleeMixin {
 @WrapMethod(method="attack")
 private void eccraft$attack(Entity target,Operation<Void> original){var old=CriticalCombat.begin((Player)(Object)this);try{original.call(target);}finally{CriticalCombat.restore(old);}}
 @ModifyConstant(method="attack",constant=@Constant(floatValue=1.5F))
 private float eccraft$nonStacking(float vanilla){var a=CriticalCombat.current();if(a==null)return vanilla;a.vanilla=true;return a.critical?1F:vanilla;}
}
