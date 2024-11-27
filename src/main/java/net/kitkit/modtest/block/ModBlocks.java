package net.kitkit.modtest.block;

import net.kitkit.modtest.ModTest;
import net.kitkit.modtest.block.custom.FurnaceEntityBlock;
import net.kitkit.modtest.block.custom.MagicBlock;
import net.kitkit.modtest.block.custom.MegaTntEntityBlock;
import net.kitkit.modtest.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ModTest.MOD_ID);

    public static final DeferredBlock<Block> MEGADIAMOND_BLOCK = registerBlock("megadiamond_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> MEGADIAMOND_ORE = registerBlock("megadiamond_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> KEK = registerBlock("kek",
            () -> new DropExperienceBlock(UniformInt.of(2,10),BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<FurnaceEntityBlock> FURNACE_ENTITY = registerBlock("furnace_entity_block",
            () -> new FurnaceEntityBlock(BlockBehaviour.Properties.of().strength(1.0F)));

    public static final DeferredBlock<MegaTntEntityBlock> MEGA_TNT = registerBlock("mega_tnt_block",
            () -> new MegaTntEntityBlock(BlockBehaviour.Properties.of().strength(1.0F)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
