package com.eccraft.block;

import com.eccraft.Eccraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class ModBlocks {
    public static final Block ANUBIS_ALTAR = registerAnubisAltar();

    public static final Block ICEWITCH_ALTAR = registerIcewitchAltar();
    private ModBlocks() {}

    private static Block registerAnubisAltar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("anubis_altar"));
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                .setId(key)
                .mapColor(MapColor.COLOR_BLACK)
                .strength(-1.0F, 3600000.0F)
                .sound(SoundType.GILDED_BLACKSTONE)
                .lightLevel(state -> state.getValue(AnubisAltarBlock.ENABLED) ? 12 : 2);
        return Registry.register(BuiltInRegistries.BLOCK, key, new AnubisAltarBlock(properties));
    }

    private static Block registerIcewitchAltar() {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Eccraft.id("icewitch_altar"));
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                .setId(key)
                .mapColor(MapColor.COLOR_BLACK)
                .strength(-1.0F, 3600000.0F)
                .sound(SoundType.GILDED_BLACKSTONE)
                .lightLevel(state -> state.getValue(IcewitchAltarBlock.ENABLED) ? 12 : 2);
        return Registry.register(BuiltInRegistries.BLOCK, key, new IcewitchAltarBlock(properties));
    }

    public static void register() {
        Eccraft.LOGGER.info("Registered Anubis blocks");
    }
}
