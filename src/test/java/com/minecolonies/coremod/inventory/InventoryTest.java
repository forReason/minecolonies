package com.minecolonies.coremod.inventory;

import com.minecolonies.coremod.colony.buildings.AbstractBuilding;
import com.minecolonies.coremod.colony.materials.MaterialStore;
import com.minecolonies.coremod.colony.materials.MaterialSystem;
import com.minecolonies.coremod.test.AbstractTest;
import com.minecolonies.coremod.tileentities.TileEntityColonyBuilding;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryTest extends AbstractTest
{

    private IInventory     inventory;
    private MaterialSystem materialSystem;
    private MaterialStore  materialStore;

    @Before
    public void setupInventories()
    {
        this.materialSystem = new MaterialSystem();
        final TileEntityColonyBuilding colonyBuilding = new TileEntityColonyBuilding();
        this.materialStore = new MaterialStore(MaterialStore.Type.CHEST, materialSystem);
        final AbstractBuilding mockBuilding = mock(AbstractBuilding.class);
        when(mockBuilding.getMaterialStore()).thenReturn(this.materialStore);
        colonyBuilding.setBuilding(mockBuilding);
        this.inventory = colonyBuilding;
    }

    @Test
    public void emptyInventoryTest()
    {
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            assertThat(inventory.getStackInSlot(i), is(nullValue()));
        }
    }

    @Test
    public void addStackTest()
    {
        final Item testItem = mock(Item.class);
        final ItemStack stuff = new ItemStack(testItem, 3);
        inventory.setInventorySlotContents(0, stuff);
        //assertThat(inventory.getStackInSlot(0), is(stuff));
    }
}
