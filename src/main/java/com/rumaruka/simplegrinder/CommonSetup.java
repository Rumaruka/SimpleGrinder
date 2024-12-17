package com.rumaruka.simplegrinder;

import com.rumaruka.simplegrinder.config.SGConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
@EventBusSubscriber(modid = SimpleGrinder.MODID)
public class CommonSetup {
  ;

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if (SGConfig.isWar) {

            player.displayClientMessage(Component.translatable("================"), false);
            player.displayClientMessage(Component.translatable(ChatFormatting.BLUE + "MAKE LOVE!"), false);
            player.displayClientMessage(Component.translatable(ChatFormatting.YELLOW + "NOT WAR!"), false);
            player.displayClientMessage(Component.translatable("================"), false);
            player.displayClientMessage(Component.translatable("=IF YOU WANT HELP="), false);
            player.displayClientMessage(CommonHooks.newChatWithLinks("https://savelife.in.ua/"), false);
            player.displayClientMessage(CommonHooks.newChatWithLinks("https://prytulafoundation.org/"), false);
            player.displayClientMessage(Component.translatable("================"), false);
        }

    }
}
