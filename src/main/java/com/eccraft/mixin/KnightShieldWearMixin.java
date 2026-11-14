package com.eccraft.mixin;
import com.eccraft.knight.KnightShieldItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Player.class)
public abstract class KnightShieldWearMixin {
    // Only the actual health subtraction path reaches setHealth; absorbed/zero damage does not.
    @ModifyArg(method="actuallyHurt",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/player/Player;setHealth(F)V"),index=0)
    private float eccraft$wear(float newHealth){
        Player player=(Player)(Object)this;
        if(newHealth<player.getHealth()&&player.getOffhandItem().getItem() instanceof KnightShieldItem)
            player.getOffhandItem().hurtAndBreak(1,player,EquipmentSlot.OFFHAND);
        return newHealth;
    }
}
