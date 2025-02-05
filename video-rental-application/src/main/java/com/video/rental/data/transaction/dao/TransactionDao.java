package com.video.rental.data.transaction.dao;

import com.video.rental.app.model.customer.Customer;

import java.util.List;
import java.util.Map;

public interface TransactionDao {
    boolean rent(Customer customer, List<String> itemList);

    Map<String, String> getItemCount(List<String> itemIds);
}
