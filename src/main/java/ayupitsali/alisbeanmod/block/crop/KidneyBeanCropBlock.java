package ayupitsali.alisbeanmod.block.crop;

import ayupitsali.alisbeanmod.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public class KidneyBeanCropBlock extends CropBlock {
    public KidneyBeanCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.KIDNEY_BEAN_SEEDS.get();
    }
}
