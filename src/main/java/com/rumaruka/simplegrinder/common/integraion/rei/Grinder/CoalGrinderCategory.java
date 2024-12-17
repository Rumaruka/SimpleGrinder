package com.rumaruka.simplegrinder.common.integraion.rei.Grinder;

import com.google.common.collect.Lists;
import com.rumaruka.simplegrinder.init.SGBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.DisplayRenderer;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.SimpleDisplayRenderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class CoalGrinderCategory implements DisplayCategory<CoalGrinderRecipeDisplay> {
    private double defaultCookingTime;
    public CoalGrinderCategory(double defaultCookingTime) {
        this.defaultCookingTime = defaultCookingTime;
    }
    @Override
    public CategoryIdentifier<? extends CoalGrinderRecipeDisplay> getCategoryIdentifier() {
        return SGPlugins.COAL_GRINDER;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Grinder");
    }

    @Override
    public Renderer getIcon() {
        return  EntryStacks.of(SGBlocks.COAL_GRINDER.get());
    }

    @Override
    public List<Widget> setupDisplay(CoalGrinderRecipeDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.y + 10);
        DecimalFormat df = new DecimalFormat("###.##");
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61, startPoint.y + 9)));
        widgets.add(Widgets.createBurningFire(new Point(startPoint.x + 1, startPoint.y + 20))
                .animationDurationMS(10000));
        if (display.cookTime().isPresent() && display.xp().isPresent()) {
            widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + 5),
                    Component.translatable("category.rei.cooking.time&xp", df.format(display.xp().getAsDouble()), df.format(display.cookTime().getAsDouble() / 20d))).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));
        }
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 24, startPoint.y + 8))
                .animationDurationTicks(display.cookTime().orElse(defaultCookingTime)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 61, startPoint.y + 9))
                .entries(display.getOutputEntries().get(0))
                .disableBackground()
                .markOutput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 1))
                .entries(display.getInputEntries().get(0))
                .markInput());
        return widgets;
    }
    @Override
    public int getDisplayHeight() {
        return 49;
    }

    @Override
    public DisplayRenderer getDisplayRenderer(CoalGrinderRecipeDisplay display) {
        return SimpleDisplayRenderer.from(Collections.singletonList(display.getInputEntries().get(0)), display.getOutputEntries());
    }
}
