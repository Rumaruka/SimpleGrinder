package com.rumaruka.simplegrinder.data.loot_table;

import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.init.SGBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SBlockLT extends BlockLootSubProvider {
    protected SBlockLT(HolderLookup.Provider p_344943_) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), p_344943_);
    }

    @Override
    protected void generate() {


        dropSelf(SGBlocks.MACHINE_CORE.get());
        dropSelf(SGBlocks.COAL_GRINDER.get());


    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(SimpleGrinder.MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }
}
