package com.rumaruka.simplegrinder.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class SGFood {
    public static FoodProperties MASH_CARROT;
    public static FoodProperties MASH_POTATO;
    public static FoodProperties OMLETE;
    public static Consumable.Builder defaultFood() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }
    static {
        MASH_CARROT = new FoodProperties.Builder().nutrition(4).saturationModifier(2.9f).build();
        MASH_POTATO = new FoodProperties.Builder().nutrition(4).saturationModifier(2.9f).build();
        OMLETE = new FoodProperties.Builder().nutrition(2).saturationModifier(2.6f).build();
    }

    public static final Consumable MASH_POTATO_EFFECTS = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 1))
                    )
            )
            .build();

    public static final Consumable MASH_CARROT_EFFECTS = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 1))
                    )
            )
            .build();

}
