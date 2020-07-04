package com.possibletriangle.shinygear.item.tools.spartan;

import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.item.ItemBattleaxe;
import com.oblivioussp.spartanweaponry.item.ItemBoomerang;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;
import com.possibletriangle.shinygear.recipes.RecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModBattleaxe extends ItemBattleaxe implements IModSpartanItem {

	private static final String tool = "battleaxe";
	private String ore, oreMaterial;

	@Override
	public String type() {
		return tool;
	}

	@Override
	public String ore() {
		return ore;
	}

	public ModBattleaxe(String prefix, ToolMaterial mat, String oreMaterial) {
		super(prefix + "_" + tool, ShinyGear.MODID, IModSpartanItem.fromMaterial(mat, oreMaterial), IModSpartanItem.getDamage(11, mat));

		this.ore = IModItem.parse(prefix);
		this.oreMaterial = oreMaterial;
		String name = prefix + "_" + tool;

		setUnlocalizedName(ShinyGear.MODID + "." + name);

		ModItems.LIST.add(this);
	}

	@Override
	public void onOreEvent() {
		OreDictionary.registerOre(tool + ore, this);
	}

	@Override
	public void recipe() {
		new RecipeHandler.ShapedRecipe(new ItemStack(this, 1), new Object[] {
				"xxx", "xsx", " y ", 'x', oreMaterial,
				'y', new ItemStack(ItemRegistrySW.material, 1, 0),
				's', "stickWood" });
	}

}
