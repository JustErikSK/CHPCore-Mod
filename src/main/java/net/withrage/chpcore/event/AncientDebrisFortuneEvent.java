package net.withrage.chpcore.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AncientDebrisFortuneEvent {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        BlockPos pos = event.getPos();

        if (level.getBlockState(pos).getBlock() != Blocks.ANCIENT_DEBRIS) return;

        Player player = event.getPlayer();
        ItemStack tool = event.getPlayer().getMainHandItem();

        if (!tool.isCorrectToolForDrops(level.getBlockState(pos))) return;

        event.setCanceled(true);

        level.destroyBlock(pos, false, player);

        int fortune = EnchantmentHelper.getItemEnchantmentLevel(
                Enchantments.BLOCK_FORTUNE,
                tool
        );

        int scraps = 1;

        boolean normalBonus = false;
        boolean jackpotBonus = false;

        if (fortune > 0) {
            float chance = switch (fortune) {
                case 1 -> 0.25F;
                case 2 -> 0.40F;
                default -> 0.55F;
            };

            if (level.random.nextFloat() < chance) {
                scraps++;
                normalBonus = true;
            }

            if (fortune >= 3 && level.random.nextFloat() < 0.03F) {
                scraps++;
                jackpotBonus = true;
            }
        }

        Block.popResource(
                level,
                pos,
                new ItemStack(Items.NETHERITE_SCRAP, scraps)
        );

        if (jackpotBonus) {
            level.playSound(
                    null,
                    pos,
                    SoundEvents.PLAYER_LEVELUP,
                    SoundSource.BLOCKS,
                    0.7F,
                    1.8F
            );
        }
        else if (normalBonus) {
            level.playSound(
                    null,
                    pos,
                    SoundEvents.EXPERIENCE_ORB_PICKUP,
                    SoundSource.BLOCKS,
                    0.6F,
                    1.8F
            );
        }

        tool.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
    }
}
