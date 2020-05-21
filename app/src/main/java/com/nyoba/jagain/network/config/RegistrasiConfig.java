package com.nyoba.jagain.network.config;

import com.nyoba.jagain.DataClass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistrasiConfig {

    @FormUrlEncoded
    @POST("register.php")
    Call<DataClass> registrasi
            (
                @Field("email") String email,
                @Field("nama") String nama,
                @Field("password") String password
            );

}
