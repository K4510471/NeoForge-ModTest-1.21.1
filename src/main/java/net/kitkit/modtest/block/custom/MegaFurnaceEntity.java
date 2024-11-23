package net.kitkit.modtest.block.custom;

import net.kitkit.modtest.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MegaFurnaceEntity extends BlockEntity{

    private int value = 0;

    public MegaFurnaceEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MEGA_FURNACE.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MegaFurnaceEntity blockEntity) {
        if (!level.isClientSide) {
            blockEntity.value++;
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.players().forEach(serverPlayer -> serverPlayer.sendSystemMessage(Component.literal("tick! " + blockEntity.value)));
                if (blockEntity.value > 2) {
                    blockEntity.value=0;
                    Entity tntEntity = EntityType.TNT.spawn(serverLevel, pos, MobSpawnType.COMMAND);
                    if (tntEntity instanceof PrimedTnt tnt) {
                        tnt.setDeltaMovement(0,-100,0);
                        tnt.setFuse(4);
                    }
                    //serverLevel.setBlockEntity(BlockEntityType.ENDER_CHEST.getBlockEntity(serverLevel, pos));
                }
            }
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.value = tag.getInt("value");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("value", this.value);
    }
}
