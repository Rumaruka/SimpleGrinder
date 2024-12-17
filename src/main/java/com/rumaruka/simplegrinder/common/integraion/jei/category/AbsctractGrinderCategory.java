//package com.rumaruka.simplegrinder.common.integraion.jei.category;
//
//import com.google.common.cache.CacheBuilder;
//import com.google.common.cache.CacheLoader;
//import com.google.common.cache.LoadingCache;
//import com.rumaruka.simplegrinder.common.integraion.jei.config.ModConts;
//import com.rumaruka.simplegrinder.common.recipe.AbstractCustomRecipe;
//
//import mezz.jei.api.constants.VanillaTypes;
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.gui.drawable.IDrawableAnimated;
//import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.IFocusGroup;
//
//import mezz.jei.common.Constants;
//import mezz.jei.library.util.RecipeUtil;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Font;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import net.minecraft.world.level.block.Block;
//
//import static mezz.jei.api.recipe.RecipeIngredientRole.INPUT;
//import static mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT;
//
//public abstract class AbsctractGrinderCategory<T extends AbstractCustomRecipe> extends GrinderVariantCategory<RecipeHolder<T>> {
//    private final IDrawable background;
//    private final int regularCookTime;
//    private final IDrawable icon;
//    private final Component localizedName;
//    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;
//
//    public AbsctractGrinderCategory(IGuiHelper guiHelper, Block icon, String translationKey, int regularCookTime) {
//        super(guiHelper);
//        this.background = guiHelper.createDrawable(ModConts.RECIPE_GUI_VANILLA, 0, 114, 82, 54);
//        this.regularCookTime = regularCookTime;
//        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(icon));
//        this.localizedName = Component.translatable(translationKey);
//        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25L).build(new CacheLoader<Integer, IDrawableAnimated>() {
//            public IDrawableAnimated load(Integer cookTime) {
//                return guiHelper.drawableBuilder(Constants.RECIPE_GUI_VANILLA, 82, 128, 24, 17).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
//            }
//        });
//    }
//
//
//    protected IDrawableAnimated getArrow(RecipeHolder<T> recipe) {
//        int cookTime = recipe.value().getCookingTime();
//        if (cookTime <= 0) {
//            cookTime = regularCookTime;
//        }
//        return this.cachedArrows.getUnchecked(cookTime);
//    }
//
//    @Override
//    public IDrawable getBackground() {
//        return background;
//    }
//
//    @Override
//    public IDrawable getIcon() {
//        return icon;
//    }
//
//    @Override
//    public void draw(RecipeHolder<T> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
//        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
//
//        animatedFlame.draw(guiGraphics, 1, 20);
//
//        IDrawableAnimated arrow = getArrow(recipe);
//        arrow.draw(guiGraphics, 24, 18);
//
//        drawExperience(recipe, guiGraphics, 0);
//        drawCookTime(recipe, guiGraphics, 45);
//    }
//
//
//    protected void drawExperience(RecipeHolder<T> recipe, GuiGraphics poseStack, int y) {
//        float experience = recipe.value().getExperience();
//        if (experience > 0) {
//            Component experienceString = Component.translatable("gui.jei.category.grinder.experience", experience);
//            Minecraft minecraft = Minecraft.getInstance();
//            Font fontRenderer = minecraft.font;
//            int stringWidth = fontRenderer.width(experienceString);
//            poseStack.drawString(fontRenderer, experienceString, background.getWidth() - stringWidth, y, 0xFF808080);
//        }
//    }
//
//    protected void drawCookTime(RecipeHolder<T> recipe, GuiGraphics poseStack, int y) {
//        int cookTime = recipe.value().getCookingTime();
//        if (cookTime > 0) {
//            int cookTimeSeconds = cookTime / 20;
//            Component timeString = Component.translatable("gui.jei.category.grinder.time.seconds", cookTimeSeconds);
//            Minecraft minecraft = Minecraft.getInstance();
//            Font fontRenderer = minecraft.font;
//            int stringWidth = fontRenderer.width(timeString);
//            poseStack.drawString(fontRenderer, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
//        }
//    }
//
//    @Override
//    public Component getTitle() {
//        return localizedName;
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<T> recipe, IFocusGroup focuses) {
//        builder.addSlot(INPUT, 1, 1)
//                .addIngredients(recipe.value().getIngredients().get(0));
//
//        builder.addSlot(OUTPUT, 61, 19)
//                .addItemStack(RecipeUtil.getResultItem(recipe.value()));
//    }
//}
