package com.nyoba.jagain.network.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    public static final String BASE_URL = "https://jagainhosting.000webhostapp.com/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if (retrofit==null){


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
