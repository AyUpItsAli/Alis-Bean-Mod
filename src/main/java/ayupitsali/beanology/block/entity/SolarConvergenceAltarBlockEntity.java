package ayupitsali.beanology.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SolarConvergenceAltarBlockEntity extends BlockEntity {
    private ItemStack stack = ItemStack.EMPTY;

    public SolarConvergenceAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("stack", stack.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        stack.deserializeNBT(pTag.getCompound("stack"));
    }

    public void dropItem() {
        Containers.dropContents(this.level, this.worldPosition, new SimpleContainer(stack));
    }

    public boolean placeItem(ItemStack stack) {
        if (this.stack.isEmpty()) {
            this.stack = stack;
            return true;
        }
        return false;
    }

    public ItemStack removeItem() {
        ItemStack removedStack = this.stack.copy();
        this.stack = ItemStack.EMPTY;
        return removedStack;
    }

    public static void tick(Level pLevel, BlockPos pBlockPos, BlockState pBlockState, SolarConvergenceAltarBlockEntity pBlockEntity) {
        if (pLevel.isClientSide()) {
            return;
        }
    }
}
