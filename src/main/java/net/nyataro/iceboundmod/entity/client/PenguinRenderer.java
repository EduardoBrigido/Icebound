package net.nyataro.iceboundmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.entity.custom.PenguinEntity;

public class PenguinRenderer extends MobRenderer<PenguinEntity, PenguinModel<PenguinEntity>> {





    public PenguinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PenguinModel<>(pContext.bakeLayer(ModModelLayers.PENGUIN_LAYER)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(PenguinEntity pEntity) {
        return new ResourceLocation(IceBound.MOD_ID, "textures/entity/penguin.png");
    }

    @Override
    public void render(PenguinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180f));


        pMatrixStack.scale(3.2f, 3.2f, 3.2f);
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}