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
import java.util.Map;

@RestController
public class PostController {
    @PostMapping("/process-orders")
    public ResponseEntity<ResponseData> processJson(@RequestBody Map<String, Object> payload){
        ObjectMapper mapper = new ObjectMapper();
        JSONObject reqObj = new JSONObject(payload);
        String reqString = reqObj.toString();

        try {

            RequestJSON requestJson = mapper.readValue(reqString, RequestJSON.class);
            System.out.println(requestJson.getProduct());
            System.out.println(requestJson.getQuantity());
            System.out.println(requestJson.getUnit_price());

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



        ResponseData test = new ResponseData("Test response");
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
}
