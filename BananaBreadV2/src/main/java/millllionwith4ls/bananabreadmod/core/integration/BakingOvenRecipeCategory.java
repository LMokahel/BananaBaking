package millllionwith4ls.bananabreadmod.core.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.blocks.blocks;
import millllionwith4ls.bananabreadmod.core.recipe.BakingOvenRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;


public class BakingOvenRecipeCategory implements IRecipeCategory<BakingOvenRecipe>{
    public static final int width = 116;
    public static final int height = 54;
    public final static ResourceLocation UID = new ResourceLocation(BananaBreadMod.MODID, "baking");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(BananaBreadMod.MODID, "textures/gui/jei_baking_oven.png");
    public final static ResourceLocation ARROW =
            new ResourceLocation(BananaBreadMod.MODID, "textures/gui/baking_oven.png");




    private final IDrawable background;
    private final IDrawable icon;
    protected final IDrawableAnimated arrow;


    public BakingOvenRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 60, width, height);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(blocks.BAKING_OVEN.get()));
        this.arrow = helper.drawableBuilder(ARROW, 177, 14, 24, 17)
                .buildAnimated(100, IDrawableAnimated.StartDirection.LEFT, false);

    }

    @Override
    public @NotNull RecipeType<BakingOvenRecipe> getRecipeType() {
        return JEIBananaPlugin.BAKING_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal("Baking Oven");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, BakingOvenRecipe recipe, @NotNull IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        ItemStack resultStack = recipe.getResultItem();
        int borderSlotSize = 18;

        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeIngredients.size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, column * borderSlotSize + 1, row * borderSlotSize + 1)
                            .addItemStacks(Arrays.asList(recipeIngredients.get(inputIndex).getItems()));
                }
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 93, 19).addItemStack(resultStack);
    }

    @Override
    public void draw(@NotNull BakingOvenRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
                     @NotNull PoseStack stack, double mouseX, double mouseY) {
        this.arrow.draw(stack,61,18);
    }
}
