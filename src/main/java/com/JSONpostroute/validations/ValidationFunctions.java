package com.JSONpostroute.validations;

public class ValidationFunctions {
    public static boolean reqHasOrdersValidation(String testString){
        testString.toLowerCase();
        if(testString.contains("orders")){
            return true;
        }
        return false;
    }
}
