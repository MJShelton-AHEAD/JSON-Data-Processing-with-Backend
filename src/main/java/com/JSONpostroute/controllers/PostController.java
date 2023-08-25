package com.JSONpostroute.controllers;

import com.JSONpostroute.functions.orders.OrderFunctions;
import com.JSONpostroute.functions.utils.GeneralFunctions;
import com.JSONpostroute.models.RequestJSON;
import com.JSONpostroute.validations.ValidationFunctions;
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
        String[] reqStringArray = new JSONObject(payload).toString().split(":",2);

        if(!ValidationFunctions.reqHasOrdersValidation(reqStringArray[0])){
            JSONObject invalidReq = new JSONObject();
            invalidReq.put("message", "Request unable to be processed");
            return new ResponseEntity<>(invalidReq, HttpStatus.BAD_REQUEST);
        };

        List<RequestJSON> requestJson = makeList(reqStringArray[1]);

        int total_orders = OrderFunctions.totalOrders(requestJson);
        double total_order_value = OrderFunctions.totalOrderValue(requestJson);
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

    private JSONObject createJson(int sumDigits, int totalOrders, double totalOrderValue){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sum_digits", sumDigits);
        jsonObject.put("total_orders", totalOrders);
        jsonObject.put("total_order_value", totalOrderValue);
        return jsonObject;
    }
}
