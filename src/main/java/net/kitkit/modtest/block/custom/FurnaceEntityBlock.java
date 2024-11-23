package net.kitkit.modtest.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import static net.kitkit.modtest.block.ModBlockEntities.MEGA_FURNACE;

public class FurnaceEntityBlock extends Block implements EntityBlock {

    // Constructor deferring to super.
    public FurnaceEntityBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                 BlockEntityType<T> type) {
        if (type == MEGA_FURNACE.get()) {
            return (lvl, pos, blockState, blockEntity) -> {
                if (blockEntity instanceof MegaFurnaceEntity furnaceEntity) {
                    MegaFurnaceEntity.tick(lvl, pos, blockState, furnaceEntity);
                }
            };
        }
        return null;
    }

    // Return a new instance of our block entity here.
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new MegaFurnaceEntity(pos, state);
    }
}
