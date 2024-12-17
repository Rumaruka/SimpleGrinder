package com.rumaruka.simplegrinder.common.integraion.rei.Grinder;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.simplegrinder.common.recipe.AbstractCustomRecipe;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public abstract class AbsctractCustomDisplay extends BasicDisplay implements CustomDisplay  {
    protected float xp;
    protected double cookTime;

    public AbsctractCustomDisplay(RecipeHolder<? extends AbstractCustomRecipe> recipe) {
        this(List.of(EntryIngredients.ofIngredient(recipe.value().ingredient)),
                List.of(EntryIngredients.of(recipe.value().result)),
                Optional.of(recipe.id().location()), recipe.value().getExperience(), recipe.value().getCookingTime());
    }

    public AbsctractCustomDisplay(List<EntryIngredient> input, List<EntryIngredient> output, Optional<ResourceLocation> id, CompoundTag tag) {
        this(input, output, id, tag.getFloat("xp"), tag.getDouble("cookTime"));
    }

    @Override
    public OptionalDouble xp() {
        return OptionalDouble.of(xp);
    }
    @Override
    public OptionalDouble cookTime() {
        return OptionalDouble.of(cookTime);
    }
    public AbsctractCustomDisplay(List<EntryIngredient> input, List<EntryIngredient> output, Optional<ResourceLocation> id, float xp, double cookTime) {
        super(input, output, id);
        this.xp = xp;
        this.cookTime = cookTime;
    }



    protected static <D extends AbsctractCustomDisplay> DisplaySerializer<D> serializer(Constructor<D> constructor) {
        return DisplaySerializer.of(
                RecordCodecBuilder.mapCodec(instance -> instance.group(
                        EntryIngredient.codec().listOf().fieldOf("inputs").forGetter(D::getInputEntries),
                        EntryIngredient.codec().listOf().fieldOf("outputs").forGetter(D::getOutputEntries),
                        ResourceLocation.CODEC.optionalFieldOf("location").forGetter(D::getDisplayLocation),
                        Codec.FLOAT.fieldOf("xp").forGetter(display -> display.xp),
                        Codec.DOUBLE.fieldOf("cookTime").forGetter(display -> display.cookTime)
                ).apply(instance, constructor::create)),
                StreamCodec.composite(
                        EntryIngredient.streamCodec().apply(ByteBufCodecs.list()),
                        D::getInputEntries,
                        EntryIngredient.streamCodec().apply(ByteBufCodecs.list()),
                        D::getOutputEntries,
                        ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC),
                        D::getDisplayLocation,
                        ByteBufCodecs.FLOAT,
                        display -> display.xp,
                        ByteBufCodecs.DOUBLE,
                        display -> display.cookTime,
                        constructor::create
                ));
    }

    protected interface Constructor<T extends AbsctractCustomDisplay> {
        T create(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location, float xp, double cookTime);
    }
}
