package com.JSONpostroute.validations;

public class ValidationFunctions {
    public static boolean reqHasOrdersValidation(String testString){
        if(testString.contains("orders")){
            return true;
        }
        return false;
    }
}
