package millllionwith4ls.bananabreadmod.core.blocks.entities;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.blocks.blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class blockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BananaBreadMod.MODID);

    public static final RegistryObject<BlockEntityType<BakingOvenBlockEntity>> BAKING_OVEN =
            BLOCK_ENTITIES.register("baking_oven", () ->
                    BlockEntityType.Builder.of(BakingOvenBlockEntity::new,
                            blocks.BAKING_OVEN.get()).build(null));





    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
