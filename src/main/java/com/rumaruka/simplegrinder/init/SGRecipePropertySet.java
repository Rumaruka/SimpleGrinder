package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.SimpleGrinder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipePropertySet;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SGRecipePropertySet {
    public static final ResourceKey<? extends Registry<RecipePropertySet>> TYPE_KEY = ResourceKey.createRegistryKey(
            ResourceLocation.withDefaultNamespace("recipe_property_set")
    );
    public static final ResourceKey<RecipePropertySet> COAL_GRINDER_INPUT = registerSG("coal_grinder_input");
    private final Set<Holder<Item>> items;
    private SGRecipePropertySet(Set<Holder<Item>> p_379778_) {
        this.items = p_379778_;
    }

    private static ResourceKey<RecipePropertySet> registerSG(String p_380268_) {
        return ResourceKey.create(TYPE_KEY, ResourceLocation.fromNamespaceAndPath(SimpleGrinder.MODID,p_380268_));
    }

    public boolean test(ItemStack p_379477_) {
        return this.items.contains(p_379477_.getItemHolder());
    }

    static SGRecipePropertySet create(Collection<Ingredient> p_380396_) {
        Set<Holder<Item>> set = p_380396_.stream().flatMap(Ingredient::items).collect(Collectors.toUnmodifiableSet());
        return new SGRecipePropertySet(set);
    }
}
