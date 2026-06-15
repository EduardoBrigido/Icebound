package net.nyataro.iceboundmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.worldgen.tree.custom.SnowPalmTrunkPlacer;

public class ModTrunkPlacerTypes {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, IceBound.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<SnowPalmTrunkPlacer>> SNOWPALM_TRUNK_PLACER =
            TRUNK_PLACER.register("snowpalm_trunk_placer", () -> new TrunkPlacerType<>(SnowPalmTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
