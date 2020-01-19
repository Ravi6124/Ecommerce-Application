package com.example.ecommerceapplication.api;

import com.example.ecommerceapplication.pojos.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {

    @GET("/category")
    Call<List<Category>> getAllCategories();
}
