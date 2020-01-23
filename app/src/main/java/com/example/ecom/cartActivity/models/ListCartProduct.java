package com.example.ecom.cartActivity.models;

import com.google.gson.annotations.SerializedName;

public class ListCartProduct {

    @SerializedName("productId")
    private String productId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("imageURL")
    private String imageURL;
    @SerializedName("merchantId")
    private String merchantId;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getMerchanrId() {
        return merchantId;
    }

    public void setMerchanrId(String merchanrId) {
        this.merchantId = merchanrId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ListCartProduct{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", merchanrId='" + merchantId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
