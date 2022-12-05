package ayupitsali.beanology.block.crop;

import ayupitsali.beanology.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public class HaricotBeanCropBlock extends CropBlock {
    public HaricotBeanCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.HARICOT_BEAN_SEEDS.get();
    }
}
