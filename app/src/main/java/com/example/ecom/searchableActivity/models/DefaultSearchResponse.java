package com.example.ecom.searchableActivity.models;

import com.google.gson.annotations.SerializedName;

public class DefaultSearchResponse {

    @SerializedName("merchantId")
    private String merchantId;

    @SerializedName("merchantName")
    private String merchantName;

    @SerializedName("productListingRating")
    private double productListingRating;


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getProductListingRating() {
        return productListingRating;
    }

    public void setProductListingRating(double productListingRating) {
        this.productListingRating = productListingRating;
    }

    @Override
    public String toString() {
        return "DefaultSearchResponse{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", productListingRating=" + productListingRating +
                '}';
    }
}
