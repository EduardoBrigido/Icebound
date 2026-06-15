package net.nyataro.iceboundmod.cold;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ColdEnvironmentChecker {

    public static boolean isPlayerCold(ServerPlayer player) {
        Level level = player.level();
        BlockPos pos = player.blockPosition();

        // temperatura do bioma
        Biome biome = level.getBiome(pos).value();
        float temperature = biome.getBaseTemperature();

        // temperatura ambiente
        boolean biomeIsCold = temperature <= 0.2f;

        // Chovendo ou se tem neve
        boolean snowing = level.isRaining() && biome.coldEnoughToSnow(pos);


        boolean snowNearby = level.getBlockStates(player.getBoundingBox().inflate(2))
                .anyMatch(state -> isSnowBlock(state));
        // Altitude do player
        boolean highAltitude = pos.getY() >= 180;


        return biomeIsCold || snowing || snowNearby || highAltitude;
    }

    private static boolean isSnowBlock(BlockState state) {
        return state.is(Blocks.SNOW) || state.is(Blocks.SNOW_BLOCK) || state.is(Blocks.POWDER_SNOW);
    }
}
