package net.nyataro.iceboundmod.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.entity.ModEntities;
import net.nyataro.iceboundmod.worldgen.biome.ModBiomes;

import java.util.List;

public class ModBiomesModifiers {



    public static final ResourceKey<BiomeModifier> ADD_CRYSTALLUM_ORE =
            ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                    new ResourceLocation(IceBound.MOD_ID, "add_crystallum_ore"));


    public static final ResourceKey<BiomeModifier> ADD_PENGUIN_SPAWNS =
            ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                    new ResourceLocation(IceBound.MOD_ID, "add_penguin_spawns"));

    public static final ResourceKey<BiomeModifier> ADD_FIRE_RACCOON_SPAWNS =
            ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                    new ResourceLocation(IceBound.MOD_ID, "add_fire_raccoon_spawns"));


    public static final ResourceKey<BiomeModifier> ADD_SNOWPALM_TREE = registerKey("add_snowpalm_tree");


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(IceBound.MOD_ID, name));
    }

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SNOWPALM_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SNOWYBEACH_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SNOWPALM_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        HolderSet<Biome> desertBiomes = HolderSet.direct(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(ModBiomes.SNOWYBEACH_BIOME)

        );

        HolderSet<Biome> coldBiomes = HolderSet.direct(
                biomes.getOrThrow(Biomes.SNOWY_PLAINS),
                biomes.getOrThrow(Biomes.SNOWY_TAIGA),
                biomes.getOrThrow(Biomes.FROZEN_RIVER),
                biomes.getOrThrow(Biomes.SNOWY_BEACH),
                biomes.getOrThrow(Biomes.SNOWY_SLOPES),
                biomes.getOrThrow(Biomes.FROZEN_PEAKS),
                biomes.getOrThrow(ModBiomes.SNOWYBEACH_BIOME)


        );

        context.register(
                ADD_CRYSTALLUM_ORE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        coldBiomes,
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CRYSTALLUM_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
        context.register(
                ADD_PENGUIN_SPAWNS,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                        coldBiomes,
                        List.of(new MobSpawnSettings.SpawnerData(
                                ModEntities.PENGUIN.get(), 50, 1, 4
                        ))
                )
        );
        context.register(
                ADD_FIRE_RACCOON_SPAWNS,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                        desertBiomes,
                        List.of(new MobSpawnSettings.SpawnerData(
                                ModEntities.FIRE_RACCOON.get(), 15, 1, 2
                        ))
                )
        );
    }
}
