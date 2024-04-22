package net.joy.minenitormod.block.custom;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public abstract class CustomCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public CustomCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
}