package com.example.ecom.productListActivity.models;

import com.google.gson.annotations.SerializedName;

public class DefaultProductResponse {

    @SerializedName("productListingRating")
    private Double productListingRating;

    @SerializedName("merchantName")
    private String merchantName;

    @SerializedName("color")
    private String color;

    @SerializedName("theme")
    private String theme;

    @SerializedName("size")
    private String size;

    public Double getProductListingRating() {
        return productListingRating;
    }

    public void setProductListingRating(Double productListingRating) {
        this.productListingRating = productListingRating;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
