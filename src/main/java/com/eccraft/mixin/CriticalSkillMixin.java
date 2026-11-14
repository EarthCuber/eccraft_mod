package com.eccraft.mixin;
import com.eccraft.combat.CriticalCombat;
import com.eccraft.staff.StaffSystem;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
@Mixin(StaffSystem.class)
public abstract class CriticalSkillMixin {
 @WrapMethod(method="cast",remap=false)
 private static boolean eccraft$skill(ServerPlayer player,Operation<Boolean> original){var old=CriticalCombat.begin(player);try{return original.call(player);}finally{CriticalCombat.restore(old);}}
}
