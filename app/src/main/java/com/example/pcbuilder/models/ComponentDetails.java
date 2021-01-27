package com.example.pcbuilder.models;

import java.util.List;

public class ComponentDetails
{
    public String img;
    public String name;
    public String price;
    public List<String> features;
    public String description;

    public ComponentDetails(String img, String name, String price, List<String> features, String description) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.features = features;
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
