package ayupitsali.beanology.block;

import ayupitsali.beanology.Beanology;
import ayupitsali.beanology.item.ModItems;
import com.epherical.croptopia.register.Content;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Beanology.MOD_ID);

    public static final RegistryObject<Block> BLACK_EYED_BEAN_CROP = BLOCKS.register("black_eyed_bean_crop",
            () -> new BeanologyCropBlock(BlockBehaviour.Properties.copy(Content.BLACKBEAN.asBlock())));
    public static final RegistryObject<Block> BROAD_BEAN_CROP = BLOCKS.register("broad_bean_crop",
            () -> new BeanologyCropBlock(BlockBehaviour.Properties.copy(Content.SOYBEAN.asBlock())));
    public static final RegistryObject<Block> HARICOT_BEAN_CROP = BLOCKS.register("haricot_bean_crop",
            () -> new BeanologyCropBlock(BlockBehaviour.Properties.copy(Content.SOYBEAN.asBlock())));
    public static final RegistryObject<Block> KIDNEY_BEAN_CROP = BLOCKS.register("kidney_bean_crop",
            () -> new BeanologyCropBlock(BlockBehaviour.Properties.copy(Content.BLACKBEAN.asBlock())));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> blockRegObj = BLOCKS.register(name, block);
        registerBlockItem(name, blockRegObj, tab);
        return blockRegObj;
    }

    private static <T extends Block> RegistryObject<BlockItem> registerBlockItem(String name, RegistryObject<T> blockRegObj, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(blockRegObj.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
