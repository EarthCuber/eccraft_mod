package com.eccraft.banshee;

import com.eccraft.Eccraft;
import com.eccraft.block.BansheeAltarBlock;
import com.eccraft.block.entity.BansheeAltarBlockEntity;
import com.eccraft.entity.BansheeEntity;
import com.eccraft.item.BansheeSummoningStoneItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.Set;

/** New content is isolated here to avoid replacing existing Boss registrations. */
public final class BansheeContent {
    // First-version collision dimensions; model scale is independently adjustable in the renderer.
    public static final EntityType<BansheeEntity> ENTITY = entity();
    public static final Block ALTAR = altar();
    public static final BlockEntityType<BansheeAltarBlockEntity> ALTAR_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("banshee_altar"),
            new BlockEntityType<>(BansheeAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();

    public static final EntityType<com.eccraft.entity.BansheeProjectile> PROJECTILE = projectile();
    private static EntityType<com.eccraft.entity.BansheeProjectile> projectile() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("banshee_projectile"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.<com.eccraft.entity.BansheeProjectile>of(com.eccraft.entity.BansheeProjectile::new, MobCategory.MISC)
                        .sized(0.3F, 0.3F).clientTrackingRange(8).updateInterval(1).build(key));
    }
    private BansheeContent() {}
    private static EntityType<BansheeEntity> entity() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("banshee"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(BansheeEntity::new, MobCategory.MONSTER)
                        .sized(1.15F, 3.5F).clientTrackingRange(12).build(key));
    }
    private static Block altar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("banshee_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new BansheeAltarBlock(
                BlockBehaviour.Properties.of().setId(key).strength(-1.0F, 3600000.0F)
                        .sound(SoundType.GILDED_BLACKSTONE)
                        .lightLevel(state -> state.getValue(BansheeAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("banshee_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("banshee_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BansheeSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() {
        FabricDefaultAttributeRegistry.register(ENTITY, BansheeEntity.createAttributes());
        com.eccraft.sound.BansheeSounds.register();

    }
}
