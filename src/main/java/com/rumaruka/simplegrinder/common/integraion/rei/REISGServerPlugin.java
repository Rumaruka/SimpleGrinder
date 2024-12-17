package com.rumaruka.simplegrinder.common.integraion.rei;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.integraion.rei.Grinder.CoalGrinderCategory;
import com.rumaruka.simplegrinder.common.integraion.rei.Grinder.CoalGrinderRecipeDisplay;
import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.init.SGBlocks;
import com.rumaruka.simplegrinder.init.SGRecipe;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import me.shedaniel.rei.forge.REIPluginCommon;
import net.minecraft.resources.ResourceLocation;

@REIPluginCommon
public class REISGServerPlugin implements REICommonPlugin {

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(ResourceLocation.fromNamespaceAndPath(SimpleGrinder.MODID, "coal_grinder"), CoalGrinderRecipeDisplay.SERIALIZER);
    }

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(GrinderRecipe.class).filterType(GrinderRecipe.RECIPE_TYPE).fill(CoalGrinderRecipeDisplay::new);

    }
}
