package millllionwith4ls.bananabreadmod.core.recipe;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Recipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BananaBreadMod.MODID);

    public static final RegistryObject<RecipeSerializer<BakingOvenRecipe>> BAKING_SERIALIZER =
            SERIALIZERS.register("baking", () -> BakingOvenRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
