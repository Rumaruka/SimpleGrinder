package com.rumaruka.simplegrinder.Init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingCore {
	
	
	public static void CraftingCoreInit(){
		
		
		ItemStack outPut;
		
		outPut = new ItemStack(BlocksCore.machine_core);
		GameRegistry.addRecipe(outPut, new Object[]{
				"SSS",
				"RPR",
				"SSS", 'S', Blocks.stone, 'R', Items.redstone, 'P', Blocks.piston
		});
		ItemStack outPUT;
		outPUT = new ItemStack(BlocksCore.coal_grinder);
		GameRegistry.addRecipe(outPUT, new Object[]{
				"SIS",
				"SMS",
				"IPI", 'S', Blocks.stone, 'I', Items.iron_ingot, 'P', Blocks.piston, 'M', BlocksCore.machine_core
		});
		
		
	}

}
