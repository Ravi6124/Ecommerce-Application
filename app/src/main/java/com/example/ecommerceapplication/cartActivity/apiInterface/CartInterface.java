package com.example.ecommerceapplication.cartActivity.apiInterface;

import androidx.annotation.NonNull;

import com.example.ecommerceapplication.cartActivity.models.CartProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CartInterface {

    @GET("/cart")
    Call<List<CartProduct>> getFromCart(@Query("userId") @NonNull String userId);
}
