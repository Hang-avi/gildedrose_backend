package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.model.Item;
import com.perficient.praxis.gildedrose.model.ItemAged;
import com.perficient.praxis.gildedrose.model.ItemNormal;
import com.perficient.praxis.gildedrose.model.ItemTicket;
import com.perficient.praxis.gildedrose.repository.ItemRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;


    @Test
    public void testGetItemByIdWhenItemWasNotFound(){

        when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                itemService.findById(0));
    }

    @Test
    public void testGetItemByIdSuccess(){

        var item = new ItemNormal(0, "Oreo", 10, 30);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));

        Item itemFound = itemService.findById(0);
        assertEquals(item, itemFound);
    }

    @Test
    /**
     * GIVEN a valid normal type item in the database
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * both will be decreased by 1
     */
    public void testUpdateQualityOfNormalTypeItem(){

        var item = new ItemNormal(0, "Oreo", 10, 30);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Oreo", itemsUpdated.get(0).name);
        assertEquals(9, itemsUpdated.get(0).sellIn);
        assertEquals(29, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemNormal"));
        verify(itemRepository,times(1)).save(any());
    }


    @Test
    /**
     * GIVEN a valid aged type item in the database
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * sellin decrease by 1 and quality increases by 1
     * if sellin is less than 10, quaility increases by 1
     */
    public void testUpdateQualityOfAgedTypeItem(){
        var item = new ItemAged( 0, "Wine", 100, 30);

        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Wine", itemsUpdated.get(0).name);
        assertEquals(99, itemsUpdated.get(0).sellIn);
        assertEquals(31, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemAged"));
        verify(itemRepository,times(1)).save(any());
    }


    @Test
    /**
     * GIVEN a valid ticket type item in the database
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * sellin decrease by 1 and quality increases by 1
     * if sellin is (6,10), quality increases by 1
     */
    public void testUpdateQualityOfTicketsTypeItemBetween6And10Days(){
        var item = new ItemTicket( 0, "Bullfighting", 9, 45);

        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Bullfighting", itemsUpdated.get(0).name);
        assertEquals(8, itemsUpdated.get(0).sellIn);
        assertEquals(47, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemTicket"));
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    /**
     * GIVEN a valid ticket type item in the database
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * sellin decrease by 1 and quality increases by 1
     * if sellin is (0,5), quality increases by 2
     * if sellin is 0, then quality drops to 0
     */
    public void testUpdateQualityOfTicketsTypeItemBetween0And5Days(){
        var item = new ItemTicket( 0, "Jamming", 4, 2);

        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Jamming", itemsUpdated.get(0).name);
        assertEquals(3, itemsUpdated.get(0).sellIn);
        assertEquals(5, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemTicket"));
        verify(itemRepository,times(1)).save(any());
    }


    @Test
    /**
     * GIVEN a valid normal type item in the database with sellin less than 0
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * sellin decrease by 1 and quality decreases by 2
     */
    public void testUpdateQualityOfNormalTypeItemWhenSellingLessThan0(){
        var item = new ItemNormal( 0, "Apple", -1, 5);

        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Apple", itemsUpdated.get(0).name);
        assertEquals(-2, itemsUpdated.get(0).sellIn);
        assertEquals(3, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemNormal"));
        verify(itemRepository,times(1)).save(any());
    }


    @Test
    /**
     * GIVEN a valid ticket type item in the database with sellin less than 0
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * sellin decrease by 1 and quality drops to 0
     */
    public void testUpdateQualityOfTicketsTypeItemWhenSellingLessThan0(){
        var item = new ItemTicket( 0, "Residente´s concert", -5, 40);

        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Residente´s concert", itemsUpdated.get(0).name);
        assertEquals(-6, itemsUpdated.get(0).sellIn);
        assertEquals(0, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemTicket"));;
        verify(itemRepository,times(1)).save(any());
    }


    @Test
    /**
     * GIVEN a valid normal type item in the database with sellin less than 0
     * WHEN updateQuality method is called
     * THEN the service should update the quality and sellIn values,
     * sellin decrease by 1 and quality increases by 2
     */
    public void testUpdateQualityOfAgedTypeItemWhenSellingLessThan0(){
        var item = new ItemAged( 0, "Red Ron", -40, 41);

        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Red Ron", itemsUpdated.get(0).name);
        assertEquals(-41, itemsUpdated.get(0).sellIn);
        assertEquals(43, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemAged"));
        verify(itemRepository,times(1)).save(any());
    }
    @Test
/**
 * GIVEN a valid item
 * WHEN createItem method is called
 * THEN the item should be saved in the database,
 */
    public void TestCreateItem(){

        var item=new Item( 0, "Red Ron", -40, 41, Item.Type.NORMAL);

        when(itemRepository.save(item)).thenReturn(item);
        assertEquals(item,itemService.createItem(item));
        verify(itemRepository,times(1)).save(any());
    }
    @Test
/**
 * GIVEN a list of items
 * WHEN listItems method is called
 * THEN a list of all items in the database should appear,
 */
    public void testListItems(){
        var item=new Item( 0, "Red Ron", -40, 41, Item.Type.NORMAL);
        when(itemRepository.findAll()).thenReturn(List.of(item));
        List<Item> itemsUpdated = itemService.listItems();
        assertEquals(item, itemsUpdated.get(0));
    }

    @Test
    /** GIVEN an item that is saved in the database
     * WHEN updateItem method is called
     * THEN the item should have the parameters of the new one
     */
    public void testUpdateItemWhenItExists(){
        Item newVersionItem = new ItemNormal( 0, "Bag of Arepas", 5, 49);

        when(itemRepository.findAll()).thenReturn(List.of(newVersionItem));
        when(itemRepository.existsById(0)).thenReturn(true);

        itemService.updateItem(0, newVersionItem);

        List<Item> itemsUpdated = itemService.updateQuality();

        assertEquals(0, itemsUpdated.get(0).getId());
        assertEquals("Bag of Arepas", itemsUpdated.get(0).name);
        assertEquals(4, itemsUpdated.get(0).sellIn);
        assertEquals(48, itemsUpdated.get(0).quality);
        assertTrue(itemsUpdated.get(0).getClass().getSimpleName().equals("ItemNormal"));
        verify(itemRepository,times(2)).save(any());
    }

    @Test
    /** GIVEN an item that is not saved in the database
     * WHEN updateItem method is called
     * THEN an ResourceNotFoundException should be thrown
     */
    public void testUpdateItemWhenItDoesNotExists(){
        Item newVersionItem = new ItemNormal( 0, "Bag of Arepas", 5, 49);

        when(itemRepository.findAll()).thenReturn(List.of(newVersionItem));
        when(itemRepository.existsById(0)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () ->
                itemService.updateItem(0, newVersionItem));
    }

}