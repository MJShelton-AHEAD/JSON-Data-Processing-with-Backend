package com.JSONpostroute.validations;

import org.springframework.stereotype.Service;

@Service
public class ValidationFunctions {
    public static boolean reqHasOrdersValidation(String testString){
        return testString.contains("orders");
    }
}
