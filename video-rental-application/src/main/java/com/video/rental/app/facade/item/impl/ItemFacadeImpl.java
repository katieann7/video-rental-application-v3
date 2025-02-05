package com.video.rental.app.facade.item.impl;

import com.video.rental.app.facade.item.ItemFacade;
import com.video.rental.app.model.item.Item;
import com.video.rental.data.item.dao.ItemDao;
import com.video.rental.data.item.dao.impl.ItemDaoImpl;

import java.util.List;

public class ItemFacadeImpl implements ItemFacade {
    ItemDao itemDao = new ItemDaoImpl();

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public Item getItemById(String id) {
        return itemDao.getItemById(id);
    }

    @Override
    public List<Item> getItemsByIdList(List<String> ids) {
        return itemDao.getItemsByIdList(ids);
    }

    @Override
    public boolean addItem(Item item) {
        boolean result = false;
        try {
            Item targetItem = getItemById(item.getId());
            if(targetItem != null) {
                throw new Exception("Item to add already exists. ");
            }
            result = itemDao.addItem(item);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateItem(Item item) {
        boolean result = false;
        try {
            Item targetItem = itemDao.getItemById(item.getId());
            if(targetItem == null) {
                throw new Exception("Item to update not found. ");
            }
            result = itemDao.updateItem(item);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteItemById(String id) {
        boolean result = false;
        try {
            Item targetItem = new Item();
            targetItem = itemDao.getItemById(id);
            if(targetItem == null) {
                throw new Exception("Item to delete not found. ");
            }
            result = itemDao.deleteItemById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
