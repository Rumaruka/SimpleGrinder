package com.rumaruka.simplegrinder.Core;

import com.rumaruka.simplegrinder.Reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {

	public static Configuration config;

	private static final String comments = Reference.MODNAME + " Config" + Reference.MODVERSIONS;
	
	private static final String outputComment = "Output Amount";

	
	private static  int bonemealOutput = 0;
	private static  int irondustOutput = 0;
	private static  int golddustOutput = 0;
	private static  int diamondOutput = 0;
	private static  int cobblesandOutput = 0;
	private static  int cactusgreenOutput = 0;
	private static  int emeraldOutput = 0;
	private static  int mashedpotatoOutput = 0;
	private static  int coalOutput = 0;
	private static  int redstoneOutput = 0;
	private static  int lapisOutput = 0;
	private static  int quartzOutput = 0;
	private static  int flourOutput = 0;
	private static  int mashedcarrotOutput = 0;
	private static  int sandstonesandOutput = 0;
	private static  int flintOutput = 0;
	private static  int bookOutput = 0;
	private static  int prismarineshardOutput = 0;
	private static  int woodchipsOutput = 0;
	private static  int diamondhorsearmorOutput = 0;
	private static  int irondusthorsearmorOutput = 0;
	private static  int golddusthorsearmorOutput = 0;
	private static  int omleteOutput = 0;

	public static void startConfig(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load(); // only need to load config once during pre initializeSoundEvents
		updateConfigInfo();
	}

	public static void updateConfigInfo()
	{
		try {
			config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, comments);


			bonemealOutput = config.get(Configuration.CATEGORY_GENERAL, "BoneMealOutput", 5, outputComment).getInt();
			irondustOutput = config.get(Configuration.CATEGORY_GENERAL, "IronDustOutput", 3, outputComment).getInt();
			golddustOutput = config.get(Configuration.CATEGORY_GENERAL, "GoldDustOutput", 3, outputComment).getInt();
			diamondOutput = config.get(Configuration.CATEGORY_GENERAL, "DiamondOutput", 4, outputComment).getInt();
			cobblesandOutput = config.get(Configuration.CATEGORY_GENERAL, "CobbleSandOutput", 2, outputComment).getInt();
			cactusgreenOutput = config.get(Configuration.CATEGORY_GENERAL, "CactusGreenOutput", 5, outputComment).getInt();
			emeraldOutput = config.get(Configuration.CATEGORY_GENERAL, "EmeraldOutput", 4, outputComment).getInt();
			mashedpotatoOutput = config.get(Configuration.CATEGORY_GENERAL, "MashedPotatoOutput", 2, outputComment).getInt();
			coalOutput = config.get(Configuration.CATEGORY_GENERAL, "CoalOutput", 15, outputComment).getInt();
			redstoneOutput = config.get(Configuration.CATEGORY_GENERAL, "RedStoneOutput", 20, outputComment).getInt();
			lapisOutput = config.get(Configuration.CATEGORY_GENERAL, "LapisOutput", 15, outputComment).getInt();
			quartzOutput = config.get(Configuration.CATEGORY_GENERAL, "QuartzOutput", 15, outputComment).getInt();
			flourOutput = config.get(Configuration.CATEGORY_GENERAL, "FlourOutput", 2, outputComment).getInt();
			mashedcarrotOutput = config.get(Configuration.CATEGORY_GENERAL, "MashedCarrotOutput", 2, outputComment).getInt();
			sandstonesandOutput = config.get(Configuration.CATEGORY_GENERAL, "SandstoneSandOutput", 4, outputComment).getInt();
			flintOutput = config.get(Configuration.CATEGORY_GENERAL, "FlintOutput", 2, outputComment).getInt();
			bookOutput = config.get(Configuration.CATEGORY_GENERAL, "BookOutput", 2, outputComment).getInt();
			prismarineshardOutput = config.get(Configuration.CATEGORY_GENERAL, "PrisMarineshardOutput", 2, outputComment).getInt();
			woodchipsOutput = config.get(Configuration.CATEGORY_GENERAL, "WoodChipsOutput", 2, outputComment).getInt();
			diamondhorsearmorOutput = config.get(Configuration.CATEGORY_GENERAL, "DiamondHorseArmorOutput", 2, outputComment).getInt();
			irondusthorsearmorOutput = config.get(Configuration.CATEGORY_GENERAL, "IronDustHorseArmorOutput", 2, outputComment).getInt();
			golddusthorsearmorOutput = config.get(Configuration.CATEGORY_GENERAL, "GoldDustHorseArmorOutput", 2, outputComment).getInt();
			omleteOutput = config.get(Configuration.CATEGORY_GENERAL, "OmleteOutput", 2, outputComment).getInt();

			config.get(Configuration.CATEGORY_GENERAL, "BoneMealOutput", 5, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "IronDustOutput", 3, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "GoldDustOutput", 3, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "DiamondOutput", 4, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "CobbleSandOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "CactusGreenOutput", 5, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "EmeraldOutput", 4, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "MashedPotatoOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "CoalOutput", 15, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "RedStoneOutput", 20, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "LapisOutput", 15, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "QuartzOutput", 15, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "FlourOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "MashedCarrotOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "SandstoneSandOutput", 4, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "FlintOutput", 4, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "BookOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "PrisMarineshardOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "WoodChipsOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "DiamondHorseArmorOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "IronDustHorseArmorOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "GoldDustHorseArmorOutput", 2, outputComment).getInt();
			config.get(Configuration.CATEGORY_GENERAL, "OmleteOutput", 2, outputComment).getInt();


		} catch (Exception e) {
			simplygrinderCore.proxy.info("failed to load or read the config file");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}


	public static int getBoneMealOutput()
	{
		return bonemealOutput;
		
	}

	public static int getIronDustOutput()
	{
		return irondustOutput;
		
	}

	public static int getGoldDustOutput()
	{
		return golddustOutput;
		
	}
	
	public static int getDiamondOutput()
	{
		return diamondOutput;
		
	}

	public static int getCobbleSandOutput()
	{
		return cobblesandOutput;
		
	}

	public static int getCactusGreenOutput()
	{
		return cactusgreenOutput;
		
	}

	public static int getEmeraldOutput()
	{
		return emeraldOutput;
		
	}

	public static int getMashedPotatoOutput()
	{
		return mashedpotatoOutput;
		
	}

	public static int getCoalOutput()
	{
		return coalOutput;
		
	}

	public static int getRedStoneOutput()
	{
		return redstoneOutput;
		
	}
	
	public static int getLapisOutput()
	{
		return lapisOutput;
		
	}

	public static int getQuartzOutput()
	{
		return quartzOutput;
		
	}
	
	public static int getFlourOutput()
	{
		return flourOutput;
		
	}
	
	public static int getMashedCarrotOutput()
	{
		return mashedcarrotOutput;
		
	}	
	
	public static int getSandstoneSandOutput()
	{
		return sandstonesandOutput;
		
	}	
	
	public static int getFlintOutput()
	{
		return flintOutput;
		
	}
	
	public static int getBookOutput()
	{
		return bookOutput;
		
	}

	public static int getPrisMarineShardOutput()
	{
		return prismarineshardOutput;
		
	}	

	public static int getWoodChipsOutput()
	{
		return woodchipsOutput;
		
	}	
	
	public static int getDiamondHorseArmorOutput()
	{
		return diamondhorsearmorOutput;
		
	}
	
	public static int getIronDustHorseArmorOutput()
	{
		return irondusthorsearmorOutput;
		
	}
	
	public static int getGoldDustHorseArmorOutput()
	{
		return golddusthorsearmorOutput;
		
	}

	public static int getOmleteOutput()
	{
		return omleteOutput;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
}