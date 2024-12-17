package com.rumaruka.simplegrinder;

import com.rumaruka.simplegrinder.config.SGConfig;
import com.rumaruka.simplegrinder.data.DataGeneration;
import com.rumaruka.simplegrinder.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

import net.neoforged.neoforge.common.NeoForge;

import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;

@Mod(MODID)
public class SimpleGrinder {
    public static final String MODID = "simplegrinder";

    public SimpleGrinder(IEventBus bus) {
        SGBlocks.setup(bus);
        SGTile.setup(bus);
        SGRecipe.setup(bus);
        SGItems.setup(bus);
        SGMenu.setup(bus);
        SGCreativeTabs.setup(bus);

        NeoForge.EVENT_BUS.register(CommonSetup.class);
        ModContainer modContainer = ModLoadingContext.get().getActiveContainer();

        modContainer.registerConfig(ModConfig.Type.COMMON, SGConfig.SPEC);
        bus.addListener(DataGeneration::onData);
    }
}
