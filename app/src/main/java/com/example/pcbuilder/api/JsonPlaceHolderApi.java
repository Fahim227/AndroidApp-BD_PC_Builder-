package com.example.pcbuilder.api;

import com.example.pcbuilder.models.ComponentDetails;
import com.example.pcbuilder.models.ProductApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("getproducts/")
    Call<List<ProductApi>> getHomeProducts();

    @FormUrlEncoded
    @POST("componentdetails/")
    Call<ComponentDetails> getCompopnentsDetails(
            @Field("url") String url
    );

}
