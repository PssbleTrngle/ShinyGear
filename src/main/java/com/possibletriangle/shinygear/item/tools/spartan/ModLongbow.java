package com.possibletriangle.shinygear.item.tools.spartan;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemLongbow;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;
import com.possibletriangle.shinygear.recipes.RecipeHandler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;

public class ModLongbow extends ItemLongbow implements IModItem {

	private static final String tool = "longbow";
	private String ore, oreMaterial;

	@Override
	public String type() {
		return tool;
	}

	@Override
	public String ore() {
		return ore;
	}

	public ModLongbow(String prefix, ToolMaterial material, String oreMaterial) {
		super( prefix + "_" + tool, ShinyGear.MODID, IModSpartanItem.fromMaterial(material, oreMaterial));

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
		new RecipeHandler.ShapedRecipe(new ItemStack(this, 1), new Object[] { "ysx", "s t", "xtt", 'x', oreMaterial,
				'y', new ItemStack(ItemRegistrySW.material, 1, 0), 's', "stickWood", 't', Items.STRING });
	}

}
