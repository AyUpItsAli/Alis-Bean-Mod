package ayupitsali.beanology.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    public static final CreativeModeTab ALIS_BEAN_MOD = new CreativeModeTab("alis_bean_mod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BAKED_BEANS.get());
        }
    };
}
