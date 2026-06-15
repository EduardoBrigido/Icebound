package net.nyataro.iceboundmod.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nyataro.iceboundmod.IceBound;


    @Mod.EventBusSubscriber(modid = IceBound.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public class ColdHudEvents {

        @SubscribeEvent
        public static void onRenderGui(RenderGuiOverlayEvent.Post event) {
            if (event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) {
                GuiGraphics guiGraphics = event.getGuiGraphics();
                int width = event.getWindow().getGuiScaledWidth();
                int height = event.getWindow().getGuiScaledHeight();
                ColdHudOverlay.HUD_COLD.render(null, guiGraphics, event.getPartialTick(), width, height);
            }
        }
    }

