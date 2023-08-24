package com.JSONpostroute.functions.orders;

import com.JSONpostroute.models.RequestJSON;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFunctions {
    public static int totalOrders(List<RequestJSON> orderList){
        int total_orders = 0;
        for (int i = 0; i < orderList.size(); i++) {
            int orderAddend = orderList.get(i).getQuantity();
            total_orders += orderAddend;
        }
        return total_orders;
    }
}
