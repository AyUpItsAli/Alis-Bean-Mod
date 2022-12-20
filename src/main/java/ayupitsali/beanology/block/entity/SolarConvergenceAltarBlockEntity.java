package ayupitsali.beanology.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class SolarConvergenceAltarBlockEntity extends BlockEntity {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public SolarConvergenceAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), pPos, pBlockState);
    }

    public ItemStack getStackToRender() {
        return itemStackHandler.getStackInSlot(0);
    }

    public void dropInventory() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public boolean placeStack(ItemStack stack) {

        // TODO: Only allow certain items to be placed via an item tag.

        if (itemStackHandler.getStackInSlot(0).isEmpty()) {
            itemStackHandler.setStackInSlot(0, stack);
            return true;
        }
        return false;
    }

    public ItemStack removeStack() {
        ItemStack removedStack = itemStackHandler.getStackInSlot(0);
        if (removedStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        itemStackHandler.setStackInSlot(0, ItemStack.EMPTY);
        return removedStack;
    }

    public static void tick(Level pLevel, BlockPos pBlockPos, BlockState pBlockState, SolarConvergenceAltarBlockEntity pBlockEntity) {
        if (pLevel.isClientSide()) {
            return;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("inventory", itemStackHandler.serializeNBT());
        return tag;
    }
}
