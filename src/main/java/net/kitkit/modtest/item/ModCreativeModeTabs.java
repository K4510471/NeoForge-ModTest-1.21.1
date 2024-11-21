package net.kitkit.modtest.item;

import net.kitkit.modtest.ModTest;
import net.kitkit.modtest.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModTest.MOD_ID);

    public static final Supplier<CreativeModeTab> MODTEST_ITEMS_TAB = CREATIVE_MODE_TAB.register("modtest_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MEGADIAMOND.get()))
                    .title(Component.translatable("creativetab.modtest.items"))
                    .displayItems((itemDisplayParameters, output) -> {            //.displayItems((parameters, output) -> {
                        output.accept(ModItems.MEGADIAMOND);
                        output.accept(ModItems.CHISEL);
                    }).build());

    public static final Supplier<CreativeModeTab> MODTEST_BLOCK_TAB = CREATIVE_MODE_TAB.register("modtest_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.KEK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ModTest.MOD_ID, "modtest_items_tab"))
                    .title(Component.translatable("creativetab.modtest.blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.MEGADIAMOND_BLOCK);
                        output.accept(ModBlocks.MEGADIAMOND_ORE);
                        output.accept(ModBlocks.KEK);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
