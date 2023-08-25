package com.JSONpostroute.functions.orders;

import com.JSONpostroute.models.RequestJSON;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFunctions {
    public static int totalOrders(List<RequestJSON> orderList){
        int total_orders = 0;
        for (int i = 0; i < orderList.size(); i++) {
            total_orders += orderList.get(i).getQuantity();
        }
        return total_orders;
    }

    public static double totalOrderValue(List<RequestJSON> list){
        double total_order_value = 0;
        for (int i = 0; i < list.size(); i++) {
            total_order_value += (list.get(i).getUnit_price() * list.get(i).getQuantity());
        }
        return total_order_value;
    }
}
