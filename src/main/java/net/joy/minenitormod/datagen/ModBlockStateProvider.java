package net.joy.minenitormod.datagen;

import net.joy.minenitormod.MinenitorMod;
import net.joy.minenitormod.block.ModBlocks;
import net.joy.minenitormod.block.custom.CustomCropBlock;
import net.joy.minenitormod.block.custom.StrawberryCropBlock;
import net.joy.minenitormod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MinenitorMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);

        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.END_STONE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock((StairBlock) ModBlocks.SAPPHIRE_STAIRS.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        slabBlock((SlabBlock) ModBlocks.SAPPHIRE_SLAB.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        buttonBlock((ButtonBlock) ModBlocks.SAPPHIRE_BUTTON.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        fenceBlock((FenceBlock) ModBlocks.SAPPHIRE_FENCE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.SAPPHIRE_FENCE_GATE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        wallBlock((WallBlock) ModBlocks.SAPPHIRE_WALL.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        doorBlockWithRenderType((DoorBlock) ModBlocks.SAPPHIRE_DOOR.get(),
                modLoc("block/sapphire_door_bottom"),
                modLoc("block/sapphire_door_top"),
                "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.SAPPHIRE_TRAP_DOOR.get(),
                modLoc("block/sapphire_trapdoor"),
                true,
                "cutout");

        makeCustomCrop((CustomCropBlock) ModBlocks.STRAWBERRY_CROP.get(), "strawberry_stage", "strawberry_stage");
        makeCustomCrop((CustomCropBlock) ModBlocks.CORN_CROP.get(), "corn_stage", "corn_stage");
    }

    private void makeCustomCrop(CustomCropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = (state) -> customCropStates(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[]  customCropStates(BlockState state, CustomCropBlock block, String modelName, String textureName) {
        ConfiguredModel[] model = new ConfiguredModel[1];
        model[0] = new ConfiguredModel(models().crop(modelName + state.getValue(block.getAgeProperty()),
                new ResourceLocation(MinenitorMod.MOD_ID, "block/" + textureName + state.getValue(block.getAgeProperty()))).renderType("cutout"));
        return model;
    }
    private void blockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
