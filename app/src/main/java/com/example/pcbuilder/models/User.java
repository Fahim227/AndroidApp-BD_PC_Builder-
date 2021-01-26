package com.example.pcbuilder.models;

public class User {
    private String username;
    private String email;
    private String password;
    private Integer img;

    public User(String username, String email, String password, Integer img) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.img = img;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
