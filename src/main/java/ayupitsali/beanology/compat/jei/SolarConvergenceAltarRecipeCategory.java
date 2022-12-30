package ayupitsali.beanology.compat.jei;

import ayupitsali.beanology.Beanology;
import ayupitsali.beanology.block.ModBlocks;
import ayupitsali.beanology.recipe.SolarConvergenceAltarRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SolarConvergenceAltarRecipeCategory implements IRecipeCategory<SolarConvergenceAltarRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Beanology.MOD_ID, SolarConvergenceAltarRecipe.Type.ID);
    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Beanology.MOD_ID, "textures/gui/jei/solar_converging_category.png");
    private static final ItemStack ALTAR_STACK = new ItemStack(ModBlocks.SOLAR_CONVERGENCE_ALTAR.get());

    private final IDrawable background;
    private final IDrawable icon;

    public SolarConvergenceAltarRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(BACKGROUND_TEXTURE, 0, 0, 84, 98).setTextureSize(84, 98).build();
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ALTAR_STACK);
    }

    @Override
    public RecipeType<SolarConvergenceAltarRecipe> getRecipeType() {
        return BeanologyJeiPlugin.SOLAR_CONVERGING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui." + Beanology.MOD_ID + ".category." + SolarConvergenceAltarRecipe.Type.ID);
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SolarConvergenceAltarRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 11, 57).addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.CATALYST, 34, 71).addItemStack(ALTAR_STACK);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 57).addItemStack(recipe.getResultItem());
    }
}
