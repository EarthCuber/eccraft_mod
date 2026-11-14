package com.eccraft.mixin;

import com.eccraft.death.DimensionKeepInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class DimensionPlayerInventoryMixin {
    @Redirect(method = {"dropEquipment", "getBaseExperienceReward"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/gamerules/GameRules;get(Lnet/minecraft/world/level/gamerules/GameRule;)Ljava/lang/Object;"))
    private Object eccraft$keepOnDeath(GameRules rules, GameRule<?> rule) {
        Player player = (Player)(Object)this;
        if (rule == GameRules.KEEP_INVENTORY && DimensionKeepInventory.protects(player.level())) return Boolean.TRUE;
        return rules.get(rule);
    }
}
