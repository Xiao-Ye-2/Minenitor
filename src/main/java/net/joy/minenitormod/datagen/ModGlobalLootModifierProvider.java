package net.joy.minenitormod.datagen;

import net.joy.minenitormod.MinenitorMod;
import net.joy.minenitormod.item.ModItems;
import net.joy.minenitormod.loot.AddItemModifier;
import net.joy.minenitormod.loot.AddArchaeologyItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, MinenitorMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("pine_cone_from_grass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()
        }, ModItems.PINE_CONE.get()));

        add("pine_cone_from_creeper", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()
        }, ModItems.PINE_CONE.get()));

        add("metal_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build()
        }, ModItems.METAL_DETECTOR.get()));

        add("metal_detector_from_suspicious_sand", new AddArchaeologyItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()
        }, ModItems.METAL_DETECTOR.get(), 0.8f));
    }
}
