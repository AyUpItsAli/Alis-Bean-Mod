package ayupitsali.beanology.compat.jei;

import ayupitsali.beanology.Beanology;
import ayupitsali.beanology.recipe.SolarConvergenceAltarRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class BeanologyJeiPlugin implements IModPlugin {
    public static RecipeType<SolarConvergenceAltarRecipe> SOLAR_CONVERGING =
            new RecipeType<>(SolarConvergenceAltarRecipeCategory.UID, SolarConvergenceAltarRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Beanology.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new SolarConvergenceAltarRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<SolarConvergenceAltarRecipe> solarConvergingRecipes = recipeManager.getAllRecipesFor(SolarConvergenceAltarRecipe.Type.INSTANCE);
        registration.addRecipes(SOLAR_CONVERGING, solarConvergingRecipes);
    }
}
