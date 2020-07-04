package com.possibletriangle.shinygear.proxy;

import java.io.File;
import java.util.HashMap;

import com.possibletriangle.shinygear.ModMaterials;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.OreManager;
import com.possibletriangle.shinygear.compat.BaublesCompat;
import com.possibletriangle.shinygear.compat.tconstruct.GemMaterials;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;

import com.possibletriangle.shinygear.recipes.RecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {

	private static final HashMap<String, Boolean> MAP = new HashMap<String, Boolean>();

	public static Configuration CONFIG;

	public static boolean enabled(String cat, String gem) {
		return MAP.containsKey(cat + ":" + gem) && MAP.get(cat + ":" + gem);
	}

	public void preInit(FMLPreInitializationEvent e) {

		MinecraftForge.EVENT_BUS.register(this);
		File directory = e.getModConfigurationDirectory();
		CONFIG = new Configuration(new File(directory.getPath(), ShinyGear.MODID + ".cfg"));

		CONFIG.load();

		for (String cat : new String[] { "Armor", "Tools", "Shields", "Charms", "Tool_Materials",
				"Spartans_Weaponry" }) {

			for (String ore : OreManager.BIOEMS_O_PLENTY)
				MAP.put(cat.toLowerCase() + ":" + ore, CONFIG.getBoolean(IModItem.parse(ore), cat, true,
						"Create " + cat.toLowerCase() + " for \"gem" + ore + "\""));
			for (String ore : OreManager.VANILLA) {
				boolean b = !(ore.equals("Emerald") && (cat.equals("Charms") || cat.equals("Tool_Materials")));
				MAP.put(cat.toLowerCase() + ":" + ore, CONFIG.getBoolean(IModItem.parse(ore), cat, b,
						"Create " + cat.toLowerCase() + " for \"gem" + ore + "\""));
			}
		}

		CONFIG.save();
		ModMaterials.init();
		ModItems.init();

		if (Loader.isModLoaded("baubles"))
			BaublesCompat.init();
		if (Loader.isModLoaded("tconstruct"))
			GemMaterials.init();

	}

	public void init(FMLInitializationEvent e) {
		ModItems.postinit();
		RecipeHandler.init();

		itemColors();
	}

	public void postInit(FMLPostInitializationEvent e) {
		if (CONFIG.hasChanged())
			CONFIG.save();

	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {

		if (!ModItems.LIST.isEmpty())
			ShinyGear.LOGGER.info("Registering " + ModItems.LIST.size() + " items");

		for (Item i : ModItems.LIST.toArray(new Item[] {})) {
			event.getRegistry().register(i);
			if (i instanceof IModItem)
				((IModItem) i).onOreEvent();
		}

	}

	public void itemColors() {}

}
