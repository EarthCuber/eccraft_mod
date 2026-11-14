package com.eccraft.castlecore;

import com.eccraft.Eccraft;
import com.eccraft.block.CastlecoreAltarBlock;
import com.eccraft.block.entity.CastlecoreAltarBlockEntity;
import com.eccraft.entity.CastlecoreEntity;
import com.eccraft.item.CastlecoreSummoningStoneItem;
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
public final class CastlecoreContent {
    // First-version collision dimensions; model scale is independently adjustable in the renderer.
    public static final EntityType<CastlecoreEntity> ENTITY = entity();
    public static final Block ALTAR = altar();
    public static final BlockEntityType<CastlecoreAltarBlockEntity> ALTAR_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("castlecore_altar"),
            new BlockEntityType<>(CastlecoreAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();

    private CastlecoreContent() {}
    private static EntityType<CastlecoreEntity> entity() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("castlecore"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(CastlecoreEntity::new, MobCategory.MONSTER)
                        .sized(1.2F, 3.5F).clientTrackingRange(12).build(key));
    }
    private static Block altar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("castlecore_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new CastlecoreAltarBlock(
                BlockBehaviour.Properties.of().setId(key).strength(-1.0F, 3600000.0F)
                        .sound(SoundType.GILDED_BLACKSTONE)
                        .lightLevel(state -> state.getValue(CastlecoreAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("castlecore_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("castlecore_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new CastlecoreSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() {
        com.eccraft.effect.ArmorRendEffect.register();
        FabricDefaultAttributeRegistry.register(ENTITY, CastlecoreEntity.createAttributes());
        com.eccraft.sound.CastlecoreSounds.register();
    }
}
