package com.rumaruka.simplegrinder.common.tiles;

import com.rumaruka.simplegrinder.common.menu.GrinderMenu;
import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.init.SGTile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class CoalGrinderBE extends AbstractCoalGrinderBE{

    public CoalGrinderBE(BlockPos p_154992_, BlockState p_154993_) {
        super(SGTile.COAL_GRINDER_BE.get(), p_154992_, p_154993_, GrinderRecipe.RECIPE_TYPE);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.coal_grinder");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return new GrinderMenu(p_58627_,p_58628_,this,dataAccess);
    }

}
