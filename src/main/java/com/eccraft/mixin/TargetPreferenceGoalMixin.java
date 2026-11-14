package com.eccraft.mixin;
import com.eccraft.combat.TargetPreference;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
@Mixin(NearestAttackableTargetGoal.class)
public abstract class TargetPreferenceGoalMixin {
 @Redirect(method="findTarget",at=@At(value="INVOKE",target="Lnet/minecraft/server/level/ServerLevel;getNearestPlayer(Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;Lnet/minecraft/world/entity/LivingEntity;DDD)Lnet/minecraft/world/entity/player/Player;"))
 private Player eccraft$prefer(ServerLevel world,TargetingConditions conditions,LivingEntity source,double x,double y,double z){return TargetPreference.find(world,conditions,source,x,y,z,world.getNearestPlayer(conditions,source,x,y,z));}
}
