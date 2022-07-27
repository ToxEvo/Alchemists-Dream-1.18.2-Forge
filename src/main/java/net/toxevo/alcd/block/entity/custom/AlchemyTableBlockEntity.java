package net.toxevo.alcd.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.toxevo.alcd.block.entity.ModBlockEntities;
import net.toxevo.alcd.item.ModItems;
import net.toxevo.alcd.screen.AlchemyTableMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Random;

public class AlchemyTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public AlchemyTableBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ALCHEMY_TABLE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Alchemy Table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AlchemyTableMenu(pContainerId, pInventory, this);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AlchemyTableBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
            craftItem(pBlockEntity);
        }
    }

    private static void craftItem(AlchemyTableBlockEntity entity) {
        entity.itemHandler.extractItem(0, 1, false);
        entity.itemHandler.extractItem(1, 1, false);
        entity.itemHandler.extractItem(2, 1, false);

        entity.itemHandler.setStackInSlot(3, new ItemStack(ModItems.VOLATILE_PETAL.get(),
                entity.itemHandler.getStackInSlot(3).getCount() + 1));
    }

    private static boolean hasRecipe(AlchemyTableBlockEntity entity) {
        boolean hasItemInWaterSlot = PotionUtils.getPotion(entity.itemHandler.getStackInSlot(0)) == Potions.WATER;
        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem() == ModItems.VOLATILE_PETAL.get();
        boolean hasItemInSecondSlot = entity.itemHandler.getStackInSlot(2).getItem() == Items.SHEARS;

        return hasItemInWaterSlot && hasItemInFirstSlot && hasItemInSecondSlot;
    }

    private static boolean hasNotReachedStackLimit(AlchemyTableBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(3).getCount() < entity.itemHandler.getStackInSlot(3).getMaxStackSize();
    }
}
