package net.withrage.chpcore.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.withrage.chpcore.client.model.DwellerBootsModel;

import java.util.List;
import java.util.function.Consumer;

public class DwellerBootsItem extends ArmorItem {

    public DwellerBootsItem(Properties properties) {
        super(new DwellerArmorMaterial(), Type.BOOTS, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private DwellerBootsModel<LivingEntity> model;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(
                    LivingEntity livingEntity,
                    ItemStack itemStack,
                    EquipmentSlot equipmentSlot,
                    HumanoidModel<?> original
            ) {

                if (model == null) {
                    model = new DwellerBootsModel<>(
                            Minecraft.getInstance()
                                    .getEntityModels()
                                    .bakeLayer(DwellerBootsModel.LAYER_LOCATION)
                    );
                }

                model.copyPropertiesTo(model);
                model.copyBootRotations(original);

                return model;
            }
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(
                Component.translatable("tooltip.chpcore.dweller_boots.ability")
                        .withStyle(ChatFormatting.DARK_GREEN)
        );

        tooltip.add(
                Component.translatable("tooltip.chpcore.dweller_boots.description")
                        .withStyle(ChatFormatting.GRAY)
        );
    }

    @Override
    public String getArmorTexture(
            ItemStack stack,
            net.minecraft.world.entity.Entity entity,
            EquipmentSlot slot,
            String type
    ) {
        return "chpcore:textures/armor/dweller_boots.png";
    }
}