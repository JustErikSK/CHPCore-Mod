package net.withrage.chpcore.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.withrage.chpcore.CHPCore.MODID;

public class ModLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>>
            LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(
                    ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    MODID
            );

    public static final RegistryObject<Codec<AddItemModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register(
                    "add_item",
                    AddItemModifier.CODEC
            );
}
