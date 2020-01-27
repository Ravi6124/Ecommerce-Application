package com.example.ecom.loginhistoryActivity.apiInterface;

import com.example.ecom.loginhistoryActivity.models.LogHistoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginHistoryInterface {

    @GET("login/login/loginhistory/{userId}")
    Call<List<LogHistoryResponse>> getUserLoginHistory(@Path("userId") String userId);
}
