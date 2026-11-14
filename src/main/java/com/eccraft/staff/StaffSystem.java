package com.eccraft.staff;

import com.eccraft.Eccraft;
import java.util.Map;
import java.util.WeakHashMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;

public final class StaffSystem {
    public static final ResourceKey<DamageType> MAGIC = ResourceKey.create(Registries.DAMAGE_TYPE, Eccraft.id("magic"));
    public static final EntityType<StaffProjectile> PROJECTILE = registerProjectile();
    private static final Identifier HELD_POWER = Eccraft.id("held_staff_power");
    private static final Map<ServerPlayer, State> STATES = new WeakHashMap<>();
    private static final class State {
        final StaffCooldown cooldown = new StaffCooldown();
        StaffRoute route = StaffRoute.NORMAL;
        int lastRemaining = -1, lastTotal = -1;
    }
    private static EntityType<StaffProjectile> registerProjectile() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("staff_projectile"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.<StaffProjectile>of(StaffProjectile::new, MobCategory.MISC)
                        .sized(0.25F, 0.25F).clientTrackingRange(8).updateInterval(1).noLootTable().build(key));
    }
    public static void register() {
        StaffBalance.initialize();
        StaffParticles.register();
        StaffAttributes.register();
        StaffItems.register();
        PayloadTypeRegistry.clientboundPlay().register(StaffCooldownPayload.TYPE, StaffCooldownPayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(StaffSkillEffectPayload.TYPE, StaffSkillEffectPayload.CODEC);
        ServerLifecycleEvents.SERVER_STARTING.register(server -> StaffMobResistance.initialize());
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof Mob mob) StaffMobResistance.apply(mob);
        });
        PayloadTypeRegistry.serverboundPlay().register(StaffSkillPayload.TYPE, StaffSkillPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(StaffSkillPayload.TYPE, (payload, context) -> { if (com.eccraft.combat.CombatSkills.supported(context.player().getMainHandItem())) com.eccraft.combat.CombatSkills.cast(context.player()); else cast(context.player()); });
        ServerTickEvents.END_SERVER_TICK.register(server -> server.getPlayerList().getPlayers().forEach(StaffSystem::refresh));
    }
    public static void refresh(ServerPlayer player) {
        State state = STATES.computeIfAbsent(player, p -> new State());
        ItemStack held = player.getMainHandItem();
        StaffRoute route = StaffEnchantments.route(player.level(), held);
        int slot = player.getInventory().getSelectedSlot();
        state.cooldown.observe(held, slot, held.getItem() instanceof StaffItem ? route : null, now(player));
        state.route = route;
        if (!com.eccraft.combat.CombatSkills.supported(held)) syncCooldown(player, state, held.getItem() instanceof StaffItem);
        AttributeInstance power = player.getAttribute(StaffAttributes.SPELL_POWER);
        if (power == null) return;
        double bonus = StaffPower.contribution(held);
        AttributeModifier current = power.getModifier(HELD_POWER);
        if (current != null && current.amount() == bonus) return;
        power.removeModifier(HELD_POWER);
        if (bonus != 0) power.addTransientModifier(new AttributeModifier(HELD_POWER, bonus, AttributeModifier.Operation.ADD_VALUE));
    }
    private static long now(ServerPlayer player) { return player.level().getServer().overworld().getGameTime(); }
    private static void syncCooldown(ServerPlayer player, State state, boolean active) {
        int remaining = active ? state.cooldown.remaining(now(player)) : 0;
        int total = active ? state.route.cooldownTicks() : 0;
        if ((remaining != state.lastRemaining || total != state.lastTotal)
                && ServerPlayNetworking.canSend(player, StaffCooldownPayload.TYPE)) {
            ServerPlayNetworking.send(player, new StaffCooldownPayload(remaining, total));
            state.lastRemaining = remaining;
            state.lastTotal = total;
        }
    }
    public static int remainingTicks(ServerPlayer player) {
        refresh(player);
        return STATES.get(player).cooldown.remaining(now(player));
    }
    public static boolean cast(ServerPlayer player) {
        if (!player.isAlive() || player.isSpectator() || !(player.getMainHandItem().getItem() instanceof StaffItem staff)) return false;
        if (!StaffTags.hasTome(player)) return false;
        if (remainingTicks(player) > 0) return false;
        ItemStack stack = player.getMainHandItem();
        State state = STATES.get(player);
        StaffRoute route = state.route;
        StaffProfile profile = staff.profile();
        int rank = StaffEnchantments.routeLevel(player.level(), stack, route);
        float amount = (float)(player.getAttributeValue(StaffAttributes.SPELL_POWER) * profile.skillMultiplier(route, rank));
        // Stop without releasing: cancelling a charged shot must not spawn a projectile.
        if (!state.cooldown.cast(now(player))) return false;
        syncCooldown(player, state, true);
        player.stopUsingItem();
        ServerLevel world = (ServerLevel)player.level();
        double radius = StaffBalance.get().skillRadius();
        StaffSkillEffectPayload.broadcast(player, route, radius);
        for (LivingEntity target : world.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius))) {
            if (!target.isAlive() || target.isSpectator() || player.distanceToSqr(target) > radius * radius) continue;
            if (route == StaffRoute.HEALING ? !(target instanceof Player) : !(target instanceof Mob)) continue;
            if (target != player && world.clip(new ClipContext(player.getEyePosition(), target.getEyePosition(),
                    ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)).getType() != HitResult.Type.MISS) continue;
            if (route == StaffRoute.HEALING) com.eccraft.combat.CombatFeedback.heal(player,target,amount);
            else if (target.hurtServer(world, damageSource(world, player, player), amount) && route == StaffRoute.FROST)
                target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, profile.slowTicks(), profile.slowAmplifier()), player);
        }
        StaffDurability.consume(player, stack, StaffBalance.get().skillDurabilityCost());
        return true;
    }
    public static DamageSource damageSource(ServerLevel world, Entity direct, Entity owner) {
        return new StaffDamageSource(world.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(MAGIC), direct, owner);
    }
    private StaffSystem() {}
}
