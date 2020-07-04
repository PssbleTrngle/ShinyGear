package com.possibletriangle.shinygear.compat.tconstruct;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitExpensive extends AbstractTrait {

	public TraitExpensive() {
		super("expensive", new Color(47, 196, 104).getRGB());
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, HarvestDropsEvent event) {
		event.setDropChance(event.getDropChance() * 1.8F);
	}

}
