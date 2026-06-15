package net.nyataro.iceboundmod.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import net.nyataro.iceboundmod.IceBound;
import terrablender.api.Regions;

public class ModTerrablender {


        public static void registerBiomes() {
            Regions.register(new ModOverworldRegion(new ResourceLocation(IceBound.MOD_ID, "overworld"), 3));
        }
    }

