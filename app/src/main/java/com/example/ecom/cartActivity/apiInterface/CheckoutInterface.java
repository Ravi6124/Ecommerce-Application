package com.example.ecom.cartActivity.apiInterface;

import com.example.ecom.cartActivity.models.CartProductRevised;
import com.example.ecom.cartActivity.models.checkout.CheckoutResponse;
import com.example.ecom.cartActivity.models.removeFromCart.Cart;
import com.example.ecom.productInfoActivity.models.CartProduct;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CheckoutInterface {

    @POST("/cartapi/order/place")
    Call<CheckoutResponse> checkout(@Body CartProductRevised cart);


}
