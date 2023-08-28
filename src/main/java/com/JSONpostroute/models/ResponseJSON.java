package com.JSONpostroute.models;

import org.json.simple.JSONObject;

public class ResponseJSON {
    private int sum_digits;
    private int total_orders;
    private double total_order_value;

    public ResponseJSON(int sum_digits, int total_orders, double total_order_value) {
        this.sum_digits = sum_digits;
        this.total_orders = total_orders;
        this.total_order_value = total_order_value;
    }

    public JSONObject createJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sum_digits", this.sum_digits);
        jsonObject.put("total_orders", this.total_orders);
        jsonObject.put("total_order_value", this.total_order_value);
        return jsonObject;
    }

}
