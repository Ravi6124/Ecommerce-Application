package com.example.ecom.orderHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.orderHistory.adapter.OrderHistoryAdapter;
import com.example.ecom.orderHistory.apiInterface.OrderHistoryInterface;
import com.example.ecom.orderHistory.models.OrderHistoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryAdapter.OrderLogInterface {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter mAdapter;
    Retrofit retrofit;
    RetrofitClass retrofitClass;
    OrderHistoryResponse orderHistoryResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        recyclerView = findViewById(R.id.recycler_view_landing);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId","");

        retrofitClass = new RetrofitClass();
        retrofit = retrofitClass.getRetrofit();
        OrderHistoryInterface orderHistoryInterface = retrofit.create(OrderHistoryInterface.class);
        Call<OrderHistoryResponse> call = orderHistoryInterface.getOrderHistory(userId);
        call.enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                orderHistoryResponse = response.body();

                mAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this,orderHistoryResponse,OrderHistoryActivity.this);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {
                Toast.makeText(OrderHistoryActivity.this, "Cannot fetch order history at this time. Try again later", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(OrderHistoryResponse orderHistoryResponse) {
        Toast.makeText(this, "Sorry you can check your mail. This feature will be added later", Toast.LENGTH_SHORT).show();

    }
}
