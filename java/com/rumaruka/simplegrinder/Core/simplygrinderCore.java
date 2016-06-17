package com.rumaruka.simplegrinder.Core;

import com.rumaruka.simplegrinder.Init.BlocksCore;
import com.rumaruka.simplegrinder.Init.CraftingCore;
import com.rumaruka.simplegrinder.Init.EventRegister;
import com.rumaruka.simplegrinder.Init.FurnaceSmelting;
import com.rumaruka.simplegrinder.Init.ItemsCore;
import com.rumaruka.simplegrinder.Init.TileEntityCore;
import com.rumaruka.simplegrinder.Proxy.CommonProxy;
import com.rumaruka.simplegrinder.Reference.Reference;
import com.rumaruka.simplegrinder.Core.ConfigHandler;

import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid=Reference.MODID, name = Reference.MODNAME, version=Reference.MODVERSIONS)
public class simplygrinderCore {
	
	public static boolean jeiModLoader = false;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	@Mod.Instance(Reference.MODID)
	public static simplygrinderCore instance;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent e)
	{
		
		 jeiModLoader = Loader.isModLoaded("");
		
		
		
		
		
		//Blocks
		BlocksCore.init();
		BlocksCore.InGameRegister();
		//TileEntity
		TileEntityCore.InGameRegist();
		//Items
		ItemsCore.init();
		ItemsCore.InGameRegister();
		//EventHandler
		EventRegister.RegisetHandlers();
		//Crafting
		CraftingCore.CraftingCoreInit();
		//Smelting
		FurnaceSmelting.Smelting();
		//Load ConfigHandler
		ConfigHandler.startConfig(e);
		//Load Config
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
  		config.load();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e)
	{
		proxy.Renders();
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent e)
	{
		
	}
	
	
	
	
	

}
