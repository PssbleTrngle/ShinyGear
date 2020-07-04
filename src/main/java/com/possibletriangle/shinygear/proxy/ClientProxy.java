package com.possibletriangle.shinygear.proxy;

import com.possibletriangle.shinygear.OreManager;
import com.possibletriangle.shinygear.item.IModItem;
import com.possibletriangle.shinygear.item.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.TutorialToast.Icons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);

		MinecraftForge.EVENT_BUS.register(Icons.class);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModItems.initModels();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void itemColors() {

		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(ClientProxy::color, ModItems.LIST.toArray(new Item[0]));

	}

	private static int color(ItemStack stack, int index) {
		return stack.isEmpty() || index != 1 ? -1 : OreManager.getColor(((IModItem) stack.getItem()).ore());
	}

}
