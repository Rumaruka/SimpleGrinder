package com.rumaruka.simplegrinder.common.recipe;

import com.rumaruka.simplegrinder.config.SGConfig;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;

public abstract class AbstractCustomRecipe implements Recipe<SingleRecipeInput> {


    protected final String group;
    public final Ingredient ingredient;
    public final ItemStack result;
    protected final float experience;
    protected final int cookingTime;
    @Nullable
    private PlacementInfo placementInfo;
    public AbstractCustomRecipe( String p_249518_, Ingredient p_251354_, ItemStack p_252185_, float p_252165_, int p_250256_) {


        this.group = p_249518_;
        this.ingredient = p_251354_;
        this.result = p_252185_;
        this.experience = p_252165_;
        this.cookingTime = p_250256_;
    }

    public boolean matches(SingleRecipeInput p_43748_, Level p_43749_) {

        return this.ingredient.test(p_43748_.getItem(0));
    }
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
    public ItemStack assemble(SingleRecipeInput p_43746_, HolderLookup.Provider p_267063_) {


        return this.result.copy();
    }

    public boolean canCraftInDimensions(int p_43743_, int p_43744_) {
        return true;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getResultItem(HolderLookup.Provider p_266851_) {
        return this.result;
    }

    public String getGroup() {
        return this.group;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(this.ingredient);
        }

        return this.placementInfo;
    }


    public interface Factory<T extends AbstractCustomRecipe> {
        T create(String var1, CookingBookCategory var2, Ingredient var3, ItemStack var4, float var5, int var6);
    }
}