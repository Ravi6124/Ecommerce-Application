package com.example.ecommerceapplication.productListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecommerceapplication.ProductInfoActivity;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.RetrofitClass;
import com.example.ecommerceapplication.productListActivity.adaptor.ProductsAdapter;
import com.example.ecommerceapplication.productListActivity.apiInterface.ProductInterface;
import com.example.ecommerceapplication.productListActivity.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductListActivity extends AppCompatActivity implements ProductsAdapter.ProductInterface {

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private List<Product> productList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RetrofitClass retrofitClass = new RetrofitClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Intent intent = getIntent();
        String categoryId = intent.getStringExtra("cId");

        recyclerView = findViewById(R.id.recycler_view_landing);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        retrofit =  retrofitClass.getRetrofit();
        ProductInterface productInterface = retrofit.create(ProductInterface.class);
        Call<List<Product>> call = productInterface.getProductsByCategoryId(categoryId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                mAdapter = new ProductsAdapter(ProductListActivity.this,productList,ProductListActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(Product product) {
        Intent intent = new Intent(ProductListActivity.this, ProductInfoActivity.class);
        //create bundle
        Bundle bundle = new Bundle();
        bundle.putString("name",product.getName());
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
}
