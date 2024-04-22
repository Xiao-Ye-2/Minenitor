package net.joy.minenitormod.item.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.joy.minenitormod.item.ModArmorMaterials;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, ImmutableList<MobEffectInstance>> MATERIAL_MOB_EFFECT_INSTANCE_MAP =
            new ImmutableMap.Builder<ArmorMaterial, ImmutableList<MobEffectInstance>>()
                    .put(ModArmorMaterials.SAPPHIRE, ImmutableList.of(
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 1,
                                    false, false, true),
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1,
                                    false, false, true)
                    ))
                    .build();

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide()) {
            if(hasFullSuitOfArmorOn(player)){
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, ImmutableList<MobEffectInstance>> entry : MATERIAL_MOB_EFFECT_INSTANCE_MAP.entrySet()) {
            ArmorMaterial material = entry.getKey();
            ImmutableList<MobEffectInstance> effectInstances = entry.getValue();

            if (hasCorrectArmorOn(material, player)) {
                for (MobEffectInstance effectInstance : effectInstances) {
                    addStatusEffectForMaterial(player, effectInstance);
                }
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, MobEffectInstance effectInstance) {
        boolean hasStatusEffect = player.hasEffect(effectInstance.getEffect());

        if (!hasStatusEffect) {
            player.addEffect(new MobEffectInstance(effectInstance));
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack stack : player.getInventory().armor) {
            if (!(stack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();

        return helmet.getMaterial() == material && boots.getMaterial() == material &&
                leggings.getMaterial() == material && chestplate.getMaterial() == material;
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack helmet = player.getInventory().getArmor(3);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }
}
