package millllionwith4ls.bananabreadmod.core.entities.renderers;

import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.entities.entities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = BananaBreadMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRendererRegister {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(entities.PRIMED_BANANA_NUKE.get(), PrimedBananaNukeRenderer::new);
    }
}
