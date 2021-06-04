package com.example.pcbuilder.api;

import com.example.pcbuilder.models.BrandsList;
import com.example.pcbuilder.models.ComponentDetails;
import com.example.pcbuilder.models.ComponentsList;
import com.example.pcbuilder.models.ProductApi;
import com.example.pcbuilder.models.Response;
import com.example.pcbuilder.models.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @FormUrlEncoded
    @POST
    Call<List<ProductApi>> getHomeProducts(
            @Url String url,
            @Field("comUrl") String comName
    );


    @FormUrlEncoded
    @POST
    Call<BrandsList> getBrandNames(
            @Url String url,
            @Field("comUrl") String comUrl
    );

    @FormUrlEncoded
    @POST
    Call<List<ProductApi>> getComponentsByBrands(
            @Url String url,
            @Field("brandurl") String brandurl
    );

    @FormUrlEncoded
    @POST
    Call<ComponentDetails> getCompopnentsDetails(
            @Url String localUrl,
            @Field("url") String url
    );

    @POST
    Call<ComponentsList> getComponentsName(
           @Url String url
    );

    @FormUrlEncoded
    @POST("addtocart/")
    Call<Response> addToCart(
            @Field("userid") int userid,
            @Field("produrl") String producturl,
            @Field("shopName") String shopName,
            @Field("quantity") int qnty
    );

    @FormUrlEncoded
    @POST("get_cart_components/")
    Call<List<ComponentDetails>> getCartItems(
            @Field("userID") int userid
    );

    @GET("allshopsapi/")
    Call<List<Shop>> getAllShops();

}
