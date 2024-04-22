package net.joy.minenitormod.item.custom;

import net.joy.minenitormod.block.ModBlocks;
import net.joy.minenitormod.item.ModItems;
import net.joy.minenitormod.utils.ModTags;
import net.joy.minenitormod.utils.TextHelper;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos pos = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean found = false;
            assert player != null;

            for (int i = 0; i <= pos.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(pos.below(i));
                if (isValuableBlock(state)) {
                    outputValuableCoordinates(pos.below(i), player, state.getBlock());
                    found = true;

                    break;
                }
            }

            if (!found) {
                player.sendSystemMessage(Component.literal("No ores found!"));
            }

        }
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable(TextHelper.tooltipKey(ModItems.METAL_DETECTOR)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
