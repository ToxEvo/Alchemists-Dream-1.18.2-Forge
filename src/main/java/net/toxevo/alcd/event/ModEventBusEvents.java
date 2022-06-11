package net.toxevo.alcd.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.toxevo.alcd.ALCD;
import net.toxevo.alcd.particle.ModParticles;
import net.toxevo.alcd.particle.custom.VolatileParticles;

@Mod.EventBusSubscriber(modid = ALCD.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.VOLATILE_PARTICLES.get(),
                VolatileParticles.Provider::new);
    }
}