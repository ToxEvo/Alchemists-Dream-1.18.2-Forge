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
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();

            entity.teleportTo(x, y, z);
            entity.setDeltaMovement(0, 0, 0);
        super.applyEffectTick(entity, amplifier);
        spawnFreezeParticle(entity.level, entity.getX(), entity.getEyeY(), entity.getZ());
        }
    }

    public static void spawnFreezeParticle(LevelAccessor world, double x, double y, double z) {
        for (int i = 0; i < 360; i++) {
            if (i % 80 == 0) {
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ModParticles.FREEZE_PARTICLES.get(),
                            x + 0,
                            y + 0.5,
                            z + 0,
                            0,
                            0,
                            0,
                            0,
                            0.15d);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}

