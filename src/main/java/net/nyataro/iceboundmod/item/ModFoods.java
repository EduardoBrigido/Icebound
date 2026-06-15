package net.nyataro.iceboundmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(0.3f).fast()
          .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300), 1f).build();

    public static final FoodProperties ICEBERRY = new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(0.5f).fast()
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400), 1f).build();

    public static final FoodProperties ICE_CREAM = new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(0.3f).fast()
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 500), 1)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300), 1f)
            .build();


}


