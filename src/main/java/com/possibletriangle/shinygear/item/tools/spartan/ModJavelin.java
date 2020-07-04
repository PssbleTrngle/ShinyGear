package com.possibletriangle.shinygear.item.tools.spartan;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemJavelin;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;
import com.possibletriangle.shinygear.recipes.RecipeHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModJavelin extends ItemJavelin implements IModItem {

	private static final String tool = "javelin";
	private String ore, oreMaterial;

	@Override
	public String type() {
		return tool;
	}

	@Override
	public String ore() {
		return ore;
	}

	public ModJavelin(String prefix, ToolMaterial mat, String oreMaterial) {
		super(prefix + "_" + tool, ShinyGear.MODID, IModSpartanItem.fromMaterial(mat, oreMaterial), IModSpartanItem.getDamage(3, mat));

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
		new RecipeHandler.ShapedRecipe(new ItemStack(this, 1),
				new Object[] { "yx", 'x', oreMaterial, 'y', new ItemStack(ItemRegistrySW.material, 1, 1) });
	}

}
