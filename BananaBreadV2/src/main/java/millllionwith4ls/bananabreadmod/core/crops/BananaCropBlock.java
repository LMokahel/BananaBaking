package millllionwith4ls.bananabreadmod.core.crops;

import millllionwith4ls.bananabreadmod.core.items.items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;


public class BananaCropBlock extends CropBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);

    public BananaCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));
    }
    @Override
    protected @NotNull ItemLike getBaseSeedId() {return items.BANANA_SEEDS.get();}

    @Override
    public @NotNull IntegerProperty getAgeProperty() {return AGE;}

    @Override
    public int getMaxAge() {return super.getMaxAge();}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AGE);}
}
