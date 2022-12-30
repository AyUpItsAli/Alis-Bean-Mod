package ayupitsali.beanology.block;

import ayupitsali.beanology.block.entity.ModBlockEntities;
import ayupitsali.beanology.block.entity.SolarConvergenceAltarBlockEntity;
import ayupitsali.beanology.block.property.SolarConvergenceAltarPart;
import ayupitsali.beanology.block.property.SolarConvergenceAltarStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;


public class SolarConvergenceAltarBlock extends BaseEntityBlock {
    public static final EnumProperty<SolarConvergenceAltarPart> PART = EnumProperty.create("part", SolarConvergenceAltarPart.class);
    public static final EnumProperty<SolarConvergenceAltarStatus> STATUS = EnumProperty.create("status", SolarConvergenceAltarStatus.class);

    public static final VoxelShape SHAPE_LOWER = Block.box(2, 0, 2, 14, 16, 14);
    public static final VoxelShape SHAPE_MIDDLE = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(4, 2, 4, 12, 4, 12),
            Block.box(6, 10, 6, 10, 12, 10),
            Block.box(4, 12, 4, 12, 14, 12),
            Block.box(2, 14, 2, 14, 16, 14),
            Block.box(2, 2, 2, 4, 14, 4),
            Block.box(12, 2, 12, 14, 14, 14),
            Block.box(2, 2, 12, 4, 14, 14),
            Block.box(12, 2, 2, 14, 14, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_UPPER = Shapes.join(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(0, 2, 0, 16, 6, 16),
            BooleanOp.OR);

    public SolarConvergenceAltarBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .strength(1.5F, 6.0F)
                .lightLevel(state -> state.getValue(STATUS).equals(SolarConvergenceAltarStatus.INACTIVE) ? 0 : 15));
        registerDefaultState(stateDefinition.any().setValue(PART, SolarConvergenceAltarPart.LOWER).setValue(STATUS, SolarConvergenceAltarStatus.INACTIVE));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(PART)) {
            case UPPER -> SHAPE_UPPER;
            case MIDDLE -> SHAPE_MIDDLE;
            case LOWER -> SHAPE_LOWER;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PART, STATUS);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos lowerPos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        BlockState middle = level.getBlockState(lowerPos.above());
        BlockState upper = level.getBlockState(lowerPos.above().above());
        if (lowerPos.getY() < level.getMaxBuildHeight() - 2 && middle.canBeReplaced(pContext) && upper.canBeReplaced(pContext)) {
            return defaultBlockState().setValue(PART, SolarConvergenceAltarPart.LOWER);
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        pLevel.setBlock(pPos.above(), pState.setValue(PART, SolarConvergenceAltarPart.MIDDLE), 3);
        pLevel.setBlock(pPos.above().above(), pState.setValue(PART, SolarConvergenceAltarPart.UPPER), 3);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState stateBelow = pLevel.getBlockState(pPos.below());
        return pState.getValue(PART).equals(SolarConvergenceAltarPart.LOWER) ? super.canSurvive(pState, pLevel, pPos) : stateBelow.is(this);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!(pNewState.getBlock() instanceof SolarConvergenceAltarBlock)) {
            if (pState.getValue(PART).equals(SolarConvergenceAltarPart.UPPER)) {
                BlockPos posLower = pPos.below().below();
                if (pLevel.getBlockState(posLower).is(this)) {
                    pLevel.destroyBlock(posLower, true);
                }
            } else if (pState.getValue(PART).equals(SolarConvergenceAltarPart.MIDDLE)) {
                BlockPos posLower = pPos.below();
                if (pLevel.getBlockState(posLower).is(this)) {
                    pLevel.destroyBlock(posLower, true);
                }
                BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                if (blockEntity instanceof SolarConvergenceAltarBlockEntity) {
                    ((SolarConvergenceAltarBlockEntity) blockEntity).dropInventory();
                }
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide() && pPlayer.isCreative()) {
            if (pState.getValue(PART).equals(SolarConvergenceAltarPart.UPPER)) {
                BlockPos posLower = pPos.below().below();
                if (pLevel.getBlockState(posLower).is(this)) {
                    pLevel.destroyBlock(posLower, false);
                }
            } else if (pState.getValue(PART).equals(SolarConvergenceAltarPart.MIDDLE)) {
                BlockPos posLower = pPos.below();
                if (pLevel.getBlockState(posLower).is(this)) {
                    pLevel.destroyBlock(posLower, false);
                }
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    // Block Entity

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return pState.getValue(PART).equals(SolarConvergenceAltarPart.MIDDLE) ? new SolarConvergenceAltarBlockEntity(pPos, pState) : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pState.getValue(PART).equals(SolarConvergenceAltarPart.MIDDLE)) {
            return switch (pState.getValue(STATUS)) {
                case INACTIVE -> createTickerHelper(pBlockEntityType, ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), SolarConvergenceAltarBlockEntity::inactiveTick);
                case STARTING -> createTickerHelper(pBlockEntityType, ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), SolarConvergenceAltarBlockEntity::startingTick);
                case ACTIVE -> createTickerHelper(pBlockEntityType, ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), SolarConvergenceAltarBlockEntity::activeTick);
                case PROCESSING -> createTickerHelper(pBlockEntityType, ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), SolarConvergenceAltarBlockEntity::processingTick);
                case STOPPING -> createTickerHelper(pBlockEntityType, ModBlockEntities.SOLAR_CONVERGENCE_ALTAR.get(), SolarConvergenceAltarBlockEntity::stoppingTick);
            };
        }
        return null;
    }

    private SolarConvergenceAltarBlockEntity getBlockEntity(BlockState pState, Level pLevel, BlockPos pPos) {
        return switch (pState.getValue(PART)) {
            case UPPER -> (SolarConvergenceAltarBlockEntity) pLevel.getBlockEntity(pPos.below());
            case MIDDLE -> (SolarConvergenceAltarBlockEntity) pLevel.getBlockEntity(pPos);
            case LOWER -> (SolarConvergenceAltarBlockEntity) pLevel.getBlockEntity(pPos.above());
        };
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        SolarConvergenceAltarBlockEntity blockEntity = getBlockEntity(pState, pLevel, pPos);
        ItemStack stack = pPlayer.getItemInHand(pHand);
        if (stack.isEmpty()) {
            if (!pPlayer.isCrouching() && blockEntity.getRecipe().isPresent()) {
                return InteractionResult.PASS;
            }
            ItemStack removedStack = blockEntity.removeStack();
            if (removedStack.isEmpty()) {
                return InteractionResult.PASS;
            }
            if (pPlayer.getInventory().contains(removedStack)) {
                pPlayer.addItem(removedStack);
            } else {
                pPlayer.setItemInHand(pHand, removedStack);
            }
        } else {
            ItemStack result = blockEntity.placeStack(ItemHandlerHelper.copyStackWithSize(stack, 1));
            if (!result.isEmpty()) {
                return InteractionResult.PASS;
            }
            stack.shrink(1);
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}
