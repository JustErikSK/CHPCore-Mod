package net.withrage.chpcore.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.chpcore.CHPCore;


public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(
                    ForgeRegistries.MOB_EFFECTS,
                    CHPCore.MODID
            );

    public static final RegistryObject<MobEffect> DWELLER_INSTINCT =
            EFFECTS.register(
                    "dweller_instinct",
                    DwellerInstinctEffect::new
            );
}
