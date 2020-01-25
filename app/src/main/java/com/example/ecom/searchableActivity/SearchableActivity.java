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
import com.example.ecom.searchableActivity.adaptor.SearchAdaptor;
import com.example.ecom.searchableActivity.apiInterface.SearchInterface;
import com.example.ecom.searchableActivity.models.ContentItem;
import com.example.ecom.searchableActivity.models.SearchResponse;

import java.util.ArrayList;
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
    public void onClick(ContentItem product) {

        Intent intent = new Intent(SearchableActivity.this, ProductInfoActivity.class);
        //create bundle
        SharedPreferences shared = getSharedPreferences("productInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("name",product.getProductName());
        editor.putString("description",product.getDescription());
        //editor.putString("defaultMerchantId",product.getMerchantId());
        String price = String.valueOf(product.getPrice());
        editor.putString("defaultPrice",price);
//        bundle.putDouble("average",product.getAverageProductRating());
//        bundle.putInt("numberOfRatings",product.getNumberOfRatings());
        editor.putString("imageURL",product.getImageURL());
        //editor.putString("categoryId",product.getCategoryId());
        editor.putString("productId",product.getProductId());
        editor.putString("color",product.getColor());
        editor.putString("size",product.getSize());
        editor.putString("theme",product.getTheme());
        //editor.putInt("totalStock",product.getTotalStock());
        editor.apply();
        startActivity(intent);

    }
}
