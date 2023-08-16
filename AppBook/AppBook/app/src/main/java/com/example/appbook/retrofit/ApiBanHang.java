package com.example.appbook.retrofit;

import android.database.Observable;

import com.example.appbook.model.UserModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiBanHang {
    @POST("signup.php")
    @FormUrlEncoded
    Observable<UserModel> signup(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username,
            @Field("sdt") String sdt

    );

    @POST("login.php")
    @FormUrlEncoded
    Observable<UserModel> login(
            @Field("email") String email,
            @Field("password") String password

    );
}
