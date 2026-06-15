package net.nyataro.iceboundmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.entity.custom.Fire_RaccoonEntity;

public class Fire_RaccoonRenderer extends MobRenderer<Fire_RaccoonEntity, Fire_RaccoonModel<Fire_RaccoonEntity>> {


    public Fire_RaccoonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Fire_RaccoonModel(pContext.bakeLayer(ModModelLayers.FIRE_RACCOON_LAYER)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(Fire_RaccoonEntity pEntity) {
        return new ResourceLocation(IceBound.MOD_ID, "textures/entity/fire_raccoon.png");
    }

    @Override
    public void render(Fire_RaccoonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180f));

        pMatrixStack.scale(1.5f, 1.5f, 1.5f);

        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}



