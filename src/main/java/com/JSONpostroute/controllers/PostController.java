package com.JSONpostroute.controllers;

import com.JSONpostroute.models.RequestJSON;
import com.JSONpostroute.functions.utils.GeneralFunctions;
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
import java.util.*;

@RestController
public class PostController {
    @PostMapping("/process-orders")
    public ResponseEntity<JSONObject> processJson(@RequestBody Map<String, Object> payload){
        String[] reqStringArray = new JSONObject(payload).toString().split(":",2);

        if(!reqIsValid(reqStringArray[0])){
            JSONObject invalidReq = new JSONObject();
            invalidReq.put("message", "Request unable to be processed");
            return new ResponseEntity<>(invalidReq, HttpStatus.BAD_REQUEST);
        };

        List<RequestJSON> requestJson = makeList(reqStringArray[1]);

        int total_orders = totalOrders(requestJson);
        double total_order_value = totalOrderValue(requestJson);
        int sum_digits = GeneralFunctions.sumDigits(total_orders);

        JSONObject response = createJson(sum_digits, total_orders, total_order_value);

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

    private boolean reqIsValid(String testString){
        testString.toLowerCase();
        if(testString.charAt(2) == 'o' && testString.charAt(3) == 'r' && testString.charAt(4) == 'd' && testString.charAt(5) == 'e' && testString.charAt(6) == 'r' && testString.charAt(7) == 's'){
            return true;
        }
        return false;
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
            double valueAddend = (list.get(i).getUnit_price() * list.get(i).getQuantity());
            total_order_value += valueAddend;
        }
        return total_order_value;
    }

    private JSONObject createJson(int sumDigits, int totalOrders, double totalOrderValue){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sum_digits", sumDigits);
        jsonObject.put("total_orders", totalOrders);
        jsonObject.put("total_order_value", totalOrderValue);
        return jsonObject;
    }
}
