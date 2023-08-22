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
import java.util.*;

@RestController
public class PostController {
    @PostMapping("/process-orders")
    public ResponseEntity<JSONObject> processJson(@RequestBody Map<String, Object> payload){
        //Turn payload into readable string for Java to parse
        String[] reqStringArray = new JSONObject(payload).toString().split(":",2);

        //Validates that the JSON contains orders
        if(!reqIsValid(reqStringArray[0])){
            JSONObject invalidReq = new JSONObject();
            invalidReq.put("message", "Request unable to be processed");
            return new ResponseEntity<>(invalidReq, HttpStatus.BAD_REQUEST);
        };

        //Turn the payload string into a list of RequestJSON classes
        List<RequestJSON> requestJson = makeList(reqStringArray[1]);

        //Create the data that will be returned
        int total_orders = totalOrders(requestJson);
        double total_order_value = totalOrderValue(requestJson);
        int sum_digits = sumDigits(total_orders);

        //Turn data into a JSON object
        JSONObject response = createJson(sum_digits, total_orders, total_order_value);

        //Return the response with the HTTP status
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //This function turns the payload string into a list of RequestJSON classes, or throws errors if data isn't given correctly
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

    //This function checks to see if the first key is "orders"
    private boolean reqIsValid(String testString){
        testString.toLowerCase();
        if(testString.charAt(2) == 'o' && testString.charAt(3) == 'r' && testString.charAt(4) == 'd' && testString.charAt(5) == 'e' && testString.charAt(6) == 'r' && testString.charAt(7) == 's'){
            return true;
        }
        return false;
    }

    //This function finds the sum of the quantity in all orders
    private int totalOrders(List<RequestJSON> list){
        int total_orders = 0;
        for (int i = 0; i < list.size(); i++) {
            int orderAddend = list.get(i).getQuantity();
            total_orders += orderAddend;
        }
        return total_orders;
    }

    //THis function finds the total value of all orders based on the cost of each product
    private int totalOrderValue(List<RequestJSON> list){
        int total_order_value = 0;
        for (int i = 0; i < list.size(); i++) {
            double valueAddend = (list.get(i).getUnit_price() * list.get(i).getQuantity());
            total_order_value += valueAddend;
        }
        return total_order_value;
    }

    //This function adds together the sum of all the digits found in total orders
    private int sumDigits(int totalOrders){
        int result = 0;
        do{
            int addend = totalOrders % 10;
            result += addend;
            totalOrders /= 10;
        }while(totalOrders%10 > 0);
        return result;
    }

    //This function creates a JSON object that can be returned to the client
    private JSONObject createJson(int sumDigits, int totalOrders, double totalOrderValue){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sum_digits", sumDigits);
        jsonObject.put("total_orders", totalOrders);
        jsonObject.put("total_order_value", totalOrderValue);
        return jsonObject;
    }
}
