package ayupitsali.beanology.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;

public class SeedItem extends ItemNameBlockItem {
    public SeedItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
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
