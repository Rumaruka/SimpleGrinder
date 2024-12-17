package com.rumaruka.simplegrinder.init;


import com.rumaruka.simplegrinder.common.tiles.CoalGrinderBE;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;
public class SGTile {
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);

    public static void setup(IEventBus bus) {
        TILES.register(bus);

    }

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CoalGrinderBE>> COAL_GRINDER_BE = TILES.register("coal_grinder", () ->  new BlockEntityType<>(CoalGrinderBE::new, SGBlocks.COAL_GRINDER.get()));


}
