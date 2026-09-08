package net.withrage.chpcore.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.withrage.chpcore.CHPCore;
import net.withrage.chpcore.effect.ModEffects;
import net.withrage.chpcore.item.ModItems;

import java.util.UUID;

@Mod.EventBusSubscriber(
        modid = CHPCore.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class DwellerInstinctEvents {

    private static final UUID SNEAK_SPEED_UUID =
            UUID.fromString("fcf58bc1-5345-4ddd-b41b-195a063d5694");

    private static final AttributeModifier SNEAK_SPEED_MODIFIER =
            new AttributeModifier(
                    SNEAK_SPEED_UUID,
                    "Dweller's Instinct sneak speed",
                    0.50D,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Player player = event.player;

        if (player.level().isClientSide()) {
            return;
        }

        boolean wearingDwellerBoots =
                player.getItemBySlot(EquipmentSlot.FEET)
                        .is(ModItems.DWELLER_BOOTS.get());

        if (wearingDwellerBoots) {
            BlockPos playerPos = player.blockPosition();

            int surfaceY = player.level().getHeight(
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    playerPos.getX(),
                    playerPos.getZ()
            );

            boolean underground =
                    !player.level().canSeeSky(playerPos)
                            && player.getY() < surfaceY - 5;

            if (underground) {
                MobEffectInstance currentEffect =
                        player.getEffect(ModEffects.DWELLER_INSTINCT.get());

                if (currentEffect == null || currentEffect.getDuration() < 220) {
                    player.addEffect(new MobEffectInstance(
                            ModEffects.DWELLER_INSTINCT.get(),
                            300,
                            0,
                            false,
                            false,
                            true
                    ));
                }
            }
        }

        AttributeInstance movementSpeed =
                player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (movementSpeed == null) {
            return;
        }

        boolean shouldHaveSneakBonus =
                player.hasEffect(ModEffects.DWELLER_INSTINCT.get())
                        && player.isCrouching();

        boolean currentlyHasBonus =
                movementSpeed.getModifier(SNEAK_SPEED_UUID) != null;

        if (shouldHaveSneakBonus) {
            if (!currentlyHasBonus) {
                movementSpeed.addTransientModifier(SNEAK_SPEED_MODIFIER);
            }
        } else if (currentlyHasBonus) {
            movementSpeed.removeModifier(SNEAK_SPEED_UUID);
        }
    }
}