package com.rumaruka.simplegrinder.common.integraion.rei.Grinder;

import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;

import java.util.OptionalDouble;

public interface CustomDisplay extends SimpleGridMenuDisplay {
    @Override
    default int getWidth() {
        return 1;
    }

    @Override
    default int getHeight() {
        return 1;
    }

    OptionalDouble xp();

    OptionalDouble cookTime();
}
