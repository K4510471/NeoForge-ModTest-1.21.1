package net.kitkit.modtest.container;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MyContainer implements Container {

    private final NonNullList<ItemStack> items = NonNullList.withSize(
            27,
            ItemStack.EMPTY
    );

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        return this.items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount) {
        ItemStack stack = ContainerHelper.removeItem(this.items, slot, amount);
        this.setChanged();
        return stack;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        ItemStack stack = ContainerHelper.takeItem(this.items, slot);
        this.setChanged();
        return stack;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        //stack.limitSize(this.getMaxStackSize(stack));
        this.items.set(slot, stack);
        this.setChanged();
    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        items.clear();
        this.setChanged();
    }
}
