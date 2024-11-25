package net.kitkit.modtest.block.custom;

import net.kitkit.modtest.block.ModBlocks;
import net.kitkit.modtest.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                        @NotNull Player player, @NotNull BlockHitResult hitResult) {

        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        if(entity instanceof ItemEntity itemEntity) {
            if((itemEntity.getItem().getItem() == ModItems.MEGADIAMOND.get()) && (itemEntity.getAge() >= 40)) {
                itemEntity.setItem(new ItemStack(ModBlocks.MEGADIAMOND_BLOCK.get(), itemEntity.getItem().getCount()));
            }
        }

        super.stepOn(level, pos, state, entity);
    }
}
