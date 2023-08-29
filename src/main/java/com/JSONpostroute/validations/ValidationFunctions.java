package com.JSONpostroute.validations;

import com.JSONpostroute.models.RequestJSON;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationFunctions {
    public static boolean reqHasOrdersValidation(String testString){
        return testString.contains("orders");
    }

    public static List<RequestJSON> reqHasCorrectData(List<RequestJSON> list){
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getUnit_price() == -1){
                list.get(i).setQuantity(0);
            }
            if(list.get(i).getQuantity() == -1){
                list.get(i).setUnit_price(0);
            }
        }
        return list;
    }
}
