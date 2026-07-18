package net.withrage.chpcore.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class CHPCoreServerConfig {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue APPLY_FROM_THE_FOG_PRESET;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("from_the_fog");

        APPLY_FROM_THE_FOG_PRESET = builder
                .comment(
                        "Applies Cave Horror Project's default From the Fog settings once per world.",
                        "After the preset is applied, settings changed through From the Fog will not be overwritten."
                )
                .define("apply_chp_preset", true);

        builder.pop();

        SPEC = builder.build();
    }

    private CHPCoreServerConfig() {
    }
}