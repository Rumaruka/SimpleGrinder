package com.rumaruka.simplegrinder.config;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = SimpleGrinder.MODID, bus = EventBusSubscriber.Bus.MOD)
public class SGConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.BooleanValue WAR_NOTIFICATION = BUILDER.comment("War notification: but i don`t recommend switch config... please!").define("Disable", true);
    public static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean isWar ;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
       isWar = WAR_NOTIFICATION.get();
    }
}
