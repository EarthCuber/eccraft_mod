package com.eccraft.darkdragon;

import com.eccraft.Eccraft;
import com.eccraft.entity.*;
import com.eccraft.block.DarkDragonAltarBlock;
import com.eccraft.block.entity.DarkDragonAltarBlockEntity;
import com.eccraft.item.DarkDragonSummoningStoneItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.Set;

public final class DarkDragonContent {
    public static final EntityType<DarkDragonEntity> ENTITY = entity("darkdragon", EntityType.Builder.of(DarkDragonEntity::new, MobCategory.MONSTER).sized(3, 5).clientTrackingRange(8).updateInterval(1));
    public static final EntityType<DarkDragonFireball> FIREBALL = entity("darkdragon_fireball", EntityType.Builder.<DarkDragonFireball>of(DarkDragonFireball::new, MobCategory.MISC).sized(2, 2).clientTrackingRange(8).updateInterval(1));
    public static final EntityType<DarkDragonFireEntity> FIRE = entity("darkdragon_fire", EntityType.Builder.<DarkDragonFireEntity>of(DarkDragonFireEntity::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(8));
    public static final Block ALTAR = altar();
    public static final BlockEntityType<DarkDragonAltarBlockEntity> ALTAR_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("darkdragon_altar"), new BlockEntityType<>(DarkDragonAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();
    private DarkDragonContent() {}
    private static <T extends Entity> EntityType<T> entity(String id, EntityType.Builder<T> builder) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id(id));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }
    private static Block altar() {
        var key = ResourceKey.create(Registries.BLOCK, Eccraft.id("darkdragon_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new DarkDragonAltarBlock(BlockBehaviour.Properties.of().setId(key)
                .strength(-1, 3600000).sound(SoundType.GILDED_BLACKSTONE).lightLevel(s -> s.getValue(DarkDragonAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        var key = ResourceKey.create(Registries.ITEM, Eccraft.id("darkdragon_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        var key = ResourceKey.create(Registries.ITEM, Eccraft.id("darkdragon_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key, new DarkDragonSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() { DarkDragonCinematics.register(); DarkDragonSounds.register(); FabricDefaultAttributeRegistry.register(ENTITY, DarkDragonEntity.createAttributes()); }
}
