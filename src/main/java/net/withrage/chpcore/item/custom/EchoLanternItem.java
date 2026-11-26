package net.withrage.chpcore.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class EchoLanternItem extends Item {

    private static final int COOLDOWN = 20 * 60;
    private static final int GLOWING_DURATION = 20 * 5;
    private static final double DETECTION_RADIUS = 32.0;

    public EchoLanternItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(
                Component.translatable("tooltip.chpcore.echo_lantern.description")
                        .withStyle(ChatFormatting.DARK_GREEN)
        );

        tooltip.add(
                Component.translatable("tooltip.chpcore.echo_lantern.craft")
                        .withStyle(ChatFormatting.GRAY)
        );

        tooltip.add(
                Component.translatable("tooltip.chpcore.echo_lantern.ability")
                        .withStyle(ChatFormatting.LIGHT_PURPLE)
        );

        tooltip.add(
                Component.translatable("tooltip.chpcore.echo_lantern.passive")
                        .withStyle(ChatFormatting.DARK_PURPLE)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        if (level instanceof ServerLevel serverLevel) {

            List<Mob> nearbyMobs = serverLevel.getEntitiesOfClass(
                    Mob.class,
                    player.getBoundingBox().inflate(DETECTION_RADIUS),
                    mob -> mob instanceof Enemy
                            && mob.isAlive()
                            && mob.distanceToSqr(player)
                            <= DETECTION_RADIUS * DETECTION_RADIUS
            );

            for (Mob mob : nearbyMobs) {
                mob.addEffect(new MobEffectInstance(
                        MobEffects.GLOWING,
                        GLOWING_DURATION,
                        0,
                        false,
                        false
                ));
            }

            serverLevel.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.BELL_RESONATE,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            serverLevel.sendParticles(
                    ParticleTypes.ENCHANT,
                    player.getX(),
                    player.getY() + 1.0,
                    player.getZ(),
                    40,
                    1.0, 0.8, 1.0,
                    0.1
            );

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