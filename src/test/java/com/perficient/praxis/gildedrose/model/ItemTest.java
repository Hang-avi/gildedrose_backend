package com.perficient.praxis.gildedrose.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemTest {
    @Test
    /**
     * GIVEN an item
     * WHEN toString method is called
     * THEN a string with the item parameters should be return
     */
    public void testToString(){
        Item item = new ItemNormal( 0, "Chocorramo", 30, 40);

        assertEquals("0, Chocorramo, 30, 40",item.toString());
    }

    @Test
    /**
     * GIVEN an item
     * WHEN setId method is called
     * THEN the item should change it´s id
     */
    public void testSetId(){
        Item item = new ItemNormal( 0, "Chocorramo", 30, 40);

        item.setId(20);
        assertEquals(20,item.getId());
    }

}