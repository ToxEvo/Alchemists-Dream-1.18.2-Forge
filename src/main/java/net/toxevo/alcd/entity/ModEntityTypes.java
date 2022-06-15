package net.toxevo.alcd.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.toxevo.alcd.ALCD;
import net.toxevo.alcd.entity.custom.RaccoonEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, ALCD.MOD_ID);

    public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON =
            ENTITY_TYPES.register("raccoon",
                    () -> EntityType.Builder.of(RaccoonEntity::new, MobCategory.CREATURE)
                            .sized(0.8f, 0.6f)
                            .build(new ResourceLocation(ALCD.MOD_ID, "raccoon").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
