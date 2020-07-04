package com.possibletriangle.shinygear;

import java.util.HashMap;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {

	public static final HashMap<String, ToolMaterial> TOOL = new HashMap<String, ToolMaterial>();
	public static final HashMap<String, ArmorMaterial> ARMOR = new HashMap<String, ArmorMaterial>();

	public static void init() {

		{
			int level = ToolMaterial.DIAMOND.getHarvestLevel();
			int maxUses = ToolMaterial.DIAMOND.getMaxUses();
			float efficiency = ToolMaterial.DIAMOND.getEfficiency();
			float damage = ToolMaterial.DIAMOND.getAttackDamage();
			int enchantability = ToolMaterial.DIAMOND.getEnchantability();

			TOOL.put("Amethyst", EnumHelper.addToolMaterial("AMETHYST", level, (int) (maxUses * 1.5), efficiency * 1.6F,
					damage * 1.6F, enchantability));
			TOOL.put("Ruby", EnumHelper.addToolMaterial("RUBY", level, maxUses, efficiency * 0.8F, damage * 1.2F,
					(int) (enchantability * 0.9)));
			TOOL.put("Peridot", EnumHelper.addToolMaterial("PERIDOT", level, (int) (maxUses * 1.1), efficiency * 1.2F,
					damage * 0.8F, enchantability));
			TOOL.put("Sapphire", EnumHelper.addToolMaterial("SAPPHIRE", level, maxUses, efficiency, damage * 0.9F,
					(int) (enchantability * 1.2)));
			TOOL.put("Malachite", EnumHelper.addToolMaterial("MALACHITE", level, maxUses, efficiency * 1.2F, damage,
					(int) (enchantability * 1.1)));
			TOOL.put("Topaz", EnumHelper.addToolMaterial("TOPAZ", level, (int) (maxUses * 0.8), efficiency * 0.9F,
					damage * 1.2F, enchantability));
			TOOL.put("Tanzanite", EnumHelper.addToolMaterial("TANZANITE", level, maxUses, efficiency, damage,
					(int) (enchantability * 1.3)));
			TOOL.put("Emerald", EnumHelper.addToolMaterial("EMERALD", level, (int) (maxUses*1.35), efficiency*1.2F, damage*0.8F,
					(int) (enchantability * 1.3)));
		}

		{
			SoundEvent sound = ArmorMaterial.DIAMOND.getSoundEvent();
			int durability = ArmorMaterial.DIAMOND.getDurability(EntityEquipmentSlot.CHEST) / 15;
			int[] amounts = new int[] { 3, 6, 8, 3 };
			int enchantability = ArmorMaterial.DIAMOND.getEnchantability();
			float toughness = ArmorMaterial.DIAMOND.getToughness();

			ARMOR.put("Amethyst", EnumHelper.addArmorMaterial("AMETHYST", "amethyst", (int) (durability * 1.5), amounts,
					enchantability, sound, toughness * 1.3F));
			ARMOR.put("Ruby", EnumHelper.addArmorMaterial("RUBY", "ruby", durability, amounts,
					(int) (enchantability * 0.9F), sound, toughness * 1.15F));
			ARMOR.put("Peridot", EnumHelper.addArmorMaterial("PERIDOT", "peridot", (int) (durability * 1.1), amounts,
					enchantability, sound, toughness));
			ARMOR.put("Sapphire", EnumHelper.addArmorMaterial("SAPPHIRE", "sapphire", durability, amounts,
					(int) (enchantability * 1.2), sound, toughness));
			ARMOR.put("Malachite", EnumHelper.addArmorMaterial("MALACHITE", "malachite", durability, amounts,
					(int) (enchantability * 1.1), sound, toughness * 0.9F));
			ARMOR.put("Topaz", EnumHelper.addArmorMaterial("TOPAZ", "topaz", (int) (durability * 0.8), amounts,
					enchantability, sound, toughness * 1.1F));
			ARMOR.put("Tanzanite", EnumHelper.addArmorMaterial("TANZANITE", "tanzanite", durability, amounts,
					(int) (enchantability * 1.3), sound, toughness * 1.15F));
			ARMOR.put("Emerald", EnumHelper.addArmorMaterial("EMERALD", "emerald", (int) (durability * 1.35), amounts,
					(int) (enchantability * 0.85), sound, toughness * 0.75F));
		}
	}

}
