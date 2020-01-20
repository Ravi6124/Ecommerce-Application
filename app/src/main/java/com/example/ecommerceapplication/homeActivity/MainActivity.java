package com.example.ecommerceapplication.homeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ecommerceapplication.productListActivity.ProductListActivity;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.RetrofitClass;
import com.example.ecommerceapplication.homeActivity.adaptor.LandingAdapter;
import com.example.ecommerceapplication.homeActivity.apiInterface.CategoryInterface;
import com.example.ecommerceapplication.homeActivity.models.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements LandingAdapter.CategoryInterface{

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private List<Category> categoryList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RetrofitClass retrofitClass = new RetrofitClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_landing);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        retrofit =  retrofitClass.getRetrofit();
        CategoryInterface categoryInterface = retrofit.create(CategoryInterface.class);
        Call<List<Category>> call = categoryInterface.getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.d("Response :",response.body().toString());
                categoryList = response.body();
                mAdapter = new LandingAdapter(MainActivity.this,categoryList,MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("getAllCategories"," : callback failure");
            }
        });
    }

    @Override
    public void onClick(Category category) {
        Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
        intent.putExtra("cId",category.getCategoryId());

        //This should start the ProductList activity
        startActivity(intent);

    }
}
