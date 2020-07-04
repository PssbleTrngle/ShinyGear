package com.possibletriangle.shinygear.item;

import java.util.ArrayList;

import com.possibletriangle.shinygear.item.tools.*;
import com.possibletriangle.shinygear.item.tools.spartan.*;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;

import com.possibletriangle.shinygear.ModMaterials;
import com.possibletriangle.shinygear.OreManager;
import com.possibletriangle.shinygear.ShinyGear;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems extends Item {

	public static final ArrayList<Item> LIST = new ArrayList<>();

	public static void init() {
		ShinyGear.LOGGER.log(Level.INFO, "Initialising Items");

		for (String[] ore : OreManager.forCategory("armor")) {

			ArmorMaterial material = ModMaterials.ARMOR.containsKey(ore[1]) ? ModMaterials.ARMOR.get(ore[1])
					: EnumHelper.addArmorMaterial(ore[1].toUpperCase(), ore[1].toLowerCase(), 33,
							new int[] { 3, 6, 8, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0F);

			new ModArmor(ore[1].toLowerCase(), material, EntityEquipmentSlot.HEAD, ore[0] + ore[1]);
			new ModArmor(ore[1].toLowerCase(), material, EntityEquipmentSlot.CHEST, ore[0] + ore[1]);
			new ModArmor(ore[1].toLowerCase(), material, EntityEquipmentSlot.LEGS, ore[0] + ore[1]);
			new ModArmor(ore[1].toLowerCase(), material, EntityEquipmentSlot.FEET, ore[0] + ore[1]);

		}

		if (Loader.isModLoaded("spartanshields")) {
			ArrayList<String[]> gems = OreManager.forCategory("shields");
			ShinyGear.LOGGER.info("Initialising compatibility for SpartanShields [" + gems.size() + " gems]");
			for (String[] ore : gems) {

				ToolMaterial material = ModMaterials.TOOL.containsKey(ore[1]) ? ModMaterials.TOOL.get(ore[1])
						: EnumHelper.addToolMaterial(ore[1].toUpperCase(), ToolMaterial.DIAMOND.getHarvestLevel(),
								ToolMaterial.DIAMOND.getMaxUses(), ToolMaterial.DIAMOND.getEfficiency(),
								ToolMaterial.DIAMOND.getAttackDamage(), ToolMaterial.DIAMOND.getEnchantability());

				String prefix = ore[1].toLowerCase(), oreMaterial = ore[0] + ore[1];
				new ModShield(prefix, material, oreMaterial);
			}
		}

		if (Loader.isModLoaded("spartanweaponry")) {
			ArrayList<String[]> gems = OreManager.forCategory("spartans_weaponry");
			ShinyGear.LOGGER.info("Initialising compatibility for SpartanWeaponry [" + gems.size() + " gems]");
			for (String[] ore : gems) {

				ToolMaterial material = ModMaterials.TOOL.containsKey(ore[1]) ? ModMaterials.TOOL.get(ore[1])
						: EnumHelper.addToolMaterial(ore[1].toUpperCase(), ToolMaterial.DIAMOND.getHarvestLevel(),
								ToolMaterial.DIAMOND.getMaxUses(), ToolMaterial.DIAMOND.getEfficiency(),
								ToolMaterial.DIAMOND.getAttackDamage(), ToolMaterial.DIAMOND.getEnchantability());

				String prefix = ore[1].toLowerCase(), oreMaterial = ore[0] + ore[1];
				new ModDagger(prefix, material, oreMaterial);
				new ModKatana(prefix, material, oreMaterial);
				new ModWarhammer(prefix, material, oreMaterial);
				new ModGreatsword(prefix, material, oreMaterial);
				new ModHalberd(prefix, material, oreMaterial);
				new ModSpear(prefix, material, oreMaterial);
				new ModHammer(prefix, material, oreMaterial);
				new ModLance(prefix, material, oreMaterial);
				new ModCrossbow(prefix, material, oreMaterial);
				new ModSaber(prefix, material, oreMaterial);
				new ModRapier(prefix, material, oreMaterial);
				new ModLongbow(prefix, material, oreMaterial);
				new ModLongsword(prefix, material, oreMaterial);
				new ModPike(prefix, material, oreMaterial);
				new ModThrowingKnife(prefix, material, oreMaterial);
				new ModThrowingAxe(prefix, material, oreMaterial);
				new ModJavelin(prefix, material, oreMaterial);
				new ModMace(prefix, material, oreMaterial);
				new ModBoomerang(prefix, material, oreMaterial);
				new ModBattleaxe(prefix, material, oreMaterial);
			}
		}

		for (String[] ore : OreManager.forCategory("tools")) {

			ToolMaterial material = ModMaterials.TOOL.containsKey(ore[1]) ? ModMaterials.TOOL.get(ore[1])
					: EnumHelper.addToolMaterial(ore[1].toUpperCase(), ToolMaterial.DIAMOND.getHarvestLevel(),
							ToolMaterial.DIAMOND.getMaxUses(), ToolMaterial.DIAMOND.getEfficiency(),
							ToolMaterial.DIAMOND.getAttackDamage(), ToolMaterial.DIAMOND.getEnchantability());

			new ModAxe(ore[1].toLowerCase(), material, ore[0] + ore[1]);
			new ModShovel(ore[1].toLowerCase(), material, ore[0] + ore[1]);
			new ModPickaxe(ore[1].toLowerCase(), material, ore[0] + ore[1]);
			new ModSword(ore[1].toLowerCase(), material, ore[0] + ore[1]);
			new ModHoe(ore[1].toLowerCase(), material, ore[0] + ore[1]);

		}

	}

	public static void postinit() {

		for (Item i : LIST) {

			if (i instanceof IModItem)
				((IModItem) i).recipe();

		}

	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {

		for (Item i : LIST.toArray(new Item[] {})) {
			boolean b = i instanceof IModItem && !((IModItem) i).isIron();
			ResourceLocation name = b ? new ResourceLocation(ShinyGear.MODID, ((IModItem) i).type()) : i.getRegistryName();
				ModelLoader.setCustomModelResourceLocation(i, 0,
						new ModelResourceLocation(name, "inventory"));
		}

	}

}
