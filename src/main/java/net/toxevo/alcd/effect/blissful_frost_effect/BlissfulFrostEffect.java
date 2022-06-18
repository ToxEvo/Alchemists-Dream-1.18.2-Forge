package net.toxevo.alcd.effect.blissful_frost_effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.toxevo.alcd.effect.ModEffects;

import java.util.Comparator;
import java.util.List;

public class BlissfulFrostEffect extends MobEffect {
    public BlissfulFrostEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(MobEffectCategory.BENEFICIAL, 5335039);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        blissfulFrostOnTick(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity, amplifier);
    }

    public static void blissfulFrostOnTick(LevelAccessor world, double x, double y, double z, Entity entity, int amp) {
        if (entity == null)
            return;
        {
            final Vec3 center = new Vec3(x, y, z);
            List<Entity> entitiesInRadius = world.getEntitiesOfClass(Entity.class, new AABB(center,
                            center).inflate(6 / 2d), e -> true).stream()
                    .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
            for (Entity entityInRadius : entitiesInRadius)
                if (!(entityInRadius instanceof Player)) {
                    if (entityInRadius instanceof LivingEntity lEntity)
                        lEntity.addEffect(new MobEffectInstance(ModEffects.FREEZE.get(),
                                20,
                                amp,
                                false,
                                false));

                }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
