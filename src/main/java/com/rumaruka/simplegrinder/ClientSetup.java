package com.rumaruka.simplegrinder;


import com.rumaruka.simplegrinder.client.screen.GrinderScreen;
import com.rumaruka.simplegrinder.config.SGConfig;
import com.rumaruka.simplegrinder.init.SGMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ClientSetup {


    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event){
        event.register(SGMenu.GRINDER_MENU.get(),GrinderScreen::new);
    }


}
