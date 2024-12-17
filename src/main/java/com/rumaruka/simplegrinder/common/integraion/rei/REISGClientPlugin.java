package com.rumaruka.simplegrinder.common.integraion.rei;

import com.rumaruka.simplegrinder.common.integraion.rei.Grinder.CoalGrinderCategory;
import com.rumaruka.simplegrinder.init.SGBlocks;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class REISGClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CoalGrinderCategory(100), configuration -> configuration.addWorkstations(EntryStacks.of(SGBlocks.COAL_GRINDER.get())));
    }

}
