package net.withrage.chpcore;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.withrage.chpcore.item.ModCreativeModeTabs;
import net.withrage.chpcore.item.ModItems;
import net.withrage.chpcore.messages.custom.PlayerJoinHandler;
import org.slf4j.Logger;

@Mod(CHPcore.MODID)
public class CHPcore
{
    public static final String MODID = "chpcore";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CHPcore(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(PlayerJoinHandler.class);
    }
}
