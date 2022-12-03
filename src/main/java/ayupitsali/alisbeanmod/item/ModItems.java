package ayupitsali.alisbeanmod.item;

import ayupitsali.alisbeanmod.AlisBeanMod;
import com.epherical.croptopia.util.FoodConstructor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlisBeanMod.MOD_ID);

    public static final RegistryObject<Item> BLACK_EYED_BEANS = ITEMS.register("black_eyed_beans",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(FoodConstructor.createFood(FoodConstructor.REG_2))));
    public static final RegistryObject<Item> BROAD_BEANS = ITEMS.register("broad_beans",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(FoodConstructor.createFood(FoodConstructor.REG_3))));
    public static final RegistryObject<Item> HARICOT_BEANS = ITEMS.register("haricot_beans",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(FoodConstructor.createFood(FoodConstructor.REG_4))));
    public static final RegistryObject<Item> KIDNEY_BEANS = ITEMS.register("kidney_beans",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(FoodConstructor.createFood(FoodConstructor.REG_3))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
