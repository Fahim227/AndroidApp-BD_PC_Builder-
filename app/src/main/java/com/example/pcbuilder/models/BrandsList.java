package com.example.pcbuilder.models;

import java.util.List;

public class BrandsList {
    public List<Brands> brands;

    public List<Brands> getBrands() {
        return brands;
    }

    public void setBrands(List<Brands> brands) {
        this.brands = brands;
    }

    public BrandsList(List<Brands> brands) {
        this.brands = brands;
    }
}

