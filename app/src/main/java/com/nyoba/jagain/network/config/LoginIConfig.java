package com.nyoba.jagain.network.config;

import com.nyoba.jagain.DataClass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginIConfig {

    @FormUrlEncoded
    @POST("login.php")
    Call<DataClass> login
            (
                @Field("email") String email,
                @Field("password") String password
            );

}
