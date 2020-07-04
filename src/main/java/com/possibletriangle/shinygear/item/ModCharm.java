package com.possibletriangle.shinygear.item;

import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.recipes.RecipeHandler;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.oredict.OreDictionary;

public class ModCharm extends Item implements IBauble, IModItem {

	private String ore, oreMaterial;

	@Override
	public String ore() {
		return ore;
	}

	@Override
	public String type() {
		return "charm";
	}

	@Override
	public void onOreEvent() {}

	public ModCharm(String prefix, String oreMaterial) {

		this.ore = IModItem.parse(prefix);
		this.oreMaterial = oreMaterial;
		String name = prefix + "_charm";

		setRegistryName(name);
		setUnlocalizedName(ShinyGear.MODID + "." + name);
		setCreativeTab(CreativeTabs.TOOLS);

		ModItems.LIST.add(this);
	}

	@Override
	public void recipe() {

		new RecipeHandler.ShapedRecipe(new ItemStack(this),
				new Object[] { " z ", "x x", " y ", 'y', oreMaterial, 'x', Items.STRING, 'z', "nuggetIron" });

	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.CHARM;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

		switch (oreMaterial.replace("gem", "").toLowerCase()) {
		case "amethyst":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("invisibility"), 2, 0));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("hunger"), 2, 1, false, false));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 2, 1, false, false));
			break;
		case "ruby":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("strength"), 2, 0));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("mining_fatigue"), 2, 1, false, false));
			break;
		case "sapphire":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("water_breathing"), 2, 0));
			if (player.isInWater()) {

				if (player.world.getBlockState(player.getPosition().up()).getBlock().equals(Blocks.AIR)) {
					if (!player.world.getBlockState(player.getPosition()).getBlock().equals(Blocks.AIR))
						if (player.motionY < 0)
							player.motionY = 0;

				} else {
					if (player.motionY < 0.95)
						player.motionY += 0.05;
					else if (player.motionY < 1)
						player.motionY = 1;

					if (player.world.isRemote)
						for (int i = 0; i < 20; i++) {

							double rx = 0.8 * (Math.random() - 0.5);
							double ry = 0.8 * (Math.random() * 2 + 1);
							double rz = 0.8 * (Math.random() - 0.5);

							player.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, player.posX + rx, player.posY,
									player.posZ + rz, rx, 2, rz);
						}
				}
			}
			break;
		case "topaz":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("haste"), 2, 0));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 2, 1, false, false));
			break;
		case "peridot":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("regeneration"), 2, 0));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("jump_boost"), 2, 150, false, false));
			break;
		case "malachite":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("speed"), 2, 0));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("unluck"), 2, 4, false, false));
			break;
		case "tanzanite":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("night_vision"), 2, 0));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 2, 3, false, false));
			break;
		case "amber":
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("luck"), 2, 0));
			player.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("glowing"), 2, 0, false, false));
			break;
		}

	}

}
