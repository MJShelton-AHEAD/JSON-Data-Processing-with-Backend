package com.JSONpostroute.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PostController {
    @PostMapping("/process-orders")
    public ResponseEntity<ResponseData> processJson(@RequestBody Map<String, Object> payload){
        ResponseData test = new ResponseData("New test");
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
