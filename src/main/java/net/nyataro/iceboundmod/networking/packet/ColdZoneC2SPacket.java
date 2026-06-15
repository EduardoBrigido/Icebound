package net.nyataro.iceboundmod.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;
import net.nyataro.iceboundmod.cold.PlayerColdProvider;
import net.nyataro.iceboundmod.networking.ModMessages;

import java.util.function.Supplier;

public class ColdZoneC2SPacket {


    private static final String MESSAGE_NEAR_SNOW = "message.tutorialmod.near_snow";
    private static final String MESSAGE_NO_SNOW = "message.tutorialmod.no_snow";


    public ColdZoneC2SPacket() {}

    public ColdZoneC2SPacket(FriendlyByteBuf buf) {}

    public void toBytes(FriendlyByteBuf buf) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            ServerLevel level = (ServerLevel) player.level();

            if (hasSnowAroundThem(player, level, 2)) {
                player.sendSystemMessage(Component.translatable(MESSAGE_NEAR_SNOW).withStyle(ChatFormatting.AQUA));
                level.playSound(null, player.blockPosition(), SoundEvents.SNOW_BREAK, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);

                player.getCapability(PlayerColdProvider.PLAYER_COLD).ifPresent(cold -> {
                    cold.addCold(1, player);
                    player.sendSystemMessage(Component.literal("Current Cold " + cold.getCold())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ColdDataSyncS2CPacket(cold.getCold()), player);
                });

            } else {
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_SNOW).withStyle(ChatFormatting.RED));

                player.getCapability(PlayerColdProvider.PLAYER_COLD).ifPresent(cold -> {
                    player.sendSystemMessage(Component.literal("Current Cold " + cold.getCold())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ColdDataSyncS2CPacket(cold.getCold()), player);
                });
            }
        });

        context.setPacketHandled(true);
        return true;
    }

    private boolean hasSnowAroundThem(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.SNOW)).toArray().length > 0;
    }

}