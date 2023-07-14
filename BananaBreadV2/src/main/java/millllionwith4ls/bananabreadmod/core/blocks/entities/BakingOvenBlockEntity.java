package millllionwith4ls.bananabreadmod.core.blocks.entities;

import millllionwith4ls.bananabreadmod.core.blocks.entities.screen.bakingOven.BakingOvenMenu;
import millllionwith4ls.bananabreadmod.core.recipe.BakingOvenRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static millllionwith4ls.bananabreadmod.core.blocks.Blocks.BakingOven.LIT;


public class BakingOvenBlockEntity extends BlockEntity implements MenuProvider {


    private final ItemStackHandler itemHandler = new ItemStackHandler(10) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private static final ArrayList<Block> blocks = new ArrayList<>();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 130;

    public BakingOvenBlockEntity(BlockPos pos, BlockState state) {
        super(blockEntities.BAKING_OVEN.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BakingOvenBlockEntity.this.progress;
                    case 1 -> BakingOvenBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BakingOvenBlockEntity.this.progress = value;
                    case 1 -> BakingOvenBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        blocks.add(Blocks.FIRE);
        blocks.add(Blocks.CAMPFIRE);
        blocks.add(Blocks.SOUL_CAMPFIRE);
        blocks.add(Blocks.SOUL_FIRE);
        blocks.add(Blocks.LAVA);
        blocks.add(Blocks.LAVA_CAULDRON);

    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Baking Oven");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
        return new BakingOvenMenu(id, inventory, this, this.data);
    }



    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }



    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("baking_oven.progress", this.progress);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("baking_oven.progress");

    }


    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BakingOvenBlockEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level, pos, state);

            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level, pos, state);
        }
    }
    private static BlockState getBlockState(BlockPos blockPos, BakingOvenBlockEntity entity){
        Level level = entity.getLevel();
        assert level != null;
        return level.getBlockState(blockPos);
    }
    private static boolean hasFire(BakingOvenBlockEntity entity){

        BlockPos blockPos = entity.getBlockPos().below();
        for (Block blocks : blocks) {
            if (getBlockState(blockPos, entity).getBlock().equals(blocks)) {
                return true;
            }
        }
        return false;
    }
    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(BakingOvenBlockEntity pEntity) {
        Level level = pEntity.level;

        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));

        }
        assert level != null;
        Optional<BakingOvenRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(BakingOvenRecipe.Type.INSTANCE, inventory, level);
        if(hasRecipe(pEntity)) {
            for(int i = 0;i<9;i++){
                pEntity.itemHandler.extractItem(i,1, false);
            }
            if(recipe.isPresent()){
                pEntity.itemHandler.setStackInSlot(9, new ItemStack(recipe.get().getResultItem().getItem(),
                        pEntity.itemHandler.getStackInSlot(9).getCount() + recipe.get().getResultItem().getCount()));
                pEntity.resetProgress();
            }

        }
    }

    private static boolean hasRecipe(BakingOvenBlockEntity entity) {
        Level level = entity.level;

        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));

        }
        assert level != null;
        Optional<BakingOvenRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(BakingOvenRecipe.Type.INSTANCE, inventory, level);
        if((hasFire(entity))){
            Objects.requireNonNull(entity.getLevel()).setBlock(entity.getBlockPos(),entity.getBlockState().setValue(LIT,true),Block.UPDATE_ALL);
            return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory, recipe) &&
                    canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem() );
        }
        else{
            Objects.requireNonNull(entity.getLevel()).setBlock(entity.getBlockPos(),entity.getBlockState().setValue(LIT,false),Block.UPDATE_ALL);
            return false;
        }
    }



    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(9).getItem() == stack.getItem() || inventory.getItem(9).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, Optional<BakingOvenRecipe> recipe) {
        return inventory.getItem(9).getMaxStackSize() > inventory.getItem(9).getCount() + recipe.get().getResultItem().getCount();
    }
}
