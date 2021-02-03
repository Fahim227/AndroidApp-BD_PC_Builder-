package com.example.pcbuilder.models;

public class Brands {
    private String BrandsName;
    private String name;
    private String link;

    public Brands(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Brands(String BrandsName) {
        this.BrandsName = BrandsName;
    }

    public String getBrandsName() {
        return BrandsName;
    }

    public void setBrandsName(String brandsName) {
        this.BrandsName = brandsName;
    }
}
