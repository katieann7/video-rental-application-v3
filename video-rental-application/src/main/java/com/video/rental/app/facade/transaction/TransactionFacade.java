package com.video.rental.app.facade.transaction;

import com.video.rental.app.model.customer.Customer;

import java.util.List;

public interface TransactionFacade {
    boolean rentItems(Customer customer, List<String> itemList);

    boolean returnItems(Customer customer);
}
