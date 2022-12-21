package ayupitsali.beanology.recipe;

import ayupitsali.beanology.Beanology;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Beanology.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SolarConvergenceAltarRecipe>> SOLAR_CONVERGING_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register(SolarConvergenceAltarRecipe.Type.ID, () -> SolarConvergenceAltarRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
