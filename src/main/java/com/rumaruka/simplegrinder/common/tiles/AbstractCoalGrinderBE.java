package com.rumaruka.simplegrinder.common.tiles;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rumaruka.simplegrinder.common.blocks.CoalGrinderBlock;
import com.rumaruka.simplegrinder.common.recipe.AbstractCustomRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractCoalGrinderBE extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
   protected static final int SLOT_INPUT = 0;
   protected static final int SLOT_FUEL = 1;
   protected static final int SLOT_RESULT = 2;
   public static final int DATA_LIT_TIME = 0;
   private static final int[] SLOTS_FOR_UP = new int[]{0};
   private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
   private static final int[] SLOTS_FOR_SIDES = new int[]{1};
   public static final int DATA_LIT_DURATION = 1;
   public static final int DATA_COOKING_PROGRESS = 2;
   public static final int DATA_COOKING_TOTAL_TIME = 3;
   public static final int NUM_DATA_VALUES = 4;
   public static final int BURN_TIME_STANDARD = 200;
   public static final int BURN_COOL_SPEED = 2;
   public static final int UNKNOWN_LIT_DURATION = 0;
   private final RecipeType<? extends AbstractCustomRecipe> recipeType;
   protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
   int litTime;
   int litDuration = 0;
   int cookingProgress;
   int cookingTotalTime;
   protected final ContainerData dataAccess = new ContainerData() {
      @Override
      public int get(int p_58431_) {
         switch (p_58431_) {
            case 0:
               if (litDuration > Short.MAX_VALUE) {
                  // Neo: preserve litTime / litDuration ratio on the client as data slots are synced as shorts.
                  return net.minecraft.util.Mth.floor(((double) litTime / litDuration) * Short.MAX_VALUE);
               }

               return AbstractCoalGrinderBE.this.litTime;
            case 1:
               return Math.min(AbstractCoalGrinderBE.this.litDuration, Short.MAX_VALUE);
            case 2:
               return AbstractCoalGrinderBE.this.cookingProgress;
            case 3:
               return AbstractCoalGrinderBE.this.cookingTotalTime;
            default:
               return 0;
         }
      }

      @Override
      public void set(int p_58433_, int p_58434_) {
         switch (p_58433_) {
            case 0:
               AbstractCoalGrinderBE.this.litTime = p_58434_;
               break;
            case 1:
               AbstractCoalGrinderBE.this.litDuration = p_58434_;
               break;
            case 2:
               AbstractCoalGrinderBE.this.cookingProgress = p_58434_;
               break;
            case 3:
               AbstractCoalGrinderBE.this.cookingTotalTime = p_58434_;
         }
      }

      @Override
      public int getCount() {
         return 4;
      }
   };
   private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
   private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCustomRecipe> quickCheck;

   protected AbstractCoalGrinderBE(
           BlockEntityType<?> p_154991_, BlockPos p_154992_, BlockState p_154993_, RecipeType<? extends AbstractCustomRecipe> p_154994_
   ) {
      super(p_154991_, p_154992_, p_154993_);
      this.quickCheck = RecipeManager.createCheck((RecipeType<AbstractCustomRecipe>)p_154994_);
      this.recipeType = p_154994_;
   }

   private boolean isLit() {
      return this.litTime > 0;
   }

   @Override
   protected void loadAdditional(CompoundTag p_155025_, HolderLookup.Provider p_323468_) {
      super.loadAdditional(p_155025_, p_323468_);
      this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
      ContainerHelper.loadAllItems(p_155025_, this.items, p_323468_);
      this.litTime = p_155025_.getInt("BurnTime");
      this.cookingProgress = p_155025_.getInt("CookTime");
      this.cookingTotalTime = p_155025_.getInt("CookTimeTotal");
      this.litDuration = 0;
      CompoundTag compoundtag = p_155025_.getCompound("RecipesUsed");

      for (String s : compoundtag.getAllKeys()) {
         this.recipesUsed.put(ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(s)), compoundtag.getInt(s));
      }
   }

   @Override
   protected void saveAdditional(CompoundTag p_187452_, HolderLookup.Provider p_323656_) {
      super.saveAdditional(p_187452_, p_323656_);
      p_187452_.putInt("BurnTime", this.litTime);
      p_187452_.putInt("CookTime", this.cookingProgress);
      p_187452_.putInt("CookTimeTotal", this.cookingTotalTime);
      ContainerHelper.saveAllItems(p_187452_, this.items, p_323656_);
      CompoundTag compoundtag = new CompoundTag();
      this.recipesUsed.forEach((p_380898_, p_380899_) -> compoundtag.putInt(p_380898_.location().toString(), p_380899_));
      p_187452_.put("RecipesUsed", compoundtag);
   }

   public static void serverTick(ServerLevel p_379747_, BlockPos p_155015_, BlockState p_155016_, AbstractCoalGrinderBE p_155017_) {
      boolean flag = p_155017_.isLit();
      boolean flag1 = false;
      if (p_155017_.isLit()) {
         p_155017_.litTime--;
      }

      ItemStack itemstack = p_155017_.items.get(1);
      ItemStack itemstack1 = p_155017_.items.get(0);
      boolean flag2 = !itemstack1.isEmpty();
      boolean flag3 = !itemstack.isEmpty();
      if (p_155017_.litDuration == 0) {
         p_155017_.litDuration = p_155017_.getBurnDuration(p_379747_.fuelValues(), itemstack);
      }

      if (p_155017_.isLit() || flag3 && flag2) {
         SingleRecipeInput singlerecipeinput = new SingleRecipeInput(itemstack1);
         RecipeHolder<? extends AbstractCustomRecipe> recipeholder;
         if (flag2) {
            recipeholder = p_155017_.quickCheck.getRecipeFor(singlerecipeinput, p_379747_).orElse(null);
         } else {
            recipeholder = null;
         }

         int i = p_155017_.getMaxStackSize();
         if (!p_155017_.isLit() && canBurn(p_379747_.registryAccess(), recipeholder, singlerecipeinput, p_155017_.items, i)) {
            p_155017_.litTime = p_155017_.getBurnDuration(p_379747_.fuelValues(), itemstack);
            p_155017_.litDuration = p_155017_.litTime;
            if (p_155017_.isLit()) {
               flag1 = true;
               var remainder = itemstack.getCraftingRemainder();
               if (!remainder.isEmpty())
                  p_155017_.items.set(1, remainder);
               else
               if (flag3) {
                  Item item = itemstack.getItem();
                  itemstack.shrink(1);
                  if (itemstack.isEmpty()) {
                     p_155017_.items.set(1, item.getCraftingRemainder()); // Neo: Remainder is handled in the `if` check above.
                  }
               }
            }
         }

         if (p_155017_.isLit() && canBurn(p_379747_.registryAccess(), recipeholder, singlerecipeinput, p_155017_.items, i)) {
            p_155017_.cookingProgress++;
            if (p_155017_.cookingProgress == p_155017_.cookingTotalTime) {
               p_155017_.cookingProgress = 0;
               p_155017_.cookingTotalTime = getTotalCookTime(p_379747_, p_155017_);
               if (burn(p_379747_.registryAccess(), recipeholder, singlerecipeinput, p_155017_.items, i)) {
                  p_155017_.setRecipeUsed(recipeholder);
               }

               flag1 = true;
            }
         } else {
            p_155017_.cookingProgress = 0;
         }
      } else if (!p_155017_.isLit() && p_155017_.cookingProgress > 0) {
         p_155017_.cookingProgress = Mth.clamp(p_155017_.cookingProgress - 2, 0, p_155017_.cookingTotalTime);
      }

      if (flag != p_155017_.isLit()) {
         flag1 = true;
         p_155016_ = p_155016_.setValue(CoalGrinderBlock.LIT, Boolean.valueOf(p_155017_.isLit()));
         p_379747_.setBlock(p_155015_, p_155016_, 3);
      }

      if (flag1) {
         setChanged(p_379747_, p_155015_, p_155016_);
      }
   }

   private static boolean canBurn(
           RegistryAccess p_266924_,
           @Nullable RecipeHolder<? extends AbstractCustomRecipe> p_301107_,
           SingleRecipeInput p_380038_,
           NonNullList<ItemStack> p_155007_,
           int p_155008_
   ) {
      if (!p_155007_.get(0).isEmpty() && p_301107_ != null) {
         ItemStack itemstack = p_301107_.value().assemble(p_380038_, p_266924_);
         if (itemstack.isEmpty()) {
            return false;
         } else {
            ItemStack itemstack1 = p_155007_.get(2);
            if (itemstack1.isEmpty()) {
               return true;
            } else if (!ItemStack.isSameItemSameComponents(itemstack1, itemstack)) {
               return false;
            } else {
               return itemstack1.getCount() + itemstack.getCount() <= p_155008_ && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize() // Neo fix: make furnace respect stack sizes in furnace recipes
                       ? true
                       : itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Neo fix: make furnace respect stack sizes in furnace recipes
            }
         }
      } else {
         return false;
      }
   }

   private static boolean burn(
           RegistryAccess p_266740_,
           @Nullable RecipeHolder<? extends AbstractCustomRecipe> p_300910_,
           SingleRecipeInput p_380269_,
           NonNullList<ItemStack> p_267073_,
           int p_267157_
   ) {
      if (p_300910_ != null && canBurn(p_266740_, p_300910_, p_380269_, p_267073_, p_267157_)) {
         ItemStack itemstack = p_267073_.get(0);
         ItemStack itemstack1 = p_300910_.value().assemble(p_380269_, p_266740_);
         ItemStack itemstack2 = p_267073_.get(2);
         if (itemstack2.isEmpty()) {
            p_267073_.set(2, itemstack1.copy());
         } else if (ItemStack.isSameItemSameComponents(itemstack2, itemstack1)) {
            itemstack2.grow(itemstack1.getCount());
         }

         if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !p_267073_.get(1).isEmpty() && p_267073_.get(1).is(Items.BUCKET)) {
            p_267073_.set(1, new ItemStack(Items.WATER_BUCKET));
         }

         itemstack.shrink(1);
         return true;
      } else {
         return false;
      }
   }

   protected int getBurnDuration(FuelValues p_363501_, ItemStack p_58343_) {
      return p_58343_.getBurnTime(this.recipeType, p_363501_);
   }

   private static int getTotalCookTime(ServerLevel p_380169_, AbstractCoalGrinderBE p_222694_) {
      SingleRecipeInput singlerecipeinput = new SingleRecipeInput(p_222694_.getItem(0));
      return p_222694_.quickCheck.getRecipeFor(singlerecipeinput, p_380169_).map(p_379263_ -> p_379263_.value().getCookingTime()).orElse(200);
   }

   @Override
   public int[] getSlotsForFace(Direction p_58363_) {
      if (p_58363_ == Direction.DOWN) {
         return SLOTS_FOR_DOWN;
      } else {
         return p_58363_ == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
      }
   }

   @Override
   public boolean canPlaceItemThroughFace(int p_58336_, ItemStack p_58337_, @Nullable Direction p_58338_) {
      return this.canPlaceItem(p_58336_, p_58337_);
   }

   @Override
   public boolean canTakeItemThroughFace(int p_58392_, ItemStack p_58393_, Direction p_58394_) {
      return p_58394_ == Direction.DOWN && p_58392_ == 1 ? p_58393_.is(Items.WATER_BUCKET) || p_58393_.is(Items.BUCKET) : true;
   }

   @Override
   public int getContainerSize() {
      return this.items.size();
   }

   @Override
   protected NonNullList<ItemStack> getItems() {
      return this.items;
   }

   @Override
   protected void setItems(NonNullList<ItemStack> p_332808_) {
      this.items = p_332808_;
   }

   @Override
   public void setItem(int p_58333_, ItemStack p_58334_) {
      ItemStack itemstack = this.items.get(p_58333_);
      boolean flag = !p_58334_.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, p_58334_);
      this.items.set(p_58333_, p_58334_);
      p_58334_.limitSize(this.getMaxStackSize(p_58334_));
      if (p_58333_ == 0 && !flag && this.level instanceof ServerLevel serverlevel) {
         this.cookingTotalTime = getTotalCookTime(serverlevel, this);
         this.cookingProgress = 0;
         this.setChanged();
      }
   }

   @Override
   public boolean canPlaceItem(int p_58389_, ItemStack p_58390_) {
      if (p_58389_ == 2) {
         return false;
      } else if (p_58389_ != 1) {
         return true;
      } else {
         ItemStack itemstack = this.items.get(1);
         return p_58390_.getBurnTime(this.recipeType, this.level.fuelValues()) > 0 || p_58390_.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
      }
   }

   @Override
   public void setRecipeUsed(@Nullable RecipeHolder<?> p_301245_) {
      if (p_301245_ != null) {
         ResourceKey<Recipe<?>> resourcekey = p_301245_.id();
         this.recipesUsed.addTo(resourcekey, 1);
      }
   }

   @Nullable
   @Override
   public RecipeHolder<?> getRecipeUsed() {
      return null;
   }

   @Override
   public void awardUsedRecipes(Player p_58396_, List<ItemStack> p_282202_) {
   }

   public void awardUsedRecipesAndPopExperience(ServerPlayer p_155004_) {
      List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(p_155004_.serverLevel(), p_155004_.position());
      p_155004_.awardRecipes(list);

      for (RecipeHolder<?> recipeholder : list) {
         if (recipeholder != null) {
            p_155004_.triggerRecipeCrafted(recipeholder, this.items);
         }
      }

      this.recipesUsed.clear();
   }

   public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel p_154996_, Vec3 p_154997_) {
      List<RecipeHolder<?>> list = Lists.newArrayList();

      for (Reference2IntMap.Entry<ResourceKey<Recipe<?>>> entry : this.recipesUsed.reference2IntEntrySet()) {
         p_154996_.recipeAccess().byKey(entry.getKey()).ifPresent(p_379268_ -> {
            list.add((RecipeHolder<?>)p_379268_);
            createExperience(p_154996_, p_154997_, entry.getIntValue(), ((AbstractCustomRecipe)p_379268_.value()).getExperience());
         });
      }

      return list;
   }

   private static void createExperience(ServerLevel p_154999_, Vec3 p_155000_, int p_155001_, float p_155002_) {
      int i = Mth.floor((float)p_155001_ * p_155002_);
      float f = Mth.frac((float)p_155001_ * p_155002_);
      if (f != 0.0F && Math.random() < (double)f) {
         i++;
      }

      ExperienceOrb.award(p_154999_, p_155000_, i);
   }

   @Override
   public void fillStackedContents(StackedItemContents p_363281_) {
      for (ItemStack itemstack : this.items) {
         p_363281_.accountStack(itemstack);
      }
   }
}
