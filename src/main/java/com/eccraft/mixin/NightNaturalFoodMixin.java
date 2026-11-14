package com.eccraft.mixin;
import com.eccraft.nightgod.NightCorrosion;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(FoodData.class)
public abstract class NightNaturalFoodMixin {
    @WrapOperation(method="tick",at=@At(value="INVOKE",target="Lnet/minecraft/server/level/ServerPlayer;heal(F)V"))
    private void eccraft$naturalFood(ServerPlayer player,float amount,Operation<Void> original){
        var previous=NightCorrosion.enterNatural(player);try{original.call(player,amount);}finally{NightCorrosion.restoreNatural(previous);}
    }
}
