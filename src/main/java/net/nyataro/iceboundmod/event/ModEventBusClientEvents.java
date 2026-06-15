package net.nyataro.iceboundmod.event;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.client.ColdHudOverlay;
import net.nyataro.iceboundmod.client.layer.FrozenLayer;
import net.nyataro.iceboundmod.entity.ModEntities;
import net.nyataro.iceboundmod.entity.client.*;

@Mod.EventBusSubscriber(modid = IceBound.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.PENGUIN_LAYER, PenguinModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FIRE_RACCOON_LAYER, Fire_RaccoonModel::createBodyLayer);

    }
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {


        add(event, EntityType.ZOMBIE);
        add(event, EntityType.SKELETON);
        add(event, EntityType.CREEPER);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void add(EntityRenderersEvent.AddLayers event, EntityType<?> type) {

        var renderer = event.getRenderer(
                (EntityType<? extends net.minecraft.world.entity.LivingEntity>) type
        );

        if (!(renderer instanceof LivingEntityRenderer)) return;

        LivingEntityRenderer livingRenderer = (LivingEntityRenderer) renderer;

        livingRenderer.addLayer(new FrozenLayer(livingRenderer));

        System.out.println("Layer adicionada em: " + type);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PENGUIN.get(), PenguinRenderer::new);
        event.registerEntityRenderer(ModEntities.FIRE_RACCOON.get(), Fire_RaccoonRenderer::new);

    }
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("cold", ColdHudOverlay.HUD_COLD);
    }
}