package net.withrage.chpcore.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.withrage.chpcore.CHPCore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mod.EventBusSubscriber(
        modid = CHPCore.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class FogCloudManager {

    private static final int CLOUD_DURATION = 40;

    private static final List<FogCloud> ACTIVE_CLOUDS =
            new ArrayList<>();

    public static void createCloud(
            ServerLevel level, double x, double y, double z) {

        ACTIVE_CLOUDS.add(new FogCloud(level, x, y, z));

        level.sendParticles(
                ParticleTypes.CLOUD,
                x, y + 0.8, z,
                40,
                0.7, 0.6, 0.7,
                0.02
        );
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {

        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (!(event.level instanceof ServerLevel level)) {
            return;
        }

        Iterator<FogCloud> iterator = ACTIVE_CLOUDS.iterator();

        while (iterator.hasNext()) {

            FogCloud cloud = iterator.next();

            if (cloud.level != level) {
                continue;
            }

            if (cloud.remainingTicks % 2 == 0) {

                level.sendParticles(
                        ParticleTypes.CLOUD,
                        cloud.x,
                        cloud.y + 0.8,
                        cloud.z,
                        8,
                        0.9, 0.6, 0.9,
                        0.01
                );
            }

            cloud.remainingTicks--;

            if (cloud.remainingTicks <= 0) {
                iterator.remove();
            }
        }
    }

    @SubscribeEvent
    public static void onLevelUnload(LevelEvent.Unload event) {
        ACTIVE_CLOUDS.removeIf(
                cloud -> cloud.level == event.getLevel()
        );
    }

    private static class FogCloud {

        private final ServerLevel level;

        private final double x;
        private final double y;
        private final double z;

        private int remainingTicks = CLOUD_DURATION;

        private FogCloud(
                ServerLevel level, double x, double y, double z) {

            this.level = level;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}