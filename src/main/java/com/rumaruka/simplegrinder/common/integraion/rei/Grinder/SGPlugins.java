package com.rumaruka.simplegrinder.common.integraion.rei.Grinder;

import com.rumaruka.simplegrinder.SimpleGrinder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;

public interface SGPlugins {

    CategoryIdentifier<CoalGrinderRecipeDisplay> COAL_GRINDER = CategoryIdentifier.of(SimpleGrinder.MODID, "plugins/coal_grinder");

}
