package com.eccraft.block.entity;

import com.eccraft.Eccraft;
import com.eccraft.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public final class ModBlockEntityTypes {
    public static final BlockEntityType<AnubisAltarBlockEntity> ANUBIS_ALTAR = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Eccraft.id("anubis_altar"),
            new BlockEntityType<>(AnubisAltarBlockEntity::new, Set.of(ModBlocks.ANUBIS_ALTAR)));

    public static final BlockEntityType<IcewitchAltarBlockEntity> ICEWITCH_ALTAR = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Eccraft.id("icewitch_altar"),
            new BlockEntityType<>(IcewitchAltarBlockEntity::new, Set.of(ModBlocks.ICEWITCH_ALTAR)));

    private ModBlockEntityTypes() {}

    public static void register() {
        Eccraft.LOGGER.info("Registered Anubis block entity types");
    }
}
