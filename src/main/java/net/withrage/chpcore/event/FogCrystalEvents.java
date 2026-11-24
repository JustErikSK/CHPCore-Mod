package net.withrage.chpcore.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.withrage.chpcore.CHPCore;
import net.withrage.chpcore.item.ModItems;

import java.util.Set;

@Mod.EventBusSubscriber(
        modid = CHPCore.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class FogCrystalEvents {

    private static final double DETECTION_MULTIPLIER = 0.75;

    private static final Set<EntityType<?>> AFFECTED_MOBS = Set.of(
            EntityType.ZOMBIE,
            EntityType.HUSK,
            EntityType.DROWNED,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.SKELETON,
            EntityType.STRAY,
            EntityType.WITHER_SKELETON,
            EntityType.CREEPER,
            EntityType.SPIDER,
            EntityType.CAVE_SPIDER,
            EntityType.PHANTOM,
            EntityType.WITCH,
            EntityType.PILLAGER,
            EntityType.VINDICATOR
    );

    @SubscribeEvent
    public static void onTargetChange(LivingChangeTargetEvent event) {

        if (!(event.getEntity() instanceof Mob mob)) {
            return;
        }

        if (mob.level().isClientSide()) {
            return;
        }

        if (!AFFECTED_MOBS.contains(mob.getType())) {
            return;
        }

        if (!(event.getNewTarget() instanceof Player player)) {
            return;
        }

        if (!hasFogCrystal(player)) {
            return;
        }

        if (mob.getAttribute(Attributes.FOLLOW_RANGE) == null) {
            return;
        }

        double followRange =
                mob.getAttributeValue(Attributes.FOLLOW_RANGE);

        double visibility = player.getVisibilityPercent(mob);

        double reducedRange = Math.max(
                2.0,
                followRange * visibility * DETECTION_MULTIPLIER
        );

        if (mob.distanceToSqr(player) >
                reducedRange * reducedRange) {

            event.setCanceled(true);
        }
    }

    private static boolean hasFogCrystal(Player player) {

        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.FOG_CRYSTAL.get())) {
                return true;
            }
        }

        return player.getOffhandItem().is(
                ModItems.FOG_CRYSTAL.get()
        );
    }
}