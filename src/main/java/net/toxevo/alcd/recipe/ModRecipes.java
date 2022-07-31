package net.toxevo.alcd.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.toxevo.alcd.ALCD;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ALCD.MOD_ID);

    public static final RegistryObject<RecipeSerializer<AlchemyTableRecipe3>> ALCHEMY_TABLE_SERIALIZER3 =
            SERIALIZERS.register("alchemy_table3", () -> AlchemyTableRecipe3.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<AlchemyTableRecipe2>> ALCHEMY_TABLE_SERIALIZER2 =
            SERIALIZERS.register("alchemy_table2", () -> AlchemyTableRecipe2.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}