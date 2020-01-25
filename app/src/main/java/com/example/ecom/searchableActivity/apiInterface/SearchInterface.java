package com.example.ecom.searchableActivity.apiInterface;

import com.example.ecom.searchableActivity.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SearchInterface {
    @GET("search/search/searchFunction/{pageSize}/{pageNumber}/{keyword}")
    Call<SearchResponse> search(@Path("pageSize") int pageSize, @Path("pageNumber") int pageNumber, @Path("keyword") String keyword);
}
