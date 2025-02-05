package com.video.rental.data.transaction.dao.impl;

import com.video.rental.app.model.customer.Customer;
import com.video.rental.data.connection.ConnectionHelper;
import com.video.rental.data.transaction.dao.TransactionDao;
import oracle.sql.ArrayDescriptor;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.video.rental.data.utils.QueryConstants.GET_TRANSACTION_ITEMS_COUNT;

public class TransactionDaoImpl implements TransactionDao {
    Connection connection = ConnectionHelper.getConnection();

    @Override
    public Map<String, String> getItemCount(List<String> itemIds) {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_TRANSACTION_ITEMS_COUNT);
            ResultSet rs = statement.executeQuery();

            Map<String, String> itemCount = new HashMap<>();
            while(rs.next()) {
                if(itemIds.contains(rs.getString("id"))) {
                    itemCount.put(rs.getString("id"), rs.getString("copies"));
                }
            }
            return itemCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean rent(Customer customer, List<String> itemIds) {
        try {
            //create an array to pass to the Oracle uiser defined function
            java.sql.Array array = new oracle.sql.ARRAY(ArrayDescriptor.createDescriptor("ITEMS", connection), connection, itemIds.toArray());

            //A callable statement is a prepared statement for functions and procedures
            CallableStatement statement = connection.prepareCall("{call rent(?, ?, ?)}");

            //pass parameters to the function
            statement.setString(1, customer.getId());
            statement.setArray(2, array);
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            int result = statement.executeUpdate();
            return result == 1? true: false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String buildParameters(List<String> ids) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        for(String id: ids) {
            sb.append("?, ");
        }

        //delete the last character added which is " "
        String params = sb.deleteCharAt(sb.length()-1).toString();

        //delete the second to the last character added which is ","
        params = sb.deleteCharAt(sb.length()-1).toString();
        params = params + ")";

        return params;
    }
}
