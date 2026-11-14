package com.eccraft.obsidianguard;

import com.eccraft.Eccraft;
import com.eccraft.block.ObsidianguardAltarBlock;
import com.eccraft.block.entity.ObsidianguardAltarBlockEntity;
import com.eccraft.entity.ObsidianguardEntity;
import com.eccraft.item.ObsidianguardSummoningStoneItem;
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
public final class ObsidianguardContent {
    // First-version collision dimensions; model scale is independently adjustable in the renderer.
    public static final EntityType<ObsidianguardEntity> ENTITY = entity();
    public static final Block ALTAR = altar();
    public static final BlockEntityType<ObsidianguardAltarBlockEntity> ALTAR_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("obsidianguard_altar"),
            new BlockEntityType<>(ObsidianguardAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();

    public static final EntityType<com.eccraft.entity.ObsidianguardMeteor> METEOR = meteor();
    public static final EntityType<com.eccraft.entity.ObsidianguardMagmaCube> MAGMA_CUBE = magmaCube();
    private static EntityType<com.eccraft.entity.ObsidianguardMeteor> meteor() {
        ResourceKey<EntityType<?>> key=ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id("obsidianguard_meteor"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.<com.eccraft.entity.ObsidianguardMeteor>of(com.eccraft.entity.ObsidianguardMeteor::new,MobCategory.MISC).sized(2,2).clientTrackingRange(12).updateInterval(1).build(key));
    }
    private static EntityType<com.eccraft.entity.ObsidianguardMagmaCube> magmaCube() {
        ResourceKey<EntityType<?>> key=ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id("obsidianguard_magma_cube"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.<com.eccraft.entity.ObsidianguardMagmaCube>of(com.eccraft.entity.ObsidianguardMagmaCube::new,MobCategory.MONSTER).sized(.52F,.52F).clientTrackingRange(12).fireImmune().build(key));
    }
    private ObsidianguardContent() {}
    private static EntityType<ObsidianguardEntity> entity() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("obsidianguard"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(ObsidianguardEntity::new, MobCategory.MONSTER)
                        .sized(1.2F, 3.5F).clientTrackingRange(12).build(key));
    }
    private static Block altar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("obsidianguard_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new ObsidianguardAltarBlock(
                BlockBehaviour.Properties.of().setId(key).strength(-1.0F, 3600000.0F)
                        .sound(SoundType.GILDED_BLACKSTONE)
                        .lightLevel(state -> state.getValue(ObsidianguardAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("obsidianguard_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("obsidianguard_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new ObsidianguardSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() {
        FabricDefaultAttributeRegistry.register(ENTITY, ObsidianguardEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MAGMA_CUBE, com.eccraft.entity.ObsidianguardMagmaCube.createAttributes());
        com.eccraft.sound.ObsidianguardSounds.register();
    }
}
