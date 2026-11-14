package com.eccraft.techtainark;

import com.eccraft.Eccraft;
import com.eccraft.block.TechtainArkAltarBlock;
import com.eccraft.block.entity.TechtainArkAltarBlockEntity;
import com.eccraft.entity.TechtainArkEntity;
import com.eccraft.item.TechtainArkSummoningStoneItem;
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
public final class TechtainArkContent {
    // First-version collision dimensions; model scale is independently adjustable in the renderer.
    public static final EntityType<TechtainArkEntity> ENTITY = entity();
    public static final Block ALTAR = altar();
    public static final BlockEntityType<TechtainArkAltarBlockEntity> ALTAR_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("techtain_ark_altar"),
            new BlockEntityType<>(TechtainArkAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();

    public static final EntityType<com.eccraft.entity.ArkMinionEntity> BOX=minion("techtain_ark_box"),COASTER=minion("techtain_ark_coaster"),SPRINTER=minion("techtain_ark_coasters");
    private static EntityType<com.eccraft.entity.ArkMinionEntity> minion(String id){ResourceKey<EntityType<?>> key=ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id(id));return Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.of(com.eccraft.entity.ArkMinionEntity::new,MobCategory.MONSTER).sized(1,2).clientTrackingRange(12).updateInterval(1).build(key));}
    private TechtainArkContent() {}
    private static EntityType<TechtainArkEntity> entity() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("techtain_ark"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(TechtainArkEntity::new, MobCategory.MONSTER)
                        .sized(1.5F, 3.5F).clientTrackingRange(12).build(key));
    }
    private static Block altar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("techtain_ark_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new TechtainArkAltarBlock(
                BlockBehaviour.Properties.of().setId(key).strength(-1.0F, 3600000.0F)
                        .sound(SoundType.GILDED_BLACKSTONE)
                        .lightLevel(state -> state.getValue(TechtainArkAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("techtain_ark_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("techtain_ark_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new TechtainArkSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() {
        com.eccraft.sound.TechtainArkSounds.register();
        ArkEquipment.register();

        FabricDefaultAttributeRegistry.register(ENTITY, TechtainArkEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BOX,com.eccraft.entity.ArkMinionEntity.attributes(0));
        FabricDefaultAttributeRegistry.register(COASTER,com.eccraft.entity.ArkMinionEntity.attributes(1));
        FabricDefaultAttributeRegistry.register(SPRINTER,com.eccraft.entity.ArkMinionEntity.attributes(2));
    }
}
