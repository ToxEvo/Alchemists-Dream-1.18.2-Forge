package net.toxevo.alcd.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ALCD_TAB = new CreativeModeTab("alcdtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.VOLATILE_PETAL.get());
        }
    };
}
