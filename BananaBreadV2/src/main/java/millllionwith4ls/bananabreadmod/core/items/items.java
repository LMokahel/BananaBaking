package millllionwith4ls.bananabreadmod.core.items;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.blocks.blocks;
import millllionwith4ls.bananabreadmod.core.liquids.liquids;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Properties;


public class items{

    static float oiledSat = 0.41666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666667f;
    static float friedSat = 0.42857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857142857143f;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BananaBreadMod.MODID);



    public static final RegistryObject<Item> BANANA_BREAD = ITEMS.register("banana_bread", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(0.7f).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_PIE = ITEMS.register("banana_pie", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(0.2f).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_OILED = ITEMS.register("banana_oiled", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(oiledSat).effect(() -> {
        return new MobEffectInstance(MobEffects.HUNGER, 1200, 0);
    }, 1.0F).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_FRIED = ITEMS.register("banana_fried", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(friedSat).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_DONUT = ITEMS.register("banana_donut", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(14).saturationMod(0.2f).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_COOKIE = ITEMS.register("banana_cookie", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.25f).effect(() -> {
        return new MobEffectInstance(MobEffects.REGENERATION, 80, 1);
    }, 2000F).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_PUDDING = ITEMS.register("banana_pudding", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(8).saturationMod(0.5f).effect(() -> {
        return new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1);
    }, 2000F).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_SMOOTHIE = ITEMS.register("banana_smoothie", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().alwaysEat().effect(() -> {
        return new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1);
    }, 2000F).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_PIE_SLICE = ITEMS.register("banana_pie_slice", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> VANILLA_BEAN_SEEDS = ITEMS.register("vanilla_bean_seeds", () ->
            new ItemNameBlockItem(blocks.VANILLA_BEAN_CROP.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> VANILLA_BEAN = ITEMS.register("vanilla_bean", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build())));

    public static final RegistryObject<Item> VANILLA_EXTRACT = ITEMS.register("vanilla_extract", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).alwaysEat().effect(() -> {
        return new MobEffectInstance(MobEffects.HUNGER, 1200, 0);
    }, 1.0F).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_CHOCOLATE = ITEMS.register("banana_chocolate", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(friedSat).build()).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> CHOCOLATE_BUCKET = ITEMS.register("chocolate_bucket", () ->
            new BucketItem(liquids.SOURCE_CHOCOLATE,
            new Item.Properties().tab(BananaBreadMod.bananaTAB).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> BANANA_SEEDS = ITEMS.register("banana_seeds", () ->
            new ItemNameBlockItem(blocks.BANANA_CROP.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build()).tab(CreativeModeTab.TAB_FOOD).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<Item> BANANA_BUNCH = ITEMS.register("banana_bunch", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<BlockItem> BANANA_CAKE = ITEMS.register("banana_cake",() ->
            new ItemNameBlockItem(blocks.BANANA_CAKE.get(), new Item.Properties().stacksTo(1).tab(BananaBreadMod.bananaTAB)));

    public static final RegistryObject<BlockItem> CLASSIC_PIZZA = ITEMS.register("classic_pizza",() ->
            new ItemNameBlockItem(blocks.CLASSIC_PIZZA.get(), new Item.Properties().stacksTo(1).tab(BananaBreadMod.bananaTAB)));

    //Compressed Bananas
    public static final RegistryObject<Item> COMPRESSED_BANANA_ONE = ITEMS.register("compressed_banana_one", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> COMPRESSED_BANANA_TWO = ITEMS.register("compressed_banana_two", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> COMPRESSED_BANANA_THREE = ITEMS.register("compressed_banana_three", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB)));
    public static final RegistryObject<Item> COMPRESSED_BANANA_FOUR = ITEMS.register("compressed_banana_four", () ->
            new Item(new Item.Properties().tab(BananaBreadMod.bananaTAB)));





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
