package millllionwith4ls.bananabreadmod.core.entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import millllionwith4ls.bananabreadmod.BananaBreadMod;
import millllionwith4ls.bananabreadmod.core.entities.PrimedBananaNuke;
import millllionwith4ls.bananabreadmod.core.blocks.blocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class PrimedBananaNukeRenderer extends EntityRenderer<PrimedBananaNuke> {
    private final BlockRenderDispatcher blockRenderer;

    public PrimedBananaNukeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PrimedBananaNuke primedBananaNuke, float entityYaw, float partialTicks, PoseStack stack, @NotNull MultiBufferSource multiBufferSource, int packedLightIn) {
        stack.pushPose();
        stack.translate(0.0F, 0.0F, 0.0F);
        int i = primedBananaNuke.getFuse();
        if (i - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float) i - partialTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0f, 1.0f);
            f *= f;
            f *= f;
            float f1 = 1.0f + f * 0.3f;
            stack.scale(f1, f1, f1);
        }



        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, blocks.BANANA_NUKE.get().defaultBlockState(), stack, multiBufferSource, packedLightIn, i / 5 % 2 == 0);
        stack.popPose();
        super.render(primedBananaNuke, entityYaw, partialTicks, stack, multiBufferSource, packedLightIn);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PrimedBananaNuke primedBananaNuke) {
        return new ResourceLocation(BananaBreadMod.MODID,"assets/bananabreadmod/models/block/banana_nuke.json");
    }
}
