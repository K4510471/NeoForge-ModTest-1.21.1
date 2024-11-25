package net.kitkit.modtest.block.custom;

import net.kitkit.modtest.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MegaFurnaceEntity extends BaseContainerBlockEntity {

    public static final int SIZE = 1;
    private int value = 0;

    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    public MegaFurnaceEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MEGA_FURNACE.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MegaFurnaceEntity blockEntity) {
        if (!level.isClientSide) {
            blockEntity.value++;
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.value = tag.getInt("value");
        ContainerHelper.loadAllItems(tag, this.items, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("value", this.value);
        ContainerHelper.saveAllItems(tag, this.items, registries);
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.modtest.megafurnaceentity");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return null;
    }

    // Create an update tag here. For block entities with only a few fields, this can just call #saveAdditional.
    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    // Handle a received update tag here. The default implementation calls #loadAdditional here,
    // so you do not need to override this method if you don't plan to do anything beyond that.
    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
    }

    // Return our packet here. This method returning a non-null result tells the game to use this packet for syncing.
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // The packet uses the CompoundTag returned by #getUpdateTag. An alternative overload of #create exists
        // that allows you to specify a custom update tag, including the ability to omit data the client might not need.
        return ClientboundBlockEntityDataPacket.create(this);
    }

}
