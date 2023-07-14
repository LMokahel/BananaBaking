package millllionwith4ls.bananabreadmod.core.blocks;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.blocks.Blocks.BakingOven;
import millllionwith4ls.bananabreadmod.core.blocks.Blocks.BananaNuke;
import millllionwith4ls.bananabreadmod.core.blocks.cakes.BananaCake;
import millllionwith4ls.bananabreadmod.core.blocks.cakes.ClassicPizza;
import millllionwith4ls.bananabreadmod.core.items.items;
import millllionwith4ls.bananabreadmod.core.liquids.liquids;
import millllionwith4ls.bananabreadmod.core.crops.BananaCropBlock;
import millllionwith4ls.bananabreadmod.core.crops.VanillaBeanCropBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;


public class blocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BananaBreadMod.MODID);



    public static final RegistryObject<Block> VANILLA_BEAN_CROP = BLOCKS.register("vanilla_bean_crop",
            () -> new VanillaBeanCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> BANANA_CROP = BLOCKS.register("banana_crop",
            () -> new BananaCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> BANANA_NUKE = registerBlock("banana_nuke",
            () -> new BananaNuke(BlockBehaviour.Properties.copy(Blocks.TNT).instabreak().noOcclusion().sound(SoundType.GRASS)));

    public static final RegistryObject<LiquidBlock> CHOCOLATE_BLOCK = BLOCKS.register("chocolate_block", () -> new LiquidBlock(liquids.SOURCE_CHOCOLATE,
            BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<BananaCake> BANANA_CAKE = BLOCKS.register("banana_cake",
            () -> new BananaCake(BlockBehaviour.Properties.of(Material.CAKE).noOcclusion().strength(0.5f).sound(SoundType.WOOL)));

    public static final RegistryObject<Block> BAKING_OVEN = registerBlock("baking_oven",
            () -> new BakingOven(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<ClassicPizza> CLASSIC_PIZZA = BLOCKS.register("classic_pizza",
            () -> new ClassicPizza(BlockBehaviour.Properties.of(Material.CAKE).noOcclusion().strength(0.5f).sound(SoundType.WOOL)));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, BananaBreadMod.bananaTAB);
        return toReturn;
    }
    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return items.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(BananaBreadMod.bananaTAB)));
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
