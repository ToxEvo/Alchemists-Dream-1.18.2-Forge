package net.toxevo.alcd.effect.custom.volatile_effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.toxevo.alcd.particle.ModParticles;

public class VolatileEffect extends MobEffect {
    public VolatileEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(MobEffectCategory.HARMFUL, 16744576);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        VolatileEffectAction.action(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity, amplifier);
        spawnVolatileParticle(entity.level, entity.getX(), entity.getY(), entity.getZ());
    }

    public static void spawnVolatileParticle(LevelAccessor world, double x, double y, double z) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
        if (world instanceof ServerLevel _level)
            _level.sendParticles(ModParticles.VOLATILE_PARTICLES.get(),
                    x + 0,
                    y + 1,
                    z + 0,
                    0,
                    Math.cos(i),
                    1,
                    Math.sin(i),
                    0.15d);
            }
        }
        if (world instanceof ServerLevel level)
            level.sendParticles(ModParticles.VOLATILE_PARTICLES.get(),
                    x,
                    y + 3,
                    z,
                    15,
                    0,
                    0,
                    0,
                    0);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int k = 50 >> amplifier;
        if (k > 0) {
            return duration % k == 0;
        } else {
            return true;
        }
    }
}
