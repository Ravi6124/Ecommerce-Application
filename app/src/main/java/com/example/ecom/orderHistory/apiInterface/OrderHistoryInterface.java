package com.example.ecom.orderHistory.apiInterface;

import com.example.ecom.orderHistory.models.OrderHistoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderHistoryInterface {

    @GET("cartandorder/order/getByUserId")
    Call<OrderHistoryResponse> getOrderHistory(@Query("userId") String userId);
}
