package com.rumaruka.simplegrinder.client.screen;

import com.rumaruka.simplegrinder.common.menu.GrinderMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;


@OnlyIn(Dist.CLIENT)
public class GrinderScreen extends AbstractGrinderScreen<GrinderMenu>{
    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/furnace/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/furnace/burn_progress");
    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/furnace.png");
    public GrinderScreen(GrinderMenu p_97825_, Inventory p_97827_, Component p_97828_) {
        super(p_97825_, p_97827_, p_97828_, TEXTURE, LIT_PROGRESS_SPRITE,BURN_PROGRESS_SPRITE);
    }


}
