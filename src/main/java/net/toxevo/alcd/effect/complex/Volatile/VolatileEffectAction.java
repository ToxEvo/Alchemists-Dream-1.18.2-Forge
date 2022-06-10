package net.toxevo.alcd.effect.complex.Volatile;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.toxevo.alcd.effect.ModEffects;

import java.util.List;
import java.util.Comparator;

public class VolatileEffectAction {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, 1, Explosion.BlockInteraction.NONE);
		if ((entity instanceof LivingEntity _entity ? _entity.getHealth() : -1) == 0) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> entityList = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(
						8 / 2d), e -> true).stream()
						.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityIterator : entityList) {
					if (entityIterator instanceof LivingEntity lEntity)
						lEntity.addEffect(new MobEffectInstance(ModEffects.VOLATILE.get(),
								100, 0, (false), (true)));
				}
			}
		}
	}
}
