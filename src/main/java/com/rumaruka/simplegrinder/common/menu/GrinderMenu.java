package com.rumaruka.simplegrinder.common.menu;

import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.init.SGMenu;
import com.rumaruka.simplegrinder.init.SGRecipePropertySet;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;

public class GrinderMenu extends AbstractGrinderMenu{
    public GrinderMenu(int p_39532_, Inventory p_39533_) {
        super(SGMenu.GRINDER_MENU.get(), GrinderRecipe.RECIPE_TYPE,   p_39532_, p_39533_);

    }

    public GrinderMenu(int p_39535_, Inventory p_39536_, Container p_39537_, ContainerData p_39538_) {
        super(SGMenu.GRINDER_MENU.get(), GrinderRecipe.RECIPE_TYPE,p_39535_, p_39536_, p_39537_, p_39538_);
    }
}
