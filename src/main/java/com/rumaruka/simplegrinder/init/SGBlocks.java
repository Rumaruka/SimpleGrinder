package com.rumaruka.simplegrinder.init;


import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.blocks.CoalGrinderBlock;
import com.rumaruka.simplegrinder.common.blocks.MachineCoreBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.ToIntFunction;

import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;
public class SGBlocks {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks( MODID);


    public static void setup(IEventBus bus) {
        BLOCKS.register(bus);

    }

    public static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }
//
    public static final DeferredBlock<CoalGrinderBlock> COAL_GRINDER = BLOCKS.registerBlock("coal_grinder",CoalGrinderBlock::new,BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(3.5f, 2.5f).lightLevel(SGBlocks.litBlockEmission(13)).noCollission() );
    public static final DeferredBlock<MachineCoreBlock> MACHINE_CORE = BLOCKS.registerBlock("machine_core", MachineCoreBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.4F).noCollission());



}
