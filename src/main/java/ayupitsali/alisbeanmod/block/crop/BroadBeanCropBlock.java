package ayupitsali.alisbeanmod.block.crop;

import ayupitsali.alisbeanmod.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public class BroadBeanCropBlock extends CropBlock {
    public BroadBeanCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.BROAD_BEAN_SEEDS.get();
    }
}
