package com.eccraft.item;

import com.eccraft.Eccraft;
import com.eccraft.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public final class ModItems {
    public static final Item ANUBIS_ALTAR = registerBlockItem("anubis_altar");
    public static final Item ANUBIS_SUMMONING_STONE = registerSummoningStone();

    public static final Item ICEWITCH_ALTAR = registerIcewitchBlockItem("icewitch_altar");
    public static final Item ICEWITCH_SUMMONING_STONE = registerIcewitchSummoningStone();
    private ModItems() {}

    private static Item registerBlockItem(String name) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id(name));
        Item item = new BlockItem(ModBlocks.ANUBIS_ALTAR,
                new Item.Properties().setId(key).useBlockDescriptionPrefix());
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    private static Item registerSummoningStone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("anubis_summoning_stone"));
        Item item = new AnubisSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    private static Item registerIcewitchBlockItem(String name) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id(name));
        Item item = new BlockItem(ModBlocks.ICEWITCH_ALTAR,
                new Item.Properties().setId(key).useBlockDescriptionPrefix());
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    private static Item registerIcewitchSummoningStone() {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Eccraft.id("icewitch_summoning_stone"));
        Item item = new IcewitchSummoningStoneItem(new Item.Properties().setId(key).stacksTo(64));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void register() {
        Eccraft.LOGGER.info("Registered Anubis items");
    }
}
