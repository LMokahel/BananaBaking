package millllionwith4ls.bananabreadmod.core.entities;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;


public class entities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BananaBreadMod.MODID);




    public static final RegistryObject<EntityType<PrimedBananaNuke>> PRIMED_BANANA_NUKE = ENTITIES.register("primed_banana_nuke",
            () -> EntityType.Builder.<PrimedBananaNuke>of(PrimedBananaNuke::new, MobCategory.MISC)
                    .fireImmune().sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(10)
                    .build("bananabreadmod:primed_banana_nuke"));


    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}





