package com.example.ecom.loginhistoryActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.loginhistoryActivity.adpater.LoginHistoryAdapter;
import com.example.ecom.loginhistoryActivity.apiInterface.LoginHistoryInterface;
import com.example.ecom.loginhistoryActivity.models.LogHistoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginHistoryActivity extends AppCompatActivity implements LoginHistoryAdapter.LogInterface {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter mAdapter;
    Retrofit retrofit;
    RetrofitClass retrofitClass;
    List<LogHistoryResponse> logHistoryResponseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);

        recyclerView = findViewById(R.id.recycler_view_landing);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId","");

        retrofitClass = new RetrofitClass();
        retrofit = retrofitClass.getRetrofit();
        LoginHistoryInterface loginHistoryInterface = retrofit.create(LoginHistoryInterface.class);
        Call<List<LogHistoryResponse>> call = loginHistoryInterface.getUserLoginHistory(userId);
        call.enqueue(new Callback<List<LogHistoryResponse>>() {
            @Override
            public void onResponse(Call<List<LogHistoryResponse>> call, Response<List<LogHistoryResponse>> response) {
                logHistoryResponseList.addAll(response.body());

                mAdapter = new LoginHistoryAdapter(LoginHistoryActivity.this,logHistoryResponseList,LoginHistoryActivity.this);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<LogHistoryResponse>> call, Throwable t) {
                Toast.makeText(LoginHistoryActivity.this, "Cannot get Login History at the moment", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(LogHistoryResponse logHistoryResponse) {

    }
}
