package millllionwith4ls.bananabreadmod.core.crops;

import millllionwith4ls.bananabreadmod.core.items.items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class VanillaBeanCropBlock extends CropBlock {
//    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    public VanillaBeanCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));
    }
    @Override
    protected ItemLike getBaseSeedId() {
        return items.VANILLA_BEAN_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 15;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0, 0, 0, 16, 2, 16), Block.box(0, 0, 0, 16, 2, 16), Block.box(0, 0, 0, 16, 3, 16), Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, 0, 16, 5, 16), Block.box(0, 0, 0, 16, 6, 16), Block.box(0, 0, 0, 16, 7, 16), Block.box(0, 0, 0, 16, 8, 16),
            Block.box(0, 0, 0, 16, 9, 16), Block.box(0, 0, 0, 16, 10, 16), Block.box(0, 0, 0, 16, 11, 16), Block.box(0, 0, 0, 16, 12, 16),
            Block.box(0, 0, 0, 16, 13, 16), Block.box(0, 0, 0, 16, 14, 16), Block.box(0, 0, 0, 16, 15, 16), Block.box(0, 0, 0, 16, 16, 16)};
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[pState.getValue(this.getAgeProperty())];
    }


}
