package com.example.ecom.cartActivity.apiInterface;

import androidx.annotation.NonNull;

//import com.example.ecom.cartActivity.models.CartProduct;
import com.example.ecom.cartActivity.models.CartProductRevised;
import com.example.ecom.cartActivity.models.removeFromCart.RemoveFromCart;
import com.example.ecom.cartActivity.models.removeRequest.RemoveFromCartRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface CartInterface {

    @GET("/cartandorder/cart/{userId}")
    Call<CartProductRevised> getFromCart(@Path("userId") @NonNull String userId);

    //@DELETE("cart/item")
    @HTTP(method = "DELETE", path = "/cartandorder/cart/reduceitem", hasBody = true)
    Call<RemoveFromCart> removeFromCart(@Body RemoveFromCartRequest removeFromCartRequest);
}
