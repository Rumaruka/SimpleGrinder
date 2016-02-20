package com.rumaruka.simplegrinder.Init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnaceSmelting {
	
	public static void Smelting(){
		GameRegistry.addSmelting(new ItemStack(ItemsCore.dust_iron),  new ItemStack(Items.iron_ingot), 0.5F);
		GameRegistry.addSmelting(new ItemStack(ItemsCore.dust_gold),  new ItemStack(Items.gold_ingot), 0.5F);
		GameRegistry.addSmelting(new ItemStack(ItemsCore.flour),  new ItemStack(Items.bread), 0.5F);
	}

}
