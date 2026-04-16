package net.withrage.chpcore.messages.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.withrage.chpcore.CHPcore;

@Mod.EventBusSubscriber(modid = CHPcore.MODID)
public class PlayerJoinHandler {

    private static final String CHP_CORE_FIRST_JOIN = "chp_core_first_join";

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        if (player.getServer() == null || player.getServer().isDedicatedServer()) {
            return;
        }

        CompoundTag data = player.getPersistentData();

        if (!data.getBoolean(CHP_CORE_FIRST_JOIN)) {
            player.sendSystemMessage(
                    Component.literal("Cave Horror Project: Origins has just been released!")
                            .withStyle(ChatFormatting.DARK_RED)
            );
            player.sendSystemMessage(
                    Component.literal("Go back to where it all began and enjoy the original experience that started this nightmare.")
                            .withStyle(ChatFormatting.RED)
            );
            player.sendSystemMessage(
                    Component.literal("")
            );
            player.sendSystemMessage(
                    Component.literal("Download it from this link...")
                            .withStyle(style -> style
                                    .withColor(ChatFormatting.AQUA)
                                    .withUnderlined(true)
                                    .withClickEvent(new ClickEvent(
                                            ClickEvent.Action.OPEN_URL,
                                            "https://curseforge.com/minecraft/modpacks/cave-horror-project-origins"
                                    ))
                            )
            );

            data.putBoolean(CHP_CORE_FIRST_JOIN, true);
        }
    }
}
