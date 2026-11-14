package com.eccraft.samurai;

import com.eccraft.Eccraft;
import com.eccraft.block.SamuraiAltarBlock;
import com.eccraft.block.entity.SamuraiAltarBlockEntity;
import com.eccraft.entity.SamuraiEntity;
import com.eccraft.item.SamuraiSummoningStoneItem;
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
public final class SamuraiContent {
    // First-version collision dimensions; model scale is independently adjustable in the renderer.
    public static final EntityType<SamuraiEntity> ENTITY = entity();
    public static final Block ALTAR = altar();
    public static final BlockEntityType<SamuraiAltarBlockEntity> ALTAR_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("samurai_altar"),
            new BlockEntityType<>(SamuraiAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();

    public static final EntityType<com.eccraft.entity.SamuraiBoggedEntity> BOGGED = bogged();
    private static EntityType<com.eccraft.entity.SamuraiBoggedEntity> bogged() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("samurai_bogged"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(com.eccraft.entity.SamuraiBoggedEntity::new, MobCategory.MONSTER)
                        .sized(0.6F, 1.99F).build(key));
    }
    private SamuraiContent() {}
    private static EntityType<SamuraiEntity> entity() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("samurai"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(SamuraiEntity::new, MobCategory.MONSTER)
                        .sized(1.15F, 3.5F).clientTrackingRange(12).build(key));
    }
    private static Block altar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("samurai_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new SamuraiAltarBlock(
                BlockBehaviour.Properties.of().setId(key).strength(-1.0F, 3600000.0F)
                        .sound(SoundType.GILDED_BLACKSTONE)
                        .lightLevel(state -> state.getValue(SamuraiAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("samurai_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("samurai_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new SamuraiSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() {
        FabricDefaultAttributeRegistry.register(ENTITY, SamuraiEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BOGGED, com.eccraft.entity.SamuraiBoggedEntity.createAttributes());
        com.eccraft.sound.SamuraiSounds.register();
        com.eccraft.effect.ArmorBreakEffect.register();
    }
}
