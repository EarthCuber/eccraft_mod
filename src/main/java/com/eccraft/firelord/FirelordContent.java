package com.eccraft.firelord;

import com.eccraft.Eccraft;
import com.eccraft.block.FirelordAltarBlock;
import com.eccraft.block.entity.FirelordAltarBlockEntity;
import com.eccraft.entity.FirelordEntity;
import com.eccraft.item.FirelordSummoningStoneItem;
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
public final class FirelordContent {
    // First-version collision dimensions; model scale is independently adjustable in the renderer.
    public static final EntityType<FirelordEntity> ENTITY = entity();
    public static final Block ALTAR = altar();
    public static final BlockEntityType<FirelordAltarBlockEntity> ALTAR_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("firelord_altar"),
            new BlockEntityType<>(FirelordAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();

    public static final EntityType<com.eccraft.entity.FireElementEntity> ELEMENT=registerEntity("fireelement",com.eccraft.entity.FireElementEntity::new,1F,2F);
    public static final EntityType<com.eccraft.entity.FirelordMeteor> METEOR=registerEntity("firelord_meteor",com.eccraft.entity.FirelordMeteor::new,2F,2F);
    private static <T extends net.minecraft.world.entity.Entity> EntityType<T> registerEntity(String id,EntityType.EntityFactory<T> factory,float width,float height){ResourceKey<EntityType<?>> key=ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id(id));return Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.of(factory,MobCategory.MONSTER).sized(width,height).clientTrackingRange(12).updateInterval(1).build(key));}
    public static final EntityType<com.eccraft.entity.FirelordLava> LAVA=lava();
    private static EntityType<com.eccraft.entity.FirelordLava> lava(){var key=ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id("firelord_lava"));return Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.<com.eccraft.entity.FirelordLava>of(com.eccraft.entity.FirelordLava::new,MobCategory.MISC).sized(0,0).clientTrackingRange(12).updateInterval(1).build(key));}
    private FirelordContent() {}
    private static EntityType<FirelordEntity> entity() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("firelord"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(FirelordEntity::new, MobCategory.MONSTER)
                        .sized(1.0F, 3.5F).clientTrackingRange(12).build(key));
    }
    private static Block altar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("firelord_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new FirelordAltarBlock(
                BlockBehaviour.Properties.of().setId(key).strength(-1.0F, 3600000.0F)
                        .sound(SoundType.GILDED_BLACKSTONE)
                        .lightLevel(state -> state.getValue(FirelordAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("firelord_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("firelord_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new FirelordSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() {

        FabricDefaultAttributeRegistry.register(ENTITY, FirelordEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ELEMENT,com.eccraft.entity.FireElementEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(METEOR,com.eccraft.entity.FirelordMeteor.createAttributes());
        FabricDefaultAttributeRegistry.register(LAVA,com.eccraft.entity.FirelordLava.createAttributes());
        com.eccraft.effect.FirelordLavaOutEffect.register();
        com.eccraft.sound.FirelordSounds.register();
    }
}
