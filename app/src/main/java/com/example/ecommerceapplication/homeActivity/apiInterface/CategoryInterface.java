package com.example.ecommerceapplication.homeActivity.apiInterface;

import com.example.ecommerceapplication.homeActivity.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {

    @GET("/product/category")
    Call<List<Category>> getAllCategories();
}
