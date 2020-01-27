package com.example.ecom.searchableActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.productInfoActivity.ProductInfoActivity;
import com.example.ecom.productListActivity.apiInterface.ProductInterface;
import com.example.ecom.productListActivity.models.DefaultProductResponse;
import com.example.ecom.productListActivity.models.Product;
import com.example.ecom.productListActivity.models.ProductPage;
import com.example.ecom.searchableActivity.adaptor.SearchAdaptor;
import com.example.ecom.searchableActivity.apiInterface.SearchInterface;
import com.example.ecom.searchableActivity.models.ContentItem;
import com.example.ecom.searchableActivity.models.DefaultSearchResponse;
import com.example.ecom.searchableActivity.models.SearchResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchableActivity extends AppCompatActivity implements SearchAdaptor.SearchInterface {
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter mAdapter;
    String query;
    private int page =0;
    private int size = 2;
    private int position;
    private int totalPages;
    private int totalElements;
    private List<ContentItem> searchResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        recyclerView = findViewById(R.id.recycler_view_landing);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

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

//        Toast.makeText(this, "Search Activity Started", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();

            apiCall(size,page);

            // todo doMySearch(query) via apiCall;
        }
    }

    public void apiCall(int size , int page){

        RetrofitClass retrofitSearch = new RetrofitClass();
        retrofit = retrofitSearch.getRetrofit();
        SearchInterface searchInterface = retrofit.create(SearchInterface.class);
        Call<SearchResponse> call = searchInterface.search(size,page,query);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                searchResult.addAll(response.body().getContent());
                totalPages =  response.body().getTotalPages();
                totalElements = response.body().getTotalElements();
                mAdapter = new SearchAdaptor(SearchableActivity.this,searchResult,SearchableActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("SearchApi","callback failed no response");
            }
        });

    }

    @Override
    public void onClick(final ContentItem product) {

        final Intent intent = new Intent(SearchableActivity.this, ProductInfoActivity.class);
        //create bundle
        SharedPreferences shared = getSharedPreferences("productInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("name",product.getProductName());
        editor.putString("description",product.getDescription());
        //editor.putString("defaultMerchantId",product.getMerchantId());
        String priceString = String.valueOf(product.getPrice());
        Float price = Float.parseFloat(priceString);
        editor.putFloat("defaultPrice",price);
//        bundle.putDouble("average",product.getAverageProductRating());
//        bundle.putInt("numberOfRatings",product.getNumberOfRatings());
        editor.putString("imageURL",product.getImageURL());
        //editor.putString("categoryId",product.getCategoryId());
        editor.putString("productId",product.getProductId());
        editor.commit();

        SharedPreferences sharedPreferences = getSharedPreferences("defaultProductData",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        String color = product.getColor();
        editor1.putString("defaultColor",product.getColor());
        editor1.putString("defaultSize",product.getSize());
        String theme = product.getTheme();
        editor1.putString("defaultTheme",product.getTheme());
        //editor.putInt("totalStock",product.getTotalStock());
        editor1.commit();
        String categoryId = product.getCategoryId();
//
//        RetrofitClass retrofitClass = new RetrofitClass();
//        Retrofit retrofit = retrofitClass.getRetrofit();
//        SearchInterface searchInterface = retrofit.create(SearchInterface.class);
//        Call<DefaultSearchResponse> call = searchInterface.getSearchProductExtraDetails(product.getProductId());
//        call.enqueue(new Callback<DefaultSearchResponse>() {
//            @Override
//            public void onResponse(Call<DefaultSearchResponse> call, Response<DefaultSearchResponse> response) {
//                SharedPreferences shared = getSharedPreferences("defaultProductData",MODE_PRIVATE);
//                SharedPreferences.Editor editor = shared.edit();
//                editor.putString("defaultMerchantId",response.body().getMerchantId());
//                editor.putString("defaultMerchantName",response.body().getMerchantName());
//                editor.putString("defaultRating",String.valueOf(response.body().getProductListingRating()));
//                String productId = shared.getString("productId","");
//                editor.commit();
//
//                RetrofitClass retrofitClass1 = new RetrofitClass();
//                Retrofit retrofit1 = retrofitClass1.getRetrofit();
//                ProductInterface productInterfaceDefault = retrofit1.create(ProductInterface.class);
//                Call<DefaultProductResponse> callDefault = productInterfaceDefault.getDefaultMerchantData(response.body().getMerchantId(),productId);
//                callDefault.enqueue(new Callback<DefaultProductResponse>() {
//                    @Override
//                    public void onResponse(Call<DefaultProductResponse> call, Response<DefaultProductResponse> response) {
//                        SharedPreferences sharedPreferences = getSharedPreferences("productInfo",MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
//                        editor1.putString("defaultColor",response.body().getColor());
//                        editor1.putString("defaultTheme",response.body().getSize());
//                        editor1.putString("defaultSize",response.body().getSize());
//                        editor1.commit();
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void onFailure(Call<DefaultProductResponse> call, Throwable t) {
//
//                    }
//                });
//            }

//            @Override
//            public void onFailure(Call<DefaultSearchResponse> call, Throwable t) {
//
//            }
//        });




//        int page =0;
//        int size =2;
        RetrofitClass retrofitClass = new RetrofitClass();
        Retrofit retrofit =  retrofitClass.getRetrofit();
        SearchInterface searchInterface = retrofit.create(SearchInterface.class);
        Call<Product> call = searchInterface.getProductById(product.getProductId());
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                String defaultMerchantId = response.body().getDefaultMerchantId();
                SharedPreferences sharedPreferences = getSharedPreferences("productInfo",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString("defaultMerchantId",defaultMerchantId);
                editor1.commit();

                RetrofitClass retrofitClass1 = new RetrofitClass();
                Retrofit retrofit1 = retrofitClass1.getRetrofit();
                ProductInterface productInterface = retrofit1.create(ProductInterface.class);
                Call<DefaultProductResponse> call1 = productInterface.getDefaultMerchantData(defaultMerchantId,product.getProductId());
                call1.enqueue(new Callback<DefaultProductResponse>() {
                    @Override
                    public void onResponse(Call<DefaultProductResponse> call, Response<DefaultProductResponse> response) {
                        SharedPreferences sharedPreferences = getSharedPreferences("defaultProductData",MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putString("defaultMerchantName",response.body().getMerchantName());
                        editor1.putString("defaultRating",String.valueOf(response.body().getProductListingRating()));
//                        editor1.putString("defaultColor",response.body().getColor());
//                        editor1.putString("defaultTheme",response.body().getTheme());
//                        String theme = response.body().getTheme();
//                        String color = response.body().getColor();
//                        String size = response.body().getSize();
//                        editor1.putString("defaultSize",response.body().getSize());
                        editor1.commit();

                        Intent intent1 = new Intent(getApplicationContext(),ProductInfoActivity.class);
                        startActivity(intent1);
                    }

                    @Override
                    public void onFailure(Call<DefaultProductResponse> call, Throwable t) {
                        Log.d("getDefaultMerchantData","callback failure");

                    }
                });
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }

}
