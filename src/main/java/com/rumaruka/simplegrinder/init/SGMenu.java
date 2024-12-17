package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.common.menu.GrinderMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;
public class SGMenu {
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(BuiltInRegistries.MENU, MODID);
    public static final DeferredHolder<MenuType<?>, MenuType<GrinderMenu>> GRINDER_MENU = CONTAINERS.register("coal_grinder", () -> IMenuTypeExtension.create((windowId, inv, data) -> {

        return new GrinderMenu(windowId, inv);
    }));

    public static void setup(IEventBus bus) {
        CONTAINERS.register(bus);

    }


}
