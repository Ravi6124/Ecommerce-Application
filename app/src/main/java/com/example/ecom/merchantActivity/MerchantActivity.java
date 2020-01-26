package com.example.ecom.merchantActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.merchantActivity.adaptor.MerchantAdaptor;
import com.example.ecom.merchantActivity.apiInterface.MerchantInterface;
import com.example.ecom.merchantActivity.models.MerchantResponse;
import com.example.ecom.productInfoActivity.ProductInfoActivity;

import java.util.List;

import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MerchantActivity extends AppCompatActivity implements MerchantAdaptor.MerchantAdaptorInterface{

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<MerchantResponse> merchantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");

        recyclerView = findViewById(R.id.recycler_view_merchant);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RetrofitClass retrofitMerchant = new RetrofitClass();
        retrofit = retrofitMerchant.getRetrofit();
        MerchantInterface merchantInterface = retrofit.create(MerchantInterface.class);

        Call<List<MerchantResponse>> call = merchantInterface.getMerchantFromProductId(productId);
        call.enqueue(new Callback<List<MerchantResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<MerchantResponse>> call, @NonNull Response<List<MerchantResponse>> response) {
                merchantList = response.body();
                mAdapter = new MerchantAdaptor(MerchantActivity.this,merchantList,MerchantActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<MerchantResponse>> call, Throwable t) {
                Log.d("MerchantResponse:","failure");
            }
        });

    }

    @Override
    public void onClick(MerchantResponse merchantResponse) {


        Intent intent = new Intent(getApplicationContext(), ProductInfoActivity.class);

        SharedPreferences sharedPref = getSharedPreferences("merchant_product_info", MODE_PRIVATE);
        SharedPreferences.Editor editorMerchant = sharedPref.edit();
        String color = merchantResponse.getColor();
        editorMerchant.putString("merchantName",merchantResponse.getMerchantName());
        editorMerchant.putString("color",merchantResponse.getColor());
        editorMerchant.putString("size",merchantResponse.getSize());
        editorMerchant.putFloat("cost",(float)merchantResponse.getCost());
        editorMerchant.putString("rating",String.valueOf(merchantResponse.getProductRating()));
        editorMerchant.putString("merchantId",merchantResponse.getMerchantId());
        editorMerchant.putString("theme",merchantResponse.getTheme());
        editorMerchant.commit();
        setResult(RESULT_OK,intent);
        //startActivity(intent);
        finish();
    }
}
