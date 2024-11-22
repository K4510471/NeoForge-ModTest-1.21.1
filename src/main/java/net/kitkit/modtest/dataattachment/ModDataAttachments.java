package net.kitkit.modtest.dataattachment;

import com.mojang.serialization.Codec;
import net.kitkit.modtest.ModTest;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ModTest.MOD_ID);

    public static final Supplier<AttachmentType<Integer>> MANA = ATTACHMENT_TYPES.register("mana",
            () -> AttachmentType.<Integer>builder(() -> 0).serialize(Codec.INT).build());

    public static final Supplier<AttachmentType<Integer>> ENERGY = ATTACHMENT_TYPES.register("energy",
            () -> AttachmentType.<Integer>builder(() -> 0).serialize(Codec.INT).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
