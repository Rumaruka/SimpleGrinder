package com.rumaruka.simplegrinder.common.recipe;

import com.rumaruka.simplegrinder.init.SGBlocks;
import com.rumaruka.simplegrinder.init.SGRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Blocks;

import java.util.Collections;
import java.util.Map;

public class GrinderRecipe extends AbstractCustomRecipe{

    public static final RecipeType<GrinderRecipe> RECIPE_TYPE = new RecipeType<GrinderRecipe>() {
        @Override
        public String toString() {
            return "simplegrinder:grinder";
        }
    };




    public GrinderRecipe(String p_249312_,  Ingredient p_252345_, ItemStack p_250002_,  float p_250535_, int p_251222_) {
        super( p_249312_,  p_252345_, p_250002_, p_250535_, p_251222_);
     }

    @Override
    public RecipeType<? extends AbstractCustomRecipe> getType() {
        return RECIPE_TYPE;
    }



    // Initialized by reload listener
    public static Map<ResourceLocation, GrinderRecipe> recipeList = Collections.emptyMap();


    @Override
    public RecipeSerializer<? extends AbstractCustomRecipe> getSerializer() {
        return SGRecipe.GRINDER.get();
    }
}
