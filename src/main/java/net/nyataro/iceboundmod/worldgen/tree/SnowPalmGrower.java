package net.nyataro.iceboundmod.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.nyataro.iceboundmod.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class SnowPalmGrower extends AbstractTreeGrower {


    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return ModConfiguredFeatures.SNOWPALM_KEY;
    }
}
