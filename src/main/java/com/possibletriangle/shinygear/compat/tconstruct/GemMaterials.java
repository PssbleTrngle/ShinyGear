package com.possibletriangle.shinygear.compat.tconstruct;

import com.possibletriangle.shinygear.OreManager;
import com.possibletriangle.shinygear.ShinyGear;

import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.tools.TinkerTraits;

public class GemMaterials {

	public static void init() {

		TraitShiny shiny = new TraitShiny();
		TraitExpensive expensive = new TraitExpensive();
		TraitGaia gaia = new TraitGaia();

		ores: for (String[] ore : OreManager.forCategory("tool_materials")) {

			for (Material mat : TinkerRegistry.getAllMaterials())
				if (mat.getIdentifier().equals(ore[1].toLowerCase())) {
					ShinyGear.LOGGER.info(
							"Did not create ToolMaterial for \"" + ore[0] + ore[1] + "\" because it already existed");
					continue ores;
				}

			{

				Material mat = new Material(ore[1].toLowerCase(), OreManager.getColor(ore[0] + ore[1]), false);
				mat.setRepresentativeItem(ore[0] + ore[1]);
				mat.setCraftable(true);
				mat.setCastable(false);
				mat.addItem(ore[0] + ore[1], 1, 100 * 100 / 69);
				mat.setVisible();

				if (ore[0].equals("gem"))
					mat.addTrait(shiny);

				switch (ore[1].toLowerCase()) {
				case "malachite":
				case "tanzanite":
					mat.addTrait(TinkerTraits.coldblooded, MaterialTypes.HEAD);
					break;
				case "ruby":
					mat.addTrait(TinkerTraits.aridiculous, MaterialTypes.HEAD);
					break;
				case "sappire":
					mat.addTrait(TinkerTraits.aquadynamic, MaterialTypes.HEAD);
					break;

				case "topaz":
				case "peridot":
					mat.addTrait(gaia, MaterialTypes.HEAD);
					break;
				case "emerald":
					mat.addTrait(expensive, MaterialTypes.HEAD);
					break;
				case "amethyst":
					mat.addTrait(TinkerTraits.alien, MaterialTypes.HEAD);
				}

				TinkerRegistry.addMaterial(mat);
				TinkerRegistry.addMaterialStats(mat, new HeadMaterialStats(400, 7, 5, 3),
						new HandleMaterialStats(0.5F, 400), new ExtraMaterialStats(100),
						new BowMaterialStats(1.5F, 1.2F, 8));

			}
		}

	}

}
