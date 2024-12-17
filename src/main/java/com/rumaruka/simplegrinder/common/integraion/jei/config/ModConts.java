//package com.rumaruka.simplegrinder.common.integraion.jei.config;
//
//import com.rumaruka.simplegrinder.SimpleGrinder;
//import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
//import mezz.jei.api.constants.ModIds;
//import mezz.jei.api.recipe.RecipeType;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.Recipe;
//import net.minecraft.world.item.crafting.RecipeHolder;
//
//public final class ModConts {
//    public static final String TEXTURE_GUI_PATH = "textures/jei/gui/";
//    public static final String TEXTURE_GUI_VANILLA = ModConts.TEXTURE_GUI_PATH + "gui_vanilla.png";
//
//    public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(ModIds.JEI_ID, TEXTURE_GUI_VANILLA);
//    public static final RecipeType<RecipeHolder<GrinderRecipe>> GRINDER = create(SimpleGrinder.MODID,"grinder",GrinderRecipe.class);
//    public static final int MAX_TOOLTIP_WIDTH = 150;
//
//    public static final ResourceLocation UNIVERSAL_RECIPE_TRANSFER_UID = new ResourceLocation(ModIds.JEI_ID, "universal_recipe_transfer_handler");
//    public static final ResourceLocation LOCATION_JEI_GUI_TEXTURE_ATLAS = new ResourceLocation(ModIds.JEI_ID, "textures/atlas/gui.png");
//
//    public ModConts() {
//
//    }
//    public static <R extends Recipe<?>> RecipeType<RecipeHolder<R>> create(String modid, String name, Class<? extends R> recipeClass) {
//        ResourceLocation uid = new ResourceLocation(modid, name);
//        @SuppressWarnings({"unchecked", "RedundantCast"})
//        Class<? extends RecipeHolder<R>> holderClass = (Class<? extends RecipeHolder<R>>) (Object) RecipeHolder.class;
//        return new RecipeType<>(uid, holderClass);
//    }
//
//}
