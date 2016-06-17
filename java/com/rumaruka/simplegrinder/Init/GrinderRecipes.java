package com.rumaruka.simplegrinder.Init;

import com.rumaruka.simplegrinder.Core.ConfigHandler;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class GrinderRecipes {

	  private static final GrinderRecipes smeltingBase = new GrinderRecipes();
	    private Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	    private Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	    /**
	     * Returns an instance of FurnaceRecipes.
	     */
	    public static GrinderRecipes instance()
	    {
	        return smeltingBase;
	    }

	    public GrinderRecipes()
	    {
	    	this.addSmelting(Items.BONE, new ItemStack(Items.DYE, ConfigHandler.getBoneMealOutput(), EnumDyeColor.WHITE.getDyeDamage()), 0.2F);
	        this.addSmeltingRecipeForBlock(Blocks.IRON_ORE, new ItemStack(ItemsCore.dust_iron,ConfigHandler.getIronDustOutput()), 0.7F);
	        this.addSmeltingRecipeForBlock(Blocks.GOLD_ORE, new ItemStack(ItemsCore.dust_gold,ConfigHandler.getGoldDustOutput()), 1.0F);
	        this.addSmeltingRecipeForBlock(Blocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND,ConfigHandler.getDiamondOutput()), 1.0F);
	        this.addSmeltingRecipeForBlock(Blocks.COBBLESTONE, new ItemStack(Blocks.SAND,ConfigHandler.getCobbleSandOutput()), 0.1F);
	        this.addSmeltingRecipeForBlock(Blocks.CACTUS, new ItemStack(Items.DYE, ConfigHandler.getCactusGreenOutput(), EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
	        this.addSmeltingRecipeForBlock(Blocks.EMERALD_ORE, new ItemStack(Items.EMERALD,ConfigHandler.getEmeraldOutput()), 1.0F);
	        this.addSmelting(Items.POTATO, new ItemStack(ItemsCore.mash_potato,ConfigHandler.getMashedPotatoOutput()), 0.35F);
	        this.addSmeltingRecipeForBlock(Blocks.COAL_ORE, new ItemStack(Items.COAL,ConfigHandler.getCoalOutput()), 0.1F);
	        this.addSmeltingRecipeForBlock(Blocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE,ConfigHandler.getRedStoneOutput()), 0.7F);
	        this.addSmeltingRecipeForBlock(Blocks.LAPIS_ORE, new ItemStack(Items.DYE, ConfigHandler.getLapisOutput(), EnumDyeColor.BLUE.getDyeDamage()), 0.2F);
	        this.addSmeltingRecipeForBlock(Blocks.QUARTZ_ORE, new ItemStack(Items.QUARTZ,ConfigHandler.getQuartzOutput()), 0.2F);
	        this.addSmelting(Items.WHEAT,new ItemStack(ItemsCore.flour,ConfigHandler.getFlourOutput()), 0.5F);
	        this.addSmelting(Items.CARROT, new ItemStack(ItemsCore.mash_carrot,ConfigHandler.getMashedCarrotOutput()), 0.5F);
	        this.addSmeltingRecipeForBlock(Blocks.SANDSTONE, new ItemStack(Blocks.SAND,ConfigHandler.getSandstoneSandOutput()), 0.6F);
	        this.addSmeltingRecipeForBlock(Blocks.GRAVEL, new ItemStack(Items.FLINT,ConfigHandler.getFlintOutput()), 0.6F);
	        this.addSmeltingRecipeForBlock(Blocks.BOOKSHELF, new ItemStack(Items.BOOK,ConfigHandler.getBookOutput()), 0.5F);
	        this.addSmeltingRecipeForBlock(Blocks.PRISMARINE, new ItemStack(Items.PRISMARINE_SHARD,ConfigHandler.getPrisMarineShardOutput()), 0.5F);
	        this.addSmeltingRecipeForBlock(Blocks.LOG, new ItemStack(ItemsCore.wood_chips,ConfigHandler.getWoodChipsOutput()), 0.5F);
	        this.addSmeltingRecipeForBlock(Blocks.LOG2, new ItemStack(ItemsCore.wood_chips,ConfigHandler.getWoodChipsOutput()), 0.5F);
	        this.addSmelting(Items.DIAMOND_HORSE_ARMOR,new ItemStack(Items.DIAMOND,ConfigHandler.getDiamondHorseArmorOutput()), 0.5F);
	        this.addSmelting(Items.IRON_HORSE_ARMOR,new ItemStack(ItemsCore.dust_iron,ConfigHandler.getIronDustHorseArmorOutput()), 0.5F);
	        this.addSmelting(Items.GOLDEN_HORSE_ARMOR,new ItemStack(ItemsCore.dust_gold,ConfigHandler.getGoldDustHorseArmorOutput()), 0.5F);
	        this.addSmelting(Items.EGG,new ItemStack(ItemsCore.omlete,ConfigHandler.getOmleteOutput()), 0.5F);
	        
	    }

	    public GrinderRecipes(List<ItemStack> inputs, ItemStack output, float experience) {
			// TODO Auto-generated constructor stub
		}

		/**
	     * Adds a smelting recipe, where the input item is an instance of Block.
	     */
	    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience)
	    {
	        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
	    }

	    /**
	     * Adds a smelting recipe using an Item as the input item.
	     */
	    public void addSmelting(Item input, ItemStack stack, float experience)
	    {
	        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
	    }

	    /**
	     * Adds a smelting recipe using an ItemStack as the input for the recipe.
	     */
	    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
	    {
	        if (getSmeltingResult(input) != null) { net.minecraftforge.fml.common.FMLLog.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack); return; }
	        this.smeltingList.put(input, stack);
	        this.experienceList.put(stack, Float.valueOf(experience));
	    }

	    /**
	     * Returns the smelting result of an item.
	     */
	    public ItemStack getSmeltingResult(ItemStack stack)
	    {
	        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
	        {
	            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
	            {
	                return (ItemStack)entry.getValue();
	            }
	        }

	        return null;
	    }

	    /**
	     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
	     */
	    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	    {
	        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	    }

	    public Map<ItemStack, ItemStack> getSmeltingList()
	    {
	        return this.smeltingList;
	    }

	    public float getSmeltingExperience(ItemStack stack)
	    {
	        float ret = stack.getItem().getSmeltingExperience(stack);
	        if (ret != -1) return ret;

	        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
	        {
	            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
	            {
	                return ((Float)entry.getValue()).floatValue();
	            }
	        }

	        return 0.1F;
	    }
		
	}