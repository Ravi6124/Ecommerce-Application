package com.example.ecommerceapplication.api;

import com.example.ecommerceapplication.pojos.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductInterface {

    @GET("/product")
    Call<List<Product>> getProductsByCategoryId(@Query("categoryId") String cId);
}
