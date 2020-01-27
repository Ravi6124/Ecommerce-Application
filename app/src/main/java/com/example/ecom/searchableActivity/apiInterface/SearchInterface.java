package com.example.ecom.searchableActivity.apiInterface;

import com.example.ecom.productListActivity.models.Product;
import com.example.ecom.searchableActivity.models.DefaultSearchResponse;
import com.example.ecom.searchableActivity.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SearchInterface {
    @GET("search/search/searchFunction/{pageSize}/{pageNumber}/{keyword}")
    Call<SearchResponse> search(@Path("pageSize") int pageSize, @Path("pageNumber") int pageNumber, @Path("keyword") String keyword);

//    @GET("search/search/getSearchProductExtraDetails/{productId}")
//    Call<DefaultSearchResponse> getSearchProductExtraDetails(@Path("productId") String productId);

    @GET("product/product/{id}")
    Call<Product> getProductById(@Path("id") String productId);


}
