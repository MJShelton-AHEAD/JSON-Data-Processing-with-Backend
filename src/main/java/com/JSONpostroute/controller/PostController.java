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
    public ResponseEntity<ResponseData> processJson(@RequestBody Map<String, Object> payload){
        ObjectMapper mapper = new ObjectMapper();
        JSONObject reqObj = new JSONObject(payload);
        String reqString = reqObj.toString();
        String[] reqStringArray = reqString.split(":",2);

        List<RequestJSON> requestJson = new ArrayList<>();

        try {
            requestJson = Arrays.asList(mapper.readValue(reqStringArray[1], RequestJSON[].class));
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

        int total_orders = 0;
        double total_order_value = 0;

        for (int i = 0; i < requestJson.size(); i++) {
            int orderAddend = requestJson.get(i).getQuantity();
            total_orders += orderAddend;
            double priceAddend = (requestJson.get(i).getUnit_price() * requestJson.get(i).getQuantity());
            total_order_value += priceAddend;
        }

        int sum_digits = sumDigits(total_orders);

        ResponseData test = new ResponseData(" sum_digits: " + sum_digits + " total orders: " + total_orders + " total price: " + total_order_value);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    public static class ResponseData {
        private String response;

        public ResponseData(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
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
