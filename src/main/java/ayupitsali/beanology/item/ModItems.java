package ayupitsali.beanology.item;

import ayupitsali.beanology.Beanology;
import ayupitsali.beanology.block.ModBlocks;
import com.epherical.croptopia.util.FoodConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Beanology.MOD_ID);

    public static final RegistryObject<Item> BLACK_EYED_BEAN_SEEDS = ITEMS.register("black_eyed_bean_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLACK_EYED_BEAN_CROP.get(), new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> BLACK_EYED_BEANS = ITEMS.register("black_eyed_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_3))));
    public static final RegistryObject<Item> BROAD_BEAN_SEEDS = ITEMS.register("broad_bean_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BROAD_BEAN_CROP.get(), new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> BROAD_BEANS = ITEMS.register("broad_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_3))));
    public static final RegistryObject<Item> HARICOT_BEAN_SEEDS = ITEMS.register("haricot_bean_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HARICOT_BEAN_CROP.get(), new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> HARICOT_BEANS = ITEMS.register("haricot_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_3))));
    public static final RegistryObject<Item> KIDNEY_BEAN_SEEDS = ITEMS.register("kidney_bean_seeds",
            () -> new ItemNameBlockItem(ModBlocks.KIDNEY_BEAN_CROP.get(), new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> KIDNEY_BEANS = ITEMS.register("kidney_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_3))));

    public static final RegistryObject<Item> BAKED_BEANS = ITEMS.register("baked_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_10))
                    .craftRemainder(Items.BOWL)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
