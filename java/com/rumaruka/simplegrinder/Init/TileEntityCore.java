package com.rumaruka.simplegrinder.Init;

import com.rumaruka.simplegrinder.Common.tileentity.TileEntityCoalGrinder;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityCore {
	
	
	public static void InGameRegist(){
		GameRegistry.registerTileEntity(TileEntityCoalGrinder.class, "TileEntityCoalGrinder");
	}

}
