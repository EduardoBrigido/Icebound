package net.nyataro.iceboundmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.nyataro.iceboundmod.worldgen.tree.ModFoliagePlacers;

public class SnowPalmFoliagePlacer extends FoliagePlacer {
    public  static final Codec<SnowPalmFoliagePlacer> CODEC = RecordCodecBuilder.create(snowPalmFoliagePlacerInstance
            -> foliagePlacerParts(snowPalmFoliagePlacerInstance). and(Codec.intRange(0,16).fieldOf("height").forGetter(fp -> fp.height))
            .apply(snowPalmFoliagePlacerInstance,SnowPalmFoliagePlacer::new));
    private final int height;

    public SnowPalmFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height= height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
            return ModFoliagePlacers.SNOW_PALM_FOLIAGE_PLACER.get();
    }

        @Override
        protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
        int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {

            BlockPos top = pAttachment.pos();

            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, top.above(3), 1, 0, false);

            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, top.above(2), 3, 0, false);
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, top.above(1), 3, 0, false);

            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, top, 2, 0, false);
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, top.below(1), 2, 0, false);
        }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return this.height;
    }


    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}
