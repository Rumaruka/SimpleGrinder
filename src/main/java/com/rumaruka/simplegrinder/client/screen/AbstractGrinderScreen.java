package com.rumaruka.simplegrinder.client.screen;

import com.rumaruka.simplegrinder.common.menu.AbstractGrinderMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public abstract class AbstractGrinderScreen<T extends AbstractGrinderMenu> extends AbstractContainerScreen<T>{


    private final ResourceLocation texture;
    private final ResourceLocation litProgressSprite;
    private final ResourceLocation burnProgressSprite;

    public AbstractGrinderScreen(
            T p_97825_,
            Inventory p_97827_,
            Component p_97828_,
            ResourceLocation p_97829_,
            ResourceLocation p_296257_,
            ResourceLocation p_295751_
    ) {
        super(p_97825_, p_97827_, p_97828_);
        this.texture = p_97829_;
        this.litProgressSprite = p_296257_;
        this.burnProgressSprite = p_295751_;
    }

    @Override
    public void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }



    @Override
    protected void renderBg(GuiGraphics p_282928_, float p_281631_, int p_281252_, int p_281891_) {
        int i = this.leftPos;
        int j = this.topPos;
        p_282928_.blit(RenderType::guiTextured, this.texture, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        if (this.menu.isLit()) {
            int k = 14;
            int l = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
            p_282928_.blitSprite(RenderType::guiTextured, this.litProgressSprite, 14, 14, 0, 14 - l, i + 56, j + 36 + 14 - l, 14, l);
        }

        int i1 = 24;
        int j1 = Mth.ceil(this.menu.getBurnProgress() * 24.0F);
        p_282928_.blitSprite(RenderType::guiTextured, this.burnProgressSprite, 24, 16, 0, 0, i + 79, j + 34, j1, 16);
    }
}
