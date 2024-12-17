//package com.rumaruka.simplegrinder.common.integraion.jei.category;
//
//import com.rumaruka.simplegrinder.common.integraion.jei.config.ModConts;
//import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
//import com.rumaruka.simplegrinder.init.SGBlocks;
//
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.RecipeType;
//import net.minecraft.world.item.crafting.RecipeHolder;
//
//public class GrinderCategory extends AbsctractGrinderCategory<GrinderRecipe> {
//    public GrinderCategory(IGuiHelper guiHelper) {
//        super(guiHelper, SGBlocks.COAL_GRINDER.get(), "gui.jei.category.grinder", 100);
//    }
//
//
//    @Override
//    public RecipeType<RecipeHolder<GrinderRecipe>> getRecipeType() {
//        return ModConts.GRINDER;
//    }
//}
