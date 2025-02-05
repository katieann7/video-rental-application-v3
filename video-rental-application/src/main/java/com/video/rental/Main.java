package com.video.rental;

import com.google.gson.Gson;
import com.video.rental.app.facade.customer.CustomerFacade;
import com.video.rental.app.facade.customer.impl.CustomerFacadeImpl;
import com.video.rental.app.facade.item.ItemFacade;
import com.video.rental.app.facade.item.impl.ItemFacadeImpl;
import com.video.rental.app.model.customer.Customer;
import com.video.rental.app.model.item.Item;

public class Main {

    private static Gson gson;

    private static Object obj;

    private static ItemFacade itemFacade;

    private static CustomerFacade customerFacade;

    public static void main(String[] args) {
        gson = new Gson();
        obj = null;
        itemFacade = new ItemFacadeImpl();
        customerFacade = new CustomerFacadeImpl();

        switch (args[0]) {
            case "getAllItems": {
                obj = itemFacade.getAllItems();
                break;
            }
            case "getItemById": {
                obj = itemFacade.getItemById(args[1]);
                break;
            }
            case "addItem": {
                String params = buildArgs(args);
                Item item = gson.fromJson(params, Item.class);
                obj = itemFacade.addItem(item);
                break;
            }
            case "updateItem": {
                String params = buildArgs(args);
                Item item = gson.fromJson(params, Item.class);
                obj = itemFacade.updateItem(item);
                break;
            }
            case "deleteItemById": {
                obj = itemFacade.deleteItemById(args[1]);
                break;
            }
            case "getAllCustomers": {
                obj = customerFacade.getAllCustomers();
                break;
            }
            case "getCustomerById": {
                obj = customerFacade.getCustomerById(args[1]);
                break;
            }
            case "addCustomer": {
                String params = buildArgs(args);
                Customer customer = gson.fromJson(params, Customer.class);
                obj = customerFacade.addCustomer(customer);
                break;
            }
            case "updateCustomer": {
                String params = buildArgs(args);
                Customer customer = gson.fromJson(params, Customer.class);
                obj = customerFacade.updateCustomer(customer);
                break;
            }
            case "deleteCustomerById": {
                obj = customerFacade.deleteCustomerById(args[1]);
                break;
            }
        }
        String result = gson.toJson(obj);
        System.out.println(result);
    }

    private static String buildArgs(String[] args){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<args.length; i++) {
            if(i==0) {
                continue;
            }
            sb.append(args[i] + " ");
        }
        return sb.toString();
    }

}
