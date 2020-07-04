package com.possibletriangle.shinygear.item.tools;

import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;
import com.possibletriangle.shinygear.recipes.RecipeHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModPickaxe extends ItemPickaxe implements IModItem {

	private static final String tool = "pickaxe";
	private String ore, oreMaterial, prefix;
	private ToolMaterial material;

	@Override
	public String type() {
		return tool;
	}

	@Override
	public boolean isIron() {
		return ore().toLowerCase().equals("amethyst");
	}

	@Override
	public String ore() {
		return ore;
	}

	public ModPickaxe(String prefix, ToolMaterial material, String oreMaterial) {
		super(material);

		this.ore = IModItem.parse(prefix);
		this.material = material;
		this.oreMaterial = oreMaterial;
		this.prefix = prefix;

		String name = prefix + "_" + tool;
		setRegistryName(name);
		setUnlocalizedName(ShinyGear.MODID + "." + name);
		setCreativeTab(CreativeTabs.TOOLS);

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

		new RecipeHandler.ShapedRecipe(new ItemStack(this),
				new Object[] { "xxx", " y ", " y ", 'x', oreMaterial, 'y', prefix.equals("amethyst") ? "ingotIron" : "stickWood" });

	}

}
