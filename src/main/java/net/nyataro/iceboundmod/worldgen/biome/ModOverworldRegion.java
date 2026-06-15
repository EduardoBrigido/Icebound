package net.nyataro.iceboundmod.worldgen.biome;


import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        super.addBiomes(registry, mapper);



        mapper.accept(Pair.of(
                Climate.parameters(
                        Climate.Parameter.span(-1.0f, -0.6f),
                        Climate.Parameter.span(-0.1f, 0.1f),
                        Climate.Parameter.span(-0.19f, -0.11f),
                        Climate.Parameter.span(-0.56f, -0.08f),
                        Climate.Parameter.span(0.0f, 0.0f),
                        Climate.Parameter.span(0.0f, 0.0f),
                        0
                ),
                ModBiomes.SNOWYBEACH_BIOME
        ));
    }
}