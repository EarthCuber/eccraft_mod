package com.eccraft.mixin;

import com.eccraft.death.DimensionKeepInventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayer.class)
public abstract class DimensionPlayerRespawnMixin {
    @Redirect(method = "restoreFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/gamerules/GameRules;get(Lnet/minecraft/world/level/gamerules/GameRule;)Ljava/lang/Object;"))
    private Object eccraft$keepFromDeathDimension(GameRules rules, GameRule<?> rule, ServerPlayer oldPlayer, boolean restoreAll) {
        if (rule == GameRules.KEEP_INVENTORY && DimensionKeepInventory.protects(oldPlayer.level())) return Boolean.TRUE;
        return rules.get(rule);
    }
}
