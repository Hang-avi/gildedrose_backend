package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.model.*;
import com.perficient.praxis.gildedrose.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    Item[] items;

    public ItemService(ItemRepository itemRepository, Item[] items) {
        this.itemRepository = itemRepository;
        this.items = items;
    }

    public List<Item> updateQuality() {
        var itemsList = itemRepository.findAll();
        var items = itemsList.toArray(new Item[itemsList.size()]);

        for (int i = 0; i < items.length; i++) {
            items[i].updateQuality();

            itemRepository.save(items[i]);
        }
        return Arrays.asList(items);
    }

    public ItemNormal createItem(ItemNormal item) {
        return itemRepository.save(item);
    }

    public ItemTicket createItem(ItemTicket item) {
        return itemRepository.save(item);
    }

    public ItemAged createItem(ItemAged item) {
        return itemRepository.save(item);
    }

    public ItemLegendary createItem(ItemLegendary item) {
        return itemRepository.save(item);
    }

    public Item createItem(Item item) {
        if (item.type == Item.Type.NORMAL){
            ItemNormal item1 = new ItemNormal(item.getId(), item.name, item.sellIn, item.quality);
            itemRepository.save(item1);
        } else if (item.type == Item.Type.AGED){
            itemRepository.save(new ItemAged(item.getId(), item.name, item.sellIn, item.quality));
        } else if (item.type == Item.Type.LEGENDARY){
            itemRepository.save(new ItemLegendary(item.getId(), item.name, item.sellIn, item.quality));
        } else{
            itemRepository.save(new ItemTicket(item.getId(), item.name, item.sellIn, item.quality));
        }
        return item;
    }

    public Item updateItem(int id, Item item) {
        if (!(itemRepository.existsById(id))) {
            throw new ResourceNotFoundException("The Item to get updated must exist");
        }
        return itemRepository.save(new Item(id, item.name, item.sellIn, item.quality, item.type));
    }

    public List<Item> listItems(){
        return itemRepository.findAll();
    }

    public Item findById(int id) {
        return itemRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(""));
    }

    public Item deleteItem(int id) {
        try {
            Item item = findById(id);
            itemRepository.delete(item);
            return item;
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }

}
