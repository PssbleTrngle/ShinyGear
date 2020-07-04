package com.possibletriangle.shinygear.item.tools.spartan;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.possibletriangle.shinygear.OreManager;
import com.possibletriangle.shinygear.ShinyGear;
import com.possibletriangle.shinygear.item.IModItem;
import net.minecraft.item.Item;

public interface IModSpartanItem extends IModItem {

    static float getDamage(float factor, Item.ToolMaterial m) {
        return m == null ? 1F : factor * (m.getAttackDamage() / Item.ToolMaterial.DIAMOND.getAttackDamage());
    }

    static ToolMaterialEx fromMaterial(Item.ToolMaterial m, String oreMaterial) {
        int color = OreManager.getColor(oreMaterial);
        return m == null ? ToolMaterialEx.LEATHER : new ToolMaterialEx(m.name(), oreMaterial, ShinyGear.MODID, color, color, m.getHarvestLevel(),
                m.getMaxUses(), m.getEfficiency(), m.getAttackDamage(), m.getEnchantability());
    }

}
