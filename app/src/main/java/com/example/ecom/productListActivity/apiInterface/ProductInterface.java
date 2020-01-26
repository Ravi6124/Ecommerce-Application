package com.example.ecom.productListActivity.apiInterface;

//import com.example.ecommerceapplication.productListActivity.models.Product;
import com.example.ecom.productListActivity.models.DefaultProductResponse;
import com.example.ecom.productListActivity.models.MerchantResponse;
import com.example.ecom.productListActivity.models.ProductPage;

//import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//import retrofit2.http.Query;

public interface ProductInterface {

    @GET("product/product/category/{id}/{page}/{size}")
    Call<ProductPage> getProductsByCategoryId(@Path("id") String cId, @Path("page")int page, @Path("size")int size);


    @GET("merchant/merchant/getMerchantById{merchantId}")
    Call<MerchantResponse> getMerchantById(@Path("merchantId") String merchantId);

    @GET("merchant/productListingController/getDefaultMerchantData/{merchantId}/{productId}")
    Call<DefaultProductResponse> getDefaultMerchantData(@Path("merchantId") String mId, @Path("productId") String pId);

}
