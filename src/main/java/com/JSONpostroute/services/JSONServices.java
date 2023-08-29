package com.JSONpostroute.services;

import com.JSONpostroute.functions.orders.OrderFunctions;
import com.JSONpostroute.functions.utils.GeneralFunctions;
import com.JSONpostroute.models.RequestJSON;
import com.JSONpostroute.models.ResponseJSON;
import com.JSONpostroute.validations.ValidationFunctions;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JSONServices {
    public static String[] createStringArray(Map<String, Object> payload){
        return new JSONObject(payload).toString().split(":",2);
    }

    public static List<RequestJSON> makeList(String data){
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

    public static ResponseJSON createResponseJsonService(List<RequestJSON> requestJson){
        ValidationFunctions.reqHasCorrectData(requestJson);
        int total_orders = OrderFunctions.totalOrders(requestJson);
        double total_order_value = OrderFunctions.totalOrderValue(requestJson);
        int sum_digits = GeneralFunctions.sumDigits(total_orders);

        return new ResponseJSON(sum_digits, total_orders, total_order_value);
    }
}
