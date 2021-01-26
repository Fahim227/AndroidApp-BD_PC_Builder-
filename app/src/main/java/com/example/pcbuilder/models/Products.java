package com.example.pcbuilder.models;

public class Products {
    public String title;
    public Integer img;
    public String price;

    public Products(String title, Integer img, String price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
