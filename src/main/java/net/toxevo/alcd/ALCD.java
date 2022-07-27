package net.toxevo.alcd;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.toxevo.alcd.block.ModBlocks;
import net.toxevo.alcd.block.entity.ModBlockEntities;
import net.toxevo.alcd.effect.ModEffects;
import net.toxevo.alcd.entity.ModEntityTypes;
import net.toxevo.alcd.entity.client.FrogRenderer;
import net.toxevo.alcd.item.ModItems;
import net.toxevo.alcd.particle.ModParticles;
import net.toxevo.alcd.potion.ModPotions;
import net.toxevo.alcd.screen.AlchemyTableScreen;
import net.toxevo.alcd.screen.ModMenuTypes;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ALCD.MOD_ID)
public class ALCD
{
    public static final String MOD_ID = "alcd";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ALCD()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEffects.register(eventBus);
        ModPotions.register(eventBus);

        ModParticles.register(eventBus);

        ModEntityTypes.register(eventBus);

        ModBlockEntities.register(eventBus);

        ModMenuTypes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);


        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.FROG.get(), FrogRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ALCHEMY_TABLE_BLOCK.get(), RenderType.cutout());

        MenuScreens.register(ModMenuTypes.ALCHEMY_TABLE_MENU.get(), AlchemyTableScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
