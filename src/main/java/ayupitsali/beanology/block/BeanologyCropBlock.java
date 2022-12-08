package ayupitsali.beanology.block;

import ayupitsali.beanology.item.BeanologySeedItem;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeanologyCropBlock extends CropBlock {
    protected static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
    };
    private final TagKey<Biome> biomeTag;
    private BeanologySeedItem seed;

    public BeanologyCropBlock(Properties pProperties, TagKey<Biome> biomeTag) {
        super(pProperties);
        this.biomeTag = biomeTag;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        // World Gen
        if (pLevel.getChunk(pPos).getStatus().getIndex() < ChunkStatus.FULL.getIndex()) {
            return pLevel.getBiome(pPos).is(this.biomeTag) && super.canSurvive(pState, pLevel, pPos);
        }
        // Player place
        return super.canSurvive(pState, pLevel, pPos);
    }

    public void setSeed(BeanologySeedItem seed) {
        this.seed = seed;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.seed;
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return super.mayPlaceOn(pState, pLevel, pPos) || pState.is(BlockTags.DIRT);
    }
}
