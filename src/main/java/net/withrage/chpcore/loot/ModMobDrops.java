package net.withrage.chpcore.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.withrage.chpcore.CHPCore;
import net.withrage.chpcore.item.ModItems;

@Mod.EventBusSubscriber(
        modid = CHPCore.MODID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ModMobDrops {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        ResourceLocation entityId =
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType());

        if (entityId == null) {
            return;
        }

        if (entityId.equals(ResourceLocation.fromNamespaceAndPath("man", "managgresive"))) {
            int fragmentAmount = 1 + event.getEntity().getRandom().nextInt(2);

            ItemStack fragmentStack = new ItemStack(
                    ModItems.FOG_FRAGMENT.get(),
                    fragmentAmount
            );

            ItemEntity drop = new ItemEntity(
                    event.getEntity().level(),
                    event.getEntity().getX(),
                    event.getEntity().getY(),
                    event.getEntity().getZ(),
                    fragmentStack
            );

            event.getDrops().add(drop);
        }

        if (entityId.equals(ResourceLocation.fromNamespaceAndPath("cave_dweller", "cave_dweller"))) {
            int hideAmount = 1 + event.getEntity().getRandom().nextInt(2);

            ItemStack hideStack = new ItemStack(
                    ModItems.TORN_HIDE.get(),
                    hideAmount
            );

            ItemEntity hideDrop = new ItemEntity(
                    event.getEntity().level(),
                    event.getEntity().getX(),
                    event.getEntity().getY(),
                    event.getEntity().getZ(),
                    hideStack
            );

            event.getDrops().add(hideDrop);

            if (event.getEntity().getRandom().nextFloat() < 0.25F) {
                ItemStack clawStack = new ItemStack(ModItems.DWELLER_CLAW.get());

                ItemEntity clawDrop = new ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        clawStack
                );

                event.getDrops().add(clawDrop);
            }
        }
    }
}
