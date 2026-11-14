package com.eccraft.mixin;
import com.eccraft.archer.ArcherArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.damagesource.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(AbstractArrow.class)
public abstract class ArcherArrowDamageMixin {
    @Redirect(method="onHitEntity",at=@At(value="INVOKE",target="Lnet/minecraft/world/damagesource/DamageSources;arrow(Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource eccraft$arrowSource(DamageSources sources,AbstractArrow arrow,Entity owner){
        return arrow instanceof ArcherArrow custom?custom.shotSource():sources.arrow(arrow,owner);
    }
    @ModifyArg(method="onHitEntity",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/Entity;hurtOrSimulate(Lnet/minecraft/world/damagesource/DamageSource;F)Z"),index=1)
    private float eccraft$fixedArrowDamage(float vanilla){return (Object)this instanceof ArcherArrow arrow?arrow.impactDamage():vanilla;}
}
