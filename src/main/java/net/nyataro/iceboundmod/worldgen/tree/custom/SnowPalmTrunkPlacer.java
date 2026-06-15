package net.nyataro.iceboundmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.nyataro.iceboundmod.worldgen.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;

public class SnowPalmTrunkPlacer  extends TrunkPlacer {
    public  static final Codec<SnowPalmTrunkPlacer> CODEC = RecordCodecBuilder.create(pineTrunkPlacerInstance ->
            trunkPlacerParts(pineTrunkPlacerInstance).apply(pineTrunkPlacerInstance, SnowPalmTrunkPlacer::new));
    public SnowPalmTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.SNOWPALM_TRUNK_PLACER.get();
    }



    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level,
                                                            BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random,
                                                            int height, BlockPos startPos, TreeConfiguration config) {

        int dx = random.nextInt(3) - 1;
        int dz = random.nextInt(3) - 1;

        if (dx == 0 && dz == 0) {
            dx = random.nextBoolean() ? 1 : -1;
        }

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int offsetX = 0;
        int offsetZ = 0;

        for (int y = 0; y < height; y++) {
            if (y > 0 && y % 2 == 0) {
                offsetX += dx;
                offsetZ += dz;
            }

            mutable.setWithOffset(startPos, offsetX, y, offsetZ);
            placeLog(level, blockSetter, random, mutable, config);
        }


        return List.of(new FoliagePlacer.FoliageAttachment(
                startPos.offset(offsetX, height, offsetZ), 0, false));
    }
}