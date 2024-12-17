package com.rumaruka.simplegrinder.common.menu;

import com.rumaruka.simplegrinder.common.menu.slots.GrinderFuelSlot;
import com.rumaruka.simplegrinder.common.menu.slots.GrinderResultSlot;
import com.rumaruka.simplegrinder.common.recipe.AbstractCustomRecipe;
import com.rumaruka.simplegrinder.init.SGRecipePropertySet;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class AbstractGrinderMenu extends AbstractContainerMenu {

    final Container container;
    private final ContainerData data;
    protected final Level level;
    private final RecipeType<? extends AbstractCustomRecipe> recipeType;

    protected AbstractGrinderMenu(
            MenuType<?> p_38966_,
            RecipeType<? extends AbstractCustomRecipe> p_38967_,

            int p_38969_,
            Inventory p_38970_
    ) {
        this(p_38966_, p_38967_, p_38969_, p_38970_, new SimpleContainer(3), new SimpleContainerData(4));
    }

    protected AbstractGrinderMenu(
            MenuType<?> p_38960_,
            RecipeType<? extends AbstractCustomRecipe> p_38961_,


            int p_38963_,
            Inventory p_38964_,
            Container p_379971_,
            ContainerData p_379737_
    ) {
        super(p_38960_, p_38963_);
        this.recipeType = p_38961_;
        checkContainerSize(p_379971_, 3);
        checkContainerDataCount(p_379737_, 4);
        this.container = p_379971_;
        this.data = p_379737_;
        this.level = p_38964_.player.level();

        this.level.recipeAccess().propertySet(SGRecipePropertySet.COAL_GRINDER_INPUT);
        this.addSlot(new Slot(p_379971_, 0, 56, 17));
        this.addSlot(new GrinderFuelSlot(this, p_379971_, 1, 56, 53));
        this.addSlot(new GrinderResultSlot(p_38964_.player, p_379971_, 2, 116, 35));
        this.addStandardInventorySlots(p_38964_, 8, 84);
        this.addDataSlots(p_379737_);
    }


    @Override
    public boolean stillValid(Player p_38974_) {
        return this.container.stillValid(p_38974_);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38986_, int p_38987_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_38987_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_38987_ == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_38987_ != 1 && p_38987_ != 0) {
                if (this.canSmelt(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 3 && p_38987_ < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 30 && p_38987_ < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_38986_, itemstack1);
        }

        return itemstack;
    }

    protected boolean canSmelt(ItemStack p_38978_) {
        return this.level.recipeAccess().propertySet(SGRecipePropertySet.COAL_GRINDER_INPUT).test(p_38978_);
    }


    public boolean isFuel(ItemStack p_38989_) {
        return p_38989_.getBurnTime(this.recipeType, this.level.fuelValues()) > 0;
    }

    public float getBurnProgress() {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return j != 0 && i != 0 ? Mth.clamp((float) i / (float) j, 0.0F, 1.0F) : 0.0F;
    }

    public float getLitProgress() {
        int i = this.data.get(1);
        if (i == 0) {
            i = 200;
        }

        return Mth.clamp((float) this.data.get(0) / (float) i, 0.0F, 1.0F);
    }

    public boolean isLit() {
        return this.data.get(0) > 0;
    }


}
