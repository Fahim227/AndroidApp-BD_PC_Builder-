package com.example.pcbuilder.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static String BaseUrl = "http://192.168.0.106:8080/bdpcbuilderapi/";
    public static String ip = "http://192.168.0.106:8080/";
    private static ApiClient mInstance;

    private static Retrofit retrofit;

    private ApiClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public static synchronized ApiClient getInstance(){
        if(mInstance == null){

            mInstance = new ApiClient();

        }
        return mInstance;
    }
    public  JsonPlaceHolderApi getApi(){
        return retrofit.create(JsonPlaceHolderApi.class);
    }

}


