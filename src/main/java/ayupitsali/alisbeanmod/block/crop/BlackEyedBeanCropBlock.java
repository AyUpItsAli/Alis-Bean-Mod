package ayupitsali.alisbeanmod.block.crop;

import ayupitsali.alisbeanmod.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public class BlackEyedBeanCropBlock extends CropBlock {
    public BlackEyedBeanCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.BLACK_EYED_BEAN_SEEDS.get();
    }
}
