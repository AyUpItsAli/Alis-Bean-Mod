package ayupitsali.beanology.item;

import ayupitsali.beanology.block.BeanologyCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;

public class BeanologySeedItem extends ItemNameBlockItem {
    public BeanologySeedItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
        if (pBlock instanceof BeanologyCropBlock) {
            ((BeanologyCropBlock) pBlock).setSeed(this);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        if (level.getBlockState(pos).getBlock() instanceof FarmBlock && pContext.getClickedFace() == Direction.UP) {
            return super.useOn(pContext);
        }
        return InteractionResult.FAIL;
    }
}
