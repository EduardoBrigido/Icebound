package net.nyataro.iceboundmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.block.ModBlocks;
import net.nyataro.iceboundmod.worldgen.tree.custom.SnowPalmFoliagePlacer;
import net.nyataro.iceboundmod.worldgen.tree.custom.SnowPalmTrunkPlacer;

import java.sql.Blob;
import java.util.List;


public class ModConfiguredFeatures {

        public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CRYSTALLUM_ORE_KEY =
                registerKey("sapphire_ore");

    public  static final  ResourceKey<ConfiguredFeature<?,?>> SNOWPALM_KEY= registerKey("snow_palm");
        public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
            RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
            RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

            List<OreConfiguration.TargetBlockState> overworldCrystallumOres =
                    List.of(
                            OreConfiguration.target(stoneReplaceable, ModBlocks.CRYSTALLUM_ORE.get().defaultBlockState()),
                            OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_CRYSTALLUM_ORE.get().defaultBlockState())
                    );

            register(
                    context,
                    OVERWORLD_CRYSTALLUM_ORE_KEY,
                    Feature.ORE,
                    new OreConfiguration(overworldCrystallumOres, 9)
            );
            register(context,SNOWPALM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.SNOWPALM_LOG.get()),
                    new SnowPalmTrunkPlacer(5, 4, 3),
                    BlockStateProvider.simple(ModBlocks.SNOW_PALM_LEAVES.get().defaultBlockState()
                            .setValue(LeavesBlock.PERSISTENT, true)),
                    new SnowPalmFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

        }

        public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                    new ResourceLocation(IceBound.MOD_ID, name));
        }

        private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
                BootstapContext<ConfiguredFeature<?, ?>> context,
                ResourceKey<ConfiguredFeature<?, ?>> key,
                F feature,
                FC configuration
        ) {
            context.register(key, new ConfiguredFeature<>(feature, configuration));
        }
    }
