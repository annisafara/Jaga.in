package com.nyoba.jagain.network.config;

import com.nyoba.jagain.DataClass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UpdateConfig {
    @FormUrlEncoded
    @POST("update.php")
    Call<DataClass> update
            (
                @Field("id") String id,
                @Field("nama") String nama,
                @Field("alamat") String alamat,
                @Field("kota") String kota,
                @Field("tanggallahir") String tanggallahir,
                @Field("jeniskelamin") String jeniskelamin,
                @Field("golongandarah") String golongandarah
            );
}
