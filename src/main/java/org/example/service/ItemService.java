package org.example.service;

import org.example.model.Item;
import org.example.model.Item;

import java.util.List;

public interface ItemService {
    void addItem(Item item);
    Item getItemById(Integer id);
    List<Item> getAllItems();
    void updateItem(Integer id, Item item);
    void deleteItem(Integer id);
}
