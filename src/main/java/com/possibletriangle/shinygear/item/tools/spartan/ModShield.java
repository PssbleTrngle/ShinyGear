package com.possibletriangle.shinygear.item.tools.spartan;

import com.oblivioussp.spartanshields.item.ItemShieldBasic;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;
import com.possibletriangle.shinygear.recipes.RecipeHandler;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class ModShield extends ItemShieldBasic implements IModItem {

	private static final String tool = "shield";
	private String ore, oreMaterial;

	@Override
	public String type() {
		return tool;
	}

	@Override
	public String ore() {
		return ore;
	}

	public ModShield(String prefix, ToolMaterial material, String oreMaterial) {
		super(prefix + "_" + tool,
				(int) (((float) material.getMaxUses() / (float) ToolMaterial.DIAMOND.getMaxUses()) * 2560), material);

		this.ore = IModItem.parse(prefix);
		this.oreMaterial = oreMaterial;

		setUnlocalizedName(ShinyGear.MODID + "." + prefix + "_" + tool);

		ModItems.LIST.add(this);
	}

	@Override
	public void onOreEvent() {
		OreDictionary.registerOre(tool + ore, this);
	}

	@Override
	public void recipe() {
		if (material.getRepairItemStack().isEmpty())
			material.setRepairItem(OreDictionary.getOres(oreMaterial).get(0));

		Item shield = Loader.isModLoaded("spartanshields") ? Item.getByNameOrId("spartanshields:shield_basic_wood")
				: Items.SHIELD;

		new RecipeHandler.ShapedRecipe(new ItemStack(this),
				new Object[] { " x ", "xyx", " x ", 'x', oreMaterial, 'y', shield });
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		if (repair == null || repair.isEmpty())
			return false;

		for (int id : OreDictionary.getOreIDs(repair))
			if (id == OreDictionary.getOreID(oreMaterial))
				return true;
		return false;
	}

}
