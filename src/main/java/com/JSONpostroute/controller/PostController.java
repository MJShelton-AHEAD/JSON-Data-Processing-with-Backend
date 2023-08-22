package com.JSONpostroute.controller;

import com.JSONpostroute.model.RequestJSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    @PostMapping("/process-orders")
    public ResponseEntity<JSONObject> processJson(@RequestBody Map<String, Object> payload){
        String[] reqObj = new JSONObject(payload).toString().split(":",2);

        List<RequestJSON> requestJson = makeList(reqObj[1]);

        int total_orders = totalOrders(requestJson);
        double total_order_value = totalOrderValue(requestJson);
        int sum_digits = sumDigits(total_orders);

        JSONObject response = new JSONObject();
        response.put("sum_digits", sum_digits);
        response.put("total_orders", total_orders);
        response.put("total_order_value", total_order_value);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private List<RequestJSON> makeList(String data){
        ObjectMapper mapper = new ObjectMapper();
        List<RequestJSON> returnList = new ArrayList<>();
        try {
            returnList = Arrays.asList(mapper.readValue(data, RequestJSON[].class));
        } catch (
                JsonGenerationException e) {
            e.printStackTrace();
        } catch (
                JsonMappingException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return returnList;
    }

    private int totalOrders(List<RequestJSON> list){
        int total_orders = 0;
        for (int i = 0; i < list.size(); i++) {
            int orderAddend = list.get(i).getQuantity();
            total_orders += orderAddend;
        }
        return total_orders;
    }

    private int totalOrderValue(List<RequestJSON> list){
        int total_order_value = 0;
        for (int i = 0; i < list.size(); i++) {
            double priceAddend = (list.get(i).getUnit_price() * list.get(i).getQuantity());
            total_order_value += priceAddend;
        }
        return total_order_value;
    }

    private int sumDigits(int totalOrders){
        int result = 0;
        do{
            int addend = totalOrders % 10;
            result += addend;
            totalOrders /= 10;
        }while(totalOrders%10 > 0);
        return result;
    }
}
