package com.JSONpostroute.model;

//That class is used for the JSON to be mapped against in order for the data to be readable in Java
public class RequestJSON {
    private String product;
    private int quantity;
    private double unit_price;

    public RequestJSON() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}
