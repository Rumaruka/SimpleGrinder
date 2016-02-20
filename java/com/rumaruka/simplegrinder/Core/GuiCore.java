package com.rumaruka.simplegrinder.Core;

import com.rumaruka.simplegrinder.Client.gui.GuiCoalGrinder;
import com.rumaruka.simplegrinder.Common.containers.ContainerCoaGrinder;
import com.rumaruka.simplegrinder.Common.tileentity.TileEntityCoalGrinder;
import com.rumaruka.simplegrinder.Reference.GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiCore implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	
		if (ID==GUI.CoalGrinder.ordinal()) {
			TileEntityCoalGrinder teCG = (TileEntityCoalGrinder) world.getTileEntity(new BlockPos(x,y,z));
			return new ContainerCoaGrinder(player.inventory, (TileEntityCoalGrinder)world.getTileEntity(new BlockPos(x, y, z)));
		
	}
	return null;
	}


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID==GUI.CoalGrinder.ordinal()) {
			TileEntityCoalGrinder teCG = (TileEntityCoalGrinder) world.getTileEntity(new BlockPos(x,y,z));
			return new GuiCoalGrinder(player.inventory, (TileEntityCoalGrinder)world.getTileEntity(new BlockPos(x, y, z)));
	}
		return null;
	}
}