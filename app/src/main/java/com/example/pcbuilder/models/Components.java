package com.example.pcbuilder.models;

public class Components {
    private String componentName;
    private String name;
    private String link;

    public Components(String name, String link) {
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

    public Components(String componentName) {
        this.componentName = componentName;
    }

    public String getBrandsName() {
        return componentName;
    }

    public void setBrandsName(String brandsName) {
        this.componentName = brandsName;
    }
}
