package net.nyataro.iceboundmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;


import net.nyataro.iceboundmod.IceBound;


    public class ColdHudOverlay {

        private static final ResourceLocation COLD_FULL =
                new ResourceLocation(IceBound.MOD_ID, "textures/cold/cold_full.png");
        private static final ResourceLocation COLD_EMPTY =
                new ResourceLocation(IceBound.MOD_ID, "textures/cold/cold_empty.png");

        public static final IGuiOverlay HUD_COLD = (gui, guiGraphics, partialTick, width, height) -> {



            int x = width / 2;
            int y = height;



            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return;

            int offsetY = 54;
            if (player.getArmorValue() > 0) {
                offsetY += 10;
            }




            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            RenderSystem.setShaderTexture(0, COLD_EMPTY);

            // Icones de frio vazios
            RenderSystem.setShaderTexture(0, COLD_EMPTY);
            for (int i = 0; i < 10; i++) {
                guiGraphics.blit(COLD_EMPTY, x - 94 + (i * 9), y - offsetY,0,0,12,12,
                        12,12);
            }

            // Icones de  frio cheios
            RenderSystem.setShaderTexture(0, COLD_FULL);
            double cold = ClientColdData.getColdLevel();

            for (int i = 0; i < 10; i++) {
                if (cold > i) {
                    double fill = Math.min(1.0, cold - i);
                    guiGraphics.blit(COLD_FULL, x - 94 + (i * 9), y - offsetY,0,0,(int)(12 * fill),
                            12,
                            12, 12);
                } else {
                    break;
                }
            }
        };
    }
