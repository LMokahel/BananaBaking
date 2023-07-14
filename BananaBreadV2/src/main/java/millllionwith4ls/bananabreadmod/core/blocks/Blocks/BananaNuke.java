package millllionwith4ls.bananabreadmod.core.blocks.Blocks;

import millllionwith4ls.bananabreadmod.core.entities.PrimedBananaNuke;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;


public class BananaNuke extends TntBlock {
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public BananaNuke(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, Boolean.FALSE));
    }


    @Override
    public void onCaughtFire(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
        if (!world.isClientSide){
            PrimedBananaNuke primedBananaNuke = new PrimedBananaNuke(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
            world.addFreshEntity(primedBananaNuke);
            world.playSound((Player)null, primedBananaNuke.getX(), primedBananaNuke.getY(), primedBananaNuke.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 0.5F, 1.0F);
            world.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }
    @Override
    public void wasExploded(Level p_57441_, @NotNull BlockPos p_57442_, @NotNull Explosion p_57443_) {
        if (!p_57441_.isClientSide) {
            PrimedBananaNuke PrimedBananaNuke = new PrimedBananaNuke(p_57441_, (double)p_57442_.getX() + 1D, (double)p_57442_.getY(), (double)p_57442_.getZ() + 1D, p_57443_.getSourceMob());
            int i = PrimedBananaNuke.getFuse();
            PrimedBananaNuke.setFuse((short)(p_57441_.random.nextInt(i) + i));
            p_57441_.addFreshEntity(PrimedBananaNuke);
        }
    }
}