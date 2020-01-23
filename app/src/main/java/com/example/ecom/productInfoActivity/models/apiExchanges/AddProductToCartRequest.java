package com.example.ecom.productInfoActivity.models.apiExchanges;

import com.example.ecom.productInfoActivity.models.CartProduct;
import com.google.gson.annotations.SerializedName;

public class AddProductToCartRequest {
    @SerializedName("userId")
    String userId;

    @SerializedName("cartProduct")
    CartProduct cartProduct;

    public AddProductToCartRequest(String userId, CartProduct cartProduct) {
        this.userId = userId;
        this.cartProduct = cartProduct;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CartProduct getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(CartProduct cartProduct) {
        this.cartProduct = cartProduct;
    }
}
