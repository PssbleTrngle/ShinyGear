package com.possibletriangle.shinygear;

import java.awt.Color;
import java.util.ArrayList;

import com.possibletriangle.shinygear.proxy.CommonProxy;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class OreManager {

	public static final String[] BIOEMS_O_PLENTY = new String[] { "Amethyst", "Ruby", "Peridot", "Sapphire",
			"Malachite", "Topaz", "Tanzanite" };
	public static final String[] VANILLA = new String[] { "Emerald" };

	public static ArrayList<String[]> forCategory(String cat) {

		cat = cat.toLowerCase();
		ArrayList<String[]> ores = new ArrayList<String[]>();

		if (Loader.isModLoaded("biomesoplenty"))
			for (String ore : BIOEMS_O_PLENTY)
				if (CommonProxy.enabled(cat, ore))
					ores.add(new String[] { "gem", ore });
		for (String ore : VANILLA)
			if (CommonProxy.enabled(cat, ore))
				ores.add(new String[] { "gem", ore });

		return ores;

	}

	public static int getColor(String ore) {

		switch (ore.replace("gem", "").toLowerCase()) {
		case "amethyst":
			return new Color(255, 51, 255).getRGB();
		case "ruby":
			return new Color(255, 51, 54).getRGB();
		case "peridot":
			return new Color(116, 255, 51).getRGB();
		case "sapphire":
			return new Color(5, 163, 255).getRGB();
		case "malachite":
			return new Color(51, 255, 153).getRGB();
		case "topaz":
			return new Color(255, 129, 51).getRGB();
		case "tanzanite":
			return new Color(153, 51, 255).getRGB();
		case "amber":
			return new Color(255, 194, 51).getRGB();
		case "emerald":
			return new Color(51, 255, 92).getRGB();
		default:
			return -1;
		}

	}

}
