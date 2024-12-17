//package com.rumaruka.simplegrinder.common.integraion.jei;
//
//
//import com.rumaruka.simplegrinder.SimpleGrinder;
//import com.rumaruka.simplegrinder.client.screen.GrinderScreen;
//import com.rumaruka.simplegrinder.common.integraion.jei.category.GrinderCategory;
//import com.rumaruka.simplegrinder.common.integraion.jei.config.ModConts;
//import com.rumaruka.simplegrinder.common.menu.GrinderMenu;
//import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
//import com.rumaruka.simplegrinder.init.SGBlocks;
//import com.rumaruka.simplegrinder.init.SGMenu;
//
//import mezz.jei.api.IModPlugin;
//import mezz.jei.api.JeiPlugin;
//import mezz.jei.api.constants.RecipeTypes;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.helpers.IJeiHelpers;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import mezz.jei.api.registration.*;
//import mezz.jei.api.runtime.IIngredientManager;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.multiplayer.ClientLevel;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import net.minecraft.world.item.crafting.RecipeManager;
//
//import javax.annotation.Nullable;
//
//@JeiPlugin
//public class GrinderPlugin implements IModPlugin {
//
//    @Nullable
//    private IRecipeCategory<RecipeHolder<GrinderRecipe>> grinderCategory;
//    private static final ResourceLocation ID = new ResourceLocation(SimpleGrinder.MODID, "main");
//
//    @Override
//    public ResourceLocation getPluginUid() {
//        return ID;
//    }
//
//    @Override
//    public void registerCategories(IRecipeCategoryRegistration registration) {
//
//        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
//        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
//        registration.addRecipeCategories(new GrinderCategory(guiHelper));
//    }
//
//    @Override
//    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
//        registration.addRecipeTransferHandler(GrinderMenu.class, SGMenu.GRINDER_MENU.get(), ModConts.GRINDER, 1, 1, 3, 36);
//
//    }
//
//    @Override
//    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
//        registration.addRecipeClickArea(GrinderScreen.class, 78, 32, 28, 23, ModConts.GRINDER, RecipeTypes.FUELING);
//
//    }
//
//
//    @Override
//    public void registerRecipes(IRecipeRegistration registration) {
//        ClientLevel level = Minecraft.getInstance().level;
//        RecipeManager recipeManager = level.getRecipeManager();
//        var grinder = recipeManager.getAllRecipesFor(GrinderRecipe.RECIPE_TYPE);
//        registration.addRecipes(ModConts.GRINDER,grinder);
//    }
//
//    @Override
//    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
//        registration.addRecipeCatalyst(new ItemStack(SGBlocks.COAL_GRINDER.get()), ModConts.GRINDER, RecipeTypes.FUELING);
//
//
//    }
//
//}
