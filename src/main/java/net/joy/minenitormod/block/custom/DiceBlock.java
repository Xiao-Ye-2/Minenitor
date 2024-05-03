package net.joy.minenitormod.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class DiceBlock extends Block {
    public static DirectionProperty FACING = DirectionProperty.create("number",
            Direction.UP, Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);


    public DiceBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getClickedFace());
    }

    public BlockState getRandomBlockState() {
        return this.defaultBlockState().setValue(FACING, getRandomDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private Direction getRandomDirection() {
        Direction[] directions = new Direction[] {
                Direction.UP, Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST
        };
        return directions[RandomSource.create().nextIntBetweenInclusive(0, (directions).length - 1)];
    }
}
