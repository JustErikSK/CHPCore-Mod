package net.withrage.chpcore.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DwellerInstinctEffect extends MobEffect {

    public DwellerInstinctEffect() {
        super(
                MobEffectCategory.BENEFICIAL,
                0x3A332A
        );

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "b55c1d78-2d5e-4f45-a8b1-8f5f02c87a15",
                0.20D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
}
