package com.example.ecommerceapplication.productListActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.RetrofitClass;
import com.example.ecommerceapplication.homeActivity.MainActivity;
import com.example.ecommerceapplication.productInfoActivity.ProductInfoActivity;
import com.example.ecommerceapplication.productListActivity.adaptor.ProductsAdapter;
import com.example.ecommerceapplication.productListActivity.apiInterface.ProductInterface;
import com.example.ecommerceapplication.productListActivity.models.ContentItem;
import com.example.ecommerceapplication.productListActivity.models.Product;
import com.example.ecommerceapplication.productListActivity.models.ProductPage;

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
//            public void onResponse(Call<ProductPage> call, Response<ProductPage> response) {
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
        Bundle bundle = new Bundle();
        bundle.putString("name",product.getProductName());
        bundle.putString("description",product.getDescription());
        bundle.putString("defaultMerchantId",product.getDefaultMerchantId());
        bundle.putDouble("defaultPrice",product.getDefaultPrice());
//        bundle.putDouble("average",product.getAverageProductRating());
//        bundle.putInt("numberOfRatings",product.getNumberOfRatings());
        bundle.putString("imageURL",product.getImageURL());
        bundle.putString("categoryId",product.getCategoryId());
        bundle.putString("productId",product.getProductId());
        bundle.putInt("totalStock",product.getTotalStock());
        intent.putExtras(bundle);
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

    }
}
