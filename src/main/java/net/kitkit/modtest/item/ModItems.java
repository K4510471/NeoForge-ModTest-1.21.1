package net.kitkit.modtest.item;

import net.kitkit.modtest.ModTest;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModTest.MOD_ID);

    public static final DeferredItem<Item> MEGADIAMOND = ITEMS.register("megadiamond",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
