package com.rumaruka.simplegrinder.init;


import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.common.recipe.GrinderRecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;
public class SGRecipe {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);

    public static void setup(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);

    }

    public static final DeferredHolder<RecipeSerializer<?>, GrinderRecipeSerializer<GrinderRecipe>> GRINDER = RECIPE_SERIALIZER.register("grinder", () -> new GrinderRecipeSerializer<>(GrinderRecipe::new, 100));


}
