package com.example.pcbuilder.models;

public class CartModel {
    private String components;
    private String quantity;
    private String price;

    public CartModel(String components, String quantity, String price) {
        this.components = components;
        this.quantity = quantity;
        this.price = price;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
