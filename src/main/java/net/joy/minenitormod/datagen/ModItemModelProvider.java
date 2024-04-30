package net.joy.minenitormod.datagen;

import net.joy.minenitormod.MinenitorMod;
import net.joy.minenitormod.block.ModBlocks;
import net.joy.minenitormod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.LinkedHashMap;


public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MinenitorMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleGeneratedItem(ModItems.SAPPHIRE);
        simpleGeneratedItem(ModItems.RAW_SAPPHIRE);

        simpleHandheldItem(ModItems.METAL_DETECTOR);
        simpleGeneratedItem(ModItems.PINE_CONE);
        simpleGeneratedItem(ModItems.STRAWBERRY);
        simpleGeneratedItem(ModItems.STRAWBERRY_SEEDS);

        simpleGeneratedItem(ModItems.CORN_SEEDS);
        simpleGeneratedItem(ModItems.CORN);

        simpleGeneratedItem(ModBlocks.SAPPHIRE_DOOR);

        fenceItem(ModBlocks.SAPPHIRE_FENCE, ModBlocks.SAPPHIRE_BLOCK);
        buttonItem(ModBlocks.SAPPHIRE_BUTTON, ModBlocks.SAPPHIRE_BLOCK);
        wallItem(ModBlocks.SAPPHIRE_WALL, ModBlocks.SAPPHIRE_BLOCK);

        simplestBlock(ModBlocks.SAPPHIRE_STAIRS);
        simplestBlock(ModBlocks.SAPPHIRE_SLAB);
        simplestBlock(ModBlocks.SAPPHIRE_FENCE_GATE);
        simplestBlock(ModBlocks.SAPPHIRE_PRESSURE_PLATE);

        simplestBlock(ModBlocks.SAPPHIRE_TRAP_DOOR, "_bottom");

        simpleHandheldItem(ModItems.SAPPHIRE_SWORD);
        simpleHandheldItem(ModItems.SAPPHIRE_PICKAXE);
        simpleHandheldItem(ModItems.SAPPHIRE_SHOVEL);
        simpleHandheldItem(ModItems.SAPPHIRE_HOE);
        simpleHandheldItem(ModItems.SAPPHIRE_AXE);

        trimmedArmorItem(ModItems.SAPPHIRE_HELMET);
        trimmedArmorItem(ModItems.SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(ModItems.SAPPHIRE_LEGGINGS);
        trimmedArmorItem(ModItems.SAPPHIRE_BOOTS);

        withExistingParent(ModItems.RHINO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleGeneratedBlock(ModBlocks.CAT_MINT);
        simpleGeneratedItem(ModItems.BAR_BRAWL_MUSIC_DISC);
    }


    private <T extends FeatureElement> void simpleGeneratedItem(RegistryObject<T> item) {
        simple(item, "item/generated", "item");
    }

    private <T extends FeatureElement> void simpleHandheldItem(RegistryObject<T> item) {
        simple(item, "item/handheld", "item");
    }

    private <T extends FeatureElement> void simpleGeneratedBlock(RegistryObject<T> item) {
        simple(item, "item/generated", "block");
    }

    private <T extends FeatureElement> void simple(RegistryObject<T> item, String type, String path) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation(type)).texture("layer0",
                new ResourceLocation(MinenitorMod.MOD_ID, path + "/" + item.getId().getPath()));
    }

    private void simplestBlock(RegistryObject<Block> item) {
        simplestBlock(item, "");
    }

    private void simplestBlock(RegistryObject<Block> item, String suffix) {
        withExistingParent(item.getId().getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(item.get()).getPath() + suffix));
    }

    private void parentBlockItem(RegistryObject<Block> child, RegistryObject<Block> parent, String parentType, String texture) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(child.get()).getPath(), parentType)
                .texture(texture, new ResourceLocation(MinenitorMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(parent.get()).getPath()));

    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        parentBlockItem(block, baseBlock, "block/fence_inventory", "texture");}

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        parentBlockItem(block, baseBlock, "block/button_inventory", "texture");
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        parentBlockItem(block, baseBlock, "block/wall_inventory", "wall");
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = MinenitorMod.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

}
