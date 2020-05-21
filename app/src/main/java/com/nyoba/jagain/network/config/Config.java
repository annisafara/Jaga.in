package com.nyoba.jagain.network.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    public static final String BASE_URL = "https://jagainhosting.000webhostapp.com/"; // Your Local IP Address or Localhost (http://10.0.2.2/)
    //public static final String BASE_URL = "https:10.0.2.2/rumahsakit/";
    //public static final String API_URL = BASE_URL + "/rumahsakit";

    //public static final String API_LOGIN = API_URL + "/login.php";
    //public static final String API_REGISTER = API_URL + "/register.php";

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
