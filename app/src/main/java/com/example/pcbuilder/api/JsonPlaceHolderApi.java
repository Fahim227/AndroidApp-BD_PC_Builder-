package com.example.pcbuilder.api;

import com.example.pcbuilder.models.ProductApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("getproducts/")
    Call<List<ProductApi>> getHomeProducts();

}
