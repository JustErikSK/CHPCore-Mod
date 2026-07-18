package net.withrage.chpcore.integration;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.withrage.chpcore.CHPCore;
import net.withrage.chpcore.config.CHPCoreServerConfig;
import net.withrage.chpcore.data.CHPCoreSavedData;

public final class FromTheFogIntegration {

    public static final int CURRENT_PRESET_VERSION = 1;

    private static final String CONFIG_OBJECTIVE =
            "ftf.configOptions";

    private FromTheFogIntegration() {
    }

    public static void tryApplyPreset(MinecraftServer server) {
        if (!CHPCoreServerConfig.APPLY_FROM_THE_FOG_PRESET.get()) {
            return;
        }

        CHPCoreSavedData savedData =
                CHPCoreSavedData.get(server);

        if (savedData.getFromTheFogPresetVersion()
                >= CURRENT_PRESET_VERSION) {
            return;
        }

        Scoreboard scoreboard = server.getScoreboard();
        Objective configObjective =
                scoreboard.getObjective(CONFIG_OBJECTIVE);

        if (configObjective == null) {
            CHPCore.LOGGER.warn(
                    "Could not apply the Cave Horror Project From the Fog preset: " +
                            "scoreboard objective '{}' was not found.",
                    CONFIG_OBJECTIVE
            );
            return;
        }

        applyPreset(scoreboard, configObjective);

        savedData.setFromTheFogPresetVersion(
                CURRENT_PRESET_VERSION
        );

        CHPCore.LOGGER.info(
                "Applied Cave Horror Project's From the Fog preset version {}.",
                CURRENT_PRESET_VERSION
        );
    }

    private static void applyPreset(
            Scoreboard scoreboard,
            Objective objective
    ) {

        set(scoreboard, objective, "crashConfig", 0);
        set(scoreboard, objective, "dayDelayConfig", 3);

        set(scoreboard, objective, "ghostDoorConfig", 1);
        set(scoreboard, objective, "ghostMineConfig", 1);
        set(scoreboard, objective, "creepingConfig", 1);
        set(scoreboard, objective, "stalkingConfig", 1);

        set(scoreboard, objective, "poofingTorchesConfig", 1);
        set(scoreboard, objective, "burningBaseConfig", 0);

        set(scoreboard, objective, "spotNoiseConfig", 1);
        set(scoreboard, objective, "glowingEyesConfig", 1);

        set(scoreboard, objective, "herobrineSkinConfig", 1);
        set(scoreboard, objective, "randomizeSkinsConfig", 1);
        set(scoreboard, objective, "randomizeCustomSkinsConfig", 0);

        set(scoreboard, objective, "sightingChanceConfig", 3);
        set(scoreboard, objective, "jumpscareConfig", 1);

        set(scoreboard, objective, "creepingVanishingDelayConfig", 0);
        set(scoreboard, objective, "stalkingVanishingDelayConfig", 0);

        set(scoreboard, objective, "noSleepConfig", 1);
        set(scoreboard, objective, "dreadfulDonationConfig", 1);
        set(scoreboard, objective, "crimsonCurseConfig", 1);

        set(scoreboard, objective, "OGshrineMechanicConfig", 1);
        set(scoreboard, objective, "nightmareMechanicConfig", 1);
        set(scoreboard, objective, "windowWatcherConfig", 1);
        set(scoreboard, objective, "chilledCandlesConfig", 1);

        set(scoreboard, objective, "sinisterSignsConfig", 1);
        set(scoreboard, objective, "lurkingLanguageConfig", 1);
        set(scoreboard, objective, "sightingSenseConfig", 1);

        set(scoreboard, objective, "hittingThatGriddyConfig", 0);
        set(scoreboard, objective, "nameTagConfig", 0);
        set(scoreboard, objective, "rekindlingShrineConfig", 1);

        set(scoreboard, objective, "advancementsConfig", 0);
        set(scoreboard, objective, "fearfulFootstepsConfig", 1);
        set(scoreboard, objective, "shrineSupriseConfig", 1);

        set(scoreboard, objective, "autoConfig", 0);
    }

    private static void set(
            Scoreboard scoreboard,
            Objective objective,
            String setting,
            int value
    ) {
        scoreboard.getOrCreatePlayerScore(setting, objective)
                .setScore(value);
    }
}