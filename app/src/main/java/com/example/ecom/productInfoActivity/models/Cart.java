package com.example.ecom.productInfoActivity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("customerId")
    private String CustomerId;

    @SerializedName("items")
    List<CartProduct> items;

    @SerializedName("totalAmount")
    Double totalAmount;

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public List<CartProduct> getItems() {
        return items;
    }

    public void setItems(List<CartProduct> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "CustomerId='" + CustomerId + '\'' +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
