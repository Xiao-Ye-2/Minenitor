package net.joy.minenitormod.entity.custom;

import net.joy.minenitormod.block.ModBlocks;
import net.joy.minenitormod.block.custom.DiceBlock;
import net.joy.minenitormod.entity.ModEntities;
import net.joy.minenitormod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class DiceProjectileEntity extends ThrowableItemProjectile {
    public DiceProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DiceProjectileEntity(Level pLevel) {
        super(ModEntities.DICE_PROJECTILE.get(), pLevel);
    }

    public DiceProjectileEntity(Level pLevel, LivingEntity pOwner) {
        super(ModEntities.DICE_PROJECTILE.get(), pOwner, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DICE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().setBlock(blockPosition(), ((DiceBlock) ModBlocks.DICE_BLOCK.get()).getRandomBlockState(),3);
        }

        this.discard();
        super.onHitBlock(pResult);
    }


}
