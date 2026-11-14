package com.eccraft.mixin;
import com.eccraft.combat.CriticalCarrier;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
@Mixin(AbstractArrow.class)
public abstract class CriticalArrowMixin {
 @Redirect(method="onHitEntity",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;isCritArrow()Z"))
 private boolean eccraft$noRandomStack(AbstractArrow arrow){return !((CriticalCarrier)arrow).eccraft$isCritical()&&arrow.isCritArrow();}
}
