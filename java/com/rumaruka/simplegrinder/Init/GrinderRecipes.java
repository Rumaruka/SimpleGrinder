package com.rumaruka.simplegrinder.Init;

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
	        this.addSmeltingRecipeForBlock(Blocks.iron_ore, new ItemStack(ItemsCore.dust_iron,2), 0.7F);
	        this.addSmeltingRecipeForBlock(Blocks.gold_ore, new ItemStack(ItemsCore.dust_gold,2), 1.0F);
	        this.addSmeltingRecipeForBlock(Blocks.diamond_ore, new ItemStack(Items.diamond,4), 1.0F);
	        this.addSmeltingRecipeForBlock(Blocks.cobblestone, new ItemStack(Blocks.sand,2), 0.1F);
	        this.addSmeltingRecipeForBlock(Blocks.cactus, new ItemStack(Items.dye, 5, EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
	        this.addSmeltingRecipeForBlock(Blocks.emerald_ore, new ItemStack(Items.emerald,4), 1.0F);
	        this.addSmelting(Items.potato, new ItemStack(ItemsCore.mash_potato,2), 0.35F);
	        this.addSmeltingRecipeForBlock(Blocks.coal_ore, new ItemStack(Items.coal,15), 0.1F);
	        this.addSmeltingRecipeForBlock(Blocks.redstone_ore, new ItemStack(Items.redstone,25), 0.7F);
	        this.addSmeltingRecipeForBlock(Blocks.lapis_ore, new ItemStack(Items.dye, 15, EnumDyeColor.BLUE.getDyeDamage()), 0.2F);
	        this.addSmeltingRecipeForBlock(Blocks.quartz_ore, new ItemStack(Items.quartz,15), 0.2F);
	        this.addSmelting(Items.wheat,new ItemStack(ItemsCore.flour,2), 0.5F);
	        this.addSmelting(Items.carrot, new ItemStack(ItemsCore.mash_carrot,2), 0.5F);
	        this.addSmeltingRecipeForBlock(Blocks.sandstone, new ItemStack(Blocks.sand,4), 0.6F);
	        this.addSmeltingRecipeForBlock(Blocks.gravel, new ItemStack(Items.flint,2), 0.6F);
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

	        return 0.0F;
	    }
		
	}