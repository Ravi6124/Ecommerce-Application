package com.example.ecom.loginActivity.apiInterface;

import com.example.ecom.loginActivity.modules.AccessTokenDTO;
import com.example.ecom.loginActivity.modules.LoginResponse;
import com.example.ecom.loginActivity.modules.signup.SignUpRequest;
import com.example.ecom.loginActivity.modules.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {

    @POST("/login/googlelogin")
    Call<LoginResponse> googleLogIn(@Body AccessTokenDTO accessTokenDTO);

    @POST("/register/custom")
    Call<SignUpResponse> register(@Body SignUpRequest signUpRequest);
}
