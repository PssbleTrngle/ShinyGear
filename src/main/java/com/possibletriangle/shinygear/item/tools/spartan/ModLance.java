package com.possibletriangle.shinygear.item.tools.spartan;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemLance;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;
import com.possibletriangle.shinygear.recipes.RecipeHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;

public class ModLance extends ItemLance implements IModItem {

	private static final String tool = "lance";
	private String ore, oreMaterial;

	@Override
	public String type() {
		return tool;
	}

	@Override
	public String ore() {
		return ore;
	}

	public ModLance(String prefix, ToolMaterial material, String oreMaterial) {
		super( prefix + "_" + tool, ShinyGear.MODID, IModSpartanItem.fromMaterial(material, oreMaterial), IModSpartanItem.getDamage(7, material));

		this.ore = IModItem.parse(prefix);
		this.oreMaterial = oreMaterial;

		setUnlocalizedName(ShinyGear.MODID + "." +  prefix + "_" + tool);

		ModItems.LIST.add(this);
	}

	@Override
	public void onOreEvent() {
		OreDictionary.registerOre(tool + ore, this);
	}

	@Override
	public void recipe() {
		new RecipeHandler.ShapedRecipe(new ItemStack(this, 1), new Object[] { "x", "y", "z", "x ", 'x', oreMaterial,
				'y', new ItemStack(ItemRegistrySW.material, 1, 1), 'z', new ItemStack(ItemRegistrySW.material, 1, 0) });
	}

}
