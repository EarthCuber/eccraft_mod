package com.eccraft.mixin;
import org.spongepowered.asm.mixin.Mixin;import org.spongepowered.asm.mixin.injection.*;import net.minecraft.world.entity.Entity;import net.minecraft.world.phys.Vec3;
@Mixin(Entity.class) public abstract class DizzinessMovementMixin{
 @ModifyVariable(method="move",at=@At("HEAD"),argsOnly=true) private Vec3 eccraft$stunMovement(Vec3 delta){Entity self=(Entity)(Object)this;if(com.eccraft.effect.DizzinessEffect.active(self)){self.setDeltaMovement(0,Math.min(0,self.getDeltaMovement().y),0);return new Vec3(0,Math.min(0,delta.y),0);}return delta;}
}
