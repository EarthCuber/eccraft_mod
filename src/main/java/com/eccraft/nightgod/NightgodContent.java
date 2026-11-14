package com.eccraft.nightgod;

import com.eccraft.Eccraft;
import com.eccraft.block.NightgodAltarBlock;
import com.eccraft.block.entity.NightgodAltarBlockEntity;
import com.eccraft.entity.NightgodEntity;
import com.eccraft.item.NightgodSummoningStoneItem;
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
public final class NightgodContent {
    // First-version collision dimensions; model scale is independently adjustable in the renderer.
    public static final EntityType<NightgodEntity> ENTITY = entity();
    public static final Block ALTAR = altar();
    public static final BlockEntityType<NightgodAltarBlockEntity> ALTAR_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Eccraft.id("nightgod_altar"),
            new BlockEntityType<>(NightgodAltarBlockEntity::new, Set.of(ALTAR)));
    public static final Item ALTAR_ITEM = altarItem();
    public static final Item SUMMONING_STONE = stone();

    public static final EntityType<com.eccraft.entity.NightgodFire> FIRE=registerEntity("nightgod_fire",com.eccraft.entity.NightgodFire::new,MobCategory.MISC,.8F,.2F);
    public static final EntityType<com.eccraft.entity.NightgodProjectile> PROJECTILE=registerEntity("nightgod_projectile",com.eccraft.entity.NightgodProjectile::new,MobCategory.MISC,.5F,.5F);
    public static final EntityType<com.eccraft.entity.LittleDevilEntity> LITTLE_DEVIL=registerEntity("littledevil",com.eccraft.entity.LittleDevilEntity::new,MobCategory.MONSTER,.7F,1.5F);
    public static final EntityType<com.eccraft.entity.NightDeathEntity> DEATH=registerEntity("death",com.eccraft.entity.NightDeathEntity::new,MobCategory.MONSTER,1F,2.8F);
    private static <T extends net.minecraft.world.entity.Entity> EntityType<T> registerEntity(String id,EntityType.EntityFactory<T> factory,MobCategory category,float width,float height){
        ResourceKey<EntityType<?>> key=ResourceKey.create(Registries.ENTITY_TYPE,Eccraft.id(id));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.of(factory,category).sized(width,height).clientTrackingRange(12).updateInterval(1).build(key));
    }
    private NightgodContent() {}
    private static EntityType<NightgodEntity> entity() {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Eccraft.id("nightgod"));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key,
                EntityType.Builder.of(NightgodEntity::new, MobCategory.MONSTER)
                        .sized(1.0F, 3.5F).clientTrackingRange(12).build(key));
    }
    private static Block altar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("nightgod_altar"));
        return Registry.register(BuiltInRegistries.BLOCK, key, new NightgodAltarBlock(
                BlockBehaviour.Properties.of().setId(key).strength(-1.0F, 3600000.0F)
                        .sound(SoundType.GILDED_BLACKSTONE)
                        .lightLevel(state -> state.getValue(NightgodAltarBlock.ENABLED) ? 12 : 2)));
    }
    private static Item altarItem() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("nightgod_altar"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(ALTAR, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
    }
    private static Item stone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("nightgod_summoning_stone"));
        return Registry.register(BuiltInRegistries.ITEM, key,
                new NightgodSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64)));
    }
    public static void register() {
        com.eccraft.effect.NightCorrosionEffect.register();
        FabricDefaultAttributeRegistry.register(ENTITY, NightgodEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(LITTLE_DEVIL,com.eccraft.entity.NightMinionEntity.attributes(false));
        FabricDefaultAttributeRegistry.register(DEATH,com.eccraft.entity.NightMinionEntity.attributes(true).add(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE,1));
        com.eccraft.sound.NightgodSounds.register();
    }
}
