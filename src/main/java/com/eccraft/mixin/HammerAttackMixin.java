package com.eccraft.mixin;
import org.spongepowered.asm.mixin.Mixin;import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;import com.llamalad7.mixinextras.injector.wrapoperation.Operation;import net.minecraft.world.entity.player.Player;import net.minecraft.world.entity.Entity;import com.eccraft.techtainark.FlyingHammerItem;
@Mixin(Player.class) public abstract class HammerAttackMixin{
 @WrapMethod(method="attack") private void eccraft$hammer(Entity target,Operation<Void> original){boolean previous=FlyingHammerItem.FULL.get();FlyingHammerItem.FULL.set(((Player)(Object)this).getAttackStrengthScale(.5F)>=1F);try{original.call(target);}finally{FlyingHammerItem.FULL.set(previous);}}
}