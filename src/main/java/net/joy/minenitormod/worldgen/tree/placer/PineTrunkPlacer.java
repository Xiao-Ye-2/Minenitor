package net.joy.minenitormod.worldgen.tree.placer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.joy.minenitormod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PineTrunkPlacer extends TrunkPlacer {
    public static final Codec<PineTrunkPlacer> CODEC = RecordCodecBuilder.create(pineTrunkPlacerInstance ->
            trunkPlacerParts(pineTrunkPlacerInstance).apply(pineTrunkPlacerInstance, PineTrunkPlacer::new));

    public PineTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.PINE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        int height = pFreeTreeHeight + pRandom.nextInt(heightRandA, heightRandA + 2) + pRandom.nextInt(heightRandB, heightRandB + 2);
        boolean branch1Appeared = false;
        for (int i = 0; i < height; ++i) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);

            if (i % 2 == 0 && i >= 3) {
                int branchLength = (int) ((height - i) / 2);
                if (branch1Appeared) {
                    continue;
                }
                if (branchLength == 2) {
                    branch1Appeared = true;
                }

                for (int j = 1; j < branchLength; ++j) {
                    pBlockSetter.accept(pPos.above(i).relative(Direction.NORTH, j), ((BlockState)
                            Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                }

                for (int j = 1; j < branchLength; ++j) {
                    pBlockSetter.accept(pPos.above(i).relative(Direction.SOUTH, j), ((BlockState)
                            Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                }

                for (int j = 1; j < branchLength; ++j) {
                    pBlockSetter.accept(pPos.above(i).relative(Direction.EAST, j), ((BlockState)
                            Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                }

                for (int j = 1; j < branchLength; ++j) {
                    pBlockSetter.accept(pPos.above(i).relative(Direction.WEST, j), ((BlockState)
                            Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(height), 0, false));
    }
}
