package com.rumaruka.simplegrinder.common.integraion.rei.Grinder;


import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;

import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CoalGrinderRecipeDisplay extends AbsctractCustomDisplay {
    public static DisplaySerializer<CoalGrinderRecipeDisplay> SERIALIZER = serializer(CoalGrinderRecipeDisplay::new);

    public CoalGrinderRecipeDisplay(RecipeHolder<GrinderRecipe> recipe) {
        super(recipe);
    }

    public CoalGrinderRecipeDisplay(List<EntryIngredient> input, List<EntryIngredient> output, Optional<ResourceLocation> id, float xp, double cookTime) {
        super(input, output, id, xp, cookTime);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return SGPlugins.COAL_GRINDER;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }

}
