package com.eccraft.mixin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Reject matching damage before health, armor durability and hurt reactions are processed. */
@Mixin(LivingEntity.class)
public abstract class BossDamageImmunityMixin {
    @Unique private static final TagKey<EntityType<?>> eccraft$damageImmuneBosses = TagKey.create(
            Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("eccraft", "bosses"));
    @Unique private static final TagKey<DamageType> eccraft$bossImmuneDamageTypes = TagKey.create(
            Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("eccraft", "boss_immune_damage_types"));

    @Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
    private void eccraft$rejectBossDamage(ServerLevel level, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.is(eccraft$damageImmuneBosses) && source.is(eccraft$bossImmuneDamageTypes)) {
            cir.setReturnValue(false);
        }
    }
}
