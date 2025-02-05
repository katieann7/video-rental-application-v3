package com.video.rental.data.item.dao;

import com.video.rental.app.model.item.Item;

import java.util.List;

public interface ItemDao {
    List<Item> getAllItems();
    Item getItemById(String id);
    List<Item> getItemsByIdList(List<String> ids);
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItemById(String id);
}
