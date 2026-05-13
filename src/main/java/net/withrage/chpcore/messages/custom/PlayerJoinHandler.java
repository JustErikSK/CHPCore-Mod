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
                    Component.literal("Want to enjoy Cave Horror Project with your friends?")
                            .withStyle(ChatFormatting.RED)
            );
            player.sendSystemMessage(
                    Component.literal("Use our code ' withrage ' to get 25% off of your server!")
                            .withStyle(style -> style
                                    .withColor(ChatFormatting.AQUA)
                                    .withUnderlined(true)
                                    .withClickEvent(new ClickEvent(
                                            ClickEvent.Action.OPEN_URL,
                                            "https://www.bisecthosting.com/withrage"
                                    ))
                            )
            );
            data.putBoolean(CHP_CORE_FIRST_JOIN, true);
        }
    }
}
