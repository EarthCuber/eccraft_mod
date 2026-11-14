package com.eccraft.mixin;
import com.eccraft.combat.TargetPreference;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.PlayerSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(PlayerSensor.class)
public abstract class TargetPreferenceSensorMixin {
 @Inject(method="doTick",at=@At("TAIL"))
 private void eccraft$prefer(ServerLevel world,LivingEntity source,CallbackInfo ci){
  if(!(source instanceof Mob mob)||!(source instanceof Enemy)||mob.getTarget()!=null)return;
  var brain=source.getBrain();if(brain.checkMemory(MemoryModuleType.ATTACK_TARGET,net.minecraft.world.entity.ai.memory.MemoryStatus.VALUE_PRESENT))return;var candidates=brain.getMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYERS).orElse(java.util.List.of());
  if(candidates.size()<2)return;var sorted=TargetPreference.sorted(source,candidates);
  brain.setMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYERS,sorted);
  brain.setMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER,sorted.getFirst());
 }
}
