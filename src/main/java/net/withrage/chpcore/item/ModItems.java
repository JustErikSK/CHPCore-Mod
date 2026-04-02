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
    public static final RegistryObject<Item> ADVENTURE_CHEST = ITEMS.register("adventure_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHITIN_CHEST = ITEMS.register("chitin_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_CHEST = ITEMS.register("food_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UTILITY_CHEST = ITEMS.register("utility_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAST_STAND_CHEST = ITEMS.register("last_stand_chest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENTROPY_CHEST = ITEMS.register("entropy_chest", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
