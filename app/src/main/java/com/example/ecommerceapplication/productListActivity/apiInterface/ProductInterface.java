package com.example.ecommerceapplication.productListActivity.apiInterface;

//import com.example.ecommerceapplication.productListActivity.models.Product;
import com.example.ecommerceapplication.productListActivity.models.ProductPage;

//import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//import retrofit2.http.Query;

public interface ProductInterface {

    @GET("productapi/product/category/{id}/{page}/{size}")
    Call<ProductPage> getProductsByCategoryId(@Path("id") String cId, @Path("page")int page, @Path("size")int size);
}
