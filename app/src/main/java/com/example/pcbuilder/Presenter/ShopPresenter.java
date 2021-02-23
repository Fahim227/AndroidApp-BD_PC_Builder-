package com.example.pcbuilder.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.pcbuilder.View.ShopView;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.api.JsonPlaceHolderApi;
import com.example.pcbuilder.models.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopPresenter {
    List<Shop> shops;
    ShopView shopView;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    public ShopPresenter(ShopView shopView) {
        this.shopView = shopView;
    }

    public void getShops(){
        shopView.showLoading();
        Call<List<Shop>> call = ApiClient.getInstance().getApi().getAllShops();
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                shopView.hideLoading();
                if(response.isSuccessful() && response.body() != null){

                    shopView.getShops(response.body());

                }
                else{
                    Toast.makeText((Context) shopView,response.message(),Toast.LENGTH_LONG).show();
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Toast.makeText((Context) shopView,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());

            }
        });

    }
}
