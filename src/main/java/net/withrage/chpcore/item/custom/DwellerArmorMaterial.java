package net.withrage.chpcore.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;

public class DwellerArmorMaterial implements ArmorMaterial {

    private static final EnumMap<ArmorItem.Type, Integer> DURABILITY = new EnumMap<>(ArmorItem.Type.class);
    private static final EnumMap<ArmorItem.Type, Integer> PROTECTION = new EnumMap<>(ArmorItem.Type.class);

    static {
        DURABILITY.put(ArmorItem.Type.BOOTS, 13);
        PROTECTION.put(ArmorItem.Type.BOOTS, 3);
    }

    private static final int BASE_DURABILITY = 37;

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return DURABILITY.get(type) * BASE_DURABILITY;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return PROTECTION.get(type);
    }

    @Override
    public int getEnchantmentValue() {
        return 18;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return "dweller";
    }

    @Override
    public float getToughness() {
        return 2.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
