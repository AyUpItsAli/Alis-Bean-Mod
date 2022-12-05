package ayupitsali.beanology.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    public static final CreativeModeTab BEANOLOGY = new CreativeModeTab("beanology") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BAKED_BEANS.get());
        }
    };
}
