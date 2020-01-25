package com.example.ecom.loginActivity.apiInterface;

import com.example.ecom.loginActivity.modules.AccessTokenDTO;
import com.example.ecom.loginActivity.modules.LoginResponse;
import com.example.ecom.loginActivity.modules.login.LoginRequest;
import com.example.ecom.loginActivity.modules.signup.SignUpRequest;
import com.example.ecom.loginActivity.modules.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginInterface {

    @POST("/login/login/googlelogin")
    Call<LoginResponse> googleLogIn(@Body AccessTokenDTO accessTokenDTO);

    @POST("/login/customer")
    Call<SignUpResponse> register(@Body SignUpRequest signUpRequest);

    @POST("/login/login")
    Call<LoginResponse> customLogin(@Body LoginRequest loginRequest);

    @POST("/login/guest")
    Call<LoginResponse> guestLogin(@Query("type") String type);
}
