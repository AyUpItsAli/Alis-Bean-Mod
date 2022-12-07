package ayupitsali.beanology.block.crop;

import ayupitsali.beanology.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlackEyedBeanCropBlock extends CropBlock {
    public BlackEyedBeanCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return super.mayPlaceOn(pState, pLevel, pPos) || pState.is(BlockTags.DIRT);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.BLACK_EYED_BEAN_SEEDS.get();
    }
}
