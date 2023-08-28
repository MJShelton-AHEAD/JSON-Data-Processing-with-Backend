package com.JSONpostroute.services;

import com.JSONpostroute.models.RequestJSON;
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
}
