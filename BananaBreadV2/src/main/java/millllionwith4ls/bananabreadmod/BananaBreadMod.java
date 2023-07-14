package millllionwith4ls.bananabreadmod;

import com.mojang.logging.LogUtils;
import millllionwith4ls.bananabreadmod.core.blocks.blocks;
import millllionwith4ls.bananabreadmod.core.blocks.entities.blockEntities;
import millllionwith4ls.bananabreadmod.core.blocks.entities.screen.bakingOven.BakingOvenScreen;
import millllionwith4ls.bananabreadmod.core.blocks.entities.screen.MenuTypes;
import millllionwith4ls.bananabreadmod.core.entities.entities;
import millllionwith4ls.bananabreadmod.core.items.items;
import millllionwith4ls.bananabreadmod.core.liquids.liquidTypes;
import millllionwith4ls.bananabreadmod.core.liquids.liquids;
import millllionwith4ls.bananabreadmod.core.recipe.Recipes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;


@Mod(BananaBreadMod.MODID)
public class BananaBreadMod {
    public static final String MODID = "bananabreadmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BananaBreadMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);

        entities.register(bus);
        blockEntities.register(bus);
        items.register(bus);
        blocks.register(bus);
        liquidTypes.register(bus);
        liquids.register(bus);
        MenuTypes.register(bus);
        Recipes.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("bananabreadmod common startup");
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("bananabreadmod serverside startup");}

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = BananaBreadMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("bananabreadmod clientside startup");
            MenuScreens.register(MenuTypes.BAKING_OVEN_MENU.get(), BakingOvenScreen::new);
        }

    }

    public static final CreativeModeTab bananaTAB = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {return new ItemStack(items.BANANA_BREAD.get());}
    };
}
