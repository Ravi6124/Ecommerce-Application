package com.example.ecom.homeActivity.apiInterface;

import com.example.ecom.homeActivity.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {

    @GET("productapi/product/category")
    Call<List<Category>> getAllCategories();
}
