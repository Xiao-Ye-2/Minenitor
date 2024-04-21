package net.joy.minenitormod.item;

import net.joy.minenitormod.MinenitorMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    SAPPHIRE("sapphire", 26, new int[]{5, 7, 5, 4}, 25, SoundEvents.ARMOR_EQUIP_GOLD,
            1f, 0f, () -> Ingredient.of(ModItems.SAPPHIRE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int enchantability;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final int[] protectionAmounts;
    private final Supplier<Ingredient> repairMaterial;

    private static final int[] durabilityAmounts = {13, 15, 16, 11};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.enchantability = enchantability;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.protectionAmounts = protectionAmounts;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return durabilityAmounts[pType.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

    @Override
    public String getName() {
        return MinenitorMod.MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
