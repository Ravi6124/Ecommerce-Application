package com.example.ecom.cartActivity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartProductRevised {

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("totalAmount")
    private double totalAmount;

    @SerializedName("items")
    private List<ListCartProduct> cartProducts;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ListCartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<ListCartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @Override
    public String toString() {
        return "CartProductRevised{" +
                "customerId='" + customerId + '\'' +
                ", totalAmount=" + totalAmount +
                ", cartProducts=" + cartProducts +
                '}';
    }
}
