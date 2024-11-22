package net.kitkit.modtest.block.custom;

import net.kitkit.modtest.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MegaFurnaceEntity extends BlockEntity{

    public MegaFurnaceEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MEGA_FURNACE.get(), pos, blockState);
    }

}
