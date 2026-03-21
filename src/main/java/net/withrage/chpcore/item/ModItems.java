package net.withrage.chpcore.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.chpcore.CHPcore;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CHPcore.MODID);

    public static final RegistryObject<Item> BASIC_CHEST = ITEMS.register("basic_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BETTER_CHEST = ITEMS.register("better_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BEST_CHEST = ITEMS.register("best_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEGENDARY_CHEST = ITEMS.register("legendary_chest", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
