package com.example.ecom.productListActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.cartActivity.CartActivity;
import com.example.ecom.homeActivity.MainActivity;
import com.example.ecom.productInfoActivity.ProductInfoActivity;
import com.example.ecom.productListActivity.adaptor.ProductsAdapter;
import com.example.ecom.productListActivity.apiInterface.ProductInterface;
import com.example.ecom.productListActivity.models.ContentItem;
import com.example.ecom.productListActivity.models.ProductPage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductListActivity extends AppCompatActivity implements ProductsAdapter.ProductInterface {

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private List<ContentItem> productList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RetrofitClass retrofitClass = new RetrofitClass();
    private String categoryId;
    private int page =0;
    private int size =2;
    private int totalPages;
    private GridLayoutManager gridLayoutManager;
    private int position;
    private int totalElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Intent intent = getIntent();
        categoryId = intent.getStringExtra("cId");

        recyclerView = findViewById(R.id.recycler_view_landing);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //endless scroll functionality
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                position =  gridLayoutManager.findLastCompletelyVisibleItemPosition();

                if ((position == size-1)&&(page!=totalPages-1)){
                    // End has been reached
                    page++;
                    apiCall(page,size);
                    Log.i("Yaeye!", "end called");
                }
            }
        });

        apiCall(page,size);

//        retrofit =  retrofitClass.getRetrofit();
//        ProductInterface productInterface = retrofit.create(ProductInterface.class);
//        Call<ProductPage> call = productInterface.getProductsByCategoryId(categoryId,page,size);
//        call.enqueue(new Callback<ProductPage>() {
//            @Override
//            public void onResponse(Call<ProductPage> call, SignUpResponse<ProductPage> response) {
//                productList = response.body().getContent();
//                totalPages =  response.body().getTotalPages();
//                totalElements = response.body().getTotalElements();
//                page++;
//                Toast.makeText(ProductListActivity.this, "size : " + productList.size(), Toast.LENGTH_SHORT).show();
//                mAdapter = new ProductsAdapter(ProductListActivity.this,productList,ProductListActivity.this);
//                recyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ProductPage> call, Throwable t) {
//                Log.d("error: ","callback failed");
//            }
//        });

    }

    public void apiCall(int page, int size){
        retrofit =  retrofitClass.getRetrofit();
        ProductInterface productInterface = retrofit.create(ProductInterface.class);
        Call<ProductPage> call = productInterface.getProductsByCategoryId(categoryId,page,size);
        call.enqueue(new Callback<ProductPage>() {
            @Override
            public void onResponse(Call<ProductPage> call, Response<ProductPage> response) {
                productList.addAll(response.body().getContent());
                totalPages =  response.body().getTotalPages();
                totalElements = response.body().getTotalElements();
                Toast.makeText(ProductListActivity.this, "size : " + productList.size(), Toast.LENGTH_SHORT).show();
                mAdapter = new ProductsAdapter(ProductListActivity.this,productList,ProductListActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ProductPage> call, Throwable t) {
                Log.d("error: ","callback failed");
            }
        });

    }

    @Override
    public void onClick(ContentItem product) {
        Intent intent = new Intent(ProductListActivity.this, ProductInfoActivity.class);
        //create bundle
        SharedPreferences shared = getSharedPreferences("productInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("name",product.getProductName());
        editor.putString("description",product.getDescription());
        editor.putString("defaultMerchantId",product.getDefaultMerchantId());
        String price = String.valueOf(product.getDefaultPrice());
        editor.putString("defaultPrice",price);
//        bundle.putDouble("average",product.getAverageProductRating());
//        bundle.putInt("numberOfRatings",product.getNumberOfRatings());
        editor.putString("imageURL",product.getImageURL());
        editor.putString("categoryId",product.getCategoryId());
        editor.putString("productId",product.getProductId());
        editor.putInt("totalStock",product.getTotalStock());
        editor.apply();
        startActivity(intent);
        //intent.putExtra("cId",product.getImageURL());


    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageButton home = findViewById(R.id.imageButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton cart  = findViewById(R.id.imageButton2);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }
}
