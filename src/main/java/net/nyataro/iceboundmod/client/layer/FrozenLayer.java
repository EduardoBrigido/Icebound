package net.nyataro.iceboundmod.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.registry.ModEffects;


public class FrozenLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation ICE_TEXTURE = new ResourceLocation("yourmodid", "textures/entity/ice_overlay.png");

    public FrozenLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.hasEffect(ModEffects.FROZEN.get())) {
            VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(ICE_TEXTURE));
            poseStack.pushPose();
            poseStack.scale(1.1f, 1.05f, 1.1f);
            this.getParentModel().renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.7F);
            poseStack.popPose();
        }
    }
}