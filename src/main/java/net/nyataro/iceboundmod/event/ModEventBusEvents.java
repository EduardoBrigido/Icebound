package net.nyataro.iceboundmod.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.cold.ColdEnvironmentChecker;
import net.nyataro.iceboundmod.cold.PlayerCold;
import net.nyataro.iceboundmod.cold.PlayerColdProvider;
import net.nyataro.iceboundmod.entity.ModEntities;
import net.nyataro.iceboundmod.entity.custom.Fire_RaccoonEntity;
import net.nyataro.iceboundmod.entity.custom.PenguinEntity;
import net.nyataro.iceboundmod.item.ColdArmorItem;
import net.nyataro.iceboundmod.networking.ModMessages;
import net.nyataro.iceboundmod.networking.packet.ColdDataSyncS2CPacket;

@Mod.EventBusSubscriber(modid = IceBound.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class    ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PENGUIN.get(), PenguinEntity.createAttributes().build());
        event.put(ModEntities.FIRE_RACCOON.get(), Fire_RaccoonEntity.createAttributes().build());

    }



    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerCold.class);
    }



    @Mod.EventBusSubscriber(modid = IceBound.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    class ForgeEvents {
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                if(!event.getObject().getCapability(PlayerColdProvider.PLAYER_COLD).isPresent()) {
                    event.addCapability(new ResourceLocation(IceBound.MOD_ID, "properties"), new PlayerColdProvider());
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerColdProvider.PLAYER_COLD).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerColdProvider.PLAYER_COLD).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }




        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (!event.player.level().isClientSide() && event.player instanceof ServerPlayer serverPlayer) {
                ServerLevel level = serverPlayer.serverLevel();

                serverPlayer.getCapability(PlayerColdProvider.PLAYER_COLD).ifPresent(cold -> {


                    boolean nearWarmMob = !level.getEntitiesOfClass(
                            Mob.class,
                            serverPlayer.getBoundingBox().inflate(5),
                            mob -> mob.getType() == ModEntities.FIRE_RACCOON.get()
                    ).isEmpty();

                    if (nearWarmMob) {
                        float warmChance = 0.009f; // 3% por tick

                        if (cold.getCold() > 0 && serverPlayer.getRandom().nextFloat() < warmChance) {
                            cold.subCold(0.5, serverPlayer);
                        }
                    }

                    boolean environmentIsCold = ColdEnvironmentChecker.isPlayerCold(serverPlayer);

                    //  Resistencia de Frio com armadura
                    double totalResistance = 0.0;
                    for (EquipmentSlot slot : EquipmentSlot.values()) {
                        if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                            if (serverPlayer.getItemBySlot(slot).getItem() instanceof ColdArmorItem armor) {
                                totalResistance += armor.getColdResistance();
                            }
                        }
                    }
                    totalResistance = Math.min(totalResistance, 0.95);

                    float baseGainChance = 0.005f; // 0.5% chance de ganhar cold
                    float baseLoseChance = 0.005f; // 0.5% chance de perder cold

                    float currentGainChance = (float) (baseGainChance * (1.0 - totalResistance));
                    float currentLoseChance = (float) (baseLoseChance * (1.0 + totalResistance * 2));


                    // Verifica se o ambiente está frio, aumentando  se for verdadeiro ou diminuindo se for falso
                    if (environmentIsCold) {
                        if (cold.getCold() < 100 && serverPlayer.getRandom().nextFloat() < currentGainChance) {
                            cold.addCold(0.5, serverPlayer);
                        }
                    } else {
                        if (cold.getCold() > 0 && serverPlayer.getRandom().nextFloat() < currentLoseChance) {
                            cold.subCold(1, serverPlayer);
                        }
                    }

                    ModMessages.sendToPlayer(new ColdDataSyncS2CPacket(cold.getCold()), serverPlayer);
                });
            }
        }


        @SubscribeEvent
        public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerColdProvider.PLAYER_COLD).ifPresent(cold -> {
                    ModMessages.sendToPlayer(new ColdDataSyncS2CPacket(cold.getCold()), player);
                });
            }
        }
    }
}







