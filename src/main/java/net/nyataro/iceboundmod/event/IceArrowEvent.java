package net.nyataro.iceboundmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nyataro.iceboundmod.IceBound;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber(modid = IceBound.MOD_ID)
public class IceArrowEvent {
    private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor();
    @SubscribeEvent
    public static void onIceArrowImpact(ProjectileImpactEvent event) {
        if (event.getProjectile() instanceof Arrow arrow && arrow.getTags().contains("is_ice_arrow")) {
            if (!arrow.level().isClientSide()) {
                Level level = arrow.level();

                // Posição central
                BlockPos center = BlockPos.containing(event.getRayTraceResult().getLocation());

                List<BlockPos> placedBlocks = new ArrayList<>();

                // Apenas os 4 lados
                BlockPos[] positions = {

                        center.north(),
                        center.south(),
                        center.east(),
                        center.west()
                };

                for (BlockPos targetPos : positions) {
                    if (level.getBlockState(targetPos).isAir()) {
                        level.setBlockAndUpdate(targetPos, Blocks.BLUE_ICE.defaultBlockState());
                        placedBlocks.add(targetPos.immutable());
                    }
                }

                // Agendador para remover após 5 segundos
                SCHEDULER.schedule(() -> {
                    if (arrow.getServer() != null) {
                        arrow.getServer().execute(() -> {
                            for (BlockPos posToReplace : placedBlocks) {
                                if (level.getBlockState(posToReplace).is(Blocks.BLUE_ICE)) {
                                    level.setBlockAndUpdate(posToReplace, Blocks.AIR.defaultBlockState());
                                }
                            }
                        });
                    }
                }, 2, TimeUnit.SECONDS);

                arrow.discard();
            }
        }
    }}