package com.eccraft.item;

import com.eccraft.Eccraft;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public final class ModCreativeTabs {
    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Eccraft.id("bosses"),
                FabricCreativeModeTab.builder().title(Component.translatable("itemGroup.eccraft.bosses"))
                .icon(() -> new ItemStack(ModItems.ANUBIS_SUMMONING_STONE))
                .displayItems((parameters, output) -> {
                    output.accept(ModItems.ANUBIS_ALTAR);
                    output.accept(ModItems.ANUBIS_SUMMONING_STONE);
                    output.accept(ModItems.ICEWITCH_ALTAR);
                    output.accept(ModItems.ICEWITCH_SUMMONING_STONE);
                    output.accept(com.eccraft.samurai.SamuraiContent.ALTAR_ITEM);
                    output.accept(com.eccraft.samurai.SamuraiContent.SUMMONING_STONE);
                    output.accept(com.eccraft.sphinx.SphinxContent.ALTAR_ITEM);
                    output.accept(com.eccraft.sphinx.SphinxContent.SUMMONING_STONE);
                    output.accept(com.eccraft.techtainark.TechtainArkContent.ALTAR_ITEM);
                    output.accept(com.eccraft.techtainark.TechtainArkContent.SUMMONING_STONE);
                    output.accept(com.eccraft.firelord.FirelordContent.ALTAR_ITEM);
                    output.accept(com.eccraft.firelord.FirelordContent.SUMMONING_STONE);
                    output.accept(com.eccraft.nightgod.NightgodContent.ALTAR_ITEM);
                    output.accept(com.eccraft.nightgod.NightgodContent.SUMMONING_STONE);
                    output.accept(com.eccraft.castlecore.CastlecoreContent.ALTAR_ITEM);
                    output.accept(com.eccraft.castlecore.CastlecoreContent.SUMMONING_STONE);
                    output.accept(com.eccraft.obsidianguard.ObsidianguardContent.ALTAR_ITEM);
                    output.accept(com.eccraft.obsidianguard.ObsidianguardContent.SUMMONING_STONE);
                        output.accept(com.eccraft.banshee.BansheeContent.ALTAR_ITEM);
                        output.accept(com.eccraft.banshee.BansheeContent.SUMMONING_STONE);
                    output.accept(com.eccraft.darkdragon.DarkDragonContent.ALTAR_ITEM);
                    output.accept(com.eccraft.darkdragon.DarkDragonContent.SUMMONING_STONE);
                    output.accept(com.eccraft.junglelord.JunglelordContent.ALTAR_ITEM);
                    output.accept(com.eccraft.junglelord.JunglelordContent.SUMMONING_STONE);
                }).build());
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Eccraft.id("weapons"),
                FabricCreativeModeTab.builder().title(Component.translatable("itemGroup.eccraft.weapons"))
                .icon(() -> new ItemStack(BuiltInRegistries.ITEM.getValue(Eccraft.id("staff_t5"))))
                .displayItems((parameters, output) -> {
                    for (int tier = 1; tier <= 5; tier++)
                        output.accept(BuiltInRegistries.ITEM.getValue(Eccraft.id("staff_t" + tier)));
                    for (int tier = 1; tier <= 5; tier++)
                        output.accept(BuiltInRegistries.ITEM.getValue(Eccraft.id("tome_t" + tier)));
                    com.eccraft.armor.MageArmorItems.all().forEach(output::accept);
                    com.eccraft.knight.KnightItems.all().forEach(output::accept);
                    com.eccraft.armor.KnightArmorItems.all().forEach(output::accept);
                    com.eccraft.archer.ArcherItems.all().forEach(output::accept);
                    com.eccraft.armor.ArcherArmorItems.all().forEach(output::accept);
                }).build());
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Eccraft.id("legend_weapons"),
                FabricCreativeModeTab.builder().title(Component.translatable("itemGroup.eccraft.legend_weapons"))
                .icon(() -> new ItemStack(BuiltInRegistries.ITEM.getValue(Eccraft.id("item_legend_sword"))))
                .displayItems((parameters, output) -> {output.accept(com.eccraft.techtainark.ArkEquipment.HAMMER);LegendItems.all().forEach(output::accept);com.eccraft.legend.LegendEquipment.all().forEach(output::accept);com.eccraft.legend.LegendTables.items().forEach(output::accept);}).build());
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Eccraft.id("equipment_materials"),
                FabricCreativeModeTab.builder().title(Component.translatable("itemGroup.eccraft.equipment_materials"))
                .icon(() -> new ItemStack(SynthesisItems.all().getFirst()))
                .displayItems((parameters, output) -> {SynthesisItems.all().forEach(output::accept);AdditionalMaterialItems.all().forEach(output::accept);output.accept(com.eccraft.techtainark.ArkEquipment.FRAGMENT);output.accept(com.eccraft.techtainark.ArkEquipment.HEART);output.accept(com.eccraft.techtainark.ArkEquipment.DUST);com.eccraft.control.ControlDeathContent.items().forEach(output::accept);}).build());
    }
    private ModCreativeTabs() {}
}
