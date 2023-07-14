package millllionwith4ls.bananabreadmod.core.liquids;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.items.items;
import millllionwith4ls.bananabreadmod.core.blocks.blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class liquids {
    public static final DeferredRegister<Fluid> LIQUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, BananaBreadMod.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_CHOCOLATE = LIQUIDS.register("chocolate_fluid",
            () -> new ForgeFlowingFluid.Source(liquids.CHOCOLATE_LIQUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CHOCOLATE = LIQUIDS.register("flowing_chocolate",
            () -> new ForgeFlowingFluid.Flowing(liquids.CHOCOLATE_LIQUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties CHOCOLATE_LIQUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            liquidTypes.CHOCOLATE_LIQUID_TYPE, SOURCE_CHOCOLATE, FLOWING_CHOCOLATE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(blocks.CHOCOLATE_BLOCK)
            .bucket(items.CHOCOLATE_BUCKET);


    public static void register(IEventBus eventBus) {
        LIQUIDS.register(eventBus);
    }
}
