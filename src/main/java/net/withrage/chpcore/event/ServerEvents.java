package net.withrage.chpcore.event;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.withrage.chpcore.CHPCore;
import net.withrage.chpcore.integration.FromTheFogIntegration;

@Mod.EventBusSubscriber(
        modid = CHPCore.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public final class ServerEvents {

    private static final int APPLY_DELAY_TICKS = 20;

    private static int remainingDelay = -1;

    private ServerEvents() {
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        remainingDelay = APPLY_DELAY_TICKS;
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (remainingDelay < 0) {
            return;
        }

        if (remainingDelay > 0) {
            remainingDelay--;
            return;
        }

        remainingDelay = -1;

        FromTheFogIntegration.tryApplyPreset(
                event.getServer()
        );
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        remainingDelay = -1;
    }
}