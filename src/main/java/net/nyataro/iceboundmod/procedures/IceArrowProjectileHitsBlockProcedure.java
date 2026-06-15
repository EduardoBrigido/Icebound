package net.nyataro.iceboundmod.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class IceArrowProjectileHitsBlockProcedure {

    public static void execute(LevelAccessor world, double x, double y, double z) {

        BlockPos pos = BlockPos.containing(x, y, z);

        if (world.isEmptyBlock(pos)
                || world.getBlockState(pos).is(Blocks.WATER)) {

            world.setBlock(pos, Blocks.ICE.defaultBlockState(), 3);
        }
    }
}
