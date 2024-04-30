package net.joy.minenitormod.datagen.lang;

import net.joy.minenitormod.MinenitorMod;
import net.joy.minenitormod.block.ModBlocks;
import net.joy.minenitormod.item.ModItems;
import net.joy.minenitormod.utils.TextHelper;
import net.joy.minenitormod.villager.ModVillagers;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.w3c.dom.Text;

import static net.joy.minenitormod.ModCreativeModTabs.MINENITOR_TAB;

public class ModLangProviderEN_US extends ModCustomLanguageProvider {
    public ModLangProviderEN_US(PackOutput output) {
        super(output,"en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.SAPPHIRE.get(), "Sapphire");
        add(ModItems.RAW_SAPPHIRE.get(), "Raw Sapphire");
        add(ModBlocks.SAPPHIRE_BLOCK.get(), "Sapphire Staff");
        add(ModBlocks.RAW_SAPPHIRE_BLOCK.get(), "Raw Sapphire");

        add(ModBlocks.SAPPHIRE_ORE.get(), "Sapphire Ore");
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), "Deepslate Sapphire Ore");
        add(ModBlocks.NETHER_SAPPHIRE_ORE.get(), "Nether Sapphire Ore");
        add(ModBlocks.END_STONE_SAPPHIRE_ORE.get(), "Endstone Sapphire Ore");

        add(ModItems.METAL_DETECTOR.get(), "Metal Detector");
        add(ModBlocks.SOUND_BLOCK.get(), "Sound Block");

        add(ModItems.STRAWBERRY.get(), "Strawberry");
        add(ModItems.SAPPHIRE_STAFF.get(), "Sapphire Staff");
        add(ModItems.PINE_CONE.get(), "Pine Cone");

        add(ModBlocks.SAPPHIRE_STAIRS.get(), "Sapphire Stairs");
        add(ModBlocks.SAPPHIRE_SLAB.get(), "Sapphire Slab");
        add(ModBlocks.SAPPHIRE_BUTTON.get(), "Sapphire Button");
        add(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), "Sapphire Pressure Plate");
        add(ModBlocks.SAPPHIRE_FENCE.get(), "Sapphire Fence");
        add(ModBlocks.SAPPHIRE_FENCE_GATE.get(), "Sapphire Fence Gate");
        add(ModBlocks.SAPPHIRE_WALL.get(), "Sapphire Wall");
        add(ModBlocks.SAPPHIRE_DOOR.get(), "Sapphire Door");
        add(ModBlocks.SAPPHIRE_TRAP_DOOR.get(), "Sapphire Trapdoor");

        add(ModItems.SAPPHIRE_SWORD.get(), "Sapphire Sword");
        add(ModItems.SAPPHIRE_PICKAXE.get(), "Sapphire Pickaxe");
        add(ModItems.SAPPHIRE_AXE.get(), "Sapphire Axe");
        add(ModItems.SAPPHIRE_HOE.get(), "Sapphire Hoe");
        add(ModItems.SAPPHIRE_SHOVEL.get(), "Sapphire Shovel");

        add(ModItems.SAPPHIRE_HELMET.get(), "Sapphire Helmet");
        add(ModItems.SAPPHIRE_CHESTPLATE.get(), "Sapphire Chestplate");
        add(ModItems.SAPPHIRE_LEGGINGS.get(), "Sapphire Leggings");
        add(ModItems.SAPPHIRE_BOOTS.get(), "Sapphire Boots");

        add(ModItems.STRAWBERRY_SEEDS.get(), "Strawberry Seeds");
        add(ModItems.CORN_SEEDS.get(), "Corn Seeds");
        add(ModItems.CORN.get(), "Corn");
        add(ModItems.RHINO_SPAWN_EGG.get(), "Rhino Spawn Egg");

        add(ModBlocks.CAT_MINT.get(), "Cat Mint");

        add(ModVillagers.SOUND_MASTER, "Sound Master");
        add(MINENITOR_TAB.get(), "Minenitor Inventory");

        add(ModItems.BAR_BRAWL_MUSIC_DISC.get(), "Bar Brawl Music Disc");
        add(ModItems.BAR_BRAWL_MUSIC_DISC.get().getDescriptionId() + ".desc", "Bryan Tech - Bar Brawl (CC0)");
        add(TextHelper.tooltipKey(ModBlocks.SOUND_BLOCK), "Makes sound");
        add(TextHelper.tooltipKey(ModItems.METAL_DETECTOR), "Find ores underground");
    }
}
