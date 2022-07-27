package net.toxevo.alcd.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.toxevo.alcd.ALCD;
import net.toxevo.alcd.block.ModBlocks;
import net.toxevo.alcd.block.entity.custom.AlchemyTableBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ALCD.MOD_ID);

    public static final RegistryObject<BlockEntityType<AlchemyTableBlockEntity>> ALCHEMY_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("alchemy_table_block_entity", () ->
                    BlockEntityType.Builder.of(AlchemyTableBlockEntity::new,
                            ModBlocks.ALCHEMY_TABLE_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
