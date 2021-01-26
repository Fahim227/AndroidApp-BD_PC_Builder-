package com.example.pcbuilder.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String BaseUrl = "http://localhost:8000/bdpcbuilderapi/"; //
    private static ApiClient mInstance;

    private static Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
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


