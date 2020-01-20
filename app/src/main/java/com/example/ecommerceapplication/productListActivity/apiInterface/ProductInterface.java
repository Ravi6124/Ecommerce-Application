package com.example.ecommerceapplication.productListActivity.apiInterface;

import com.example.ecommerceapplication.productListActivity.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductInterface {

    @GET("/product")
    Call<List<Product>> getProductsByCategoryId(@Query("categoryId") String cId);
}
