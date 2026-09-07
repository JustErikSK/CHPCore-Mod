package net.withrage.chpcore.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class DwellerBootsModel<T extends LivingEntity> extends HumanoidModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(
                    ResourceLocation.fromNamespaceAndPath("chpcore", "dweller_boots"),
                    "main"
            );

    private final ModelPart boots;
    private final ModelPart leftBoot;
    private final ModelPart rightBoot;

    public DwellerBootsModel(ModelPart root) {
        super(root);

        this.boots = root.getChild("boots");
        this.leftBoot = this.boots.getChild("left_boot");
        this.rightBoot = this.boots.getChild("right_boot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(
                new CubeDeformation(0.0F),
                0.0F
        );

        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition boots = partdefinition.addOrReplaceChild(
                "boots",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        PartDefinition leftBoot = boots.addOrReplaceChild(
                "left_boot",
                CubeListBuilder.create()
                        .texOffs(12, 24).addBox(-2.0F, 8.0F, -3.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(12, 28).addBox(-2.0F, 8.0F, 3.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(22, 0).addBox(3.0F, 8.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(22, 10).addBox(-2.0F, 8.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-2.0F, 12.0F, -3.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 6).addBox(-2.0F, 8.0F, -3.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 30).addBox(1.0F, 11.0F, -5.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 32).addBox(-1.0F, 11.0F, -5.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.9F, -12.0F, 0.0F)
        );

        PartDefinition rightBoot = boots.addOrReplaceChild(
                "right_boot",
                CubeListBuilder.create()
                        .texOffs(22, 30).addBox(1.8F, 8.0F, -3.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(12, 32).addBox(1.8F, 8.0F, 3.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(22, 20).addBox(1.8F, 8.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 24).addBox(6.8F, 8.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 12).addBox(1.8F, 12.0F, -3.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 18).addBox(1.8F, 8.0F, -3.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(34, 0).addBox(2.8F, 11.0F, -5.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 34).addBox(4.8F, 11.0F, -5.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-6.9F, -12.0F, 0.0F)
        );

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void copyBootRotations(HumanoidModel<?> source) {
        this.leftBoot.xRot = source.leftLeg.xRot;
        this.leftBoot.yRot = source.leftLeg.yRot;
        this.leftBoot.zRot = source.leftLeg.zRot;

        this.rightBoot.xRot = source.rightLeg.xRot;
        this.rightBoot.yRot = source.rightLeg.yRot;
        this.rightBoot.zRot = source.rightLeg.zRot;
    }

    @Override
    public void renderToBuffer(
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        boots.render(
                poseStack,
                vertexConsumer,
                packedLight,
                packedOverlay,
                red,
                green,
                blue,
                alpha
        );
    }
}
