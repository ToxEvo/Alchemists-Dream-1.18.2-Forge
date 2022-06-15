package net.toxevo.alcd.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.toxevo.alcd.ALCD;
import net.toxevo.alcd.effect.custom.blissful_frost_effect.BlissfulFrostEffect;
import net.toxevo.alcd.effect.custom.volatile_effect.VolatileEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ALCD.MOD_ID);

    public static final RegistryObject<MobEffect> FREEZE = MOB_EFFECTS.register("freeze",
            () -> new FreezeEffect(MobEffectCategory.HARMFUL, 3124687));

    public static final RegistryObject<MobEffect> VOLATILE = MOB_EFFECTS.register("volatile",
            () -> new VolatileEffect(MobEffectCategory.HARMFUL, 16744576));

    public static final RegistryObject<MobEffect> BLISSFUL_FROST = MOB_EFFECTS.register("blissful_frost",
            () -> new BlissfulFrostEffect(MobEffectCategory.BENEFICIAL, 5335039));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
