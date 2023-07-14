package millllionwith4ls.bananabreadmod.core.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.recipe.BakingOvenRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIBananaPlugin implements IModPlugin {
    public static RecipeType<BakingOvenRecipe> BAKING_TYPE =
            new RecipeType<>(BakingOvenRecipeCategory.UID, BakingOvenRecipe.class);

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(BananaBreadMod.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                BakingOvenRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<BakingOvenRecipe> recipesBaking = rm.getAllRecipesFor(BakingOvenRecipe.Type.INSTANCE);
        registration.addRecipes(BAKING_TYPE, recipesBaking);
    }
}
