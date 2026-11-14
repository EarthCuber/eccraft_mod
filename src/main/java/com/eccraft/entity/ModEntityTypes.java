package com.eccraft.entity;

import com.eccraft.Eccraft;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntityTypes {
    public static final EntityType<AnubisEntity> ANUBIS = register("anubis",
            EntityType.Builder.of(AnubisEntity::new, MobCategory.MONSTER).sized(1.15F, 3.5F).clientTrackingRange(12));
    public static final EntityType<AnubisFlameEntity> ANUBIS_FLAME = register("anubis_flame",
            EntityType.Builder.of(AnubisFlameEntity::new, MobCategory.MISC).sized(0.35F, 0.35F)
                    .fireImmune().clientTrackingRange(10).updateInterval(1).noLootTable());
    public static final EntityType<AnubisHuskEntity> ANUBIS_HUSK = register("anubis_husk",
            EntityType.Builder.of(AnubisHuskEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).noLootTable());
    public static final EntityType<com.eccraft.npc.NpcNormalEntity> NPC_NORMAL = register("npc_normal",
            EntityType.Builder.of(com.eccraft.npc.NpcNormalEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(10).updateInterval(2).noLootTable());

    private ModEntityTypes() {}

    public static final EntityType<IcewitchEntity> ICEWITCH = register("icewitch",
            EntityType.Builder.of(IcewitchEntity::new, MobCategory.MONSTER).sized(0.6F, 3.5F).clientTrackingRange(12));
    public static final EntityType<IcewitchStrayEntity> ICEWITCH_STRAY = register("icewitch_stray",
            EntityType.Builder.of(IcewitchStrayEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F));
    public static final EntityType<IcefireEntity> ICEFIRE = register("icefire",
            EntityType.Builder.of(IcefireEntity::new, MobCategory.MISC).sized(0.35F, 0.35F)
                    .fireImmune().clientTrackingRange(10).updateInterval(1).noLootTable());

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void register() {
        FabricDefaultAttributeRegistry.register(ICEWITCH, IcewitchEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ICEWITCH_STRAY, IcewitchStrayEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ANUBIS, AnubisEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ANUBIS_HUSK, AnubisHuskEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(NPC_NORMAL, com.eccraft.npc.NpcNormalEntity.createAttributes());
        Eccraft.LOGGER.info("Registered Anubis entity types");
    }
}
