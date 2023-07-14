package millllionwith4ls.bananabreadmod.core.blocks.cakes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ClassicPizza extends CakeBlock {

    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D),
            Block.box(3.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D),
            Block.box(5.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D),
            Block.box(7.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D),
            Block.box(9.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D),
            Block.box(11.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D),
            Block.box(13.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D)
    };


    public ClassicPizza(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
    }


    @Override
    @NotNull
    public VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter blockGetter, @NotNull BlockPos pPos, @NotNull CollisionContext context) {
        return SHAPE_BY_BITE[pState.getValue(BITES)];
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel,
                                          @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (itemstack.is(ItemTags.CANDLES) && pState.getValue(BITES) == 0) {
            Block block = Block.byItem(item);
            if (block instanceof CandleBlock) {
                if (!pPlayer.isCreative()) {
                    itemstack.shrink(1);
                }
                pLevel.playSound((Player)null, pPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.setBlockAndUpdate(pPos, CandleCakeBlock.byCandle(block));
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos);
                pPlayer.awardStat(Stats.ITEM_USED.get(item));
                return InteractionResult.SUCCESS;
            }
        }
        if (pLevel.isClientSide) {
            if (eat(pLevel, pPos, pState, pPlayer).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }
        return eat(pLevel, pPos, pState, pPlayer);
    }


    protected static @NotNull InteractionResult eat(@NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, Player pPlayer) {
        if (!pPlayer.canEat(true)) {
            return InteractionResult.PASS;
        } else {
            pPlayer.awardStat(Stats.EAT_CAKE_SLICE);
            pPlayer.getFoodData().eat(3, 0.2F);
            pPlayer.addEffect(new MobEffectInstance(MobEffects.SATURATION,40,1,false, false,true));
            int i = pState.getValue(BITES);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            if (i < 6) {
                pLevel.setBlock(pPos, pState.setValue(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                pLevel.removeBlock(pPos, false);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            }

            return InteractionResult.SUCCESS;
        }
    }

}
