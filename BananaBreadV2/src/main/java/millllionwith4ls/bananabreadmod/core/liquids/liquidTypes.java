package millllionwith4ls.bananabreadmod.core.liquids;

import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import millllionwith4ls.bananabreadmod.BananaBreadMod;
import net.minecraftforge.registries.RegistryObject;

public class liquidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation OVERLAY_WATER_RL = new ResourceLocation("block/water_still");


    public static final DeferredRegister<FluidType> LIQUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BananaBreadMod.MODID);


    //Fluids
    public static final RegistryObject<FluidType> CHOCOLATE_LIQUID_TYPE = register(FluidType.Properties.create()
            .canSwim(true).canDrown(true).canConvertToSource(true).fallDistanceModifier(0f).canExtinguish(true).canHydrate(true).canPushEntity(true)
            .lightLevel(2).viscosity(15).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));






    //Register Methods
    private static RegistryObject<FluidType> register(FluidType.Properties properties){
        return LIQUID_TYPES.register("chocolate_fluid_type", () -> new liquidBaseType(WATER_STILL_RL,WATER_FLOWING_RL,OVERLAY_WATER_RL,
                0xF136240e, new Vector3f(133f/255f, 133f/255f,133f/255f ), properties));
    }
    public static void register(IEventBus eventBus){
        LIQUID_TYPES.register(eventBus);
    }


}
