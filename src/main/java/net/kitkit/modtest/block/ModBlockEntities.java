package net.kitkit.modtest.block;

import net.kitkit.modtest.ModTest;
import net.kitkit.modtest.block.custom.MegaFurnaceEntity;
import net.kitkit.modtest.block.custom.MegaTntEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ModTest.MOD_ID);

    public static final Supplier<BlockEntityType<MegaFurnaceEntity>> MEGA_FURNACE =
            BLOCK_ENTITIES.register("mega_furnace",
                    () -> BlockEntityType.Builder.of(
                        MegaFurnaceEntity::new,
                            ModBlocks.FURNACE_ENTITY.get()).build(null)
                    );

    public static final Supplier<BlockEntityType<MegaTntEntity>> MEGA_TNT =
            BLOCK_ENTITIES.register("mega_tnt",
                    () -> BlockEntityType.Builder.of(
                        MegaTntEntity::new,
                            ModBlocks.MEGA_TNT.get()).build(null)
                    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
