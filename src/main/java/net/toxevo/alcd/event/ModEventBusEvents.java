package net.toxevo.alcd.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.toxevo.alcd.ALCD;
import net.toxevo.alcd.entity.ModEntityTypes;
import net.toxevo.alcd.entity.custom.FrogEntity;
import net.toxevo.alcd.particle.ModParticles;
import net.toxevo.alcd.particle.custom.FreezeParticles;
import net.toxevo.alcd.particle.custom.VolatileParticles;
import net.toxevo.alcd.recipe.AlchemyTableRecipe;

@Mod.EventBusSubscriber(modid = ALCD.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.VOLATILE_PARTICLES.get(),
                VolatileParticles.Provider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.FREEZE_PARTICLES.get(),
                FreezeParticles.Provider::new);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.FROG.get(), FrogEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, AlchemyTableRecipe.Type.ID, AlchemyTableRecipe.Type.INSTANCE);
    }
}
