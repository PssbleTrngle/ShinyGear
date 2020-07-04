package com.possibletriangle.shinygear.compat;

import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.OreManager;
import com.possibletriangle.shinygear.item.ModCharm;

public class BaublesCompat {

	public static void init() {
		
		ShinyGear.LOGGER.info("Creating Charms");

		for (String[] ore : OreManager.forCategory("charms")) {

			new ModCharm(ore[1].toLowerCase(), ore[0] + ore[1]);

		}

	}

}
