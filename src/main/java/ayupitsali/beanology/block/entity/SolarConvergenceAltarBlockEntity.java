package ayupitsali.beanology.block.entity;

import ayupitsali.beanology.block.SolarConvergenceAltarBlock;
import ayupitsali.beanology.recipe.SolarConvergenceAltarRecipe;
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
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.Range;

import javax.annotation.Nullable;
import java.util.Optional;

public class SolarConvergenceAltarBlockEntity extends BlockEntity {

    // TODO: Config file

    public static final Range<Long> DAY_TIME_INTERVAL = Range.between(5600L, 6400L);
    public static final Range<Long> NIGHT_TIME_INTERVAL = Range.between(17850L, 18150L);
    public static final int TOTAL_BEAM_PROGRESS = 20;
    public static final int TOTAL_PROCESSING_TICKS = 160;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public int getSlotLimit(int slot) {
            return slot == 0 ? 16 : super.getSlotLimit(slot);
        }
    };
    enum Status {
        IDLE,
        STARTING,
        PROCESSING,
        STOPPING
    }
    private Status status = Status.IDLE;
    private int beamProgress = 0;
    private int processingTicks = 0;

    public SolarConvergenceAltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), pPos, pBlockState);
    }

    public ItemStack getStackToRender() {
        return itemStackHandler.getStackInSlot(0);
    }

    public int getBeamProgress() {
        return beamProgress;
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    public void dropInventory() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, inventory);
    }

    public ItemStack placeStack(ItemStack stack) {
        Optional<SolarConvergenceAltarRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(SolarConvergenceAltarRecipe.Type.INSTANCE, new SimpleContainer(stack), level);
        if (recipe.isEmpty()) {
            return stack;
        }
        return itemStackHandler.insertItem(0, stack, false);
    }

    public ItemStack removeStack() {
        return itemStackHandler.extractItem(0, itemStackHandler.getStackInSlot(0).getCount(), false);
    }

    private Optional<SolarConvergenceAltarRecipe> getRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        return level.getRecipeManager().getRecipeFor(SolarConvergenceAltarRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canProcess() {
        if (level.isRaining() || level.isThundering()) {
            return false;
        }
        long dayTime = level.getDayTime();
        if (NIGHT_TIME_INTERVAL.contains(dayTime)) {
            return level.getMoonBrightness() >= 0.5f;
        }
        return DAY_TIME_INTERVAL.contains(dayTime);
    }

    public static void tick(Level pLevel, BlockPos pBlockPos, BlockState pBlockState, SolarConvergenceAltarBlockEntity pBlockEntity) {
        if (pLevel.isClientSide()) {
            return;
        }
        Optional<SolarConvergenceAltarRecipe> recipeOptional = pBlockEntity.getRecipe();
        if (pBlockEntity.status.equals(Status.IDLE)) {
            if (recipeOptional.isPresent() && pBlockEntity.canProcess()) {
                pBlockEntity.status = Status.STARTING;
                pBlockEntity.setChanged();
            }
        } else if (pBlockEntity.status.equals(Status.STARTING)) {
            if (recipeOptional.isEmpty() || !pBlockEntity.canProcess()) {
                pBlockEntity.status = Status.STOPPING;
            } else {
                pBlockEntity.beamProgress++;
                if (pBlockEntity.beamProgress >= TOTAL_BEAM_PROGRESS) {
                    pBlockEntity.status = Status.PROCESSING;
                }
            }
            pBlockEntity.setChanged();
        } else if (pBlockEntity.status.equals(Status.PROCESSING)) {
            if (recipeOptional.isEmpty() || !pBlockEntity.canProcess()) {
                pBlockEntity.status = Status.STOPPING;
                pBlockEntity.processingTicks = 0;
            } else {
                pBlockEntity.processingTicks++;
                if (pBlockEntity.processingTicks >= TOTAL_PROCESSING_TICKS) {
                    ItemStack result = recipeOptional.get().getResultItem();
                    result.setCount(pBlockEntity.itemStackHandler.getStackInSlot(0).getCount());
                    pBlockEntity.itemStackHandler.setStackInSlot(0, result);
                    pBlockEntity.status = Status.STOPPING;
                    pBlockEntity.processingTicks = 0;
                }
            }
            pBlockEntity.setChanged();
        } else if (pBlockEntity.status.equals(Status.STOPPING)) {
            if (recipeOptional.isPresent() && pBlockEntity.canProcess()) {
                pBlockEntity.status = Status.STARTING;
            } else {
                pBlockEntity.beamProgress--;
                if (pBlockEntity.beamProgress <= 0) {
                    pBlockEntity.status = Status.IDLE;
                }
            }
            pBlockEntity.setChanged();
        }
        BlockPos pos = pBlockEntity.getBlockPos();
        BlockState state = pLevel.getBlockState(pos);
        boolean converging = !pBlockEntity.status.equals(Status.IDLE);
        if (state.getValue(SolarConvergenceAltarBlock.CONVERGING) != converging) {
            pLevel.setBlock(pos, state.setValue(SolarConvergenceAltarBlock.CONVERGING, converging), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        pTag.putString("status", status.toString());
        pTag.putInt("beamProgress", beamProgress);
        pTag.putInt("processTicks", processingTicks);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
        status = Status.valueOf(pTag.getString("status"));
        beamProgress = pTag.getInt("beamProgress");
        processingTicks = pTag.getInt("processTicks");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }
}
