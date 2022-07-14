package net.toxevo.alcd.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.toxevo.alcd.ALCD;
import net.toxevo.alcd.entity.custom.FrogEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrogModel extends AnimatedGeoModel<FrogEntity> {
    @Override
    public ResourceLocation getModelLocation(FrogEntity object) {
        return new ResourceLocation(ALCD.MOD_ID, "geo/frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FrogEntity object) {
        return new ResourceLocation(ALCD.MOD_ID, "textures/entity/raccoon/frog.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FrogEntity animatable) {
        return new ResourceLocation(ALCD.MOD_ID, "animations/frog.animation.json");
    }
}
