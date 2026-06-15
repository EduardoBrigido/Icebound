package net.nyataro.iceboundmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.nyataro.iceboundmod.client.ClientColdData;

import java.util.function.Supplier;

public class ColdDataSyncS2CPacket {
    private final double cold;

    public ColdDataSyncS2CPacket(double cold) {
        this.cold = cold;
    }

    public ColdDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.cold = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(cold);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientColdData.set(cold);

        });
        context.setPacketHandled(true);

        return true;
    }


}

