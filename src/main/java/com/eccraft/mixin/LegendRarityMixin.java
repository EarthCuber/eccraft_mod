package com.eccraft.mixin;
import com.eccraft.legend.LegendEquipment;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(ItemStack.class)
public abstract class LegendRarityMixin {
 @Inject(method="getRarity",at=@At("HEAD"),cancellable=true)
 private void eccraft$legendRarity(CallbackInfoReturnable<Rarity> cir){
  int stage=LegendEquipment.stage((ItemStack)(Object)this);if(stage>0)cir.setReturnValue(stage==4?Rarity.EPIC:Rarity.RARE);
 }
}
