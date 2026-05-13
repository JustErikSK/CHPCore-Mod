package net.withrage.chpcore.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
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

        ItemStack tool = event.getPlayer().getMainHandItem();

        int fortune = EnchantmentHelper.getItemEnchantmentLevel(
                Enchantments.BLOCK_FORTUNE,
                tool
        );

        if (fortune <= 0) return;

        float chance = switch (fortune) {
            case 1 -> 0.25F;
            case 2 -> 0.40F;
            default -> 0.55F;
        };

        int extraDebris = 0;

        if (level.random.nextFloat() < chance) {
            extraDebris = 1;
        }

        if (fortune >= 3 && level.random.nextFloat() < 0.03F) {
            extraDebris = 2;
        }

        if (extraDebris <= 0) return;

        Block.popResource(
                level,
                pos,
                new ItemStack(Items.ANCIENT_DEBRIS, extraDebris)
        );

        level.playSound(
                null,
                pos,
                SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.BLOCKS,
                0.6F,
                1.8F
        );
    }
}
