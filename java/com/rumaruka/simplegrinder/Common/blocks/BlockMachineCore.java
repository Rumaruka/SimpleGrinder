package com.rumaruka.simplegrinder.Common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMachineCore extends Block {

	public BlockMachineCore( ) {
		super(Material.rock);
		setHarvestLevel("pickaxe", 0);
		
	}
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean isFullCube()
	{
		return false;
	}
}
