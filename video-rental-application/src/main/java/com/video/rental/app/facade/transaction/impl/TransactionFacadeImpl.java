package com.video.rental.app.facade.transaction.impl;

import com.video.rental.app.facade.item.ItemFacade;
import com.video.rental.app.facade.item.impl.ItemFacadeImpl;
import com.video.rental.app.facade.transaction.TransactionFacade;
import com.video.rental.app.model.customer.Customer;
import com.video.rental.app.model.item.Item;
import com.video.rental.data.transaction.dao.TransactionDao;
import com.video.rental.data.transaction.dao.impl.TransactionDaoImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionFacadeImpl implements TransactionFacade {
    TransactionDao transactionDao = new TransactionDaoImpl();
    ItemFacade itemFacade = new ItemFacadeImpl();

    @Override
    public boolean rentItems(Customer customer, List<String> itemList) {
        try {
            //check if sufficient copies of items
            Map<String, String> itemCount = transactionDao.getItemCount(itemList);
            String firstUnavailableItem = validateItemAvailability(itemCount);
            Item unavailableItem = itemFacade.getItemById(firstUnavailableItem);
            if(firstUnavailableItem!=null) {
                System.out.println(unavailableItem.getTitle() + " has no remaining copy.");
                return false;
            }
            transactionDao.rent(customer, itemList);
            return true;
        } catch (Exception e) {}
        return false;
    }

    @Override
    public boolean returnItems(Customer customer) {
        //TODO: add functionality
        return false;
    }

    private String validateItemAvailability(Map<String, String> itemCount) {
        List<Item> itemList = itemFacade.getAllItems();
        for(Map.Entry<String, String> entry: itemCount.entrySet()) {
            Optional<Item> item = itemList.stream().filter(i -> i.getId().equals(entry.getKey())).findFirst();
            if(item == null || (item.get().getCopies() - Integer.parseInt(entry.getValue()) <= 0) ) {
                return entry.getKey();
            }
        }
        return null;
    }

}
