package net.nyataro.iceboundmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.nyataro.iceboundmod.entity.animations.ModAnimationDefinitions;
import net.nyataro.iceboundmod.entity.custom.Fire_RaccoonEntity;

public class Fire_RaccoonModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart controller;
    private final ModelPart body;
    private final ModelPart front_leg_l;
    private final ModelPart back_leg_l;
    private final ModelPart back_leg_r;
    private final ModelPart front_leg_r;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart ear_r;
    private final ModelPart ear_l;
    private final ModelPart torso;
    private final ModelPart tail;

    public Fire_RaccoonModel(ModelPart root) {
        this.controller = root.getChild("controller");
        this.body = this.controller.getChild("body");
        this.front_leg_l = this.body.getChild("front_leg_l");
        this.back_leg_l = this.body.getChild("back_leg_l");
        this.back_leg_r = this.body.getChild("back_leg_r");
        this.front_leg_r = this.body.getChild("front_leg_r");
        this.head = this.body.getChild("head");
        this.nose = this.head.getChild("nose");
        this.ear_r = this.head.getChild("ear_r");
        this.ear_l = this.head.getChild("ear_l");
        this.torso = this.body.getChild("torso");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition controller = partdefinition.addOrReplaceChild("controller", CubeListBuilder.create(), PartPose.offset(-4.0F, 14.5F, 8.0F));

        PartDefinition body = controller.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_leg_l = body.addOrReplaceChild("front_leg_l", CubeListBuilder.create().texOffs(14, 31).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.75F, -5.75F));

        PartDefinition back_leg_l = body.addOrReplaceChild("back_leg_l", CubeListBuilder.create().texOffs(30, 31).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 9.75F, -14.5F));

        PartDefinition back_leg_r = body.addOrReplaceChild("back_leg_r", CubeListBuilder.create().texOffs(0, 37).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.25F, 9.75F, -14.5F));

        PartDefinition front_leg_r = body.addOrReplaceChild("front_leg_r", CubeListBuilder.create().texOffs(22, 31).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.25F, 9.75F, -5.75F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 19).addBox(-5.0F, -6.0F, -1.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 6.0F, -3.5F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 31).addBox(-1.25F, -3.25F, -1.5F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 0.0F, 4.5F));

        PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create().texOffs(8, 37).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 3.5F));

        PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create().texOffs(14, 38).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -6.0F, 3.5F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.5F, -7.0F, 10.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 6.25F, -9.25F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(4.0F, 5.5F, -18.5F));

        PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 19).addBox(-3.0F, -3.0F, -4.0F, 4.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        this.animateWalk(ModAnimationDefinitions.raccoon_walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((Fire_RaccoonEntity) entity).idleAnimationState, ModAnimationDefinitions.raccoon_idle, ageInTicks, 1f);

    }
    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        controller.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public ModelPart root() {
        return controller;
    }
}

