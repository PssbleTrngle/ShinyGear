package com.possibletriangle.shinygear;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.possibletriangle.shinygear.proxy.CommonProxy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.EnchantWithLevels;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

@Mod(dependencies = ShinyGear.DEPENDENCIES, modid = ShinyGear.MODID, version = ShinyGear.VERSION, name = ShinyGear.MODNAME, useMetadata = false, acceptedMinecraftVersions = "[1.12,)")
public class ShinyGear {

	@SidedProxy(clientSide = "com.possibletriangle.shinygear.proxy.ClientProxy",
				serverSide = "com.possibletriangle.shinygear.proxy.CommonProxy")
	public static CommonProxy PROXY;

	@Mod.Instance
	public static ShinyGear INSTANCE;

	public static Logger LOGGER;

	public static final String MODID = "shinygear";
	public static final String MODNAME = "Shiny Gear";
	public static final String VERSION = "1.7";
	public static final String DEPENDENCIES = 	"after:biomesoplenty;" +
												"after:spartanshields;" +
												"after:spartanweaponry;" +
												"after:tconstruct;" +
												"after:armorchroma";

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		LOGGER = event.getModLog();
		LOGGER.log(Level.INFO, "More love for gems!");
		PROXY.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(INSTANCE);
		PROXY.init(event);

	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		PROXY.postInit(event);
	}

	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent evt) {

		String prefix = "minecraft:chests/";
		String name = evt.getName().toString();

		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());

			RandomValueRange s = new RandomValueRange(1, 3), single = new RandomValueRange(1, 1);
			boolean inject = true;

			switch (file) {
			case "end_city_treasure":
				evt.getTable()
						.addPool(new LootPool(
								new LootEntry[] { new LootEntryTable(new ResourceLocation(ShinyGear.MODID, "end"), 1, 0,
										new LootCondition[0], "amethyst") },
								new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), name));
				for (LootEntry entry : merge(getEntry("gemAmethyst", 4, false, s),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_sword"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_shovel"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_pickaxe"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_shield"), 1, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_axe"), 2, true, single),

						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_helmet"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_chestplate"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_leggings"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "amethyst_boots"), 2, true, single)))
					evt.getTable().getPool("main").addEntry(entry);
				break;
			case "woodland_mansion":
				for (LootEntry entry : merge(
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_sword"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "sapphire_shovel"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "topaz_pickaxe"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "peridot_shield"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "malachite_hoe"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "tanzanite_axe"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "emerald_hoe"), 1, true, single),

						getEntry(new ResourceLocation(ShinyGear.MODID, "sapphire_helmet"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "topaz_chestplate"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "peridot_leggings"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "malachite_boots"), 2, true, single)))
					evt.getTable().getPool("main").addEntry(entry);
				break;
			case "village_blacksmith":
			case "abandoned_mineshaft":
			case "stronghold_corridor":
			case "desert_pyramid":
			case "jungle_temple":
				for (LootEntry entry : merge(getEntry("gemRuby", 2, false, s), getEntry("gemSapphire", 2, false, s),
						getEntry("gemPeridot", 2, false, s), getEntry("gemMalachite", 2, false, s),
						getEntry("gemTanzanite", 2, false, s), getEntry("gemTopaz", 2, false, s)))
					evt.getTable().getPool("main").addEntry(entry);
				break;
			case "nether_bridge":
				for (LootEntry entry : merge(getEntry("gemRuby", 3, false, s),

						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_sword"), 1, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_shovel"), 1, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_pickaxe"), 1, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_shield"), 1, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_axe"), 1, true, single),

						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_helmet"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_chestplate"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_leggings"), 2, true, single),
						getEntry(new ResourceLocation(ShinyGear.MODID, "ruby_boots"), 2, true, single)))
					evt.getTable().getPool("main").addEntry(entry);
				break;
			default:
				inject = false;
				break;
			}

			if (inject)
				LOGGER.info("Inject to loot table \"" + file + "\"");
		}

	}

	private LootEntry[] merge(LootEntry[]... in) {

		int size = 0;
		for (LootEntry[] e : in)
			size += e.length;

		LootEntry[] out = new LootEntry[size];

		int i = 0;
		for (LootEntry[] e : in) {
			for (int ii = i; ii < e.length + i; ii++)
				out[ii] = e[ii - i];
			i += e.length;
		}

		return out;

	}

	private LootEntry[] getEntry(Item item, int weight, boolean enchant, RandomValueRange size) {
		if (item == null)
			return new LootEntry[0];

		return getEntry(new ItemStack(item, 1, 0), weight, enchant, size);
	}

	private LootEntry[] getEntry(ResourceLocation src, int weight, boolean enchant, RandomValueRange size) {
		if (src == null)
			return new LootEntry[0];

		if (!Item.REGISTRY.containsKey(src))
			return new LootEntry[0];

		Item item = Item.REGISTRY.getObject(src);

		return getEntry(item, weight, enchant, size);
	}

	private LootEntry[] getEntry(ItemStack stack, int weight, boolean enchant, RandomValueRange size) {

		if (stack == null || stack.isEmpty())
			return new LootEntry[0];

		SetMetadata metaFunction = new SetMetadata(new LootCondition[0],
				new RandomValueRange(stack.getMetadata(), stack.getMetadata()));
		SetCount sizeFunction = new SetCount(new LootCondition[0], size);
		LootFunction[] functions = null;

		if (enchant)
			functions = new LootFunction[] { metaFunction, sizeFunction,
					new EnchantWithLevels(new LootCondition[0], new RandomValueRange(20, 30), false) };
		else
			functions = new LootFunction[] { sizeFunction, metaFunction };

		return new LootEntry[] { new LootEntryItem(stack.getItem(), weight, 1, functions, new LootCondition[0],
				stack.getItem().getRegistryName().toString() + ":" + stack.getMetadata()) };
	}

	private LootEntry[] getEntry(String ore, int weight, boolean enchant, RandomValueRange size) {

		if (!OreDictionary.doesOreNameExist(ore) || OreDictionary.getOres(ore).isEmpty())
			return new LootEntry[0];

		ItemStack stack = OreDictionary.getOres(ore).get(0);
		return getEntry(stack, weight, enchant, size);
	}
}
