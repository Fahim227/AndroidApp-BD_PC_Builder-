package com.example.pcbuilder.models;

import java.util.List;

public class ComponentsList {
    public List<Components> components;

    public List<Components> getComponents() {
        return components;
    }

    public void setBrands(List<Components> components) {
        this.components = components;
    }

    public ComponentsList(List<Components> components) {
        this.components = components;
    }
}
