package net.withrage.chpcore.client.renderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.withrage.chpcore.CHPCore;
import net.withrage.chpcore.client.model.DwellerBootsModel;

@Mod.EventBusSubscriber(
        modid = CHPCore.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class DwellerBootsRenderer {

    @SubscribeEvent
    public static void registerLayerDefinitions(
            EntityRenderersEvent.RegisterLayerDefinitions event
    ) {
        event.registerLayerDefinition(
                DwellerBootsModel.LAYER_LOCATION,
                DwellerBootsModel::createBodyLayer
        );
    }
}
