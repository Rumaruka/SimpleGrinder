package com.rumaruka.simplegrinder.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;

public class SGCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tabSimpleGrinder = CREATIVE_MODE_TABS.register("simplegrinder",() -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(SGBlocks.COAL_GRINDER.get()))
            .title(Component.translatable("itemGroup.simplegrinder"))
            .displayItems((p_270258_, p_259752_) ->  {
                p_259752_.accept(SGBlocks.COAL_GRINDER.get());
                p_259752_.accept(SGBlocks.MACHINE_CORE.get());
                p_259752_.accept(SGItems.COPPER_DUST.get());
                p_259752_.accept(SGItems.GOLD_DUST.get());
                p_259752_.accept(SGItems.IRON_DUST.get());
                p_259752_.accept(SGItems.FLOUR.get());
                p_259752_.accept(SGItems.MASH_CARROT.get());
                p_259752_.accept(SGItems.MASH_POTATO.get());
                p_259752_.accept(SGItems.OMLETE.get());
            }).build()
    );

    public static void setup(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);

    }

}
