package com.eccraft;

import com.eccraft.block.ModBlocks;
import com.eccraft.block.entity.ModBlockEntityTypes;
import com.eccraft.entity.ModEntityTypes;
import com.eccraft.item.ModItems;
import com.eccraft.sound.ModSoundEvents;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Eccraft implements ModInitializer {
	public static final String MOD_ID = "eccraft";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        com.eccraft.config.BundledDefaults.initialize();
		net.fabricmc.fabric.api.resource.ResourceManagerHelper.get(net.minecraft.server.packs.PackType.SERVER_DATA).registerReloadListener(com.eccraft.quest.QuestRegistry.INSTANCE);
		net.fabricmc.fabric.api.resource.ResourceManagerHelper.get(net.minecraft.server.packs.PackType.SERVER_DATA).registerReloadListener(com.eccraft.npc.NpcRegistry.INSTANCE);
		com.eccraft.quest.QuestManager.register();
		com.eccraft.npc.NpcDialogueManager.register();
        com.eccraft.death.DimensionKeepInventory.initialize();
		com.eccraft.recipe.EccraftRecipeBook.register();
		com.eccraft.effect.ModEffects.register();
		com.eccraft.staff.StaffSystem.register();
		com.eccraft.enchanting.TableBalance.initialize();
		com.eccraft.armor.MageArmorItems.register();
        com.eccraft.armor.KnightArmorItems.register();
		com.eccraft.knight.KnightItems.register();
        com.eccraft.archer.ArcherItems.register();
        com.eccraft.armor.ArcherArmorItems.register();
        com.eccraft.combat.CombatSkills.register();
		com.eccraft.junglelord.JunglelordContent.register();
		com.eccraft.samurai.SamuraiContent.register();
		com.eccraft.sphinx.SphinxContent.register();
        com.eccraft.techtainark.TechtainArkContent.register();
		com.eccraft.firelord.FirelordContent.register();
		com.eccraft.nightgod.NightgodContent.register();
		com.eccraft.castlecore.CastlecoreContent.register();
		com.eccraft.obsidianguard.ObsidianguardContent.register();
		com.eccraft.banshee.BansheeContent.register();
		com.eccraft.darkdragon.DarkDragonContent.register();
		ModBlocks.register();
		ModItems.register();
		com.eccraft.item.SynthesisItems.register();
        com.eccraft.item.AdditionalMaterialItems.register();
        com.eccraft.control.ControlDeathContent.register();
        com.eccraft.item.LegendItems.register();
        com.eccraft.legend.LegendEquipment.register();
        com.eccraft.legend.LegendTables.register();
		com.eccraft.item.ModCreativeTabs.register();
		ModBlockEntityTypes.register();
		ModSoundEvents.register();
		ModEntityTypes.register();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
