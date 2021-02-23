package com.example.pcbuilder.View;

import com.example.pcbuilder.models.Shop;

import java.util.List;

public interface ShopView {
    void showLoading();
    void hideLoading();
    void getShops(List<Shop> shopList);
}
