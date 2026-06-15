package net.nyataro.iceboundmod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.networking.packet.ColdDataSyncS2CPacket;
import net.nyataro.iceboundmod.networking.packet.ColdZoneC2SPacket;
import net.nyataro.iceboundmod.networking.packet.ExampleC2SPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

        public static void register() {
            SimpleChannel net = NetworkRegistry.ChannelBuilder
                    .named(new ResourceLocation(IceBound.MOD_ID, "messages"))
                    .networkProtocolVersion(() -> "1.0")
                    .clientAcceptedVersions(s -> true)
                    .serverAcceptedVersions(s -> true)
                    .simpleChannel();

            INSTANCE = net;

            net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                    .decoder(ExampleC2SPacket::new)
                    .encoder(ExampleC2SPacket::toBytes)
                    .consumerMainThread(ExampleC2SPacket::handle)
                    .add();

            net.messageBuilder(ColdZoneC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                    .decoder(ColdZoneC2SPacket::new)
                    .encoder(ColdZoneC2SPacket::toBytes)
                    .consumerMainThread(ColdZoneC2SPacket::handle)
                    .add();

            net.messageBuilder(ColdDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                    .decoder(ColdDataSyncS2CPacket::new)
                    .encoder(ColdDataSyncS2CPacket::toBytes)
                    .consumerMainThread(ColdDataSyncS2CPacket::handle)
                    .add();
        }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}