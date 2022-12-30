package ayupitsali.beanology.item;

import ayupitsali.beanology.Beanology;
import ayupitsali.beanology.block.ModBlocks;
import com.epherical.croptopia.util.FoodConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Beanology.MOD_ID);

    public static final RegistryObject<Item> BLACK_EYED_BEAN_SEEDS = ITEMS.register("black_eyed_bean_seeds",
            () -> new BeanologySeedItem(ModBlocks.BLACK_EYED_BEAN_CROP.get(), new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> BLACK_EYED_BEANS = ITEMS.register("black_eyed_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_3))));
    public static final RegistryObject<Item> BROAD_BEAN_SEEDS = ITEMS.register("broad_bean_seeds",
            () -> new BeanologySeedItem(ModBlocks.BROAD_BEAN_CROP.get(), new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> BROAD_BEANS = ITEMS.register("broad_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_3))));
    public static final RegistryObject<Item> HARICOT_BEAN_SEEDS = ITEMS.register("haricot_bean_seeds",
            () -> new BeanologySeedItem(ModBlocks.HARICOT_BEAN_CROP.get(), new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> HARICOT_BEANS = ITEMS.register("haricot_beans",
            () -> new Item(new Item.Properties()
                    .tab(ModItemGroups.BEANOLOGY)
                    .food(FoodConstructor.createFood(FoodConstructor.REG_3))));
    public static final RegistryObject<Item> KIDNEY_BEAN_SEEDS = ITEMS.register("kidney_bean_seeds",
            () -> new BeanologySeedItem(ModBlocks.KIDNEY_BEAN_CROP.get(), new Item.Properties()
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

    public static final RegistryObject<Item> CHARGED_BLACK_BEANS = ITEMS.register("charged_black_beans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_BLACK_EYED_BEANS = ITEMS.register("charged_black_eyed_beans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_BROAD_BEANS = ITEMS.register("charged_broad_beans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_COCOA_BEANS = ITEMS.register("charged_cocoa_beans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_COFFEE_BEANS = ITEMS.register("charged_coffee_beans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_GREEN_BEAN = ITEMS.register("charged_green_bean",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_HARICOT_BEANS = ITEMS.register("charged_haricot_beans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_KIDNEY_BEANS = ITEMS.register("charged_kidney_beans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));
    public static final RegistryObject<Item> CHARGED_SOYBEANS = ITEMS.register("charged_soybeans",
            () -> new Item(new Item.Properties().tab(ModItemGroups.BEANOLOGY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
