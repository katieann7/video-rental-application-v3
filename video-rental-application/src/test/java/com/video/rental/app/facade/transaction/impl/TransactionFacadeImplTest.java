package com.video.rental.app.facade.transaction.impl;

import com.video.rental.app.facade.transaction.TransactionFacade;
import com.video.rental.app.model.customer.Customer;
import com.video.rental.app.model.item.Item;
import com.video.rental.data.transaction.dao.TransactionDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionFacadeImplTest {

    @Test
    public void testRentTransaction() {
        // Customer customer = new Customer("1", "John", null, "Doe", "USA", null);
        // List<String> itemIds = new ArrayList<>();
        // itemIds.add("1");
        // itemIds.add("2");
        //
        // TransactionFacade transactionFacade = mock(TransactionFacade.class);
        // TransactionDao transactionDao = mock(TransactionDao.class);
        // when(transactionDao.rent(customer,itemIds)).thenReturn(true);
        // when(transactionFacade.rentItems(customer, itemIds)).thenReturn(true);
        // boolean result = transactionFacade.rentItems(customer, itemIds);
        //
        // assertEquals(result, true);
        // verify(transactionDao, times(1)).getItemCount(itemIds);
    }

}