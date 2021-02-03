package com.example.pcbuilder.api;

import com.example.pcbuilder.models.BrandsList;
import com.example.pcbuilder.models.ComponentDetails;
import com.example.pcbuilder.models.ProductApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @FormUrlEncoded
    @POST("getproducts/")
    Call<List<ProductApi>> getHomeProducts(
            @Field("componentName") String comName
           // @Field("brandName") String brandName
    );

    @FormUrlEncoded
    @POST("getbrands/")
    Call<BrandsList> getBrandNames(
            @Field("componentName") String comName
    );

    @FormUrlEncoded
    @POST("brandscomponents/")
    Call<List<ProductApi>> getComponentsByBrands(
            @Field("brandurl") String brandurl
    );

    @FormUrlEncoded
    @POST("componentdetails/")
    Call<ComponentDetails> getCompopnentsDetails(
            @Field("url") String url
    );

}
