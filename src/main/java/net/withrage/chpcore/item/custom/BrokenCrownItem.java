package net.withrage.chpcore.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BrokenCrownItem extends Item {

    private static final int NIGHT_VISION_DURATION = 20 * 60 * 3;
    private static final int COOLDOWN = 20 * 60 * 5;

    public BrokenCrownItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(
                Component.translatable("tooltip.chpcore.broken_crown.description")
                        .withStyle(ChatFormatting.DARK_GREEN)
        );

        tooltip.add(
                Component.translatable("tooltip.chpcore.broken_crown.craft")
                        .withStyle(ChatFormatting.GRAY)
        );

        tooltip.add(
                Component.translatable("tooltip.chpcore.broken_crown.ability")
                        .withStyle(ChatFormatting.LIGHT_PURPLE)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide()) {

            player.addEffect(new MobEffectInstance(
                    MobEffects.NIGHT_VISION,
                    NIGHT_VISION_DURATION,
                    0,
                    false,
                    false,
                    true
            ));

            player.getCooldowns().addCooldown(this, COOLDOWN);

            if (!player.getAbilities().instabuild) {
                stack.hurtAndBreak(1, player,
                        p -> p.broadcastBreakEvent(hand));
            }
        }

        return InteractionResultHolder.sidedSuccess(
                stack, level.isClientSide());
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}