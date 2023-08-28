package com.JSONpostroute.controllers;

import com.JSONpostroute.models.RequestJSON;
import com.JSONpostroute.models.ResponseJSON;
import com.JSONpostroute.services.JSONServices;
import com.JSONpostroute.validations.ValidationFunctions;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    @PostMapping("/process-orders")
    public ResponseEntity<JSONObject> processJson(@RequestBody Map<String, Object> payload){
        String[] reqStringArray = JSONServices.createStringArray(payload);

        if(!ValidationFunctions.reqHasOrdersValidation(reqStringArray[0])){
            JSONObject invalidReq = new JSONObject();
            invalidReq.put("message", "Request unable to be processed");
            return new ResponseEntity<>(invalidReq, HttpStatus.BAD_REQUEST);
        };

        List<RequestJSON> requestJson = JSONServices.makeList(reqStringArray[1]);

        ResponseJSON responseJSON = JSONServices.createResponseJsonService(requestJson);

        return new ResponseEntity<>(responseJSON.createJson(), HttpStatus.CREATED);
    }
}
