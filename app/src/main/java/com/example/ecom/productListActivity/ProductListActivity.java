package com.example.ecom.productListActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.cartActivity.CartActivity;
import com.example.ecom.homeActivity.MainActivity;
import com.example.ecom.productInfoActivity.ProductInfoActivity;
import com.example.ecom.productListActivity.adaptor.ProductsAdapter;
import com.example.ecom.productListActivity.apiInterface.ProductInterface;
import com.example.ecom.productListActivity.models.ContentItem;
import com.example.ecom.productListActivity.models.DefaultProductResponse;
import com.example.ecom.productListActivity.models.MerchantResponse;
import com.example.ecom.productListActivity.models.ProductPage;
import com.example.ecom.searchableActivity.SearchableActivity;

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

        //Search View

        SearchView searchView = findViewById(R.id.search_bar);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName = new ComponentName(ProductListActivity.this, SearchableActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        recyclerView = findViewById(R.id.recycler_view_landing);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //endless scroll functionality
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                position =  gridLayoutManager.findLastCompletelyVisibleItemPosition();

                if ((position == productList.size()-1)&&(page!=totalPages)){
                    // End has been reached
                    page++;
                    apiCall(page,size);
                    Log.i("Yaeye!", "end called");
                }
            }
        });

        apiCall(page,size);

    }

    public void apiCall(int page, int size){
        retrofit =  retrofitClass.getRetrofit();
        ProductInterface productInterface = retrofit.create(ProductInterface.class);
        Call<ProductPage> call = productInterface.getProductsByCategoryId(categoryId,page,size);
        call.enqueue(new Callback<ProductPage>() {
            @Override
            public void onResponse(Call<ProductPage> call, Response<ProductPage> response) {
                int length = productList.size();
                productList.addAll(response.body().getContent());
                totalPages =  response.body().getTotalPages();
                totalElements = response.body().getTotalElements();
                Toast.makeText(ProductListActivity.this, "size : " + productList.size(), Toast.LENGTH_SHORT).show();
                mAdapter = new ProductsAdapter(ProductListActivity.this,productList,ProductListActivity.this);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyItemRangeInserted(length,response.body().getContent().size());
            }

            @Override
            public void onFailure(Call<ProductPage> call, Throwable t) {
                Log.d("error: ","callback failed");
            }
        });

    }

    @Override
    public void onClick(final ContentItem product) {
        //create bundle
        SharedPreferences shared = getSharedPreferences("productInfo",MODE_PRIVATE);
        final SharedPreferences.Editor editor = shared.edit();
        editor.putString("name",product.getProductName());
        editor.putString("description",product.getDescription());
        editor.putString("defaultMerchantId",product.getDefaultMerchantId());
        editor.commit();
        //editor.putString("defaultMerchantName",product.g)

        final RetrofitClass retrofitClass = new RetrofitClass();
        Retrofit retrofit = retrofitClass.getRetrofit();
        ProductInterface productInterface = retrofit.create(ProductInterface.class);
        Call<MerchantResponse> callMerchant = productInterface.getMerchantById(product.getDefaultMerchantId());
        callMerchant.enqueue(new Callback<MerchantResponse>() {
            @Override
            public void onResponse(Call<MerchantResponse> call, Response<MerchantResponse> response) {
                SharedPreferences shared = getSharedPreferences("productInfo",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = shared.edit();

                Float price = (float)product.getDefaultPrice();
                editor1.putFloat("defaultPrice",price);
                editor1.putString("imageURL",product.getImageURL());
                editor1.putString("categoryId",product.getCategoryId());
                editor1.putString("productId",product.getProductId());
                editor1.putInt("totalStock",product.getTotalStock());
                editor1.commit();

                RetrofitClass retrofitClass1 = new RetrofitClass();
                Retrofit retrofit1 = retrofitClass1.getRetrofit();
                ProductInterface productInterfaceDefault = retrofit1.create(ProductInterface.class);
                Call<DefaultProductResponse> callDefault = productInterfaceDefault.getDefaultMerchantData(product.getDefaultMerchantId(),product.getProductId());
                callDefault.enqueue(new Callback<DefaultProductResponse>() {
                    @Override
                    public void onResponse(Call<DefaultProductResponse> call, Response<DefaultProductResponse> response) {
                        SharedPreferences sharedPreferences = getSharedPreferences("defaultProductData",MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putString("defaultMerchantName",response.body().getMerchantName());
                        editor1.putString("defaultRating",String.valueOf(response.body().getProductListingRating()));
                        editor1.putString("defaultColor",response.body().getColor());
                        editor1.putString("defaultTheme",response.body().getTheme());
                        editor1.putString("defaultSize",response.body().getSize());
                        editor1.commit();

                        SharedPreferences preferences = getSharedPreferences("productInfo",MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = sharedPreferences.edit();
                        editor2.putString("name",product.getProductName());
                        editor2.putString("description",product.getDescription());
                        String price = String.valueOf(product.getDefaultPrice());
                        editor2.putFloat("defaultPrice",Float.parseFloat(price));
                        editor2.putString("imageURL",product.getImageURL());
                        editor2.putString("categoryId",product.getCategoryId());
                        editor2.putString("productId",product.getProductId());
                        editor2.putInt("totalStock",product.getTotalStock());
                        editor2.putString("defaultMerchantId",product.getDefaultMerchantId());
                        editor2.commit();

                        Intent intent = new Intent(ProductListActivity.this, ProductInfoActivity.class);
                       // intent.putExtras(editor2);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<DefaultProductResponse> call, Throwable t) {
                        Log.d("defaultMerchantData","not working");
                        Toast.makeText(ProductListActivity.this, "defaultMerchantDataResponse Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<MerchantResponse> call, Throwable t) {
                        Log.d("merchantResponse","failure");
            }
        });

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
