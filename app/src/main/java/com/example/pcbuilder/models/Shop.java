package com.example.pcbuilder.models;

public class Shop {
    public String shop_name;
    public String shop_address;
    public String shop_img;
    public int shop_id;

    public Shop(String shop_name, String shop_address, String shop_img, int shop_id) {
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.shop_img = shop_img;
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}
