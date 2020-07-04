package com.possibletriangle.shinygear.compat.tconstruct;

import java.awt.Color;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitGaia extends AbstractTrait {

	public TraitGaia() {
		super("gaia", new Color(99, 204, 42).getRGB());
	}

	@Override
	public void miningSpeed(ItemStack tool, BreakSpeed event) {
		event.setNewSpeed(getModifiedValue(event.getPos().getY(), event.getOriginalSpeed()));
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage,
			boolean isCritical) {
		return getModifiedValue(player.getPosition().getY(), damage);
	}

	float getModifiedValue(int yLevel, float base) {

		float m = (100 - yLevel) * 0.065F;
		
		return base + m;
		
	}

}
