package net.toxevo.alcd.effect.complex;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class VolatileEffect extends MobEffect {
    public VolatileEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(MobEffectCategory.HARMFUL, 16744576);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        VolatileEffectAction.execute(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 30 == 0;
    }
}
