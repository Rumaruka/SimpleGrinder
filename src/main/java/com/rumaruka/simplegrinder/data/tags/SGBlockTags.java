package com.rumaruka.simplegrinder.data.tags;


import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.init.SGBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS;
import static net.neoforged.neoforge.common.Tags.Blocks.STORAGE_BLOCKS;

public class SGBlockTags extends BlockTagsProvider {
    public SGBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SimpleGrinder.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SGBlocks.MACHINE_CORE.get())
                .add(SGBlocks.COAL_GRINDER.get());



        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(SGBlocks.MACHINE_CORE.get())
                .add(SGBlocks.COAL_GRINDER.get());


    }
}
