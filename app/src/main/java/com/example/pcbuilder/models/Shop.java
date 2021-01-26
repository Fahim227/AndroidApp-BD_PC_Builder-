package com.example.pcbuilder.models;

public class Shop {

    public String title;
    public Integer img;

    public Shop(String title, Integer img) {
        this.title = title;
        this.img = img;
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
}
