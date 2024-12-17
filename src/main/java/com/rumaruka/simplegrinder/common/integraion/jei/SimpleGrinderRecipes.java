//package com.rumaruka.simplegrinder.common.integraion.jei;
//
//import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import mezz.jei.api.runtime.IIngredientManager;
//
//import mezz.jei.common.util.ErrorUtil;
//import mezz.jei.library.plugins.vanilla.crafting.CategoryRecipeValidator;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.multiplayer.ClientLevel;
//import net.minecraft.world.Container;
//import net.minecraft.world.item.crafting.*;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public final class SimpleGrinderRecipes {
//	private final RecipeManager recipeManager;
//	private final IIngredientManager ingredientManager;
//
//	public SimpleGrinderRecipes(IIngredientManager ingredientManager) {
//		Minecraft minecraft = Minecraft.getInstance();
//		ErrorUtil.checkNotNull(minecraft, "minecraft");
//		ClientLevel world = minecraft.level;
//		ErrorUtil.checkNotNull(world, "minecraft world");
//		this.recipeManager = world.getRecipeManager();
//		this.ingredientManager = ingredientManager;
//	}
//
//
//
//	public List<RecipeHolder<GrinderRecipe>> getGrinderRecipes(IRecipeCategory<RecipeHolder<GrinderRecipe>> grinder) {
//		CategoryRecipeValidator<GrinderRecipe> validator = new CategoryRecipeValidator<>(grinder, ingredientManager, 1);
//		return getValidHandledRecipes(recipeManager, GrinderRecipe.RECIPE_TYPE, validator);
//	}
//
//
//
//	private static <C extends Container, T extends Recipe<C>> List<RecipeHolder<T>> getValidHandledRecipes(RecipeManager recipeManager, RecipeType<T> recipeType, CategoryRecipeValidator<T> validator) {
//		return recipeManager.getAllRecipesFor(recipeType).stream().filter((r) -> {
//			return validator.isRecipeValid(r) && validator.isRecipeHandled(r);
//		}).toList();
//	}
//
//}
//
