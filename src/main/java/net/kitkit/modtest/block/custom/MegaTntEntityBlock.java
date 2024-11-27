package net.kitkit.modtest.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MegaTntEntityBlock extends Block implements EntityBlock {

    public MegaTntEntityBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new MegaTntEntity(pos, state);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {

            if ((level.getBlockEntity(pos) instanceof Container container) && (level.getBlockEntity(pos) instanceof MegaTntEntity megaTntEntity) && (player.getUsedItemHand() == InteractionHand.MAIN_HAND)) {
                if (player.getMainHandItem().is(Items.DIAMOND)) {
                    if (level.isClientSide) {
                        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, container.getItem(0).getCount() + 1F, (float) (container.getItem(0).getCount() * 0.1) + 1F);
                    } else {
                    ItemStack itemFrom = player.getItemInHand(InteractionHand.MAIN_HAND).copy();
                    player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                    itemFrom.setCount(container.getItem(0).getCount() + 1);
                    container.setItem(0, itemFrom);
                    }
                }

                megaTntEntity.getUpdatePacket();
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
            }

        if (level instanceof ServerLevel serverLevel) {
            if (level.getBlockEntity(pos) instanceof Container container) {
                if (container.getItem(0).getCount() >= 4) {
                    serverLevel.removeBlockEntity(pos);
                    serverLevel.explode(null, pos.getX(), pos.getY(), pos.getZ(), 10.0F, false, Level.ExplosionInteraction.TNT);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}
