package com.possibletriangle.shinygear.recipes;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.possibletriangle.shinygear.ShinyGear;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {

	private static final ArrayList<ShapedRecipe> SHAPED = new ArrayList<ShapedRecipe>();
	private static final ArrayList<ShapelessRecipe> SHAPELESS = new ArrayList<ShapelessRecipe>();

	public static String getMaterial(String[] mats, String size) {

		for (String mat : mats) {
			mat = mat.substring(0, 1).toUpperCase() + mat.substring(1).toLowerCase();
			size.toLowerCase();

			if (OreDictionary.doesOreNameExist(size + mat))
				return size + mat;

		}

		return size + "Iron";
	}

	public static String getMaterial(String mat, String size) {
		return getMaterial(new String[] { mat }, size);
	}

	public static List<ItemStack> tools(String toolname) {

		ArrayList<ItemStack> tools = new ArrayList<ItemStack>();

		if (toolname.equals("sword"))
			for (ResourceLocation key : Item.REGISTRY.getKeys()) {
				Item item = Item.REGISTRY.getObject(key);
				if (item instanceof ItemSword)
					tools.add(new ItemStack(item));
			}

		else
			for (ResourceLocation key : Item.REGISTRY.getKeys()) {
				Item item = Item.REGISTRY.getObject(key);
				if (item instanceof ItemTool
						&& ((ItemTool) item).getToolClasses(new ItemStack(item)).contains(toolname))
					tools.add(new ItemStack(item));

			}

		return tools;

	}

	public static void init() {
		ShinyGear.LOGGER.log(Level.INFO, "Initialising Recipes");

		if (!SHAPED.isEmpty())
		System.out.println("Registering " + SHAPED.size() + " shaped recipes");
		for (ShapedRecipe recipe : SHAPED.toArray(new ShapedRecipe[] {})) {

			try {
				GameRegistry.addShapedRecipe(recipe.name, recipe.group, recipe.output, recipe.input);

			} catch (RuntimeException ex) {
				System.err.println("Error at shaped recipe " + recipe.name);
			}

		}

		if (!SHAPELESS.isEmpty())
			System.out.println("Registering " + SHAPELESS.size() + " shapeless recipes");
		for (ShapelessRecipe recipe : SHAPELESS.toArray(new ShapelessRecipe[] {})) {

			try {
				GameRegistry.addShapelessRecipe(recipe.name, recipe.group, recipe.output, recipe.input);

			} catch (RuntimeException ex) {
				System.err.println("Error at shaped recipe " + recipe.name);
			}

		}

	}

	public static class ShapedRecipe {

		public ResourceLocation name, group;
		public ItemStack output;
		public Object[] input;

		public ShapedRecipe(String name, ItemStack output, Object[] input) {
			this.group = new ResourceLocation(ShinyGear.MODID);
			this.name = new ResourceLocation(ShinyGear.MODID + ":" + name);
			this.output = output;
			this.input = input;

			SHAPED.add(this);
		}

		public ShapedRecipe(ItemStack output, Object[] input) {
			this(output.getItem().getUnlocalizedName(), output, input);
		}

	}

	public static class ShapelessRecipe {

		public ResourceLocation name, group;
		public ItemStack output;
		public Ingredient[] input;

		public ShapelessRecipe(String name, ItemStack output, Ingredient... input) {
			this.group = new ResourceLocation(ShinyGear.MODID);
			this.name = new ResourceLocation(ShinyGear.MODID + ":" + name);
			this.output = output;
			this.input = input;

			SHAPELESS.add(this);
		}

		public ShapelessRecipe(String name, ItemStack output, ItemStack... input) {
			this.group = new ResourceLocation(ShinyGear.MODID);
			this.name = new ResourceLocation(ShinyGear.MODID + ":" + name);
			this.output = output;
			this.input = new Ingredient[input.length];
			for (int i = 0; i < input.length; i++)
				this.input[i] = Ingredient.fromStacks(input[i]);

			SHAPELESS.add(this);
		}

		public ShapelessRecipe(ItemStack output, ItemStack... input) {
			this(output.getItem().getUnlocalizedName(), output, input);
		}

		public ShapelessRecipe(ItemStack output, Ingredient... input) {
			this(output.getItem().getUnlocalizedName(), output, input);
		}

	}
}
