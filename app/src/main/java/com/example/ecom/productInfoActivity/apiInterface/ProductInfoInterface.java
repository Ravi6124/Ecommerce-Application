package com.example.ecom.productInfoActivity.apiInterface;

import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartRequest;
import com.example.ecom.productInfoActivity.models.CartResponse;
import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProductInfoInterface {

    @POST("/cartapi/cart/item")
    Call<AddProductToCartResponse> addItemToCart(@Body AddProductToCartRequest addProductToCart);


}
