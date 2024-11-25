package net.kitkit.modtest.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import static net.kitkit.modtest.block.ModBlockEntities.MEGA_FURNACE;

public class FurnaceEntityBlock extends Block implements EntityBlock {

    public FurnaceEntityBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> type) {
        if (type == MEGA_FURNACE.get()) {
            return (lvl, pos, blockState, blockEntity) -> {
                if (blockEntity instanceof MegaFurnaceEntity furnaceEntity) {
                    MegaFurnaceEntity.tick(lvl, pos, blockState, furnaceEntity);
                }
            };
        }
        return null;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new MegaFurnaceEntity(pos, state);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {

        if (level.getBlockEntity(pos) instanceof Container container) {
            if (level.getBlockEntity(pos) instanceof MegaFurnaceEntity megaFurnaceEntity) {
                if (player.getMainHandItem() != ItemStack.EMPTY) {

                    if (container.getItem(0) != ItemStack.EMPTY) {
                        ItemStack itemFrom = container.getItem(0);
                        container.setItem(0, player.getMainHandItem());
                        player.setItemInHand(InteractionHand.MAIN_HAND, itemFrom);

                    } else {
                        container.setItem(0, player.getMainHandItem());
                        player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    }

                    megaFurnaceEntity.getUpdatePacket();
                    level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);

                } else {

                    if (container.getItem(0) != ItemStack.EMPTY) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, container.getItem(0));
                        container.setItem(0, ItemStack.EMPTY);
                        megaFurnaceEntity.getUpdatePacket();
                        level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
                    }

                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
