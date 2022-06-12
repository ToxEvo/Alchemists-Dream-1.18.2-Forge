package net.toxevo.alcd.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.toxevo.alcd.particle.ModParticles;

public class FreezeEffect extends MobEffect {

    public FreezeEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(MobEffectCategory.HARMFUL, 3124687);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level.isClientSide()) {
            Double x = entity.getX();
            Double y = entity.getY();
            Double z = entity.getZ();

            entity.teleportTo(x, y, z);
            entity.setDeltaMovement(0, 0, 0);
        super.applyEffectTick(entity, amplifier);
        spawnFreezeParticle(entity.level, entity.getX(), entity.getY(), entity.getZ());
        }
    }

    public static void spawnFreezeParticle(LevelAccessor world, double x, double y, double z) {
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0) {
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ModParticles.FREEZE_PARTICLES.get(),
                            x + 0,
                            y + 2,
                            z + 0,
                            1,
                            Math.cos(i),
                            0,
                            Math.sin(i),
                            -0.15d);
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ModParticles.FREEZE_PARTICLES.get(),
                            x + 0,
                            y + 0,
                            z + 0,
                            1,
                            Math.cos(i),
                            0,
                            Math.sin(i),
                            0.15d);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}

