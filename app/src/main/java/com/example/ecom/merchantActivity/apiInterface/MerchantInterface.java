package com.example.ecom.merchantActivity.apiInterface;

import com.example.ecom.merchantActivity.models.MerchantResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MerchantInterface {

    @GET("/merchant/productListingController/getMerchantByProductId/{productId}")
    Call<List<MerchantResponse>> getMerchantFromProductId(@Path("productId") String productId);
}
