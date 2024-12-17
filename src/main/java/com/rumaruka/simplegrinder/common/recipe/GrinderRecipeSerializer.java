package com.rumaruka.simplegrinder.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class GrinderRecipeSerializer<T extends AbstractCustomRecipe> implements RecipeSerializer<T> {
   private final CookieBaker<T> factory;
   private final MapCodec<T> codec;
   private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;
   public GrinderRecipeSerializer(CookieBaker<T> p_44330_, int p_44331_) {
      this.factory = p_44330_;
      this.codec = RecordCodecBuilder.mapCodec((p_296927_) -> {
         return p_296927_.group(Codec.STRING.optionalFieldOf( "group", "").forGetter((p_296921_) -> {
            return p_296921_.group;
         }), Ingredient.CODEC.fieldOf("ingredient").forGetter((p_296920_) -> {
            return p_296920_.ingredient;
         }),  ItemStack.STRICT_CODEC.fieldOf("result").forGetter((p_301142_) -> {
            return p_301142_.result;


         }), Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter((p_296922_) -> {
            return p_296922_.experience;
         }), Codec.INT.fieldOf("cookingtime").orElse(p_44331_).forGetter((p_296919_) -> {
            return p_296919_.cookingTime;
         })).apply(p_296927_, p_44330_::create);
      });
      this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
   }



    public MapCodec<T> codec() {
      return this.codec;
   }

   @Override
   public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
      return streamCodec;
   }

   public T fromNetwork(RegistryFriendlyByteBuf p_44351_) {
      String s = p_44351_.readUtf();
      Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(p_44351_);
      ItemStack itemstack = ItemStack.STREAM_CODEC.decode(p_44351_);
      float f = p_44351_.readFloat();

      int i = p_44351_.readVarInt();

      return this.factory.create(s,  ingredient, itemstack, f, i);
   }

   public void toNetwork(RegistryFriendlyByteBuf p_44335_, T p_44336_) {
      p_44335_.writeUtf(p_44336_.group);
      Ingredient.CONTENTS_STREAM_CODEC.encode(p_44335_,p_44336_.ingredient);
      ItemStack.STREAM_CODEC.encode(p_44335_,p_44336_.result);

      p_44335_.writeFloat(p_44336_.experience);
      p_44335_.writeVarInt(p_44336_.cookingTime);
   }

   public  interface CookieBaker<T extends AbstractCustomRecipe> {
      T create(String p_44354_, Ingredient p_44355_, ItemStack p_44356_,  float p_44357_, int p_44358_);
   }
}