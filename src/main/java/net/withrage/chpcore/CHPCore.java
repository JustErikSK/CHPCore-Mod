package net.withrage.chpcore;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.withrage.chpcore.config.CHPCoreServerConfig;
import net.withrage.chpcore.effect.ModEffects;
import net.withrage.chpcore.item.ModCreativeModeTabs;
import net.withrage.chpcore.item.ModItems;
import net.withrage.chpcore.loot.ModLootModifiers;
import net.withrage.chpcore.messages.custom.PlayerJoinHandler;
import org.slf4j.Logger;

@Mod(CHPCore.MODID)
public class CHPCore
{
    public static final String MODID = "chpcore";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CHPCore(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(PlayerJoinHandler.class);
        ModEffects.EFFECTS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        ModLoadingContext.get().registerConfig(
                ModConfig.Type.SERVER,
                CHPCoreServerConfig.SPEC,
                "chp_core-server.toml"
        );
    }
}
