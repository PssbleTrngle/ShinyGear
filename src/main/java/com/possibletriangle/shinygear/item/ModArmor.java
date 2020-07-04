package com.possibletriangle.shinygear.item;

import com.possibletriangle.shinygear.OreManager;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.recipes.RecipeHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModArmor extends ItemArmor implements IModItem {

	private String ore, oreMaterial, prefix, piece;
	private ArmorMaterial material;

	@Override
	public String type() {
		return piece;
	}

	@Override
	public String ore() {
		return ore;
	}

	public ModArmor(String prefix, ArmorMaterial material, EntityEquipmentSlot slot, String oreMaterial) {
		super(material, 0, slot);

		this.prefix = prefix;

		switch (slot) {
			case HEAD: piece = "helmet"; break;
			case CHEST: piece = "chestplate"; break;
			case LEGS: piece = "leggings"; break;
			case FEET: piece = "boots"; break;
		}

		String name = prefix + "_" + piece;
		this.ore = IModItem.parse(prefix);
		this.material = material;
		this.oreMaterial = oreMaterial;

		setRegistryName(name);
		setUnlocalizedName(ShinyGear.MODID + "." + name);
		setCreativeTab(CreativeTabs.COMBAT);

		ModItems.LIST.add(this);
	}

	public void onOreEvent() {
		OreDictionary.registerOre(piece + ore, this);
	}

	@Override
	public void recipe() {
		if (material.repairMaterial.isEmpty())
			material.setRepairItem(OreDictionary.getOres(oreMaterial).get(0));

		switch (this.armorType) {
		case HEAD: new RecipeHandler.ShapedRecipe(new ItemStack(this), new Object[] { "xxx", "x x", 'x', oreMaterial }); break;
		case CHEST: new RecipeHandler.ShapedRecipe(new ItemStack(this), new Object[] { "x x", "xxx", "xxx", 'x', oreMaterial }); break;
		case LEGS: new RecipeHandler.ShapedRecipe(new ItemStack(this), new Object[] { "xxx", "x x", "x x", 'x', oreMaterial }); break;
		case FEET: new RecipeHandler.ShapedRecipe(new ItemStack(this), new Object[] { "x x", "x x", 'x', oreMaterial }); break;
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ShinyGear.MODID + ":textures/models/armor/layer_"
				+ (slot == EntityEquipmentSlot.LEGS ? 1 : 0) + (type == null ? "" : ("_" + type)) + ".png";
	}

	@Override
	public boolean hasOverlay(ItemStack stack) {
		return true;
	}

	@Override
	public int getColor(ItemStack stack) {
		return OreManager.getColor(ore());
	}

}
