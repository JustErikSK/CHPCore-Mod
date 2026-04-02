package net.withrage.chpcore.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.chpcore.CHPcore;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CHPcore.MODID);

    public static final RegistryObject<CreativeModeTab> CHPCORE_CREATIVE_TAB = CREATIVE_MODE_TABS.register("chpcore_creative_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LEGENDARY_CHEST.get()))
                    .title(Component.translatable("creativetab.chpcore.creative_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BASIC_CHEST.get());
                        output.accept(ModItems.ADVENTURE_CHEST.get());
                        output.accept(ModItems.UTILITY_CHEST.get());
                        output.accept(ModItems.FOOD_CHEST.get());
                        output.accept(ModItems.CHITIN_CHEST.get());
                        output.accept(ModItems.BETTER_CHEST.get());
                        output.accept(ModItems.BEST_CHEST.get());
                        output.accept(ModItems.LEGENDARY_CHEST.get());
                        output.accept(ModItems.LAST_STAND_CHEST.get());
                        output.accept(ModItems.ENTROPY_CHEST.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
