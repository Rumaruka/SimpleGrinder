package com.rumaruka.simplegrinder.data;

import com.rumaruka.simplegrinder.data.loot_table.SGLootTables;
import com.rumaruka.simplegrinder.data.tags.SGBlockTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class DataGeneration {

    @SubscribeEvent
    public static void onData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        PackOutput output = generator.getPackOutput();
        SGBlockTags blockTags = new SGBlockTags(output, provider, fileHelper);

        event.addProvider(blockTags);
        event.addProvider(new SGLootTables(output, provider));


    }
}
