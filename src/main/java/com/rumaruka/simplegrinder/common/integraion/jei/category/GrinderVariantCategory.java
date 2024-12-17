//package com.rumaruka.simplegrinder.common.integraion.jei.category;
//
//
//import com.rumaruka.simplegrinder.common.integraion.jei.config.ModConts;
//import mezz.jei.api.gui.drawable.IDrawableAnimated;
//import mezz.jei.api.gui.drawable.IDrawableStatic;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//
//public abstract class GrinderVariantCategory<T> implements IRecipeCategory<T> {
//
//    protected final IDrawableStatic staticFlame;
//    protected final IDrawableAnimated animatedFlame;
//
//    public GrinderVariantCategory(IGuiHelper guiHelper) {
//        staticFlame = guiHelper.createDrawable(ModConts.RECIPE_GUI_VANILLA, 82, 114, 14, 14);
//        animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
//    }
//}
