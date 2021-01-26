package com.example.pcbuilder.models;

public class ProductApi {
    public String images;
    public String name;
    public String price;
    public String urls;

    public ProductApi(String images, String name, String price, String urls) {
        this.images = images;
        this.name = name;
        this.price = price;
        this.urls = urls;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
