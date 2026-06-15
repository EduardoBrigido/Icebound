package net.nyataro.iceboundmod.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.worldgen.tree.custom.SnowPalmFoliagePlacer;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, IceBound.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<SnowPalmFoliagePlacer>> SNOW_PALM_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("snow_palm_foliage_placer", () -> new FoliagePlacerType<>(SnowPalmFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
